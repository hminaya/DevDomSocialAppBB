package DevDom.Utils;

import javax.microedition.io.HttpConnection;
import java.io.IOException;
import java.io.InputStream;
import java.lang.String;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.component.Dialog;

public final class BitmapFromURL {
		/**
	 * Fetches the content on the speicifed url.
	 * 
	 * @param url
	 *            The url of the content to fetch
	 */

	public static Bitmap getImageFromUrl(String url) {
		Bitmap bitmap = null;

		try {
			String bitmapData = getDataFromUrl(url);
			bitmap = Bitmap.createBitmapFromBytes(bitmapData.getBytes(), 0,
					bitmapData.length(), 1);
			// Image.createImage(imageData.getBytes(), 0,imageData.length());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return bitmap;
	}

	/**
	 * Fetches the content on the speicifed url.
	 * 
	 * @param url
	 *            The url of the content to fetch
	 */
	private static String getDataFromUrl(String url) {
		StringBuffer b = new StringBuffer();
		InputStream is = null;
		long len = 0;
		int ch = 0;

		HttpConnectionFactory factory = new HttpConnectionFactory(url, HttpConnectionFactory.TRANSPORT_BIS | HttpConnectionFactory.TRANSPORT_WIFI | HttpConnectionFactory.TRANSPORT_DIRECT_TCP ); 
        while( true ) {
            try {
               HttpConnection connection = factory.getNextConnection();
               try {
                  connection.setRequestMethod( "GET" );
                     if (connection.getResponseCode() == HttpConnection.HTTP_OK)
                    {

                    	is = connection.openInputStream();
            			len = connection.getLength();
            			if (len != -1) {
            				// Read exactly Content-Length bytes
            				for (int i = 0; i < len; i++)
            					if ((ch = is.read()) != -1) {
            						b.append((char) ch);
            					}
            			} else {
            				// Read until the connection is closed.
            				while ((ch = is.read()) != -1) {
            					len = is.available();
            					b.append((char) ch);
            				}
            			}
            			is.close();
            			connection.close();
                    	
            			

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

        return b.toString();
		
	}

}