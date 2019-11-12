package app.aaman007.com.tablayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class logIn extends AppCompatActivity implements View.OnClickListener{


    // Declarations
    private EditText username , password;
    private Button logIn , register;
    String user , pass;
    static String userName;
    //private TextView logo;
    public static  ArrayList< Pair<String,String> > usersID = new ArrayList<>();
    public static final ArrayList<String> adminsID = new ArrayList<>() , bannedID = new ArrayList<>();


    /// OnCreate Method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        this.setTitle("Log In");

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // In case there is no Users
        if(usersID.isEmpty()) {
            usersID.add(new Pair<String, String>("a", "a"));
            usersID.add(new Pair<String, String>("admin", "admin"));
            usersID.add(new Pair<String, String>("banned", "banned"));

            MyAccount.detailsData.put("a",new UserDetails("A007@gmail.com"));
            MyAccount.detailsData.put("admin",new UserDetails("admin007@gmail.com"));
            Register.allUsers.add("a");
            Register.allUsers.add("admin");
            Register.allUsers.add("banned");
        }
        if(adminsID.isEmpty())
            adminsID.add("admin");
        if(bannedID.isEmpty())
            bannedID.add("banned");

        // Finding Views
        username = findViewById(R.id.usernameId);
        password = findViewById(R.id.passwordId);

        logIn = findViewById(R.id.logInButtonId);
        register = findViewById(R.id.registerButtonId);

/*        logo = findViewById(R.id.logoID);
        Typeface typeface = Typeface.createFromAsset(this.getAssets(),"login.ttf");
        logo.setTypeface(typeface);*/


        // Setiing OnClick Listeners
        logIn.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    // Contains without matching case
    public static boolean containsCaseInsensitive(String s, ArrayList<String> l){
        for (String string : l){
            if (string.equalsIgnoreCase(s)){
                return true;
            }
        }
        return false;
    }

    /// Onclick Listeners
    @Override
    public void onClick(View v) {
        try {
            if (v.getId() == R.id.logInButtonId) {
                userName = username.getText().toString();
                if(containsCaseInsensitive(userName,bannedID) && userChecker())
                {
                    Toast.makeText(this, "You are banned by admins", Toast.LENGTH_SHORT).show();
                }
                else if(containsCaseInsensitive(userName,adminsID) && userChecker())
                {
                    Toast.makeText(this, "LogIn Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(logIn.this, AdminLayout.class);
                    if(CartActivity.itemsName.get(userName) == null)
                    {
                        CartActivity.itemsName.put(userName,new ArrayList<String>());
                        CartActivity.itemPrice.put(userName,new ArrayList<Integer>());
                    }
                    startActivity(intent);
                    finish();
                }
                else if (userChecker()) {
                    Toast.makeText(this, "LogIn Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(logIn.this, MainActivity.class);
                    userName = username.getText().toString();
                    if(CartActivity.itemsName.get(userName) == null)
                    {
                        CartActivity.itemsName.put(userName,new ArrayList<String>());
                        CartActivity.itemPrice.put(userName,new ArrayList<Integer>());
                    }
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Intent intent = new Intent(logIn.this,Register.class);
                startActivityForResult(intent,1331);

            }
            password.setText("");
        }
        catch (Exception e)
        {
            Toast.makeText(this,"Please enter valid username and password",Toast.LENGTH_SHORT).show();
        }
    }

    // Confirming the user to EXIT
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogueBuilder = new AlertDialog.Builder(this);
        alertDialogueBuilder.setIcon(R.drawable.alert);
        alertDialogueBuilder.setTitle("EXIT");
        alertDialogueBuilder.setMessage(R.string.alert_message);

        alertDialogueBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertDialogueBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialogueBuilder.setCancelable(false);
        final AlertDialog alertDialog = alertDialogueBuilder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }
        });

        alertDialog.show();
    }
    private boolean userChecker()
    {
        String userInput = username.getText().toString();
        String passInput = password.getText().toString();
        for(Pair <String,String> i : usersID)
        {
            if(i.first.equalsIgnoreCase(userInput) && i.second.equals(passInput))
                return true;
        }
        return false;
    }


    // Retrieving data from Register Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1331 && resultCode == 1331)
        {
            user = data.getStringExtra("username");
            pass = data.getStringExtra("password");
            usersID.add(new Pair<String, String>(user,pass));
        }
    }
}
