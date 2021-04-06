package gluecode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class APIMethodCalls 
{
	String mainURL = "https://api.jsonbin.io";
    String readLine = null, responseValue = null;
    String strXMasterKey = "$2b$10$CNE9K9KCJ8SzopMQjSQ4q.eune3Ri6.xqQxT2Ek8flSNzxQTf8CIi";

    HttpURLConnection conection = null;	

    int responseCode;

	// Function will be called in step definition for GET request
	public void GETRequest(String strBinId) throws Throwable 
	{
		URL urlForGetRequest = new URL(mainURL+"/b/"+strBinId);
		
		conection = (HttpURLConnection) urlForGetRequest.openConnection();
	    conection.setRequestMethod("GET");

	    responseCode = conection.getResponseCode();
		System.out.println("GET Response Code :  " + responseCode);
	    System.out.println("GET Response Message : " + conection.getResponseMessage());
	    responseValue = ResponseCodeString(conection).toString();
		
		if (responseCode == HttpURLConnection.HTTP_OK) 
	    {
	    	System.out.println("HTTP OK Response:" + responseValue);
	    } 
	    else if(responseCode == HttpURLConnection.HTTP_BAD_GATEWAY)
	    {
	    	System.out.println("HTTP BAD GATEWAY Response:" + responseValue);
	    }
	    else
	    {
	        System.out.println("GET request is not worked.");
	        throw new Exception("GET request not worked.");
	    }
	}
	
	// Function will be called in step definition for PUT request
	public void POSTRequest(String strRawdata) throws Throwable 
	{
	    URL obj = new URL(mainURL+"/v3/b");

	    conection = (HttpURLConnection) obj.openConnection();
	    conection.setRequestMethod("POST");
	    conection.setRequestProperty("Content-Type", "application/json");
	    conection.setRequestProperty("X-Master-Key",strXMasterKey);
	    conection.setRequestProperty("X-Bin-Private","false");
	    conection.setRequestProperty("X-Bin-Name","TestBhaskar"+UniqueIdCreation());
	    conection.setRequestProperty("Connection","keep-alive");
	    conection.setDoOutput(true);

	    OutputStream os = conection.getOutputStream();
	    os.write(strRawdata.getBytes());
	    os.flush();
	    os.close();


	    responseCode = conection.getResponseCode();
	    System.out.println("POST Response Code :  " + responseCode);
	    System.out.println("POST Response Message : " + conection.getResponseMessage());
	    responseValue = ResponseCodeString(conection).toString();

	    if (responseCode == HttpURLConnection.HTTP_OK) 
	    { 
	    	System.out.println("HTTP OK Response:" + responseValue);
	    } 
	    else 
	    {
	        System.out.println("POST request not worked.");
	        throw new Exception("POST request not worked.");
	    }
	}

	// Function will be called in step definition for PUT request
	public void PUTRequest(String strRawdata, String strBinId) throws IOException, Throwable
	{
	    URL obj = new URL(mainURL+"/v3/b/"+strBinId);

	    conection = (HttpURLConnection) obj.openConnection();
	    conection.setRequestMethod("PUT");
	    conection.setRequestProperty("Content-Type", "application/json");
	    conection.setRequestProperty("X-Master-Key",strXMasterKey);
	    conection.setRequestProperty("Connection","keep-alive");
	    conection.setDoOutput(true);

	    OutputStream os = conection.getOutputStream();
	    os.write(strRawdata.getBytes());
	    os.flush();
	    os.close();


	    responseCode = conection.getResponseCode();
	    System.out.println("PUT Response Code :  " + responseCode);
	    System.out.println("PUT Response Message : " + conection.getResponseMessage());
	    responseValue = ResponseCodeString(conection).toString();

	    if (responseCode == HttpURLConnection.HTTP_OK) 
	    { 
	    	System.out.println("HTTP OK Response:" + responseValue);
	    } 
	    else 
	    {
	        System.out.println("PUT request not worked.");
	        throw new Exception("PUT request not worked.");
	    }
	}

	// Function will be called in step definition for DELETE request
	public void DELETERequest(String strBinId) throws Throwable 
	{
	    URL obj = new URL(mainURL+"/v3/b/"+strBinId);

	    conection = (HttpURLConnection) obj.openConnection();
	    conection.setRequestMethod("DELETE");
	    conection.setRequestProperty("X-Master-Key",strXMasterKey);
	    conection.setRequestProperty("Connection","keep-alive");
	    conection.setDoOutput(true);

	    responseCode = conection.getResponseCode();
	    System.out.println("DELETE Response Code :  " + responseCode);
	    System.out.println("DELETE Response Message : " + conection.getResponseMessage());
	    

	    if (responseCode == HttpURLConnection.HTTP_OK) 
	    { 
	    	responseValue = ResponseCodeString(conection).toString();
	    	System.out.println("HTTP OK Response:" + responseValue);
	    } 
	    else if(responseCode == HttpURLConnection.HTTP_NOT_FOUND)
	    {
	        System.out.println("HTTP 404 Not Found Response, Bin id not found so DELETE request not worked.");
	        throw new Exception("Bin id not found so DELETE request not worked.");
	    }
	    else 
	    {
	        System.out.println("DELETE request not worked.");
	        throw new Exception("DELETE request not worked.");
	    }
	    
	}

	// Function will be called in step definition to validate the text in a response code
	public void VerifyResponseText(String textValue) throws Throwable
	{
		if (responseValue.contains(textValue)) 
	    {
	    	System.out.println("Text is present in the response.");
	    } 
	    else
	    {
	        System.out.println("Text is not present in the response.");
	        throw new Exception("Text is not present in the response.");
	    }
	}

	
/*
 * Below is the list of generic functions.
 * 
 */
		
	// This is Generic Function to read the response code string
	public StringBuffer ResponseCodeString(HttpURLConnection conection) throws IOException
	{
        BufferedReader in = new BufferedReader( new InputStreamReader(conection.getInputStream()));
        StringBuffer response = new StringBuffer();
        while ((readLine = in .readLine()) != null) 
        {
            response.append(readLine);
        } in .close();
        return response;
	}
	
	//This is a Generic Function to create unique string text with current system date and time
	public String UniqueIdCreation()
	{
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyyHH-mm-ss");
		return formatter.format(date).toString();
				
	}
	
}
