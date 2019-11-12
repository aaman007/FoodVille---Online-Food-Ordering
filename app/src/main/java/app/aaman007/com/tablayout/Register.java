package app.aaman007.com.tablayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity implements View.OnClickListener{

    private EditText username , email , password , confirmPassword;
    private Button register;
    public static List<String> allUsers = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setTitle("Register");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        username = findViewById(R.id.usernameId);
        email = findViewById(R.id.emailId);
        password = findViewById(R.id.passwordId);
        confirmPassword = findViewById(R.id.confirmpasswordId);

        register = findViewById(R.id.registerButtonId);

        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String user , pass , email1 , confirmPass;
        user = username.getText().toString();
        email1 = email.getText().toString();
        pass = password.getText().toString();
        confirmPass = confirmPassword.getText().toString();

        if(user.equals("") || email1.equals("") || pass.equals("") || confirmPass.equals(""))
            toast("Please fill all informations");
        else if(!pass.equals(confirmPass))
            toast("Password doesn't match");
        else if(allUsers.contains(user) == false)
        {
            allUsers.add(user);
            MyAccount.detailsData.put(user,new UserDetails(email1));
            toast("Registration Successful!!");
            Intent intent = new Intent(Register.this,logIn.class);
            intent.putExtra("username",user);
            intent.putExtra("password",pass);
            setResult(1331,intent);
            finish();
        }
        else
            toast("Username already exists");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            this.finish();
        return super.onOptionsItemSelected(item);
    }


    public void toast(String s)
    {
        Toast.makeText(Register.this,s, Toast.LENGTH_SHORT).show();
    }
}
