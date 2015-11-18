package com.qian.widget;

 import com.qian.storyalbum.R;
import com.qian.storyalbum.StoryalbumDB;

import android.app.Service;  
 import android.appwidget.AppWidgetManager;  
 import android.content.BroadcastReceiver;  
 import android.content.ComponentName;  
 import android.content.Context;  
import android.app.PendingIntent;
 import android.content.Intent;  
 import android.content.IntentFilter;  
import android.database.Cursor;
 import android.graphics.Color;  
import android.net.Uri;
 import android.os.IBinder;  
import android.util.Log;
import android.widget.RemoteViews;  
    
 public  class storyalbumReceiver extends BroadcastReceiver {  
	 private final String broadCastString = "zengjun"; 
	 private static int index = 0;
     private StoryalbumDB mBooksDB;
     private Cursor cur;
     private int totalCount;
     private String  imagePathString = null;
     private String  flagstr = null;
     private String[] imagePath;
	 private int flag = 0;
     private int id;
     private RemoteViews rv;
     private ComponentName componentName;   
      
     
     @Override      
     public void onReceive(Context context, Intent intent) {      	         	  
    	         	flagstr = intent.getStringExtra("flag");
    	         	flag=Integer.parseInt(flagstr);
    	                rv = new RemoteViews(context.getPackageName(), R.layout.appwidget_main);
    	                mBooksDB = new StoryalbumDB(context);

    	            	cur = mBooksDB.select();				
    	            	totalCount = cur.getCount();
    	                cur.moveToFirst(); 
    	        		
    	        		if (totalCount != 0)
    	        		{
    
    	        		imagePathString = cur.getString(3);   	        	
    	        		imagePath = imagePathString.split(" ");							
    	        		
    	        		        id=imagePath.length - 1;
    	        		   
    	        		      //  rv.setImageViewUri(R.id.photo, Uri.parse(imagePath[flag]));    	        	
    	        		
    	        		     rv.setImageViewUri(R.id.photo, Uri.parse(imagePath[flag]));     	        

    	        		}else
    	        		{
    	        			 rv.setImageViewResource(R.id.photo, R.drawable.popup_widget_img);
    	        		} 
    	               Intent intent1 = new Intent(context, com.qian.storyalbum.MainActivity.class);        
        		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, 0);
        		rv.setOnClickPendingIntent(R.id.photo, pendingIntent);
        		
                       componentName = new ComponentName(context,storyalbumWidgetProvider.class);
    	                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
    	                componentName = new ComponentName(context,storyalbumWidgetProvider.class);
    	                appWidgetManager.updateAppWidget(componentName, rv);
						cur.close();
    	         }          	        
 }  
