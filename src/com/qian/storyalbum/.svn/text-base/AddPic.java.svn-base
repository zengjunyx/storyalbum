package com.qian.storyalbum;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

public class AddPic extends Activity {
protected static final int RESULT_LOAD_IMAGE = 0;
private TextView addpic_tx_3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addpic);
		
		addpic_tx_3=(TextView)this.findViewById(R.id.addpic_tx_3);
		addpic_tx_3.setOnClickListener(new OnClickListener() {
			@Override  
            public void onClick(View arg0) {  
				//startActivity(new Intent(AddPic.this,CreateStory.class));
				 startActivity(new Intent(AddPic.this,GalleryActivity.class));
				finish();
//                Intent i = new Intent(  
//                        Intent.ACTION_PICK,  
//                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);  
//   
//                startActivityForResult(i, RESULT_LOAD_IMAGE);  
            }  
		});


	}
//	  @Override  
//	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
//	        super.onActivityResult(requestCode, resultCode, data);  
//	   
//	        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {  
//	            Uri selectedImage = data.getData();  
//	            String[] filePathColumn = { MediaStore.Images.Media.DATA };  
//	   
//	            Cursor cursor = getContentResolver().query(selectedImage,  
//	                    filePathColumn, null, null, null);  
//	            cursor.moveToFirst();  
//	   
//	            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);  
//	            String picturePath = cursor.getString(columnIndex);  
//	            cursor.close();  
//	            startActivity(new Intent(AddPic.this,CreateStory.class));
// 
//	   
//	        }  
//	   
//
//	    }
}
	  
