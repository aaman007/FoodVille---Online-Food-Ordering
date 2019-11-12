package app.aaman007.com.tablayout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class TopRatedFragment extends Fragment {

    private ListView topListView;

    public static ArrayList<FoodItem>items = new ArrayList<>();
    public static HashMap<String,Integer> freq = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_top_rated, container, false);
        topListView = view.findViewById(R.id.topListViewId);

        Collections.sort(items, new Comparator<FoodItem>() {
            @Override
            public int compare(FoodItem o1, FoodItem o2) {
                return  freq.get(o2.name) - freq.get(o1.name);
            }
        });

        final ArrayList<String> itemName = new ArrayList<>();
        final ArrayList<Integer> itemPrice = new ArrayList<>();
        for(int i=0;i<items.size();i++)
        {
            itemName.add(items.get(i).name);
            itemPrice.add(items.get(i).price);
        }

        // Setting Adapter
        ListViewAdapter adapter = new ListViewAdapter(getActivity(),itemName,itemPrice);
        topListView.setAdapter(adapter);

        topListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = itemName.get(position).toString();
                cartDialog(name,position);
            }
        });
        return view;
    }

    // Cart Dialog
    public void cartDialog(final String name, final int position)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle("Cart");
        alertDialogBuilder.setMessage("Do you want to add "+name+" to Cart ?");
        alertDialogBuilder.setIcon(R.drawable.alert);

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CartActivity.itemsName.get(logIn.userName).add(items.get(position).name);
                CartActivity.itemPrice.get(logIn.userName).add(items.get(position).price);
                Toast.makeText(getActivity(),name+" is added to Cart",Toast.LENGTH_SHORT).show();
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

}
