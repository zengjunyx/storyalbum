package com.qian.storyalbum;

 
   
 import java.util.ArrayList;  
 import java.util.HashMap;  
 import java.util.List;  
 import java.util.Map;  
   
 import android.app.Activity;  
import android.content.ContentResolver;
 import android.content.Context;  
import android.content.Intent;
 import android.database.Cursor;  
 import android.graphics.Bitmap;  
 import android.net.Uri;  
import android.os.Build;
 import android.os.Bundle;  
 import android.provider.MediaStore;  
 import android.provider.MediaStore.Images;  
import android.util.Log;
 import android.view.LayoutInflater;  
 import android.view.View;  
 import android.view.ViewGroup;  
 import android.widget.AdapterView;  
import android.widget.Button;
 import android.widget.CheckedTextView;  
 import android.widget.ImageView;  
 import android.widget.ListView;  
 import android.widget.SimpleAdapter;  
 import android.widget.TextView;  
import android.widget.AdapterView.OnItemClickListener;  
   
 public class GalleryActivity extends Activity {  
       
     private ListView lvImageList;  
       
     private String imageID= "imageID"; 
     public  final static  String LIST_KEY = "imagepath";
     private String imageName = "imageName";  
    private String imageInfo = "imageInfo"; 
    private Button sureButton;
    private ArrayList<String> thumbnailsImagepath = new ArrayList<String>(); 
     private ArrayList<String> fileNames = new ArrayList<String>(); 
     private ArrayList<String> imagepath = new ArrayList<String>();//zengjun add for path
  
     private String imagePathString;
     private MultipleChoiceImageListAdapter mAdapter;  
      
    /** Called when the activity is first created. */  
     @Override  
     public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
         setContentView(R.layout.gallery);  
           
         lvImageList=(ListView) this.findViewById(R.id.lvImageList);  
         lvImageList.setItemsCanFocus(false);
         sureButton=(Button) this.findViewById(R.id.sure);  
         sureButton.setOnClickListener(new Button.OnClickListener(){//创建监听对象
             @Override
                 public void onClick(View v){
            	 if(imagepath.isEmpty())//没有选中的图片
            	 {
            		  finish();
            	 }else
            	 {
            	 Intent list_intent = new Intent();
                 list_intent.putStringArrayListExtra(LIST_KEY, imagepath);
           
            	 //list_intent.putExtra(LIST_KEY, imagePathString);
                 list_intent.setClass(GalleryActivity.this, CreateStory.class);
                 startActivity(list_intent);
            	 }
             	}
               });
         lvImageList.setOnItemClickListener(new OnItemClickListener() {  
             @Override  
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {  
                   
                 CheckedTextView checkedTextView = (CheckedTextView) view.findViewById(R.id.itemChkImageInfo);  
                 checkedTextView.toggle();  
                mAdapter.setCheckItem(position, checkedTextView.isChecked());  
             }  
         });  
        
         try{  
             String[] from = {imageID, imageName, imageInfo};  
             int[] to = {R.id.itemImgImageInfo, R.id.itemChkImageInfo, R.id.itemTxtImageInfo};  
            mAdapter = new MultipleChoiceImageListAdapter(GalleryActivity.this, GetImageList(), R.layout.listitem, from, to);  
            lvImageList.setAdapter(mAdapter);  
         }  
         catch(Exception ex){  
             return;  
        }  
     }  
       
     //��ȡͼƬ�б�  
     private ArrayList<Map<String, String>> GetImageList(){  
           
         ArrayList<Map<String, String>> imageList = new ArrayList<Map<String,String>>();  
         HashMap<String, String> imageMap;  
           
         //��ȡSD��������ͼƬ  
        // Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;  
         Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI; 
         String[] projection = { MediaStore.Images.Media._ID, MediaStore.Images.Media.DISPLAY_NAME,MediaStore.Images.Media.DATA, MediaStore.Images.Media.SIZE};  
         String selection = MediaStore.Images.Media.MIME_TYPE + "=?";  
         String[] selectionArg ={"image/jpeg"};  
         Cursor mCursor = this.managedQuery(uri, projection, selection, selectionArg, MediaStore.Images.Media.DISPLAY_NAME);   
         imageList.clear();  
         if (mCursor != null) {   
             mCursor.moveToFirst();  
             while (mCursor.getPosition() != mCursor.getCount())   
             {   
                 imageMap= new HashMap<String, String>();  
                 imageMap.put(imageID, mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media._ID)));  
                 imageMap.put(imageName, mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)));  
               imageMap.put(imageInfo, " " + (mCursor.getLong(mCursor.getColumnIndex(MediaStore.Images.Media.SIZE))/1024)+"KB");  
                imageList.add(imageMap);  
                fileNames.add(mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA)));  //照片路径
               
                mCursor.moveToNext();     
             }   
              try //zengjun 修改返回报错 
                         {  
                             //4.0以上的版本会自动关闭 (4.0--14;; 4.0.3--15)  
                             if(Integer.parseInt(Build.VERSION.SDK) < 14)  
                             {  
                            	 mCursor.close();  
                             }  
                         }catch(Exception e)  
                         {  
                             Log.d("GalleryActivity", "error:" + e);  
                         }  
         }  
         return imageList;  
     }  
       
     //�ɶ�ѡͼƬ�б�������  
     class MultipleChoiceImageListAdapter extends SimpleAdapter {  
   
         private Map<Integer, Boolean> map;   
         private List<Integer> state;   
         private List<? extends Map<String, ?>> mList;  
           
         LayoutInflater mInflater;  
           
         public MultipleChoiceImageListAdapter(Context context, List<Map<String, String>> data, int resource, String[] from, int[] to) {  
             super(context, data, resource, from, to);  
             map = new HashMap<Integer, Boolean>();  
            mInflater = LayoutInflater.from(context);  
             mList = data;  
             for(int i = 0; i < data.size(); i++) {  
                 map.put(i, false);  
             }   
             state = new ArrayList<Integer>();  
        }  
        
         @Override  
         public int getCount() {  
            return mList.size();  
        }  
   
        @Override  
         public Object getItem(int position) {  
             return position;  
         }  
   
         @Override  
         public long getItemId(int position) {  
            return position;  
        }  
   
         //������Ŀѡ��״̬  
         public void setCheckItem(int position, Boolean isChecked){  
            map.put(position, isChecked);  
             if (state.contains(position))  
                 state.remove((Object)position);  
            if (isChecked){  
                 state.add(position);
                 imagepath.add(GetThumbnailsImagepath(position));//zengjun add  将选中的图片添件进数组 
             }  
         }  
         private String GetThumbnailsImagepath(int ThumbnailsIndex){  //zengjun add取略缩图路径
         //获取上下文
         Context ctx = GalleryActivity.this;
         //获取ContentResolver对象
         ContentResolver resolver = ctx.getContentResolver();
         //获得外部存储卡上的图片缩略图
         Cursor c = resolver.query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, null, null, null, null);
         //为了for循环性能优化，用一变量存储数据条数MediaStore.Images.Media.EXTERNAL_CONTENT_URI
         int totalCount = c.getCount();
         
         //将Cursor移动到第一位
         c.moveToFirst();
         //将缩略图数据添加到ArrayList中
         for(int i=0; i<totalCount; i++){
             int index = c.getColumnIndexOrThrow(MediaStore.Images.Thumbnails.DATA);
             String src = c.getString(index);
            // imagepath.add(src);//zengjun add
             thumbnailsImagepath.add(src);
             index = i;
             c.moveToNext();
         }
         return thumbnailsImagepath.get(ThumbnailsIndex);
         }
         //��ȡ�б�����ѡ����Ŀ  
         public long[] getCheckItemIds(){  
            int count = state.size();  
             long[] ids = new long[count];  
             for (int i = 0; i < count; i++) {  
                ids[i]= (long)state.get(i);  
             }  
             return ids;  
        }  
           
         @Override  
         public View getView(int position, View convertView, ViewGroup parent) {  
             if(convertView == null) {  
                convertView = mInflater.inflate(R.layout.listitem, null);  
            }  
              
            CheckedTextView checkedTextView = (CheckedTextView) convertView.findViewById(R.id.itemChkImageInfo);  
            checkedTextView.setChecked(map.get(position));   
            checkedTextView.setText((String)mList.get(position).get(imageName));  
              
            TextView textView = (TextView) convertView.findViewById(R.id.itemTxtImageInfo);  
             textView.setText((String)mList.get(position).get(imageInfo));  
               
             //设值 
             ImageView image = (ImageView) convertView.findViewById(R.id.itemImgImageInfo);  
             Bitmap bm = MediaStore.Images.Thumbnails.getThumbnail(getContentResolver(), Long.parseLong((String)mList.get(position).get(imageID)), Images.Thumbnails.MICRO_KIND, null);  
             image.setImageBitmap(bm);
            //      image.setImageURI(Uri.parse(GetThumbnailsImagepath(position)));      
               
             return convertView;  
         }  
     }  
 }  