package toptour;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class pingUrl{
public static String pingUrl() {
 try {
  final URL url = new URL("https://google.com.ua");
  final HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
  urlConn.setConnectTimeout(1000); // mTimeout is in seconds
  final long startTime = System.currentTimeMillis();
  urlConn.connect();
  final long endTime = System.currentTimeMillis();
  if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
   
       /*System.out.println("Time (ms) : " + (endTime - startTime));
   System.out.println("Ping  was success");*/
   View.status="Online";
     }
 } catch (final MalformedURLException e1) {
    View.status="OFFline";
    // System.out.println("OFFFFFFFFLINE");
  //e1.printStackTrace();
 } catch (final IOException e) {
     View.status="OFFline";
     //System.out.println("OFFFFFFFLINE ");
  //e.printStackTrace();
 }
    
    return View.status;
}

}