package com.mpiyush3510.employeereg;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
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
                if(checkValidation()){
                    //Toast.makeText(SignUpEmp.this, "Very Good", Toast.LENGTH_SHORT).show();
                }
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

    private void showToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
    private Boolean checkValidation(){
        if(binding.EmpEditName.getText().toString().isEmpty()){
            showToast("Please Enter Employee Name !");
            return false;
        }else if(binding.EmpEditEmail.getText().toString().isEmpty()){
            showToast("Email Can't be empty !");
            return false;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(binding.EmpEditEmail.getText().toString()).matches()){
            showToast("Please enter Valid Email !");
            return false;
        } else if (!binding.empEditmale.isChecked() && !binding.empEditFemale.isChecked()) {
            showToast("Please Choose Gender !");
            return false;
        } else if (binding.EmpJoinDate.getText().toString().isEmpty()) {
            showToast("Please Select Jdate !");
            return false;
        } else if (binding.EmpEditMno.getText().toString().isEmpty()) {
            showToast("Please Add Your MobileNo !");
            return false;
        } else if (!Patterns.PHONE.matcher(binding.EmpEditMno.getText().toString()).matches()) {
            showToast("Please enter Correct PhoneNo !");
            return false;
        } else if (!binding.empEditBcom.isChecked() && !binding.empEditBca.isChecked() && !binding.empEditBba.isChecked()) {
            showToast("Choose Your Course !");
            return false;
        } else if (binding.EmpEditPasswd.getText().toString().isEmpty()) {
            showToast("Please Enter Password");
            return false;
        } else if (binding.EmpEditPasswd.getText().toString().matches(binding.EmpEditEmail.getText().toString()) ||
                binding.EmpEditPasswd.getText().toString().matches(binding.EmpEditMno.getText().toString()) ||
                binding.EmpEditPasswd.getText().toString().matches(binding.EmpEditName.getText().toString()) ) {
            showToast(" Try Again !");
            custAlert();
            return false;
        }else if (!binding.EmpCheckTerms.isChecked()){
            showToast("Please accept terms & Condition !");
            return false;
    }else{
            showToast("Good");
        }
        return true;
    }

    private void custAlert(){
        MaterialAlertDialogBuilder builder=new MaterialAlertDialogBuilder(this)
                .setTitle("Error")
                .setMessage(R.string.dialogue_mess)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showToast("Developer : Piyush Makwana ⭐");
                        dialog.dismiss();
                    }
                });
        builder.create();
        builder.show();
    }
}