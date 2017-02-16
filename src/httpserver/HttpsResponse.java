/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
    HttpsResponse(HttpRequest request) throws FileNotFoundException, IOException 
    {
        req = request;
        
        try
        {
        //now we have to open the file in the request
        File f = new File(root + req.fileName);
        
        //to read the file
        FileInputStream fis = new FileInputStream(f);
           // "HTTP/1.0 200 Document follows\n" +
            //"MIME-Version: 1.0\n" +
            //"Server: CERN/3.0\n" +
            //"Date: Sunday, 10-Nov-96 06:54:46 GMT\n" +
            //"Content-Type: text/html\n" +
           // "Content-Length: 4531\n" +
            //"Last-Modified: Wednesday, 16-Oct-96 10:14:01 GMT";
        
    response = "HTTP/1.1 200 \r\n"; //ver of http and 200 means everythink is ok
    response +="Server: Our Java Server/1.0 \r\n"; //identtity orf server
    response +="Content-Type: text/html \r\n";
    response +="Connection: close \r\n";
    response +="Content-Lenth" + f.length() +"\r\n";
    response +="\r\n";//after blank line we have to append file data
    
        int s ;
        while((s = fis.read())!=-1)
        {
            response +=(char)s;
        }
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
    
}
