package app.aaman007.com.tablayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminLayout extends AppCompatActivity {

    private Button addAdmin , banUser , addItem , gotoApp , logOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_layout);

        this.setTitle("Admin Panel");

        addAdmin = findViewById(R.id.addAdminButton);
        banUser = findViewById(R.id.banUserButton);
        addItem = findViewById(R.id.addItemButton);
        gotoApp = findViewById(R.id.gotoAppButton);
        logOut = findViewById(R.id.logOutButton);

        // Add Item Listener
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLayout.this,AddNewFood.class);
                startActivity(intent);
            }
        });

        // Goto Main App Listener
        gotoApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLayout.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //Log Out Listener
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),logIn.class);
                startActivity(intent);
                WelcomeScreen.lastActivity = 0;
                finish();
            }
        });

        // Add Admin Listener
        addAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLayout.this,AddAdmin.class);
                intent.putExtra("button","Add Admin");
                startActivity(intent);
            }
        });

        // Ban User Listener
        banUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLayout.this,AddAdmin.class);
                intent.putExtra("button","Ban User");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        //Toast.makeText(AdminLayout.this,"Please Log Out",Toast.LENGTH_SHORT).show();
        WelcomeScreen.lastActivity = 2;
        finish();
    }

    //Creating Menu Items
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.orders_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.ordersMenuId)
        {
            Intent intent = new Intent(this,OrdersList.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
