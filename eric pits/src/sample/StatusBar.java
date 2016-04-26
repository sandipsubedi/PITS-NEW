package sample;

import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by sandi on 4/6/2016.
 */




public class StatusBar implements Runnable,Initializable {


    @FXML
    Label statusBar;

    @Override
    public void run() {


        System.out.println("before sleep");

        try {
            Thread.sleep(5000);
            System.out.println("After sleep");


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public String statusReturn(){
        return "returned from the status bar";
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
