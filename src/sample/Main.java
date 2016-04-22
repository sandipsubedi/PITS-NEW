package sample;


import com.kinvey.nativejava.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/*
App Id : kid_ZyBaA-MgAe
App Secret: 4e43176fdfe14d44878492851d93385f
Master Secret: 81181f297d044117a537c53378eae68d
 */

public class Main extends Application {

    public static final String appKey = "kid_ZyBaA-MgAe";
    public static final String masterSecret = "81181f297d044117a537c53378eae68d";
    public static Client mKinveyClient;

    public static final String nameOfCollection = "eventsCollection";

    public static Stage pStage;

    public static Scene ourScene;

    // app secret : 4e43176fdfe14d44878492851d93385f



    @Override
    public void start(Stage primaryStage) throws Exception{
        pStage =  primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("Pagliai's Inventory Tracking System");

        ourScene = new Scene(root, 1000,700);

        primaryStage.setScene(ourScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }



    public static void main(String[] args) {

        /*
        * Do not delete this part ...
        * It has key and app id for our kinvey
        * */

        mKinveyClient = new Client.Builder("kid_ZyBaA-MgAe", "4e43176fdfe14d44878492851d93385f").build();
        try{
            mKinveyClient.user().loginBlocking("kid_ZyBaA-MgAe", "81181f297d044117a537c53378eae68d").execute();
            System.out.println("Client logged in -> " + mKinveyClient.user().isUserLoggedIn());
        }catch (IOException e){
            System.out.println("Couldn't login -> " + e);
            e.printStackTrace();
        }


        /*
        * This query is for testing the table structure of Kinvey database
        * */
        //The ItemEntity class is defined above


        try{

/*
            // second row
            ItemEntity pepperoni = new ItemEntity();
            pepperoni.setId("pepperoni");
            pepperoni.put("unit","Package");
            pepperoni.put("walmartHyvee","13.44");
            pepperoni.put("usFoods","13.44");
            pepperoni.put("roma","13.44");
            pepperoni.put("count","13.44");

            // second row
            ItemEntity sauce = new ItemEntity();
            sauce.setId("sauce");
            sauce.put("unit","can");
            sauce.put("walmartHyvee","0.0");
            sauce.put("usFoods","13.44");
            sauce.put("roma","13.44");
            sauce.put("count","13.44");

            // second row
            ItemEntity blackOlives = new ItemEntity();
            blackOlives.setId("blackOlives");
            blackOlives.put("unit","package");
            blackOlives.put("walmartHyvee","12.34");
            blackOlives.put("usFoods","13.44");
            blackOlives.put("roma","13.44");
            blackOlives.put("count","13.44");

            // second row
            ItemEntity beef = new ItemEntity();
            beef.setId("beef");
            beef.put("unit","we are testing");
            beef.put("walmartHyvee","12.24");
            beef.put("usFoods","13.44");
            beef.put("roma","13.44");
            beef.put("count","13.44");


            // this will create a collection k=in kinvey
            AppData<ItemEntity> myevents = mKinveyClient.appData("eventsCollection",ItemEntity.class);
            try{

                ItemEntity result2 = myevents.saveBlocking(pepperoni).execute();
                ItemEntity result3 = myevents.saveBlocking(sauce).execute();
                ItemEntity result4 = myevents.saveBlocking(blackOlives).execute();
                ItemEntity result5 = myevents.saveBlocking(beef).execute();

            }catch (IOException e){
                System.out.println("Couldn't save! -> " + e);
            }*/


            /**
             * this also work:
             * basically its the same thing :::
             * */
            //another test from the kivey website:
            //The ItemEntity class is defined above
//            ItemEntity event = new ItemEntity();
//            event.setName("Launch Party");
//            //  event.setAddress("Kinvey HQ");
//            AppData<ItemEntity> myevents0 = mKinveyClient.appData("eventsCollection",ItemEntity.class);
//            try{
//                ItemEntity result = myevents0.saveBlocking(event).execute();
//            }catch (IOException e){
//                System.out.println("Couldn't save! -> " + e);
//            }


        /*
        * fetching data from kinvey
        * */
            String[] items = {"pepperoni","sauce","blackOlives","beef"};

            //The ItemEntity class is defined above
            //The ItemEntity class is defined above
            ItemEntity event000 = new ItemEntity();
//            AppData<ItemEntity> myEvents = mKinveyClient.appData("eventsCollection", ItemEntity.class);
//            try{
//
//                   for(String s: items){
//                       ItemEntity result = myEvents.getEntityBlocking(s).execute();
//                       System.out.println();
//                       System.out.println("We are printing the result here:");
//                       System.out.println("Printing the ID: " + result.getId());
//                       //  System.out.println(result.values().toArray().toString());
//                       // System.out.println(result);
//                       //  System.out.println(tempResult);
//
//                       System.out.println("Unit");
//                       System.out.println(result.getUnit());
//                       System.out.println();
//                       System.out.println("walmartHyvee");
//                       System.out.println(result.getWalmartHyvee());
//                       // more
//                       System.out.println("usfoods");
//                       System.out.println(result.getUsFoods());
//                       //more
//                       System.out.println("roma");
//                       System.out.println(result.getRoma());
//                       //more
//                       System.out.println("count");
//                       System.out.println(result.getCount());
//
//                   }
//
//
//            }catch (IOException e){
//                System.out.println("Couldn't get! -> " + e);
//            }


        }

        catch(Exception e){
            System.out.println("Some problem with Kinvey");
        }

        launch(args);

    }




}





