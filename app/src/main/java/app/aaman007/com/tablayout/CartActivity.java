package app.aaman007.com.tablayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    private Button button;
    private ListView listView;
    TextView textView;

    static Map< String ,List<String> > itemsName = new HashMap<>();
    static Map< String,ArrayList<Integer> > itemPrice = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Cart");
        //getSupportActionBar().setLogo(R.drawable.ic_shopping_cart);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayUseLogoEnabled(true);

        listView = findViewById(R.id.cartListViewId);

        // Setting Adapter
        setAdapters();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = itemsName.get(logIn.userName).get(position).toString();
                cartDialog(name,position);
            }
        });

        setTotal();

        // Finding Button and setting listeners
        button = findViewById(R.id.buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemsName.get(logIn.userName).isEmpty()){
                    Toast.makeText(CartActivity.this,"Cart is empty",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(CartActivity.this, OrderNow.class);
                    startActivity(intent);

                }
            }
        });
    }

    /// OptionMenu Create
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /// option Item Selection Listener
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            this.finish();
        else
        {
            if(itemsName.get(logIn.userName).size() != 0)
                cartDialogForClear();
            else
                Toast.makeText(getApplicationContext(),"Cart is already empty",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    /// Setting Adapter
    public void setAdapters()
    {
        ListViewAdapter adapter = new ListViewAdapter(CartActivity.this,itemsName.get(logIn.userName),itemPrice.get(logIn.userName));
        listView.setAdapter(adapter);
    }

    /// Setting Total
    public void setTotal()
    {
        textView = findViewById(R.id.totalId);
        textView.setText("Total : "+getSum()+" Taka");
    }

    /// Getting Total Bill
    public static Integer getSum()
    {
        Integer sum = 0;
        for(int i=0;i<itemPrice.get(logIn.userName).size();i++)
            sum += itemPrice.get(logIn.userName).get(i);
        return sum;
    }

    // Cart Dialog
    public void cartDialog(final String name, final int position)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CartActivity.this);

        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle("Remove");
        alertDialogBuilder.setMessage("Do you want to remove "+name+" from Cart?");
        alertDialogBuilder.setIcon(R.drawable.alert);

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    itemsName.get(logIn.userName).remove(position);
                    itemPrice.get(logIn.userName).remove(position);
                    setTotal();
                    setAdapters();
                    Toast.makeText(CartActivity.this, name + " is deleted from Cart", Toast.LENGTH_SHORT).show();
                }catch (Exception e){

                }
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    // Cart Dialog for Clear All
    public void cartDialogForClear()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CartActivity.this);

        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle("Clear All");
        alertDialogBuilder.setMessage("Do you want Clear All from Cart?");
        alertDialogBuilder.setIcon(R.drawable.alert);


        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CartActivity.itemsName.get(logIn.userName).clear();
                CartActivity.itemPrice.get(logIn.userName).clear();
                setTotal();
                setAdapters();
                Toast.makeText(CartActivity.this,"Cart list is cleared!!",Toast.LENGTH_SHORT).show();
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        final AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.black));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.black));
            }
        });
        alertDialog.show();
        //Toast.makeText(getApplicationContext(),flag.size(),Toast.LENGTH_SHORT).show();
    }
}
