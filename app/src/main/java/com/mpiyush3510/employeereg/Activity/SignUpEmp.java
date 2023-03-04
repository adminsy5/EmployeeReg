package com.mpiyush3510.employeereg.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.mpiyush3510.employeereg.DbHelper;
import com.mpiyush3510.employeereg.R;
import com.mpiyush3510.employeereg.databinding.ActivitySignUpEmpBinding;

public class SignUpEmp extends AppCompatActivity {
ActivitySignUpEmpBinding binding;
DbHelper db;
String[] Designation={"Developer","Engineer"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpEmpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db=new DbHelper(this);
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
                    signUp();
                }
            }
        });

        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this, R.layout.spinner_item,Designation);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spin1.setAdapter(arrayAdapter);
        binding.SignInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ig = new Intent(SignUpEmp.this, SignInEmp.class);
                custAlert1();
                Runnable runnable= new Runnable() {
                    @Override
                    public void run() {
                        startActivity(ig);
                    }
                };
                Handler handler=new Handler();
                handler.postDelayed(runnable,1500);
            }
        });
    }
    private void custAlert1(){
        MaterialAlertDialogBuilder builder= new MaterialAlertDialogBuilder(this)
                .setTitle("User Notice")
                .setMessage("Before sign In you need to Sign Up in Application, If you Already Sign up in App then forgot this message")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
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
    private void signUp() {
        String email=binding.EmpEditEmail.getText().toString();
        String password=binding.EmpEditPasswd.getText().toString();
        boolean checkEmail=db.checkEmail(email);
        if(!checkEmail){
            boolean insert=db.insertData(email,password);
            if(insert){
                Toast.makeText(SignUpEmp.this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();
                showExplicitData();
            }else
                showToast("Can't Sign Up");
        }else
            showToast("You Have Already Account ! Sign In Please👍");
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
        }
        return true;
    }

    private void showExplicitData(){
        String gender=((RadioButton)findViewById(binding.rdgGender.getCheckedRadioButtonId())).getText().toString();
        String course=((RadioButton)findViewById(binding.rdgCourse.getCheckedRadioButtonId())).getText().toString();

        Intent ig=new Intent(SignUpEmp.this, MainActivity.class);
        ig.putExtra("empName",binding.EmpEditName.getText().toString());
        ig.putExtra("empEmail",binding.EmpEditEmail.getText().toString());
        ig.putExtra("empGender",gender);
        ig.putExtra("empCourse",course);
        ig.putExtra("empJdate",binding.EmpJoinDate.getText().toString());
        ig.putExtra("empMno",binding.EmpEditMno.getText().toString());
        ig.putExtra("empDesignation",binding.spin1.getSelectedItem().toString());
        startActivity(ig);
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