package DevDom.Utils;

import java.io.IOException;

import javax.microedition.io.HttpConnection;

import net.rim.device.api.io.IOUtilities;
import net.rim.device.api.ui.component.Dialog;

import DevDom.Utils.HttpConnectionFactory;
import DevDom.Utils.NoMoreTransportsException;

public class DB {

	public static String getResult()
    {
        String result = "";
       
        HttpConnectionFactory factory = new HttpConnectionFactory("http://js.developers.do/js/devdom.js", HttpConnectionFactory.TRANSPORT_BIS | HttpConnectionFactory.TRANSPORT_WIFI | HttpConnectionFactory.TRANSPORT_DIRECT_TCP ); 
        while( true ) {
            try {
               HttpConnection connection = factory.getNextConnection();
               try {
                  connection.setRequestMethod( "GET" );
                  //connection.setRequestProperty( "Content-type", "application/json" );
                 
                    if (connection.getResponseCode() == HttpConnection.HTTP_OK)
                    {

                    	// IOUtilities es de la API de BB
                    	byte[] streamToBytes = IOUtilities.streamToBytes(connection.openInputStream());

                    	result = new String(streamToBytes,0, streamToBytes.length, "UTF-8");

                    }
               }
               catch(IOException ex ) {
            	   Dialog.alert("Unable to perform request" );
            	   //Log the error or store it for displaying to the end user if no transports succeed
               }
            }
            catch( NoMoreTransportsException e ) {
            	 //There are no more transports to attempt
            	break;
            }
         }
       
        return result;
    }
	
	
}
