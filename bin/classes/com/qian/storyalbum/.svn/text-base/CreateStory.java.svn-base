package com.qian.storyalbum;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;



import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;

import android.content.ContentResolver;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import android.view.Window;
import android.view.View.OnTouchListener;

import android.widget.AdapterView;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;
public class CreateStory extends Activity  implements
AdapterView.OnItemClickListener {
//	private static final int RESULT_LOAD_IMAGE = 1;
	private ImageView createstory_iv_1;
    private Button createstory_bt_1 ;
    private Button createstory_bt_2 ;
    private TextView createstory_tv_2_2;
//	private int RESULT_CAMARA_IMAGE;
    public static final float DISPLAY_WIDTH = 200;  
    public static final float DISPLAY_HEIGHT = 200; 
    private static final int TAKE_PICTURE = 0;
	private static final int CHOOSE_PICTURE = 1;
	private static final int SCALE = 5;
	private EditText title = null;
	private EditText fame = null;
	//private ListView BooksList;
	public StoryalbumDB mBooksDB;
	public Cursor mCursor;
	private int BOOK_ID = 0;
	private ArrayList<String>  m_arrayList;
	private String  imagePathString;
	private String[] imagePath;
//	private int pathcount =0;
	 private  Intent list_intent;//add for imagepath

	protected final static int MENU_ADD = Menu.FIRST;

	protected final static int MENU_DELETE = Menu.FIRST + 1;

	protected final static int MENU_UPDATE = Menu.FIRST + 2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createstory);
	
		setUpViews();
		createstory_iv_1=(ImageView)this.findViewById(R.id.createstory_iv_1);
		createstory_bt_1=(Button)this.findViewById(R.id.createstory_bt_1);
		createstory_bt_2=(Button)this.findViewById(R.id.createstory_bt_2);
		createstory_tv_2_2=(TextView)this.findViewById(R.id.createstory_tv_2_2);

		/*接受数据*/
		list_intent = getIntent();
		 m_arrayList = list_intent.getExtras().getStringArrayList(
				 GalleryActivity.LIST_KEY);
	       m_arrayList.get(0);
	       int m_arrayListsize = m_arrayList.size();
	      
	       //将imagepath转化为字串
	       StringBuffer  testStrBuff=new StringBuffer( Stingforpath(m_arrayList.get(0)));
	     
	         for (int i=1;i < m_arrayList.size();i++)
	         {         
	         testStrBuff.append(Stingforpath(m_arrayList.get(i)));//循环添加物理地址
	         }
	       imagePathString = testStrBuff.toString();	 
		imagePath = imagePathString.split(" ");
	     //end  
		createstory_tv_2_2.setText(m_arrayListsize+"");  //选中照片数量  
	   createstory_iv_1.setImageURI(Uri.parse(m_arrayList.get(0)));
		//createstory_iv_1.setImageURI(Uri.parse(imagePath[0]));
		createstory_bt_1.setOnTouchListener(new OnTouchListener(){     
            @Override    
            public boolean onTouch(View v, MotionEvent event) {     
                    if(event.getAction() == MotionEvent.ACTION_DOWN){     
                            //更改为按下时的背景图片     
                            v.setBackgroundResource(R.drawable.title_bt_2);     
                    }else if(event.getAction() == MotionEvent.ACTION_UP){     
                            //改为抬起时的图片     
                            v.setBackgroundResource(R.drawable.title_bt_1);                     
                           //startActivity(new Intent(HelpActivity.this,Button_main_1.class));
                            startActivity(new Intent(CreateStory.this,MainActivity.class));                       
                    }        
                     return false; 
                } 
         
                }); 
		createstory_bt_2.setOnClickListener(new Button.OnClickListener(){//创建监听对象
        @Override
            public void onClick(View v){
        	add();
        	startActivity(new Intent(CreateStory.this,MainActivity.class));
        	}
          });
