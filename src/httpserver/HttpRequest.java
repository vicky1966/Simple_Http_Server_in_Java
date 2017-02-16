
package httpserver;

public class HttpRequest 
{
    String fileName;
    public HttpRequest(String s)
    {   
        //getting file name
        String lines[]=s.split("\n");
        //this is basically spli the first line using space" " and the selects the 2nd item
        //which is our file names
        fileName = lines[0].split(" ")[1];
        System.out.println(fileName);
    }
}
