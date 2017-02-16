package httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionHandler extends Thread
{
    Socket s;
    PrintWriter pw;
    BufferedReader br;
    public ConnectionHandler(Socket s) throws IOException
    {
        this.s = s;
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        pw = new PrintWriter(s.getOutputStream());
    }

    @Override
    public void run() 
    {
        
        try {
            String reqS = "";
            while(br.ready() || reqS.length() == 0 )
            {
                reqS+=(char)br.read();
            }
            System.out.println(reqS);
            
        HttpRequest req =new HttpRequest(reqS);
        HttpsResponse res = new HttpsResponse(req);
        
        //write the final output to pw
        
        pw.write(res.response.toCharArray());
        pw.close();
        br.close();
        s.close();
        
        } catch (IOException ex) {
            Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}