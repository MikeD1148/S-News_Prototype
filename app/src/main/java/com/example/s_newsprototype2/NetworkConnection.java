package com.example.s_newsprototype2;

import android.content.Context;
import android.util.Log;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.PrintWriter;
import java.io.StringReader;
import java.net.Socket;
import java.util.List;

//Creates a new Thread for every connection to the Server
public class NetworkConnection extends Thread{
    private Context connectionContext;
    private String serverAddress;
    private int serverPort;

    //Network Connection Constructor
    public NetworkConnection(String serverAddress, int serverPort, Context context) {
        connectionContext = context;
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    @Override
    public void run() {
        try {
            //Create a connection to the Server
            Socket socket = new Socket(serverAddress, serverPort);

            if (socket != null && socket.isConnected()) {
                Log.i("NetworkConnection", "Connection to server is successful");
            } else {
                Log.e("NetworkConnection", "Connection to server failed");
            }

            //To read in data
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //Send data request message
            String message = "Send";
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println(message);

            //Receive data from Server
            StringBuilder articleList = new StringBuilder();
            char[] buffer = new char[4096];

            //Add received data to Article List
            while (true) {
                int charInput = bufferedReader.read(buffer, 0, buffer.length);
                if (charInput == -1) {
                    //End of data
                    break;
                }
                articleList.append(buffer, 0, charInput);
            }
            //Close connection to Server
            socket.close();

            try {
                //Inputting entire String of Articles data into a list of rows
                CSVReader csvReader = new CSVReader(new StringReader(articleList.toString()));
                List<String[]> rows = csvReader.readAll();

                //Loading Local file of last List of Articles and deleting it
                File file = new File(connectionContext.getFilesDir(), "articles.csv");
                file.delete();

                //To write to local file
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter BufferedWriter = new BufferedWriter(fileWriter);

                //Write data to file
                for (String[] row : rows) {
                    //Write line to file
                    for (int i = 0; i < row.length; i++) {
                        BufferedWriter.append(row[i]);
                        //Creating Columns
                        if (i < row.length - 1) {
                            BufferedWriter.append(',');
                        }
                    }
                    //Creating Rows
                    BufferedWriter.append('\n');
                }
                //Finished writing
                BufferedWriter.close();

            }catch (CsvException e) {
                throw new RuntimeException(e);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

}
