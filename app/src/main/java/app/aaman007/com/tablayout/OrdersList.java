package app.aaman007.com.tablayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersList extends AppCompatActivity {

    //Declarations
    public static final List<String>orderersNameList = new ArrayList<>();
    public static final List<String>orderersPhoneList = new ArrayList<>();
    public static final List<String>orderersAddressList = new ArrayList<>();
    public static final List<Integer>orderersIdList = new ArrayList<>();
    static Map< Integer ,List<String> > itemsName = new HashMap<>();
    static Map< Integer,ArrayList<Integer> > itemPrice = new HashMap<>();
    private ListView orderersName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_list);

        this.setTitle("Pending Orders");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Finding Views
        orderersName = findViewById(R.id.ordererNameId);

        //Setting adapter
        setAdapters();

        //Setting Listener
        orderersName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(OrdersList.this,OrderedItems.class);
                intent.putExtra("Username",orderersIdList.get(position));
                startActivity(intent);

            }
        });
        orderersName.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                cartDialog(position);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            this.finish();
        return super.onOptionsItemSelected(item);
    }
    // Cart Dialog
    public void cartDialog(final int position)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle("Remove Order");
        alertDialogBuilder.setMessage("Do you want to remove this order?");
        alertDialogBuilder.setIcon(R.drawable.alert);

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    orderersNameList.remove(position);
                    orderersAddressList.remove(position);
                    orderersPhoneList.remove(position);
                    itemsName.get(orderersIdList.get(position)).clear();
                    itemPrice.get(orderersIdList.get(position)).clear();
                    orderersIdList.remove(position);
                    setAdapters();
                    Toast.makeText(OrdersList.this, "Order removed from Pending Orders", Toast.LENGTH_SHORT).show();
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

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.black));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.black));
            }
        });
        alertDialog.show();
    }

    // Set Adapter Method
    private void setAdapters()
    {
        NameCustomAdapter adapter = new NameCustomAdapter(this,orderersNameList);
        orderersName.setAdapter(adapter);
    }
}