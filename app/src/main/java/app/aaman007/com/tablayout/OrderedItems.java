package app.aaman007.com.tablayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

public class OrderedItems extends AppCompatActivity {

    private ListView orderedItemsListView;
    private Integer orderId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered_items);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Ordered Items");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Fetching Data from previous Activity
        Bundle bundle = getIntent().getExtras();
        String temp = bundle.get("Username").toString();
        orderId = Integer.parseInt(temp);

        orderedItemsListView = findViewById(R.id.orderedItemsListViewId);
        setAdapters();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            this.finish();
        return super.onOptionsItemSelected(item);
    }

    public void setAdapters()
    {
        ListViewAdapter adapter = new ListViewAdapter(this,OrdersList.itemsName.get(orderId),OrdersList.itemPrice.get(orderId));
        orderedItemsListView.setAdapter(adapter);
    }
}
