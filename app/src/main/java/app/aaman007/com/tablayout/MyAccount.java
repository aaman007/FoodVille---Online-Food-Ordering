package app.aaman007.com.tablayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.HashMap;

public class MyAccount extends AppCompatActivity {

    private EditText fullNameInfo , emailInfo , addressInfo , genderInfo , dateOfBirthInfo , ageInfo , phoneInfo;
    private Button updateDetails;
    public static final HashMap<String,UserDetails>detailsData = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        this.setTitle("My Account");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Getting Views
        fullNameInfo = findViewById(R.id.fullnameInfoId);
        emailInfo = findViewById(R.id.emailInfoId);
        addressInfo = findViewById(R.id.addressInfoId);
        updateDetails = findViewById(R.id.updateDetailsId);
        genderInfo = findViewById(R.id.genderInfoId);
        dateOfBirthInfo = findViewById(R.id.dateOfBirthInfoId);
        ageInfo = findViewById(R.id.ageInfoId);
        phoneInfo = findViewById(R.id.phoneInfoId);


        // Setting Data
        setData();

        // Listener for EditTexts
        fullNameInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullNameInfo.setFocusableInTouchMode(true);
            }
        });
        emailInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailInfo.setFocusableInTouchMode(true);

            }
        });
        addressInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressInfo.setFocusableInTouchMode(true);
            }
        });
        ageInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ageInfo.setFocusableInTouchMode(true);
            }
        });
        dateOfBirthInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateOfBirthInfo.setFocusableInTouchMode(true);
            }
        });
        genderInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genderInfo.setFocusableInTouchMode(true);
            }
        });
        phoneInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneInfo.setFocusableInTouchMode(true);
            }
        });

        /// Listener for Update
        updateDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserDetails temp = detailsData.get(logIn.userName);

                temp.setFullName(fullNameInfo.getText().toString());
                temp.setAddress(addressInfo.getText().toString());
                temp.setEmail(emailInfo.getText().toString());
                temp.setAge(ageInfo.getText().toString());
                temp.setDateOfBirth(dateOfBirthInfo.getText().toString());
                temp.setGender(genderInfo.getText().toString());
                temp.setPhoneNumber(phoneInfo.getText().toString());

                Toast.makeText(MyAccount.this,"Update Succesfull!!",Toast.LENGTH_SHORT).show();

                disableEditTextsFocusable();
            }
        });
    }

    // Disable All EditTexts
    private void disableEditTexts() {
        fullNameInfo.setEnabled(false);
        ageInfo.setEnabled(false);
        addressInfo.setEnabled(false);
        emailInfo.setEnabled(false);
        dateOfBirthInfo.setEnabled(false);
        genderInfo.setEnabled(false);
        phoneInfo.setEnabled(false);
    }
    // Disable all editext Focusable
    private void disableEditTextsFocusable() {
        fullNameInfo.setFocusable(false);
        ageInfo.setFocusable(false);
        addressInfo.setFocusable(false);
        emailInfo.setFocusable(false);
        dateOfBirthInfo.setFocusable(false);
        genderInfo.setFocusable(false);
        phoneInfo.setFocusable(false);
    }
    // Enable all EditTexts
    private void enableEditTexts() {
        fullNameInfo.setEnabled(true);
        ageInfo.setEnabled(true);
        addressInfo.setEnabled(true);
        emailInfo.setEnabled(true);
        dateOfBirthInfo.setEnabled(true);
        genderInfo.setEnabled(true);
        phoneInfo.setEnabled(true);
    }


    void setData()
    {
        fullNameInfo.setText(detailsData.get(logIn.userName).getFullName());
        addressInfo.setText(detailsData.get(logIn.userName).getAddress());
        emailInfo.setText(detailsData.get(logIn.userName).getEmail());
        genderInfo.setText(detailsData.get(logIn.userName).getGender());
        dateOfBirthInfo.setText(detailsData.get(logIn.userName).getDateOfBirth());
        ageInfo.setText(detailsData.get(logIn.userName).getAge());
        phoneInfo.setText(detailsData.get(logIn.userName).getPhoneNumber());
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            this.finish();
        return super.onOptionsItemSelected(item);
    }
}
