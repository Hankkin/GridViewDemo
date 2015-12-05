package com.hankkin.GridViewDemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:gridview的Adapter
 * @author http://blog.csdn.net/finddreams
 */
public class MyGridAdapter extends BaseAdapter {
	private Context mContext;

	private List<String> data = new ArrayList<>();
	private OnCustomItemClickListener listener;
	private int clickPos=0;


	public MyGridAdapter(Context mContext,OnCustomItemClickListener listener) {
		super();
		this.listener = listener;
		this.mContext = mContext;
		for (int i=0;i<12;i++){
			data.add("item"+i);
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.grid_item, parent, false);
			holder.tv = (TextView) convertView.findViewById(R.id.tv_item);
			holder.itemImage = (ImageView) convertView.findViewById(R.id.ItemImage);
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (!TextUtils.isEmpty(data.get(position))){
			holder.tv.setText(data.get(position));
		}
		holder.itemImage.setOnTouchListener(onTouchListener);
		holder.itemImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				clickPos = position;
				listener.onCustomItemClk(clickPos);
			}
		});
		return convertView;
	}

	class ViewHolder{
		TextView tv;
		ImageView itemImage;
	}
	public View.OnTouchListener onTouchListener = new View.OnTouchListener() {
		@Override
		public boolean onTouch(View view, MotionEvent event) {
			switch (event.getAction()) {
				case MotionEvent.ACTION_UP:
					changeLight((ImageView) view, 0);
//                    listener.onCustomItemClk(listener.getPostion());
//                    view.getParent().requestDisallowInterceptTouchEvent(false);//通知父控件勿拦截本控件
					// onclick
					break;
				case MotionEvent.ACTION_DOWN:
					changeLight((ImageView) view, -80);
					break;
				case MotionEvent.ACTION_MOVE:
					// changeLight(view, 0);
					break;
				case MotionEvent.ACTION_CANCEL:
					changeLight((ImageView) view, 0);
					break;
				default:
					changeLight((ImageView) view, 0);
					break;
			}
			return false;
		}

	};

	private void changeLight(ImageView imageview, int brightness) {
		ColorMatrix matrix = new ColorMatrix();
		matrix.set(new float[] { 1, 0, 0, 0, brightness, 0, 1, 0, 0,
				brightness, 0, 0, 1, 0, brightness, 0, 0, 0, 1, 0 });
		imageview.setColorFilter(new ColorMatrixColorFilter(matrix));

	}
}
