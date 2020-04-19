/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.service;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author fedi
 */
public class UploadImage {
    
    
    public  void adImage( File myObj)
    {
        CloseableHttpClient httpclient = HttpClients.createDefault();
 
      //Creating a file object
 
        try {
            myObj.createNewFile();
            //Creating the FileBody object
      

      //Creating the MultipartEntityBuilder
      MultipartEntityBuilder entitybuilder = MultipartEntityBuilder.create();

      //Setting the mode
      entitybuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

      //Adding a file
      entitybuilder.addBinaryBody("avatar", myObj);

      //Building a single entity using the parts
      HttpEntity mutiPartHttpEntity = entitybuilder.build();

      //Building the RequestBuilder request object
      RequestBuilder reqbuilder = RequestBuilder.post("http://localhost/pidev20mod/web/uploads/image.php");

      //Set the entity object to the RequestBuilder
      reqbuilder.setEntity(mutiPartHttpEntity);

      //Building the request
      HttpUriRequest multipartRequest = reqbuilder.build();

      //Executing the request
      HttpResponse httpresponse = httpclient.execute(multipartRequest);

      //Printing the status and the contents of the response
      System.out.println(EntityUtils.toString(httpresponse.getEntity()));
      System.out.println(httpresponse.getStatusLine());
  } catch (IOException ex) {
            Logger.getLogger(UploadImage.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }
    
    
}
