package com.mpiyush3510.employeereg;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.mpiyush3510.employeereg.databinding.ActivitySignUpEmpBinding;

public class SignUpEmp extends AppCompatActivity {
ActivitySignUpEmpBinding binding;
String[] Designation={"Developer","Engineer"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpEmpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AdminListeners();
        binding.hyperLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://sutexianinc.w3spaces.com/index.html";
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
        binding.BtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        // ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Designation);
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,R.layout.spinner_item,Designation);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spin1.setAdapter(arrayAdapter);
    }
    private void AdminListeners(){
        DatePicker();
    }
    private void DatePicker() {
        MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        binding.imgCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(),"Material_Date_Picker");
                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        binding.EmpJoinDate.setText(materialDatePicker.getHeaderText());
                    }
                });
            }
        });
    }
}