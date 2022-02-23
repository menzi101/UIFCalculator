package com.example.payfastexam;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private EditText mTotalIncome;
    private EditText mBenefits;
    private EditText mUIFExclusion;
    private Button mButtonCalculate;
    private TextView mEmployeeContribution;
    private TextView mEmployerContribution;
    private TextView mTotalUIF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    public void onClick(View view) {
        mTotalIncome = findViewById(R.id.totalIncome);
        mBenefits = findViewById(R.id.enterBenefits);
        mUIFExclusion = findViewById(R.id.editTextNumber3);
        mEmployeeContribution = findViewById(R.id.employeeAns);
        mEmployerContribution = findViewById(R.id.employerContAns);
        mTotalUIF = findViewById(R.id.totEmpCont);
        mButtonCalculate = findViewById(R.id.button);

        if (mBenefits.getText().toString().length() == 0){
            mBenefits.setText("0");
        }
        if (mUIFExclusion.getText().toString().length() == 0){
            mUIFExclusion.setText("0");
        }
        if (mTotalIncome.getText().toString().length() == 0){
            mTotalIncome.setText("0");
        }

        int totalIncome = Integer.parseInt(mTotalIncome.getText().toString());
        int benefits = Integer.parseInt(mBenefits.getText().toString());
        int uifEx = Integer.parseInt((mUIFExclusion.getText().toString()));

        double totalUIF = (totalIncome + uifEx - benefits)*0.02;
        double empCont = totalUIF/2;

        mTotalUIF.setText(String.valueOf(totalUIF));
        mEmployeeContribution.setText(String.valueOf(empCont));
        mEmployerContribution.setText(String.valueOf(empCont));
    }
}