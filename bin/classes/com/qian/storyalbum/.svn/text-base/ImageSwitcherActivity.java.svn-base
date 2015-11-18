package com.qian.storyalbum;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;
 public class  ImageSwitcherActivity extends Activity implements ViewFactory {  //zengjun add for see image 
        
        private StoryalbumDB mBooksDB;
        private Cursor cur;
        private String  imagePathString = null;
        private String[] imagePath;
        private ImageSwitcher imageSwitcher;  
        private TextView mtextview;  
           
        /** Called when the activity is first created. */  
        @Override  
        public void onCreate(Bundle savedInstanceState) {  
            super.onCreate(savedInstanceState);  
            setContentView(R.layout.imageswitch); 
            /*传过来index在数据库中去path*/
        	mBooksDB = new StoryalbumDB(this);
			cur = mBooksDB.select();    
             Intent path = getIntent();
             String imagePathindex = path.getStringExtra("path");
             Log.d("imageSwitcherActivity","imagePathindex======"+imagePathindex);
            int i=Integer.parseInt(imagePathindex);//string zhuan int
            Log.d("imageSwitcherActivity","i======"+i);          
            cur.moveToPosition(i-1);
            imagePathString = cur.getString(3);		
		     imagePath = imagePathString.split(" ");
		     Log.d("imageSwitcherActivity","imagePath.length======"+imagePath.length);
		    /*传过来index在数据库中去path*/ 
            imageSwitcher = (ImageSwitcher) findViewById(R.id.switcher1);  
            imageSwitcher.setFactory(this); 
            mtextview = (TextView) findViewById(R.id.textview1);
            mtextview.setText(getString(R.string.story_title).toString()+cur.getString(1) + "   "+getString(R.string.story_flam).toString()
		+ cur.getString(2));
           // imageSwitcher.setImageResource(imageIDs[0]);
            imageSwitcher.setImageURI(Uri.parse(imagePath[0]));
               
            
           // 淡入淡出效果
            imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
                    android.R.anim.fade_in));
            imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
                    android.R.anim.fade_out));
            
               /*
            // 左右滑动效果  
            imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,  
                    android.R.anim.slide_in_left));  
            imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,  
                    android.R.anim.slide_out_right));  
          */
       
            Gallery gallery = (Gallery) findViewById(R.id.gallery1);  
            gallery.setAdapter(new ImageAdapter(this));  
            gallery.setOnItemClickListener(new OnItemClickListener()  
            {  
                public void onItemClick(AdapterView<?> parent,  
                View v, int position, long id)  
                {  
                    imageSwitcher.setImageURI(Uri.parse(imagePath[position]));  //zengjun 显示数据库图片
                }  
            });  
        }  
           
        public View makeView()  
        {  
            ImageView imageView = new ImageView(this);  
            imageView.setBackgroundColor(0xFF000000);  
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);  
            imageView.setLayoutParams(new  
                    ImageSwitcher.LayoutParams(  
                            LayoutParams.FILL_PARENT,  
                            LayoutParams.FILL_PARENT));  
            return imageView;  
        }  
       
        public class ImageAdapter extends BaseAdapter  
        {  
            private Context context;  
            private int itemBackground;  
       
            public ImageAdapter(Context c)  
            {  
                context = c;  
       
                //---setting the style---  
                TypedArray a = obtainStyledAttributes(R.styleable.Gallery1);  
                itemBackground = a.getResourceId(  
                        R.styleable.Gallery1_android_galleryItemBackground, 0);  
                a.recycle();  
            }  
       
            //---returns the number of images---  
            public int getCount()  
            {  
                return imagePath.length;  
            }  
       
            //---returns the item---  
            public Object getItem(int position)  
            {  
                return position;  
            }  
       
            //---returns the ID of an item---  
            public long getItemId(int position)  
            {  
                return position;  
            }  
       
            //---returns an ImageView view---  
            public View getView(int position, View convertView, ViewGroup parent)  
            {  
                ImageView imageView = new ImageView(context);  
                   
                imageView.setImageURI(Uri.parse(imagePath[position]));
                //imageView.setImageResource(imageIDs[position]);  
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);  
                imageView.setLayoutParams(new Gallery.LayoutParams(150, 120));  
                imageView.setBackgroundResource(itemBackground);  
                   
                return imageView;  
            }  
        }  
       
    }  
