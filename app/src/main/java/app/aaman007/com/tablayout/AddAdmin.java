package app.aaman007.com.tablayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddAdmin extends AppCompatActivity {

    private EditText user;
    private Button add;
    private TextView header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        user = findViewById(R.id.userId);
        add = findViewById(R.id.addId);
        header = findViewById(R.id.headerId);

        Bundle bundle = getIntent().getExtras();
        final String command = bundle.getString("button");

        header.setText(command);
        this.setTitle(command);

        // Listener for Add Button
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getText().toString();
                if(username.equals(""))
                    Toast.makeText(AddAdmin.this,"Please enter username",Toast.LENGTH_SHORT).show();
                else if(Register.allUsers.contains(username) == false)
                    Toast.makeText(AddAdmin.this,"User doesn't exist",Toast.LENGTH_SHORT).show();
                else
                {
                    if(command.equals("Add Admin") && !logIn.adminsID.contains(username)) {
                        logIn.adminsID.add(username);
                        user.setText("");
                        Toast.makeText(AddAdmin.this,"Admin added",Toast.LENGTH_SHORT).show();

                    }
                    else if(command.equals("Ban User") && !logIn.bannedID.contains(username)) {
                        logIn.bannedID.add(username);
                        user.setText("");
                        Toast.makeText(AddAdmin.this,"User banned",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        user.setText("");
                        Toast.makeText(AddAdmin.this,"User already exists",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
