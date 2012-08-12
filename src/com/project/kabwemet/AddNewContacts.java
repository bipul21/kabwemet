package com.project.kabwemet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class AddNewContacts extends Activity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
		String index;
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_eventslist);
        index=this.getIntent().getStringExtra("position");
        
        
        
        
	}
	
	public void scanCode()
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
    	    
    	      } else if (resultCode == RESULT_CANCELED) {
    	         // Handle cancel
    	      }
    	   }
    	}
    
	public void addNewContacts(View v)
	{
		DisplayToast("Hello world");
		//scanCode();
		
	}
	 private void DisplayToast(String msg)
	    {
	         Toast.makeText(getBaseContext(), msg, 
	                 Toast.LENGTH_SHORT).show();        
	    } 
	    

}
