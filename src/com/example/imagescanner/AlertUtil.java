package com.example.imagescanner;

import android.content.Context;
import android.widget.Toast;

public class AlertUtil {
	public static void alert(Context context,String string){
		Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
	}
}
