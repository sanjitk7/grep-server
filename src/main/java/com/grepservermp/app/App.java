package com.grepservermp.app;

import java.net.*;
import java.io.*;

public class App {
    public static void main(String[] args) {
        try {

            ServerSocket ss = new ServerSocket(3000);
            boolean flag = true;

            while (flag) {
                try {

                    Socket s = ss.accept();
                    DataInputStream dis = new DataInputStream(s.getInputStream());
                    String grepCommand = (String) dis.readUTF();
                    
                    System.out.println("Received grepCommand on server side: " + grepCommand);

                    String grepCommandResult = "This is grep command result for the input - "+grepCommand;
                    DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                    dos.writeUTF(grepCommandResult);
                    dos.flush();

                    if (grepCommand.equals("exit")){
                        flag = false;
                        dos.close();
                        ss.close();
                    }
                    // Thread.sleep(10000);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
