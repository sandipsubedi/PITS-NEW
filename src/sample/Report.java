package sample;

import com.kinvey.java.model.KinveyDeleteResponse;
import com.kinvey.nativejava.AppData;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by sandi on 3/31/2016.
 *
 */


public class Report {


     public String reportGenerationStatus = "";

    public ObservableList<ItemEntity> reportList ;

    public Report(ObservableList<ItemEntity> list)

    {
        this.reportList = list;
    }

    AppData<ItemEntity> myEvents;

    /*
    This function will ask the user for the  location and will call generateFile method
     */


    public String execute() throws Exception{
        // Create the custom dialog.
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Save File");

        dialog.setHeaderText("THIS WILL RESET THE COUNT TO 0 \n Enter the Name for the file: ");


        // Set the button types.
        ButtonType browseLocationBTN = new ButtonType("Browse Location", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(browseLocationBTN, ButtonType.CANCEL);

        // Create the userLocation and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField userLocation = new TextField();
        userLocation.setPromptText("Monthly Report");

        grid.add(new Label("File Name: "), 0, 0);
        grid.add(userLocation, 1, 0);


        // Enable/Disable login button depending on whether a userLocation was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(browseLocationBTN);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        userLocation.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the userLocation field by default.
        Platform.runLater(() -> userLocation.requestFocus());



        /*
        This will run when browse locaiton button is clicked
         */
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == browseLocationBTN) {

                DirectoryChooser dc = new DirectoryChooser();
                File fileLocation = dc.showDialog(null);
                if (fileLocation != null) {
                    fileLocation = new File(fileLocation.getAbsolutePath());
                }

                /*
                Generate File is called here
                 */
                 generateFile(userLocation,fileLocation,reportList);

                return (userLocation.getText());
            }
            return null;
        });
        Optional<String> result = dialog.showAndWait();


        return reportGenerationStatus;

    }

    /*
    This method will just generate the file in the selected location
     */

    public  void generateFile(TextField username, File fileLocation, ObservableList<ItemEntity> reportList){

        String fileName = username.getText();

        System.out.println("");
        System.out.println("FileName:" + fileName + "File Location: " + fileLocation) ;

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Monthly Report");

        Map<Integer, Object[]> data = new TreeMap<>();


        Double total = 0.0;

        for(ItemEntity item:reportList){


            if(item.getSelectedPrice()==null){

                item.setSelectedPrice("Other");

            }


            if(item.getSelectedPrice().equals("Other")){
//                System.out.println(item.getId());
//                System.out.println(Double.valueOf(item.getCount()));
//                System.out.println(Double.valueOf(item.getWalmartHyvee()));
//                System.out.println();
                total = total + (Double.valueOf(item.getCount())*Double.valueOf(item.getWalmartHyvee()));
            }

            if(item.getSelectedPrice().equals("USFoods")){

                total = total + (Double.valueOf(item.getCount())*Double.valueOf(item.getUsFoods()));
            }

            if(item.getSelectedPrice().equals("Roma")){

                total = total + (Double.valueOf(item.getCount())*Double.valueOf(item.getRoma()));
            }

            System.out.println("_____we are here ____");
            System.out.println(item.getCount());

            ItemEntity newItem = new ItemEntity(item.getId(),item.getUnit(),item.getWalmartHyvee(),item.getUsFoods(),item.getRoma(),"0",item.getSelectedPrice());
            myEvents = Main.mKinveyClient.appData(Main.nameOfCollection, ItemEntity.class);
            try{
                ItemEntity result = myEvents.saveBlocking(newItem).execute();


            }catch (IOException e){
                System.out.println("Couldn't save new item! -> " + e);
            }

        }


        data.put(1, new Object[] {"Monthly Report for Pagliai's Pizza"});

        data.put(2, new Object[] {"Total : ", String.format("$%.2f",total)});

        data.put(3, new Object[] {"Name", "Unit", "Other","US Foods","Roma ","Count"});
        int count = 3;
        for(ItemEntity item: reportList)
        {
            count++;
            data.put(count, new Object[] {item.getId() , item.getUnit() , item.getWalmartHyvee(), item.getUsFoods(),item.getRoma() ,item.getCount()});
        }

        System.out.println("");
        System.out.println("add everything to the list");


        Set<Integer> keyset = data.keySet();
        int rownum = 0;
        for (Integer key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if(obj instanceof Date)
                    cell.setCellValue((Date)obj);
                else if(obj instanceof Boolean)
                    cell.setCellValue((Boolean)obj);
                else if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Double)
                    cell.setCellValue((Double)obj);
            }
        }

        try {

            FileOutputStream out = new FileOutputStream(new File( fileLocation+ "\\" + fileName + ".xls"));
            System.out.println("The total was === "+  fileLocation+ "\\" + fileName + ".xls");
            workbook.write(out);
            out.close();
            System.out.println("Excel written successfully..");
            reportGenerationStatus = "Report Generated !!!";



            /*
            Dialog for total and report generated:

             */




            /*
            For the status Bar
             */
          //  MainController test = new MainController();
           // test.statusBar("Report Generated;");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Report Generated!!!");
        // alert.setHeaderText("The total is: " + String.format("$%.2f",total));
        alert.setHeaderText(null);

        alert.setContentText("The total is: " + String.format("$%.2f",total));
        ButtonType cancelButtonDeleteDialog = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);

        //  ButtonType buttonTypeCancel = new ButtonType("Delete");

        //  alert.getButtonTypes().setAll(buttonTypeCancel,cancelButtonDeleteDialog);
        alert.getButtonTypes().setAll(cancelButtonDeleteDialog);

        Optional<ButtonType> result1 = alert.showAndWait();




    }

}
