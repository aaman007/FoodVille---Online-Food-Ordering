package app.aaman007.com.tablayout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DrinksFragment extends Fragment {

    private ListView listView;
    public static final List<String>drinksName = new ArrayList<>();
    public static final ArrayList<Integer>drinksPrice = new ArrayList<>();
    private static boolean isVisited;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drinks, container, false);
        listView = view.findViewById(R.id.listViewId);

        // Adding Drinks
        if(drinksName.isEmpty())
            addDrinks();

        // Setting Adapter
        ListViewAdapter adapter = new ListViewAdapter(getActivity(),drinksName,drinksPrice);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = drinksName.get(position).toString();
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
                CartActivity.itemsName.get(logIn.userName).add(drinksName.get(position));
                CartActivity.itemPrice.get(logIn.userName).add(drinksPrice.get(position));
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
    public void reviewDialog(final String name)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle("Review");
        alertDialogBuilder.setMessage("Did you liked "+name+" ?");
        alertDialogBuilder.setIcon(R.drawable.alert);

        alertDialogBuilder.setPositiveButton("Like", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"Liked",Toast.LENGTH_SHORT).show();
            }
        });

        alertDialogBuilder.setNegativeButton("Dislike", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"Disliked",Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static void addDrinks()
    {
        //drinksPrice = new ArrayList<>();
        //drinksName = new ArrayList<>();

        drinksName.add("Sprite");
        drinksPrice.add(15);

        drinksName.add("Spite Can");
        drinksPrice.add(30);

        drinksName.add("Coca-Cola");
        drinksPrice.add(15);

        drinksName.add("Coca-Cola Can");
        drinksPrice.add(30);

        drinksName.add("Fanta");
        drinksPrice.add(15);

        drinksName.add("Mirinda");
        drinksPrice.add(15);

    }
}
