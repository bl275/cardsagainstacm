package com.example.cardsagainstacm;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Olen on 2/22/14.
 */
public class Client extends Activity implements Runnable{
    private Socket clientSocket;
    private static final int portNumber = 6969;
    private PrintWriter clientOutput;
    private BufferedReader input;
    private String hostName;

    public Client(String hostName){
        this.hostName = hostName;
    }

    public String getInput(){
        try {
            if(input != null){
            if(input.ready())
                return input.readLine();
            else
                return "NO NEW INPUT";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    public void sendOutput(String output){
        clientOutput.println(output);
    }

    @Override
    public void run() {
        try {
            InetAddress serverAddr = InetAddress.getByName(hostName);
            clientSocket = new Socket(serverAddr, portNumber);
            clientOutput = new PrintWriter(clientSocket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
