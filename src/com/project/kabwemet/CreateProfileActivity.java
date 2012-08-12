package com.project.kabwemet;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.android.Contents;
import com.google.zxing.client.android.Intents;
import com.google.zxing.client.android.encode.*;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.view.View;

public class CreateProfileActivity extends Activity{
	
	public static String qrURL=null;
	
	
	
	public String url="http://kabwemet.appspot.com/fetch_status";
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprofile);
       
        
        
    }
	
	public void submitInfo(View v)
	{

		
		String first_name;
		String last_name;
		String email;
		String mobile_number;
		String designation;
		String company;
		UrlEncodedFormEntity values=null;
		String output=null;
		String website;
		
		
		String toEncode;
		
		
		EditText value = (EditText) findViewById(R.id.first_name);
		first_name=value.getText().toString();
		
		value = (EditText) findViewById(R.id.last_name);
		last_name=value.getText().toString();
		
		value = (EditText) findViewById(R.id.email);
		email=value.getText().toString();
		
		value = (EditText) findViewById(R.id.mobile_number);
		mobile_number=value.getText().toString();
		value = (EditText) findViewById(R.id.website);
		website=value.getText().toString();		
		
		value = (EditText) findViewById(R.id.designation);
		
		designation=value.getText().toString();
		value = (EditText) findViewById(R.id.company);
		company=value.getText().toString();
		DisplayToast(first_name);
		
		
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs.add(new BasicNameValuePair("first_name", first_name));
        nameValuePairs.add(new BasicNameValuePair("last_name", last_name));
        nameValuePairs.add(new BasicNameValuePair("email", email));
        nameValuePairs.add(new BasicNameValuePair("mobile_number", mobile_number));
        nameValuePairs.add(new BasicNameValuePair("company",company));
        nameValuePairs.add(new BasicNameValuePair("designation", designation));
        nameValuePairs.add(new BasicNameValuePair("website", website));
        
        try {
			values=new UrlEncodedFormEntity(nameValuePairs);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
		}
        
        toEncode="MECARD:N:"+first_name+" "+last_name+";ORG:"+company+";TEL:"+mobile_number+";URL:"+website+";EMAIL:"+email+";KEY:value"+";;";
        
        
        
        try{
        	//output=postData(url,values);
        	
            toEncode="MECARD:N:"+first_name+" "+last_name+";ORG:"+company+";TEL:"+mobile_number+";URL:"+website+";EMAIL:"+email+";KEY:value"+";;";
            String s=generateQRCode(toEncode);
            final Context context = this;
            Intent intent = new Intent(context, displayKabWeMet.class);
            intent.putExtra("first_name", first_name);
            intent.putExtra("last_name", last_name);
            intent.putExtra("email", email);
            intent.putExtra("mobile_number", mobile_number);
            intent.putExtra("designation", designation);
            intent.putExtra("company", company);
            intent.putExtra("website", website);
            intent.putExtra("qrcode", s);
            startActivity(intent);
            
        	}catch(Exception e)
        {
        	DisplayToast(e.getMessage().toString());
        	
        }
      
		
	}
	
	
	

	 private String generateQRCode(String val) {

		 
		 String base_url="http://chart.apis.google.com/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=";
		 try {
			base_url+=URLEncoder.encode(val, "UTF-8");
			Log.i("pop",base_url);
			
			return base_url;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 
		 return base_url;
		 
		 
		 
		 
		 	
		
		   }
	
	
	


public JSONObject getJSONObject(String val){
		
		JSONObject json=null;
		try {
			json = new JSONObject(val);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
		
	}
	
	
	private void DisplayToast(String msg)
    {
         Toast.makeText(getBaseContext(), msg, 
                 Toast.LENGTH_SHORT).show();        
    }
	
	public String postData(String url,UrlEncodedFormEntity values) {
	    // Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost(url);
	    HttpContext localContext = null;
	    InputStream res=null;
	    try {
	        httppost.setEntity(values);
	        HttpResponse response = httpclient.execute(httppost,localContext);
	        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
	        return reader.readLine();
	        
	    } catch (ClientProtocolException e) {
	    	Log.i("eroor now 1 here", e.getMessage());
	    	
	    	
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	    	
	    	Log.i("eroor 2", e.getStackTrace().toString());
	    	// TODO Auto-generated catch block
	    }
	    
	return null;
	}
	
	
	
	
	

	

}