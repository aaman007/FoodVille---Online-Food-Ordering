package app.aaman007.com.tablayout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FoodMenuFragment extends Fragment {

    private ExpandableListView expandableListView;

    public static final List<String>categoryList = new ArrayList<>();
    public static final HashMap<String,List<Pair<String,Integer>>> itemList = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food_menu, container, false);

        expandableListView = view.findViewById(R.id.foodListId);

        // Setting Data
        if (categoryList.isEmpty())
            setData();

        // Setting Adapter for Expandable ListView
        setAdapters();

        // Setting Listeners
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                expandableListView.expandGroup(groupPosition);
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String name = itemList.get(categoryList.get(groupPosition)).get(childPosition).first;
                Integer price = itemList.get(categoryList.get(groupPosition)).get(childPosition).second;
                cartDialog(name,price);
                return false;
            }
        });

        return view;
    }

    // Cart Dialog
    public void cartDialog(final String itemName, final Integer itemPrce)
    {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle("Cart");
        alertDialogBuilder.setMessage("Do you want to add "+itemName+" to Cart ?");
        alertDialogBuilder.setIcon(R.drawable.alert);

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CartActivity.itemsName.get(logIn.userName).add(itemName);
                CartActivity.itemPrice.get(logIn.userName).add(itemPrce);
                Toast.makeText(getActivity(),itemName+" is added to Cart",Toast.LENGTH_SHORT).show();
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

    // Setting Adapter
    public void setAdapters()
    {
        CustomAdapter adapter = new CustomAdapter(getActivity());
        expandableListView.setAdapter(adapter);
        int cnt = adapter.getGroupCount();
        for(int i=0;i<cnt;i++)
            expandableListView.expandGroup(i);
    }

    // Setting Data
    public static void setData()
    {
        categoryList.add("Fried Rice");
        categoryList.add("Soups");
        categoryList.add("Appetizer");
        categoryList.add("Chowmein");
        categoryList.add("Fish");


        /// Adding Fried Rices
        List<Pair<String,Integer>> temp = new ArrayList<>();
        temp.add(new Pair<String, Integer>("Vagetable Fried Rice",200));
        temp.add(new Pair<String, Integer>("Mixed Fried Rice",250));
        temp.add(new Pair<String, Integer>("Egg Fried Rice",220));

        itemList.put(categoryList.get(0),temp);

        /// Adding Soups
        List<Pair<String,Integer>> temp3 = new ArrayList<>();
        temp3.add(new Pair<String, Integer>("Szechuan Soup",290));
        temp3.add(new Pair<String, Integer>("Special Thai Soup",410));
        temp3.add(new Pair<String, Integer>("FoodVille Special Soup",350));

        itemList.put(categoryList.get(1),temp3);

        /// Adding Appetizer
        List<Pair<String,Integer>> temp4 = new ArrayList<>();
        temp4.add(new Pair<String, Integer>("Chicken Wings(6 pcs)",250));
        temp4.add(new Pair<String, Integer>("French Fry",100));
        temp4.add(new Pair<String, Integer>("Samossa(10 pcs)",100));

        itemList.put(categoryList.get(2),temp4);

        /// Adding Chowmein
        List<Pair<String,Integer>> temp5 = new ArrayList<>();
        temp5.add(new Pair<String, Integer>("Mixed Chowmein",290));
        temp5.add(new Pair<String, Integer>("Vagetable Chowmein",220));
        temp5.add(new Pair<String, Integer>("Egg Chowmein",250));

        itemList.put(categoryList.get(3),temp5);

        /// Adding Fishes
        List<Pair<String,Integer>> temp6 = new ArrayList<>();
        temp6.add(new Pair<String, Integer>("King Prawn Masala",350));
        temp6.add(new Pair<String, Integer>("Prawn Sizzler",380));
        temp6.add(new Pair<String, Integer>("Rupchanda Fry Gravy",350));

        itemList.put(categoryList.get(4),temp6);

    }
}
