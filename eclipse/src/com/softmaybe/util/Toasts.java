package com.softmaybe.util;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.softmaybe.R;

public final class Toasts {

	private Toasts() {}
	
	public static void slow(Activity context, String message) {
		Toast toast = Toast.makeText(context, "", Toast.LENGTH_LONG);
		
		LayoutInflater inflater = context.getLayoutInflater();
		View layout = inflater.inflate(R.layout.toast_layout,
		             (ViewGroup) context.findViewById(R.id.toast_layout_root));
		TextView text = (TextView) layout.findViewById(R.id.text);
		text.setText(message);
		
		toast.setGravity(Gravity.CENTER, 0 /* xOffset */, 0 /* yOffset */);
		toast.setView(layout);
		toast.show();
	}
}