//调用相机		
//		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//		startActivityForResult(intent, RESULT_CAMARA_IMAGE);
//调用相册		
		//  Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	     //   startActivityForResult(i, RESULT_LOAD_IMAGE); 
		
      
		}
	private String Stingforpath(String string)//取数据要用空格拆分所以追加空格存入
	{
		StringBuffer  testStrBuff1=new StringBuffer(string);
		testStrBuff1.append(" ");
		return testStrBuff1.toString();
	}
	/*
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	if(keyCode == KeyEvent.KEYCODE_BACK) { //监控/拦截/屏蔽返回键
		 startActivity(new Intent(CreateStory.this,MainActivity.class));
	return false;
	}
	return super.onKeyDown(keyCode, event);
	}
	*/
	public void setUpViews() {

		mBooksDB = new StoryalbumDB(this);

		mCursor = mBooksDB.select();



		title = (EditText)this.findViewById(R.id.titleEditText);
		fame = (EditText)this.findViewById(R.id.fameEditText);
		//BooksList = (ListView) findViewById(R.id.bookslist);



		//BooksList.setAdapter(new BooksListAdapter(this, mCursor));

		//BooksList.setOnItemClickListener(this);

		}

	@Override

	public boolean onCreateOptionsMenu(Menu menu) {

	super.onCreateOptionsMenu(menu);



	menu.add(Menu.NONE, MENU_ADD, 0, "ADD");

	menu.add(Menu.NONE, MENU_DELETE, 0, "DELETE");

	menu.add(Menu.NONE, MENU_DELETE, 0, "UPDATE");

	return true;

	}



	public boolean onOptionsItemSelected(MenuItem item) {

	super.onOptionsItemSelected(item);

	switch (item.getItemId()) {

	case MENU_ADD:

	add();

	break;

	case MENU_DELETE:

	delete();

	break;

	case MENU_UPDATE:

	update();

	break;

	}

	return true;

	}



	public void add() {

	String titleString = title.getText().toString();

	String fameString = fame.getText().toString();
	
	//String imageString =  m_arrayList.get(0);
	String imageString =  imagePathString;
	// 标题和主题都不能为空，或者退出

	if (titleString.equals("") || fameString.equals("")) {

	return;

	}

	mBooksDB.insert(titleString, fameString, imageString);

	mCursor.requery();

	

	title.setText("");

	fame.setText("");

	Toast.makeText(this, "Add Successed!", Toast.LENGTH_SHORT).show();

	}



	public void delete() {

	if (BOOK_ID == 0) {

	return;

	}

	mBooksDB.delete(BOOK_ID);

	mCursor.requery();

	

	title.setText("");

	fame.setText("");

	Toast.makeText(this, "Delete Successed!", Toast.LENGTH_SHORT).show();

	}



	public void update() {

		String titleString = title.getText().toString();

		String fameString = fame.getText().toString();
		
		//String imageString =  m_arrayList.get(0);

		String imageString =  imagePathString;
	// 书名和作者都不能为空，或者退出

	if (titleString.equals("") || fameString.equals("")) {

	return;

	}

	mBooksDB.update(BOOK_ID, titleString, fameString, imageString);

	mCursor.requery();

	

	title.setText("");

	fame.setText("");

	Toast.makeText(this, "Update Successed!", Toast.LENGTH_SHORT).show();

	}
	
@Override
public void onItemClick(AdapterView<?> parent, View view, int position,

long id) {



mCursor.moveToPosition(position);

BOOK_ID = mCursor.getInt(0);

title.setText(mCursor.getString(1));

fame.setText(mCursor.getString(2));



}

/*public class BooksListAdapter extends BaseAdapter {

private Context mContext;

private Cursor mCursor;



public BooksListAdapter(Context context, Cursor cursor) {



mContext = context;

mCursor = cursor;

}



@Override

public int getCount() {

return mCursor.getCount();

}



@Override

public Object getItem(int position) {

return null;

}



@Override

public long getItemId(int position) {

return 0;

}



@Override

public View getView(int position, View convertView, ViewGroup parent) {

TextView mTextView = new TextView(mContext);

mCursor.moveToPosition(position);

mTextView.setText(mCursor.getString(1) + "___"

+ mCursor.getString(2));

return mTextView;

}

}*/
//===============================从图库选择取图片===============================================	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case TAKE_PICTURE:
		
				Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/image.jpg");
				Bitmap newBitmap = ImageTools.zoomBitmap(bitmap, bitmap.getWidth() / SCALE, bitmap.getHeight() / SCALE);

				bitmap.recycle();
				
			
				createstory_iv_1.setImageBitmap(newBitmap);
				ImageTools.savePhotoToSDCard(newBitmap, Environment.getExternalStorageDirectory().getAbsolutePath(), String.valueOf(System.currentTimeMillis()));
				break;

			case CHOOSE_PICTURE:
				ContentResolver resolver = getContentResolver();
			
				Uri originalUri = data.getData(); 
	            try {

					Bitmap photo = MediaStore.Images.Media.getBitmap(resolver, originalUri);
					if (photo != null) {
						
						Bitmap smallBitmap = ImageTools.zoomBitmap(photo, photo.getWidth() / SCALE, photo.getHeight() / SCALE);

						photo.recycle();
						
						createstory_iv_1.setImageBitmap(smallBitmap);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}  
				break;
			
			default:
				break;
			}
		}
	}
//==========================从相机选择图片==================================================
//	public void showPicturePicker(Context context){
//		AlertDialog.Builder builder = new AlertDialog.Builder(context);
//		builder.setTitle("鍥剧墖鏉ユ簮");
//		builder.setNegativeButton("鍙栨秷", null);
//		builder.setItems(new String[]{"鎷嶇収","鐩稿唽"}, new DialogInterface.OnClickListener() {
//			
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				switch (which) {
//				case TAKE_PICTURE:
//					Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//					Uri imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"image.jpg"));
//
//					openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//					startActivityForResult(openCameraIntent, TAKE_PICTURE);
//					break;
//					
//				case CHOOSE_PICTURE:
//					Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
//					openAlbumIntent.setType("image/*");
//					startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
//					break;
//
//				default:
//					break;
//				}
//			}
//		});
//		builder.create().show();

//}
}