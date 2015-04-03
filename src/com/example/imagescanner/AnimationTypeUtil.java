package com.example.imagescanner;

import android.app.Activity;

public class AnimationTypeUtil {
	/**
	 * @author darkhorse_pxf
	 * @date 2014-10-22
	 * @param activity
	 */
	/*
	 * 从右边滑入
	 */
	public static void sliteFromRightOutToLeft(Activity activity){
		activity.overridePendingTransition(R.anim.tsl_slite_in_from_right , R.anim.tsl_slite_out_to_left);	
	}
	public static void sliteFromRightOutToUp(Activity activity){
		activity.overridePendingTransition(R.anim.tsl_slite_in_from_right, R.anim.tsl_slite_out_to_up);
	}
	public static void sliteFromRightOutToButtom(Activity activity){
		activity.overridePendingTransition(R.anim.tsl_slite_in_from_right, R.anim.tsl_slite_out_to_buttom);
	}
	/*
	 * 从左边滑入
	 */
	public static void sliteFromLeftOutToRight(Activity activity){
		activity.overridePendingTransition(R.anim.tsl_slite_in_from_left, R.anim.tsl_slite_out_to_right);
	}
	public static void sliteFromLeftOutToUp(Activity activity){
		activity.overridePendingTransition(R.anim.tsl_slite_in_from_left, R.anim.tsl_slite_out_to_up);
	}
	public static void sliteFromLeftOutToButtom(Activity activity){
		activity.overridePendingTransition(R.anim.tsl_slite_in_from_left, R.anim.tsl_slite_out_to_buttom);
	}
	/*
	 * 从上面滑入
	 */
	public static void sliteFromUpOutToRight(Activity activity){
		activity.overridePendingTransition(R.anim.tsl_slite_in_from_up, R.anim.tsl_slite_out_to_right);
	}
	public static void sliteFromUpOutToButtom(Activity activity){
		activity.overridePendingTransition(R.anim.tsl_slite_in_from_up, R.anim.tsl_slite_out_to_buttom);
	}
	public static void sliteFromUpOutToleft(Activity activity){
		activity.overridePendingTransition(R.anim.tsl_slite_in_from_up, R.anim.tsl_slite_out_to_left);
	}
	/*
	 * 从下面滑入
	 */
	public static void sliteFromButtomOutToRight(Activity activity){
		activity.overridePendingTransition(R.anim.tsl_slite_in_from_buttom, R.anim.tsl_slite_out_to_right);
	}
	public static void sliteFromButtomOutToLeft(Activity activity){
		activity.overridePendingTransition(R.anim.tsl_slite_in_from_buttom, R.anim.tsl_slite_out_to_left);
	}
	public static void sliteFromButtomOutToUp(Activity activity){
		activity.overridePendingTransition(R.anim.tsl_slite_in_from_buttom, R.anim.tsl_slite_out_to_up);
	}
	/*
	 * 淡入淡出
	 */
	public static void fadeInFadeOut(Activity activity) {
		activity.overridePendingTransition(R.anim.alpha_fade_in, R.anim.alpha_fade_out);
	}
	/*interpolator设置动画进出快慢顺序：
	 * @android:anim/accelerate_interpolator： 越来越快

       @android:anim/decelerate_interpolator：越来越慢

       @android:anim/accelerate_decelerate_interpolator：先快后慢

       @android:anim/anticipate_interpolator: 先后退一小步然后向前加速

       @android:anim/overshoot_interpolator：快速到达终点超出一小步然后回到终点

       @android:anim/anticipate_overshoot_interpolator：到达终点超出一小步然后回到终点

       @android:anim/bounce_interpolator：到达终点产生弹球效果，弹几下回到终点

       @android:anim/linear_interpolator：均匀速度。
	 */
	
	/*
	 * anim种类：
	 * translate：滑动
	 * alpha：淡入淡出
	 * rotate：旋转
	 * scale：缩放/放大
	 */

}
