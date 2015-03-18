package com.example.imagescanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;
import android.provider.MediaStore.Images;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

public class MainActivity extends ActionBarActivity {
	GridView gvImages;
	Context context;
	ArrayList<HashMap<String, Object>> picList;
	Handler handler;
	
	ImageLoader imageLoader=ImageLoader.getInstance(); //step 1:get ImageLoader

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initData();
		
	}

	private void initData() {
		context=this;
		picList=new ArrayList<>();
		getImagesList();
		
		imageLoader.init(ImageLoaderConfiguration	//step 2:init the ImageLoader
				.createDefault(context));			
		
		
		gvImages=(GridView) findViewById(R.id.gvImages);
		gvImages.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				showImage(view,position);
			}

			private void showImage(View view, int position) {
				String path=picList.get(position).get("path").toString();
				Bitmap bitmap=BitmapFactory.decodeFile(path);
				((ImageView)view).setImageBitmap(bitmap);
			}
		});
		/*handler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				if(msg.what==ImagesAdapter.REFRESH_IMAGE){
					ImageView iv=((ViewHolder)msg.obj).iv;
					Bitmap bm=((ViewHolder)msg.obj).bm;
					iv.setImageBitmap(bm);
				}
			}
		};
		*/
		Adapter adapter=new ImagesAdapter(context,picList);
		gvImages.setAdapter((ListAdapter) adapter);
		
	}

	private void getImagesList() {   //get images data
		Cursor cursor=getContentResolver()
				.query(Images.Media.EXTERNAL_CONTENT_URI  	//you have to give permission
						, null, null, null, null);			//at AndroidManifest
		while(cursor.moveToNext()){
			String name=cursor.getString(
					cursor.getColumnIndex(Media.DISPLAY_NAME));  //get file name
			String path=cursor.getString(
					cursor.getColumnIndex(MediaStore.Images.Media.DATA)); //get file path
			HashMap<String, Object> map=new HashMap<String, Object>();
			map.put("name", name==null?"":name);
			map.put("path", path==null?"":path);
			picList.add(map);
		}
		cursor.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public class ImagesAdapter extends BaseAdapter implements Adapter {
		//ExecutorService executorService=Executors.newFixedThreadPool(2);
		Context mContext;
		ArrayList<HashMap<String, Object>> picList;
		ViewHolder holder;
		public static final int REFRESH_IMAGE=0x123;
		
		
		
		
		
		DisplayImageOptions options;
		
		public ImagesAdapter(Context context,ArrayList<HashMap<String, Object>> picList) {
			
			options=new DisplayImageOptions.Builder()	//step3:set options of ImageLoader
			.cacheInMemory(true)	
			.cacheOnDisk(true)
			.considerExifParams(true)
			.showImageOnLoading(R.drawable.ic_stub)
			.showImageOnFail(R.drawable.ic_error)
			.showImageForEmptyUri(R.drawable.ic_empty)
			.build();
			
			
			this.mContext=context;
			this.picList=picList;
		}
		@Override
		public int getCount() {
			return picList.size();
		}

		@Override
		public Object getItem(int position) {
			return picList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView==null){
				convertView=LayoutInflater.from(mContext).inflate(R.layout.layout_image_content, null);
				holder=new ViewHolder();
				holder.iv=(ImageView) convertView.findViewById(R.id.ivImage);
				convertView.setTag(holder);
			}
			final String path=(String) picList.get(position).get("path");
			holder=(ViewHolder) convertView.getTag();
			holder.iv.setImageResource(R.drawable.ic_launcher);
			
			/*ignore the fail code
			final int pst=position;
			try {
				executorService.execute(new Runnable() {
					
					@Override
					public void run() {
						Bitmap bitmap=BitmapUtil.tryGetBitmap(path, 100000, 100000);
						holder.bm=bitmap;
						Message msg=new Message();
						msg.what=REFRESH_IMAGE;
						msg.arg1=pst;
						msg.obj=holder;
						handler.sendMessage(msg);
					}
				});
				System.out.println(path);
			} catch (OutOfMemoryError e) {
				
				AlertUtil.alert(mContext, "内存爆啦~~~加载失败！");
			}
			*/
			imageLoader.displayImage(
					"file:/"+path, 	//URL:	local files-->"file:/"
									//		Internet files-->"http://"
									//		contentProvider-->"content:/"
									//		drawable files-->"drawable:/"
									
					holder.iv, 		//display on this imageView 
					options);		//displayed options
			return convertView;
		}
		class ViewHolder{
			ImageView iv;
			Bitmap bm;
		}

	}
	
	
}
