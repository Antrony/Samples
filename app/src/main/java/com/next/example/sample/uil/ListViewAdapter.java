package com.next.example.sample.uil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.next.example.sample.R;
import com.next.example.sample.bal.Bean;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter{
	private ArrayList<Bean> employeelist = new ArrayList<Bean>();
	Context context;
	private static LayoutInflater inflater=null;
	public ListViewAdapter(Context context,ArrayList<Bean> employeelist) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.employeelist=employeelist;
		inflater = ( LayoutInflater )context.
				getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return employeelist.size();
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

	public class Holder
	{
		TextView txtV_first,txtV_last,txtV_team,txtV_username;
		ImageView img;
	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder holder=new Holder();
		View rowView;
		rowView = inflater.inflate(R.layout.listview_item, null);
		holder.txtV_first=(TextView) rowView.findViewById(R.id.txtV_first);
		holder.txtV_last=(TextView) rowView.findViewById(R.id.txtV_last);
		holder.txtV_team=(TextView) rowView.findViewById(R.id.txtV_team);
		holder.txtV_username=(TextView) rowView.findViewById(R.id.txtV_username);
		holder.txtV_first.setText(employeelist.get(position).getfName());
		holder.txtV_last.setText(employeelist.get(position).getlName());
		holder.txtV_team.setText(employeelist.get(position).getTeam());
		holder.txtV_username.setText(employeelist.get(position).getuName());

		rowView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "You Clicked "+employeelist.get(position), Toast.LENGTH_LONG).show();
			}
		});
		return rowView;
	}

}