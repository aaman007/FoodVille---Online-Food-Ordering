package app.aaman007.com.tablayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderNow extends AppCompatActivity {

    private EditText buyerName , buyerPhone , buyerAddress;
    private Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_now);

        this.setTitle("Order Now");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buyerName = findViewById(R.id.buyerNameId);
        buyerPhone = findViewById(R.id.buyerPhoneId);
        buyerAddress = findViewById(R.id.buyerAddressId);

        confirm = findViewById(R.id.confirmId);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String name = buyerName.getText().toString();
                    String phone = buyerPhone.getText().toString();
                    String address = buyerAddress.getText().toString();
                    if(name.equals("") || phone.equals("") || address.equals(""))
                        throw new IOException();

                    //Sending Data on Pending Orders
                    OrdersList.orderersNameList.add(name);
                    OrdersList.orderersPhoneList.add(phone);
                    OrdersList.orderersAddressList.add(address);
                    int cnt = 0;
                    while(OrdersList.orderersIdList.contains(cnt))
                        cnt++;
                    OrdersList.orderersIdList.add(cnt);

                    List<String> temp1 = new ArrayList<>(CartActivity.itemsName.get(logIn.userName));
                    temp1.add("Total : ");

                    ArrayList<Integer> temp2 = new ArrayList<>(CartActivity.itemPrice.get(logIn.userName));
                    temp2.add(CartActivity.getSum());
                    OrdersList.itemsName.put(cnt,temp1);
                    OrdersList.itemPrice.put(cnt,temp2);

                    addToTopFragment();

                    confirmationDialog();

                    buyerPhone.setText("");
                    buyerName.setText("");
                    buyerAddress.setText("");

                }catch (Exception e){
                    Toast.makeText(OrderNow.this,"Please fill all fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            this.finish();
        return super.onOptionsItemSelected(item);
    }

    void addToTopFragment()
    {
        int j = 0;
        for(String i : CartActivity.itemsName.get(logIn.userName))
        {
            if(TopRatedFragment.freq.containsKey(i) == false)
            {
                TopRatedFragment.freq.put(i,1);
                TopRatedFragment.items.add(new FoodItem(i,CartActivity.itemPrice.get(logIn.userName).get(j)));
            }
            else
            {
                TopRatedFragment.freq.put(i,TopRatedFragment.freq.get(i)+1);
            }
            j++;
        }
    }
    /// Confirmation Dialog
    private void confirmationDialog()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(OrderNow.this);

        alertDialogBuilder.setMessage("Your Order is confirmed.It will be delivered to you within an hour.\n" +
                "For any kind of problem feel free to call us.\n" +
                "Thank you for choosing FoodVille.");
        alertDialogBuilder.setTitle("Confirmation");

        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }
        });
        alertDialog.show();

    }
}
