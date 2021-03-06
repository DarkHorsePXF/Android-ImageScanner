package com.example.imagescanner.util;

import java.io.FileDescriptor;
import java.io.FileInputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtil {
	/** 
	 * get Bitmap 
	 *  
	 * @param imgFile 
	 * @param minSideLength 
	 * @param maxNumOfPixels 
	 * @return 
	 */  
	public static Bitmap tryGetBitmap(String imgFile, int minSideLength,  
	        int maxNumOfPixels) {  
	    if (imgFile == null || imgFile.length() == 0)  
	        return null;  
	  
	    try {  
	        FileDescriptor fd = new FileInputStream(imgFile).getFD();  
	        BitmapFactory.Options options = new BitmapFactory.Options();  
	        options.inJustDecodeBounds = true;  
	        // BitmapFactory.decodeFile(imgFile, options);  
	        BitmapFactory.decodeFileDescriptor(fd, null, options);  
	  
	        options.inSampleSize = computeSampleSize(options, minSideLength,  
	                maxNumOfPixels);  
	        try {  
	            // 这里一定要将其设置回false，因为之前我们将其设置成了true  
	            // 设置inJustDecodeBounds为true后，decodeFile并不分配空间，即，BitmapFactory解码出来的Bitmap为Null,但可计算出原始图片的长度和宽度  
	            options.inJustDecodeBounds = false;  
	  
	            Bitmap bmp = BitmapFactory.decodeFile(imgFile, options);  
	            return bmp == null ? null : bmp;  
	        } catch (OutOfMemoryError err) {  
	            return null;  
	        }  
	    } catch (Exception e) {  
	        return null;  
	    }  
	}  
	/** 
	 * compute Sample Size 
	 *  
	 * @param options 
	 * @param minSideLength 
	 * @param maxNumOfPixels 
	 * @return 
	 */  
	public static int computeSampleSize(BitmapFactory.Options options,  
	        int minSideLength, int maxNumOfPixels) {  
	    int initialSize = computeInitialSampleSize(options, minSideLength,  
	            maxNumOfPixels);  
	  
	    int roundedSize;  
	    if (initialSize <= 8) {  
	        roundedSize = 1;  
	        while (roundedSize < initialSize) {  
	            roundedSize <<= 1;  
	        }  
	    } else {  
	        roundedSize = (initialSize + 7) / 8 * 8;  
	    }  
	  
	    return roundedSize;  
	}  
	  
	/** 
	 * compute Initial Sample Size 
	 *  
	 * @param options 
	 * @param minSideLength 
	 * @param maxNumOfPixels 
	 * @return 
	 */  
	private static int computeInitialSampleSize(BitmapFactory.Options options,  
	        int minSideLength, int maxNumOfPixels) {  
	    double w = options.outWidth;  
	    double h = options.outHeight;  
	  
	    // 上下限范围  
	    int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math  
	            .sqrt(w * h / maxNumOfPixels));  
	    int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(  
	            Math.floor(w / minSideLength), Math.floor(h / minSideLength));  
	  
	    if (upperBound < lowerBound) {  
	        // return the larger one when there is no overlapping zone.  
	        return lowerBound;  
	    }  
	  
	    if ((maxNumOfPixels == -1) && (minSideLength == -1)) {  
	        return 1;  
	    } else if (minSideLength == -1) {  
	        return lowerBound;  
	    } else {  
	        return upperBound;  
	    }  
	}  
}
