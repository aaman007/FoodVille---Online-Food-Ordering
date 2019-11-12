package app.aaman007.com.tablayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    private EditText oldPassword , newPassword;
    private Button changePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        this.setTitle("Settings");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        oldPassword = findViewById(R.id.oldPasswordId);
        newPassword = findViewById(R.id.newPasswordId);
        changePassword = findViewById(R.id.changePasswordId);

        oldPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPassword.setFocusableInTouchMode(true);
            }
        });
        newPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newPassword.setFocusableInTouchMode(true);
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!changeUserPassword(logIn.userName,oldPassword.getText().toString()))
                    Toast.makeText(Settings.this,"Incorrect Password",Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(Settings.this, "Password Changed!!", Toast.LENGTH_SHORT).show();
                    oldPassword.setText("");
                    newPassword.setText("");
                }
                oldPassword.setFocusable(false);
                newPassword.setFocusable(false);
            }
        });
    }

    private boolean changeUserPassword(String username,String password)
    {
        for(Pair<String,String> i : logIn.usersID)
        {
            if(i.first.equals(username) && i.second.equals(password))
            {
                logIn.usersID.remove(i);
                logIn.usersID.add(new Pair<String, String>(username,newPassword.getText().toString()));
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            this.finish();
        return super.onOptionsItemSelected(item);
    }
}
