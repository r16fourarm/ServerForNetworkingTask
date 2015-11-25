/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverjrk;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author R16
 */
public class ServerStart implements Runnable {

    Serverjrk svr ;//= new Serverjrk();
    guiserver gui ;//= new guiserver();

    public ServerStart(guiserver gui,Serverjrk svr) {
        this.gui = gui;
        this.svr=svr;
    }

    @Override
    public void run() {
        ArrayList clientOutputStreams = new ArrayList();
        ArrayList users = new ArrayList();
        svr.setClientOutputStreams(clientOutputStreams);
        svr.setUsers(users);

        try {
            ServerSocket serverSock = new ServerSocket(2225);
            System.out.println(serverSock.toString());
            while (true) {
                Socket clientSock = serverSock.accept();
                System.out.println(";ewt");
                PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
                System.out.println("awe");
                svr.getClientOutputStreams().add(writer);
                 if(clientOutputStreams!=null){System.out.println("nonull");
        }else{System.out.println("null");}
                Thread listener = new Thread(new clienthandler(clientSock, writer,svr,gui));
                listener.start();
                 if(clientOutputStreams!=null){System.out.println("nonull");
        }else{System.out.println("null");}
                gui.getTa_chat().append("Got a connection. \n");
            }
        } catch (Exception ex) {
            gui.getTa_chat().append("Error making a connection. \n");
            ex.printStackTrace();
        }
    }
}
