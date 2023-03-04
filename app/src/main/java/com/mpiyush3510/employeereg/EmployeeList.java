package com.mpiyush3510.employeereg;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.mpiyush3510.employeereg.databinding.ActivityEmployeeListBinding;

public class EmployeeList extends AppCompatActivity {
ActivityEmployeeListBinding binding;
int empImg[]={R.drawable.admin_sir,R.drawable.anamika_saini,R.drawable.anju,R.drawable.hitixa_patel,R.drawable.kd,R.drawable.nimi_jagid,R.drawable.noor_jahan,R.drawable.saima_khanjpg,R.drawable.sheetal_sinha};
String empName[]={"Piyush Makwana","Anamika Saini","Anjali Parmar","Hitixa Patel","Rajat Kevat","Nimi Jagid","Noor Jahan","Saima Khan","Sheetal Sinha"};
String empMail[]={"piyush@sutexian.in","anamika@sutexian.in","anjali@sutexian.in","hitixa@sutexian.in","rajat@sutexian.in","nimi@sutexian.in","noor@sutexian.in","saima@sutexian.in","sheetal@sutexian.in"};
String empMno[]={"+91 79907 64102","+91 65464 35363","+91 96463 24433","+91 90860 45773","+91 90904 45663","+91 90768 57755","+91 96454 67866","+91 89785 46474","+91 98677 67765"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEmployeeListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        empListAdapter empListAdapter= new empListAdapter(getApplicationContext(),empImg,empName,empMail,empMno);
        binding.lts1.setAdapter(empListAdapter);

    }
}