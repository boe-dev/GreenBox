package de.boe_dev.greenbox;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by benny on 20.01.18.
 */

public class GreenBox {

    private static final String LOG_FILE_NAME = "log";

    public static void Log(Context context, String message) {

        try {

            String currentLog = readLog(context);
            String logMessage = createLogString(currentLog, message);
            writeIntoLog(context, logMessage);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String readLog(Context context) throws IOException {

        FileInputStream inputStream = context.openFileInput(LOG_FILE_NAME);
        String oldLog = convertStreamToString(inputStream);
        inputStream.close();
        return oldLog;
    }

    private static String convertStreamToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        Boolean firstLine = true;
        while ((line = reader.readLine()) != null) {
            if(firstLine){
                stringBuilder.append(line);
                firstLine = false;
            } else {
                stringBuilder.append("\n").append(line);
            }
        }
        reader.close();
        return stringBuilder.toString();
    }

    private static void writeIntoLog(Context context, String message) throws IOException {
        FileOutputStream outputStream = context.openFileOutput(LOG_FILE_NAME, Context.MODE_PRIVATE);
        outputStream.write(message.getBytes());
        outputStream.close();
    }

    private static String createLogString(String currentLog, String message) {

        String logMessage = getTimeStamp() + message;
        return currentLog + "\n" + logMessage;
    }

    private static String getTimeStamp() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return "[" + mdformat.format(calendar.getTime()) + "] ";
    }



}
