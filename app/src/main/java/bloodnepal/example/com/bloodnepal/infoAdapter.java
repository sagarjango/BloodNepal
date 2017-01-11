package bloodnepal.example.com.bloodnepal;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Lenovo on 08/03/2016.
 */
public class infoAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private Context context;
	String[] name, address, bloodgrp;
	List<row> rowItem;

	infoAdapter(Context context, List<row> listItem) {
		this.context = context;
		rowItem = listItem;
	}

	@Override
	public int getCount() {
		Log.d("count", "count");
		return rowItem.size();
	}

	@Override
	public Object getItem(int arg0) {
		Log.d("item", "item");
		return rowItem.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		Log.d("const", "item id");
		return rowItem.indexOf(getItem(position));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			mInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.info, parent, false);
		}
		row rowitem = (row) getItem(position);
		TextView tv = (TextView) convertView.findViewById(R.id.textView2);
		TextView tv1 = (TextView) convertView.findViewById(R.id.textView1);
		TextView tv2 = (TextView) convertView.findViewById(R.id.textView3);

		tv1.setText(rowitem.getname());
		tv.setText(rowitem.getaddress());
		tv2.setText(rowitem.getblood());
		return convertView;
	}

}
