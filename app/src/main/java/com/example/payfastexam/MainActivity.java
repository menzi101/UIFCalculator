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
        mTotalIncome = findViewById(R.id.totalIncome); // total income text edit
        mBenefits = findViewById(R.id.enterBenefits); // benefits text edit
        mUIFExclusion = findViewById(R.id.editTextNumber3); // UIF exclussion text edit
        mEmployeeContribution = findViewById(R.id.employeeAns); // employee contribution text view
        mEmployerContribution = findViewById(R.id.employerContAns); // employer contribution text view
        mTotalUIF = findViewById(R.id.totEmpCont); // Total UIF contributions
        mButtonCalculate = findViewById(R.id.button);

        if (mBenefits.getText().toString().length() == 0){ // if user leaves benefits blank it sets it to zero
            mBenefits.setText("0");
        }
        if (mUIFExclusion.getText().toString().length() == 0){// if user leaves UIFExclusions blank it sets it to zero
            mUIFExclusion.setText("0");
        }
        if (mTotalIncome.getText().toString().length() == 0){// if user leaves total income blank it sets it to zero
            mTotalIncome.setText("0");
        }

        int totalIncome = Integer.parseInt(mTotalIncome.getText().toString()); // stores 'total Income' user input in totalIncome Variable
        int benefits = Integer.parseInt(mBenefits.getText().toString()); // stores 'benefits' user input in benefits Variable
        int uifEx = Integer.parseInt((mUIFExclusion.getText().toString())); // stores 'UIF exclusions' user input in uif Exclusions Variable

        double uifRem = totalIncome + uifEx - benefits; // stores the 'UIF Remunerations' in uifRem variable
        int limitUIF = 14872; // The 2012 UIF Ceiling, the June 2021 is R 17 712

        if (uifRem > limitUIF){ // if the employee earns more than the ceiling amount, the contribution is calculated using the maximum earnings ceiling amount.

            uifRem = 14872; // Set to the maximum earnings ceiling amaount if Income exceeds the maximum earnings ceiling amount.
            double totalUIF = (uifRem)*0.02; // The algorithm used to calculate the totalUIF payment
            double empCont = totalUIF/2; // The algorithm used to calculate the both the employee and the employer contributions

            mTotalUIF.setText(String.valueOf(totalUIF)); // set the textView to total UIF Contributions
            mEmployeeContribution.setText(String.valueOf(empCont)); // set the textView to employee UIF Contributions
            mEmployerContribution.setText(String.valueOf(empCont)); // set the textView to employer UIF Contributions
        }else
            {// When the emplloyee earns below the service amount
            double totalUIF = (uifRem)*0.02; // The algorithm used to calculate the totalUIF payment
            double empCont = totalUIF/2; // The algorithm used to calculate the both the employee and the employer contributions

            mTotalUIF.setText(String.valueOf(totalUIF));// set the textView to total UIF Contributions
            mEmployeeContribution.setText(String.valueOf(empCont)); // set the textView to employee UIF Contributions
            mEmployerContribution.setText(String.valueOf(empCont));} // set the textView to employer UIF Contributions
    }
}