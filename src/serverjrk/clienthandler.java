/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverjrk;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author R16
 */
public class clienthandler implements Runnable{
        guiserver gui;//=iserver();
        Serverjrk svr; //= Serverjrk();
         BufferedReader reader;
       Socket sock;
       PrintWriter client;

       public clienthandler(Socket clientSocket, PrintWriter user, Serverjrk svr,guiserver gui)
       {
           this.svr=svr;
           this.gui=gui;
            client = user;
            try 
            {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            }
            catch (Exception ex) 
            {
                gui.getTa_chat().append("Unexpected error... \n");
            }

       }

       @Override
       public void run() 
       {
            String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat" ;
            String[] data;

            try 
            {
                while ((message = reader.readLine()) != null) 
                {
                    gui.getTa_chat().append("Received: " + message + "\n");
                    data = message.split(":");
                    
                    for (String token:data) 
                    {
                        gui.getTa_chat().append(token + "\n");
                    }

                    if (data[2].equals(connect)) 
                    {
                        svr.tellEveryone((data[0] + ":" + data[1] + ":" + chat));
                        svr.userAdd(data[0]);
                    } 
                    else if (data[2].equals(disconnect)) 
                    {
                       svr.tellEveryone((data[0] + ":has disconnected." + ":" + chat));
                        svr.userRemove(data[0]);
                    } 
                    else if (data[2].equals(chat)) 
                    {
                        svr.tellEveryone(message);
                    } 
                    else 
                    {
                        gui.getTa_chat().append("No Conditions were met. \n");
                    }
                } 
             } 
             catch (Exception ex) 
             {
                //gui.getTa_chat().append("Lost a connection. \n");
                ex.printStackTrace();
                svr.getClientOutputStreams().remove(client);
             } 
	} 
}
