package com.mpiyush3510.employeereg;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.mpiyush3510.employeereg.databinding.ActivitySignUpEmpBinding;

public class SignUpEmp extends AppCompatActivity {
ActivitySignUpEmpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignUpEmpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AdminListeners();
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