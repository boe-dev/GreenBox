package de.boe_dev.greenbox;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by benny on 20.01.18.
 */

public class GreenBox {


    public static void Log(Context context, String message) {

        try {
            FileOutputStream outputStream = context.openFileOutput("log", Context.MODE_PRIVATE);
            String logMessage = message + "\n";
            outputStream.write(logMessage.getBytes());
            outputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
