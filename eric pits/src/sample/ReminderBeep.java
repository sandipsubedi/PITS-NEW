package sample;

/**
 * Created by sandi on 4/5/2016.
 */



import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Simple demo that uses java.util.Timer to schedule a task to execute once 5
 * seconds have passed.
 */

public class ReminderBeep {

    Toolkit toolkit;
    Timer timer;

    public ReminderBeep(int seconds) {
        toolkit = Toolkit.getDefaultToolkit();
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds * 1000);
    }

    class RemindTask extends TimerTask {
        public void run() {
            // doThis();
            System.out.println("Time's up!");
            toolkit.beep();
            newMethod();
            //timer.cancel(); //Not necessary because we call System.exit
            // System.exit(0); //Stops the AWT thread (and everything else)
          //   statusBar.setText("This is at the end");
        }
    }

    public String  newMethod(){
        System.out.println("we were here");

        return "the class was called";
    }
}