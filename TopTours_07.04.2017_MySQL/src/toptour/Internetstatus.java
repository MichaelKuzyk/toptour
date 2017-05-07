package toptour;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import static javafx.scene.text.TextAlignment.CENTER;
import javax.swing.JOptionPane;

public class Internetstatus extends TimerTask {
 String status="status inet";
  public static void run1(){
       Timer timer = new Timer();
       timer.schedule(new Internetstatus(), 0,1000);
         }
   
      public void  run() {
 try {
     
  final URL url = new URL("https://google.com.ua");
  final HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
  urlConn.setConnectTimeout(2000); // mTimeout is in seconds
  final long startTime = System.currentTimeMillis();
  urlConn.connect();
  final long endTime = System.currentTimeMillis();
  if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
    status="ONLINE";
          
   /*System.out.println("Time (ms) : " + (endTime - startTime));
   System.out.println("Ping  was success");*/
     }
  else{ JOptionPane.showMessageDialog (null,"YOu are currently OFFLINE, check your internet connection", "ERROR", JOptionPane.ERROR_MESSAGE );
      status="2OFFline";
     //System.out.println("2OFFLINE ");
  }
 } catch (final MalformedURLException e1) {
         
    status="1OFFline";
    //System.out.println("1OFFLINE");
  //e1.printStackTrace();
 } catch (final IOException e) {
     JOptionPane.showMessageDialog (null,"YOu are currently OFFLINE, check your internet connection", "ERROR", JOptionPane.ERROR_MESSAGE );
      
     
     status="2OFFline";
   //  System.out.println("2OFFLINE ");
  //e.printStackTrace();
  
 }
 
}
  public String status(){
  run();
  return status;
  }    
      
             
     
   
  

}