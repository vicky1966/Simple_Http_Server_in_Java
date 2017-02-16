/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpserver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;

/**
 *
 * @author Vicky
 */
public class HttpsResponse 
{
    HttpRequest req;
    //this is the final response which is generated
    String response;
    //root path of the server
    String root="S:/webserser/root";
    String contentType="";
    byte[] arr;
    HttpsResponse(HttpRequest request) throws FileNotFoundException, IOException 
    {
        req = request;
        
        try
        {
            
        //now we have to open the file in the request
            if(req.fileName.indexOf(".jpg")>=0 && req.fileName.indexOf(".jpg")<req.fileName.length() )
                    contentType+="image/jpg";
            else
                    contentType+="text/html";
            String path = root +req.fileName;
            System.out.print(path+ "i am in response");
            File f = new File(path);
        
        //to read the file
        FileInputStream fis = new FileInputStream(f);
        // BufferedInputStream bis = new BufferedInputStream(fis);
           // "HTTP/1.0 200 Document follows\n" +
            //"MIME-Version: 1.0\n" +
            //"Server: CERN/3.0\n" +
            //"Date: Sunday, 10-Nov-96 06:54:46 GMT\n" +
            //"Content-Type: text/html\n" +
           // "Content-Length: 4531\n" +
            //"Last-Modified: Wednesday, 16-Oct-96 10:14:01 GMT";
        
    response = "HTTP/1.1 200 \r\n"; //ver of http and 200 means everythink is ok
    response +="Server: Our Java Server/1.0 \r\n"; //identtity orf server
    response +="Content-Type:" +contentType+" \r\n";
    response +="Connection: close \r\n";
    response +="Content-Lenth" + f.length() +"\r\n";
    response +="\r\n";//after blank line we have to append file data
     
        fillResponse(getBytes(f));
        }
        catch(FileNotFoundException e)
        {
         response =response.replace("200", "404");
        }
        catch(Exception ex)
        {
            response =response.replace("200", "500");
        }
    }
    
    private byte[] getBytes(File file) throws IOException
    {
        int length =(int) file.length();
        byte[] data = new byte[length];
        InputStream in = new FileInputStream(file);
        int offset = 0;
        while(offset<length)
        {
            int count = in.read(data,offset,(length-offset));
            offset += count;
        }
        return data;
    }
    
    private void fillResponse(byte[] resp)
    {
        arr =resp;
    }
    public void write(OutputStream os) throws IOException
    {
        DataOutputStream dout = new DataOutputStream(os);
        dout.writeBytes(response);
        if(arr!=null)
        {
            dout.write(arr);
        }
        dout.writeBytes("\r\n");
        dout.flush();        
    }
    
}
