/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverjrk;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author R16
 */
public class Serverjrk {

    /**
     * @param args the command line arguments
     */
    ArrayList clientOutputStreams;
    ArrayList<String> users;
     public String userAdd (String data) 
    {
        String message, add = ": :Connect", done = "Server: :Done", name = data,sret;
       sret="Before " + name + " added. \n";
        users.add(name);
        sret="After " + name + " added. \n";
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);
        //clientOutputStreams = new ArrayList();
        System.out.println("lewt");
        for (String token:tempList) 
        {
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
        return sret;
    }
    
    public void userRemove (String data) 
    {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        users.remove(name);
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList) 
        {
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }
    
    public String tellEveryone(String message) 
    {
        if(clientOutputStreams!=null){System.out.println("nonullx");
        }else{System.out.println("nullx");}
	Iterator it = clientOutputStreams.iterator();
        String cht ="";
        while (it.hasNext()) 
        {
            try 
            {
                PrintWriter writer = (PrintWriter) it.next();
		writer.println(message);
		cht="Sending: " + message + "\n";
                writer.flush();
                //ta_chat.setCaretPosition(ta_chat.getDocument().getLength());

            } 
            catch (Exception ex) 
            {
		cht="Error telling everyone. \n";
            }
        }
        return cht;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }

    public void setClientOutputStreams(ArrayList clientOutputStreams) {
        this.clientOutputStreams = clientOutputStreams;
    }

    public ArrayList getClientOutputStreams() {
        return clientOutputStreams;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

}
