package com.project.kabwemet;

import java.util.HashMap;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class EventsListView extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_eventslist);
        ListView listView = (ListView) findViewById(R.id.list);
        final String[] values = new String[] { "Yahoo Open Hack Day", "Global Service Jam", 
        		"IEEE submit","Beer Buddies" };
        final Context context = this;

        // First paramenter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
          android.R.layout.simple_list_item_1, values);
        listView.setAdapter(adapter);
        final OnItemClickListener myClickListener = new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> a, View view, int position, long id)
            {
           
            //    Intent i = new Intent(view.getContext(), NewsDetails.class);
           //     i.putExtra("content_id", o.get("id"));
            //    i.putExtra("title", o.get("naam"));
            		
            		
            		Intent intent = new Intent(context, AddNewContacts.class);
                    intent.putExtra("id", position);
                    
                    startActivity(intent);
            		
            		
           
            }
        };
        listView.setOnItemClickListener(myClickListener);   
      
       
     
    }
	   private void DisplayToast(String msg)
	    {
	         Toast.makeText(getBaseContext(), msg, 
	                 Toast.LENGTH_SHORT).show();        
	    } 
	
	

}
