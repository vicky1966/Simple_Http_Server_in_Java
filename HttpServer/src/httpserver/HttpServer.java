package httpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class HttpServer 
{
    ServerSocket serverSocket;
    public static void main(String[] args) throws IOException 
    {
        new HttpServer().runServer();
    }
    
    public void runServer() throws IOException
    {
        System.out.println("SErver is startef");
        serverSocket = new ServerSocket(6543);
        acceptRequests();
    }
    
    private void acceptRequests() throws IOException
    {
        while(true)
        {
            //connect to client i.e browser 
            Socket s = serverSocket.accept();
            ConnectionHandler ch = new ConnectionHandler(s);
            // ch is the thread, so we have to start our thread using run
            ch.start();
        }
    }
    
}
