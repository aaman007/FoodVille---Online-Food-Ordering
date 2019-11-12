package app.aaman007.com.tablayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddNewFood extends AppCompatActivity {

    private EditText categoryName,itemName,itemPrice;
    private Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_food);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Add New");

        // Finding View by Id
        categoryName = findViewById(R.id.categoryId);
        itemName = findViewById(R.id.newItemNameId);
        itemPrice = findViewById(R.id.newItemPriceId);
        addButton = findViewById(R.id.addButoonId);

        //Setting Data for Menu
        if(FoodMenuFragment.categoryList.isEmpty())
            FoodMenuFragment.setData();
        if(DrinksFragment.drinksName.isEmpty())
            DrinksFragment.addDrinks();

        //Setting onClick listener for addButton
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String category = categoryName.getText().toString();
                    String item = itemName.getText().toString();
                    String temp = itemPrice.getText().toString();
                    Integer price = Integer.parseInt(temp);

                    // Fixing Letters of Item and categroy
                    item = fixLetters(item);
                    category = fixLetters(category);

                    if(category.equals("") || item.equals("") || temp.equals(""))
                        throw new IOException();
                    if(category.equalsIgnoreCase("Drinks"))
                    {
                        DrinksFragment.drinksName.add(item);
                        DrinksFragment.drinksPrice.add(price);
                    }
                    else{
                        if(FoodMenuFragment.categoryList.contains(category) == false) {
                            FoodMenuFragment.categoryList.add(category);
                            List<Pair<String, Integer>> temp2 = new ArrayList<>();
                            FoodMenuFragment.itemList.put(category, temp2);
                        }
                        FoodMenuFragment.itemList.get(category).add(new Pair<String, Integer>(item, price));
                    }
                    Toast.makeText(AddNewFood.this,"Item added",Toast.LENGTH_SHORT).show();
                    categoryName.setText("");
                    itemName.setText("");
                    itemPrice.setText("");

                }catch (Exception e){
                    Toast.makeText(AddNewFood.this,"Please fill all fields",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //Fixing Letters
    String fixLetters(String s)
    {
        boolean flag = true;
        for(int i=0;i<s.length();i++){
            if(flag) {
                char s1[] = s.toCharArray();
                s1[i] = Character.toUpperCase(s.charAt(i));
                s = new String(s1);
            }
            else{
                char s1[] = s.toCharArray();
                s1[i] = Character.toLowerCase(s.charAt(i));
                s = new String(s1);
            }
            flag = false;
            if(s.charAt(i) == ' ')
                flag = true;
        }
        return s;
    }

    // OnOptionItem Selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
