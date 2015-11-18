package com.qian.widget;

import java.util.Timer;
import java.util.TimerTask;

import com.qian.storyalbum.R;
import com.qian.storyalbum.StoryalbumDB;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;


public class storyalbumWidgetProvider extends AppWidgetProvider
{
        private static Timer myTimer = null;
        private StoryalbumDB mBooksDB;
        private Cursor cur;
        private int totalCount;
        private String  imagePathString = null;
        private String[] imagePath;       
        private int id;
        private RemoteViews rv;
        private ComponentName componentName;
        //定义我们要发送的事件
        private final String broadCastString = "zengjun"; 
         
        @Override
        public void onDeleted(Context context, int[] appWidgetIds)
        {
                // TODO Auto-generated method stub
                super.onDeleted(context, appWidgetIds);
               if(myTimer != null)
               {
            	   myTimer.cancel();
               }           	
                System.out.println("onDeleted");
        }
         
         
 
        @Override
        public void onEnabled(Context context)
        {
                System.out.println("onEnabled");
                // TODO Auto-generated method stub
                super.onEnabled(context);
                Log.d("storyalbumWidgetProvider", "onEnabled==========");
        }
 
 
 
        @Override
        public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                        int[] appWidgetIds)
        {
        	
                System.out.println("onUpdate");
                // TODO Auto-generated method stub
                super.onUpdate(context, appWidgetManager, appWidgetIds);
                Log.d("storyalbum","onUpdate=======");
                 rv = new RemoteViews(context.getPackageName(), R.layout.appwidget_main);
                mBooksDB = new StoryalbumDB(context);

            	cur = mBooksDB.select();				
            	totalCount = cur.getCount();
                cur.moveToFirst(); 
        		
        		if (totalCount != 0)
        		{
        	    Log.d("storyalbum","imagePathString"+cur.getString(3) );
        		imagePathString = cur.getString(3);
        		imagePath = imagePathString.split(" ");							
        		
        		        id=imagePath.length - 1;
        		        Log.d("storyalbumWidgetProvider", "onUpdate id=========="+id);
        		        rv.setImageViewUri(R.id.photo, Uri.parse(imagePath[0]));
        		        Log.d("storyalbum","imagePath[0]====="+imagePath[0]);
        		      /*  //在插件被创建的时候这里会被调用， 所以我们在这里开启一个timer 每秒执行一次
        		        MyTask mMyTask = new MyTask(context);//定时器
                        myTimer = new Timer();
                        myTimer.schedule(mMyTask, 1000, 4*1000);
                        System.out.println("onEnabled2");*/
        		}else
        		{        		       			
        			 rv.setImageViewResource(R.id.photo, R.drawable.popup_widget_img);
        		}
        		//在插件被创建的时候这里会被调用， 所以我们在这里开启一个timer 每秒执行一次
		        MyTask mMyTask = new MyTask(context);//定时器
                myTimer = new Timer();
                myTimer.schedule(mMyTask, 1000, 4*1000);
                System.out.println("onEnabled2");
              Intent intent = new Intent(context, com.qian.storyalbum.MainActivity.class);        
        		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        		rv.setOnClickPendingIntent(R.id.photo, pendingIntent);
        		
              componentName = new ComponentName(context,storyalbumWidgetProvider.class);
                appWidgetManager.updateAppWidget(componentName, rv);
            
        }
         
         
         
        @Override
        public void onReceive(Context context, Intent intent)
        {
                //当判断到是该事件发过来时， 我们就获取插件的界面， 然后将index自加后传入到textview中
                System.out.println("onReceive");
                Log.d("storyalbumWidgetProvider", "onReceive==========");            
                // TODO Auto-generated method stub
                super.onReceive(context, intent);
        }
 
 
         
        class MyTask extends TimerTask
        {
 
                private Context mcontext = null;
                private Intent intent = null;
                private int flag = 0;
                
                public MyTask(Context context) {
                         
                        //新建一个要发送的Intent
                        mcontext = context;
                        intent = new Intent();                       
                        intent.setAction(broadCastString);
                }
                @Override
                public void run()
                {
                        System.out.println("2");
                        if (totalCount != 0)
                		{
                     cur.moveToFirst();
                	    Log.d("storyalbum","imagePathString"+cur.getString(3) );
                		imagePathString = cur.getString(3);
                		imagePath = imagePathString.split(" ");							               		
                		id=imagePath.length - 1;
                		        if(flag == id)
            	                  {
            	           		   Log.d("storyalbumReceiver", "onReceive2==========");
            	                  	flag=0;
            	                  }else{
            	               	   Log.d("storyalbumReceiver", "onReceive3==========");
            	                  	flag++;
            	                  }
                		        }
                      //  id=imagePath.length - 1;
                        Log.d("storyalbumWidgetProvider", "send===id======="+id);
                        //发送广播(由onReceive来接收)
                        Log.d("storyalbumWidgetProvider", "send==========");
                        Log.d("storyalbumWidgetProvider", "intent.getAction()=====send====="+intent.getAction());                       
                  
                        String flagStr = flag + "";
                        intent.putExtra("flag", flagStr);
                        mcontext.sendBroadcast(intent);
                }
                 
        }
} 
