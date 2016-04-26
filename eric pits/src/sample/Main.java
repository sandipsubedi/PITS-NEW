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

        launch(args);

    }
}