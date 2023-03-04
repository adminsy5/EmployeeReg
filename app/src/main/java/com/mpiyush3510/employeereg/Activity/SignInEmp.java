package com.mpiyush3510.employeereg.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mpiyush3510.employeereg.DbHelper;
import com.mpiyush3510.employeereg.EmployeeList;
import com.mpiyush3510.employeereg.databinding.ActivitySignInEmpBinding;

public class SignInEmp extends AppCompatActivity {

    ActivitySignInEmpBinding binding;
    DbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignInEmpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db=new DbHelper(this);
        binding.BtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkValidation()){
                    signIn();
                }
            }
        });
    }
    private void signIn(){
        String email=binding.EmpEditEmail.getText().toString();
        String password = binding.EmpEditPasswd.getText().toString();

        Boolean chkEmailPassword=db.checkEmailPassword(email,password);
        if(chkEmailPassword){
            showToast("Welcome ❤️");
            Intent ig= new Intent(SignInEmp.this, EmployeeList.class);
            startActivity(ig);
        }else{
            showToast("Invalid Credential !");
        }
    }
    private Boolean checkValidation(){
        if(binding.EmpEditEmail.getText().toString().isEmpty()){
            showToast("Email can't be Empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.EmpEditEmail.getText().toString()).matches()) {
            showToast("Please Enter valid Email");
            return false;
        } else if (binding.EmpEditPasswd.getText().toString().isEmpty()) {
            showToast("Password can't be Empty");
            return false;
        }
        return true;
    }
    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}