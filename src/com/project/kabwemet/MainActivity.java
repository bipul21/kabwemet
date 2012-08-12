package com.project.kabwemet;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @SuppressLint("ParserError")
	public void moveToCreate(View v)
    {
    	
        final Context context = this;
        Intent intent = new Intent(context, CreateProfileActivity.class);
        startActivity(intent);  
        
    }
    
    
   	public void moveToEvents(View v)
       {
       	
           final Context context = this;
           Intent intent = new Intent(context, EventsListView.class);
           startActivity(intent);  
           
       }

    
    private void DisplayToast(String msg)
    {
         Toast.makeText(getBaseContext(), msg, 
                 Toast.LENGTH_SHORT).show();        
    } 
    
    public void scanCode(View v)
    {
    	try{
    Intent intent = new Intent("com.google.zxing.client.android.SCAN");
   intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
    startActivityForResult(intent, 0);
    	}catch(Exception e)	
    	{
    		Log.i("Error camera",e.getMessage());
    	}
    	
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	   if (requestCode == 0) {
    	      if (resultCode == RESULT_OK) {
    	         String contents = intent.getStringExtra("SCAN_RESULT");
    	         String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
    	         DisplayToast("Contents  "+contents);
    	         DisplayToast("Format   "+format);
    	         
    	         // Handle successful scan
    	      } else if (resultCode == RESULT_CANCELED) {
    	         // Handle cancel
    	      }
    	   }
    	}

    
}
