package com.qian.storyalbum;



import java.util.ArrayList;

import net.youmi.android.AdManager;
import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;
import net.youmi.android.banner.AdViewListener;
import net.youmi.android.offers.OffersManager;
import net.youmi.android.offers.PointsManager;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
private Button main_bt_1;
private Button main_bt_0;
private Button button1;
private ImageView main_iv_1;
private ImageView main_iv_2;
private TextView main_tx_1;
private TextView mTextView;
private TextView textnumView;
private TextView mTextViewPoints;
private Button rightBut;
private StoryalbumDB mBooksDB;
private Cursor cur;
private final static String TABLE_NAME = "Storyalbum_table";
private int index = 1;
private int totalCount;
private String  imagePathString = null;
private String[] imagePath;
private int STORYALBUM_ID = 0;
private ArrayList<String> imageSrcs = new ArrayList<String>();
private String di;
private String jige;
private String title;
private String flam;
private int pointsBalance;
/*private final static String	DATABASE_NAME	= "Storyalbum.db";
private final static String TABLE_NAME = "Storyalbum_table";
private final static String	STORYALBUM_ID		= "Storyalbum_id";
private final static String	STORYALBUM_TITLE		= "Storyalbum_title";
private final static String	STORYALBUM_FAME		= "Storyalbum_fame";
private SQLiteDatabase		mSQLiteDatabase	= null;*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		 // 参数：appId, appSecret, 调试模式
 		AdManager.getInstance(this).init("9ab80878590a2de5",
 				"d958ae542ef2904f", false); 
 		// 如果使用积分广告，请务必调用积分广告的初始化接口:
		setContentView(R.layout.main);
		
		LinearLayout adLayout = (LinearLayout)findViewById(R.id.adLayout);
        AdView adView = new AdView(this, AdSize.FIT_SCREEN);
        adLayout.addView(adView);
	//打开数据库
        // 监听广告条接口
        adView.setAdListener(new AdViewListener() {
            
            @Override
            public void onSwitchedAd(AdView arg0) {
                Log.i("YoumiSample", "广告条切换");
            } 
            
            @Override
            public void onReceivedAd(AdView arg0) {
                Log.i("YoumiSample", "请求广告成功");
                
            }
            
            @Override
            public void onFailedToReceivedAd(AdView arg0) {
                Log.i("YoumiSample", "请求广告失败");
            }
        });
		creatCur();
		textnumView = (TextView) findViewById(R.id.textnum);		
		mTextViewPoints = (TextView) findViewById(R.id.main_tx_point);
		rightBut = (Button)this.findViewById(R.id.nextbut);
		mTextView = (TextView) findViewById(R.id.main_tx_2);
		main_tx_1 = (TextView) findViewById(R.id.main_tx_1);
		main_iv_1 = (ImageView) findViewById(R.id.main_iv_1);
		main_iv_2 = (ImageView) findViewById(R.id.main_iv_2);
		di = getString(R.string.story_di).toString();
		jige = getString(R.string.story_ge).toString();
		title = getString(R.string.story_title).toString();
		flam = getString(R.string.story_flam).toString();
		isnull();
		
		main_bt_1=(Button)this.findViewById(R.id.main_bt_1);
		main_bt_0=(Button)this.findViewById(R.id.main_bt_0);
		main_bt_0.setOnClickListener(this);
	
		//取出字串分割
		cur.moveToFirst(); 
		
		if (totalCount != 0)
		{
	    Log.d("storyalbum","imagePathString"+cur.getString(3) );
		imagePathString = cur.getString(3);
		
		if(imagePathString != null)
		{
		imagePath = imagePathString.split(" ");							
		totalCount = cur.getCount();
		textnumView.setText(di+index+""+"/"+totalCount+""+jige);
		/*积分*/
	   pointsBalance = PointsManager.getInstance(this).queryPoints();// 获取积分
		mTextViewPoints.setText("积分:" + pointsBalance);
		
		 //   while (!cur.isAfterLast()) { 
		if (mBooksDB.tabIsExist(TABLE_NAME)){
		        int id=cur.getInt(0); 
		        mTextView.setText(title+cur.getString(1) + "  "+flam

		+ cur.getString(2));
		      //  main_iv_2.setImageURI(Uri.parse(cur.getString(3)));//tupian
		        main_iv_2.setImageURI(Uri.parse(imagePath[0]));//tupian
		}
		}
		}
		  //      cur.moveToNext(); 
		  //    } 

		    //点击按钮，切换图片
	         main_iv_2.setOnClickListener(new OnClickListener(){
	             @Override
	             public void onClick(View v) {
	            	 Intent ImageSwitchIntent = new Intent(MainActivity.this,ImageSwitcherActivity.class);
	            	 String indexstring=index+"";
	            	 ImageSwitchIntent.putExtra("path", indexstring);
	            	  startActivity(ImageSwitchIntent);
	                 }
	             }
	         );
	     /*    main_bt_0.setOnClickListener(new OnClickListener(){
	             @Override
	             public void onClick(View v) {
	            	   OffersManager.getInstance(this).showOffersWall();
	                 }
	             }
	         );*/
		rightBut.setOnClickListener(new Button.OnClickListener(){//创建监听对象
        @Override
            public void onClick(View v){
            cur.moveToNext(); 
            if(index == totalCount){
               	cur.moveToFirst(); 
               	index = 1;
               	index --;
                }
        	  mTextView.setText(title+cur.getString(1) + "  "+flam

        			+ cur.getString(2));
        	  imagePathString = cur.getString(3);				
		     imagePath = imagePathString.split(" ");
        			      //  main_iv_2.setImageURI(Uri.parse(cur.getString(3)));//tupian 
        	               main_iv_2.setImageURI(Uri.parse(imagePath[0]));//tupian
        			        index ++;
        			        textnumView.setText(di+index+""+"/"+totalCount+""+jige);
       	                
        	}
          });     
		
		main_bt_1.setOnTouchListener(new OnTouchListener(){    
			
            @Override    
            public boolean onTouch(View v, MotionEvent event) {     
                    if(event.getAction() == MotionEvent.ACTION_DOWN){     
                            //更改为按下时的背景图片     
                            v.setBackgroundResource(R.drawable.main_bt_1_back2);     
                    }else if(event.getAction() == MotionEvent.ACTION_UP){     
                            //改为抬起时的图片     
                    	if(pointsBalance < 20) 
                    	{
                    		dialog();
                    		 v.setBackgroundResource(R.drawable.main_bt_1_back);   
                 		
                    	}else{
                            v.setBackgroundResource(R.drawable.main_bt_1_back);  
                            delPoints();
                            startActivity(new Intent(MainActivity.this,AddPic.class)); 
                    	}
                    }     
                    
                    return false;     
            }     
    });

	}
	
	protected void dialog() {
		 AlertDialog.Builder builder = new Builder(MainActivity.this);
		  builder.setMessage("积分少于20分，请点赚取积分！");
		  builder.setTitle("提示");
		  builder.setPositiveButton("赚取", new  DialogInterface.OnClickListener() {
	  @Override
 public void onClick(DialogInterface dialog, int which) {
		  startAction();}
		});
			 builder.setNegativeButton("取消", new  DialogInterface.OnClickListener() {
		@Override
		  public void onClick(DialogInterface dialog, int which) {
		   dialog.dismiss();}
		 });
		  builder.create().show();}
	
	
	public void startAction(){
		OffersManager.getInstance(this).showOffersWall();
}
	
	public void delPoints(){
	if(pointsBalance >= 20){
		  PointsManager.getInstance(this).spendPoints(20);}
	 }
	 
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.main_bt_0:
			OffersManager.getInstance(this).showOffersWall();
			break;
		default: 
			
			break;
		}

	}
	@Override  
    protected void onResume() {  
        super.onResume();
        /*积分*/
	 pointsBalance = PointsManager.getInstance(this).queryPoints();// 获取积分
		mTextViewPoints.setText("积分:" + pointsBalance);
        creatCur();
        isnull();
        if (totalCount != 0)//判断tab是否存在且不为空
		{
            	cur.moveToFirst(); 
     	  mTextView.setText(title+cur.getString(1) + "  "+flam

     			+ cur.getString(2));
     	  imagePathString = cur.getString(3);				
		     imagePath = imagePathString.split(" ");
     			      //  main_iv_2.setImageURI(Uri.parse(cur.getString(3)));//tupian 
     	               main_iv_2.setImageURI(Uri.parse(imagePath[0]));//tupian
     			        textnumView.setText(di+index+""+"/"+totalCount+""+jige);
		}  
        
    }  
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
    public boolean onOptionsItemSelected(MenuItem item) 
     {
       
        switch (item.getItemId()) {
		case R.id.action_add://zengjun test
			 startActivity(new Intent(MainActivity.this,AddPic.class));     
			return true;    
		case R.id.action_delete://zengjun test
			if (totalCount != 0)
			{
				delete();
			}else
			{
				Toast.makeText(this, "NO Storyalbum!", Toast.LENGTH_SHORT).show();
			}
			return true;  
        }
        return true;	
     }
	public void delete() {  //zengjun add删除当前
		cur.moveToPosition(index-1);
		STORYALBUM_ID = cur.getInt(0);
		mBooksDB.delete(STORYALBUM_ID);
		//cur.requery();
		//刷新一次界面
		 creatCur();
		isnull();
		if (totalCount != 0)//判断tab是否存在且不为空
		{
            	cur.moveToFirst(); 
     	  mTextView.setText(title+cur.getString(1) + "  "+flam

     			+ cur.getString(2));
     	  imagePathString = cur.getString(3);				
		     imagePath = imagePathString.split(" ");
     			      //  main_iv_2.setImageURI(Uri.parse(cur.getString(3)));//tupian 
     	               main_iv_2.setImageURI(Uri.parse(imagePath[0]));//tupian
     			        textnumView.setText(di+index+""+"/"+totalCount+""+jige);
		}               	
		
		Toast.makeText(this, "Delete Successed!", Toast.LENGTH_SHORT).show();
          cur.close();
		}
	public void isnull()  //主界面显示判断
	{
		 if (totalCount != 0)//判断tab是否存在且不为空
			{
				textnumView.setVisibility(View.VISIBLE);
				rightBut.setVisibility(View.VISIBLE);
				mTextView.setVisibility(View.VISIBLE);
				main_iv_2.setVisibility(View.VISIBLE);
				main_tx_1.setVisibility(View.GONE);
				main_iv_1.setVisibility(View.GONE);	
				
			}else{
				main_tx_1.setVisibility(View.VISIBLE);
				main_iv_1.setVisibility(View.VISIBLE);
				main_iv_2.setVisibility(View.GONE);	
				mTextView.setVisibility(View.GONE);
				textnumView.setVisibility(View.GONE);
				rightBut.setVisibility(View.GONE);
					
			}
		
	}
	public void creatCur()  //获取数据库基本参数
	{
	mBooksDB = new StoryalbumDB(this);
	index = 1;
	cur = mBooksDB.select();				
	totalCount = cur.getCount();
	}
	
}
