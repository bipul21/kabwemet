package com.project.kabwemet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class displayKabWeMet extends Activity {
	
	ImageView image;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayprofile);
        TextView t=(TextView) findViewById(R.id.name);
        t.setText(this.getIntent().getStringExtra("first_name")+" "+this.getIntent().getStringExtra("last_name"));
        t=(TextView) findViewById(R.id.email);
        t.setText(this.getIntent().getStringExtra("email"));
        t=(TextView) findViewById(R.id.mobile_number);
        t.setText(this.getIntent().getStringExtra("mobile_number"));
        t=(TextView) findViewById(R.id.designation);
        t.setText(this.getIntent().getStringExtra("designation"));
        t=(TextView) findViewById(R.id.company);
        t.setText(this.getIntent().getStringExtra("company"));
        downloadFile(this.getIntent().getStringExtra("qrcode"));
        

    }
	
	private void DisplayToast(String msg)
    {
         Toast.makeText(getBaseContext(), msg, 
                 Toast.LENGTH_SHORT).show();        
    }
	
	
	 @SuppressLint({ "ParserError", "ParserError" })
	void downloadFile(String fileUrl) {
		 
		 Bitmap bmImg;
		 ImageView imView = (ImageView) findViewById(R.id.qr);
	        URL myFileUrl = null;
	        try {
	            myFileUrl = new URL(fileUrl);
	            if(null == myFileUrl)
	        	          	return ;
	            
	        } catch (MalformedURLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        try {
	        	
	        		HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
		            conn.setDoInput(true);
		            conn.connect();
		            InputStream is = conn.getInputStream();
		            
		            bmImg = BitmapFactory.decodeStream(is);

		            imView.setImageBitmap(bmImg);

	        	
	        	
	          } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	       
	        return;
	    }
	
	

}
