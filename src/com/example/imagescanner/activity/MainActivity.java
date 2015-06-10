package com.example.imagescanner.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.imagescanner.R;
import com.example.imagescanner.adapter.ImageLoaderAdapter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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
	static ArrayList<HashMap<String, Object>> picList;
	Handler handler;
	AlertDialog showImageAlert;
	

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
		
		gvImages=(GridView) findViewById(R.id.gvImages);
		gvImages.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				showImage(position);
			}

			private void showImage(int position) {
				Intent intent=new Intent(context, ShowImageActivity.class);
				intent.putExtra(ShowImageActivity.EXTRA_IMAGE_INDEX, position);
				startActivity(intent);
			}
		});
		
		Adapter adapter=new ImageLoaderAdapter(context,picList);
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
	
	
	
	
}
