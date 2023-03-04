package com.mpiyush3510.employeereg.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mpiyush3510.employeereg.R;
import com.mpiyush3510.employeereg.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Bundle bundle=getIntent().getExtras();
        final String name=bundle.getString("empName");
        final String email=bundle.getString("empEmail");
        final String gender=bundle.getString("empGender");
        final String course=bundle.getString("empCourse");
        final String jdate=bundle.getString("empJdate");
        final String mno=bundle.getString("empMno");
        final String designation=bundle.getString("empDesignation");

        binding.TextEmpName.setText(String.valueOf(name));
        binding.TextEmpEmail.setText(String.valueOf(email));
        binding.TextEmpGender.setText(String.valueOf(gender));
        binding.TextEmpCourse.setText(String.valueOf(course));
        binding.TextEmpJdate.setText(String.valueOf(jdate));
        binding.TextEmpMno.setText(String.valueOf(mno));
        binding.TextEmpDesignation.setText(String.valueOf(designation));

        binding.BtnWantSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ig=new Intent(MainActivity.this, SignInEmp.class);
                Toast.makeText(MainActivity.this, "Please Sign In Here !", Toast.LENGTH_LONG).show();
                startActivity(ig);
            }
        });
    }
}