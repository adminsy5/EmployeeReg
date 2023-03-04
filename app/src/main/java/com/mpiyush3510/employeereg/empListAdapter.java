package com.mpiyush3510.employeereg;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class empListAdapter extends BaseAdapter {
    Context context;
    int[] empImg;
    String[] empName;
    String[] empMail;
    String[] empMno;
    LayoutInflater inflater;

    public empListAdapter(Context context, int[] empImg, String[] empName, String[] empMail, String[] empMno) {
        this.context = context;
        this.empImg = empImg;
        this.empName = empName;
        this.empMail = empMail;
        this.empMno = empMno;
        inflater=(LayoutInflater.from(context.getApplicationContext()));
    }

    @Override
    public int getCount() {
        return empName.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.emp_listview, null);
        TextView name = (TextView) convertView.findViewById(R.id.TextEmpName);
        TextView email=(TextView)  convertView.findViewById(R.id.TextEmpEmail);
        TextView mno=(TextView) convertView.findViewById(R.id.TextEmpMno);
        ImageView img=(ImageView) convertView.findViewById(R.id.empPhoto);
        name.setText(empName[position]);
        email.setText(empMail[position]);
        mno.setText(empMno[position]);
        img.setImageResource(empImg[position]);
        return convertView;
    }
}
