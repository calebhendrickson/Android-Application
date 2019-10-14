package edu.iastate.tlavan.bonappetit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This class allows the user to View their shopping list, add or remove elements
 */
public class ShoppingList extends AppCompatActivity {

    /**
     * The constant JSON_URL for Deleting an Item from Shopping List Database.
     */
    public static final String JSON_URL_DELETE = "http://proj-309-gb-5.cs.iastate.edu/deleteShopListItem.php";

    /**
     * The constant JSON_URL for Adding an Item to Shopping List Database.
     */
    public static final String JSON_URL_ADD = "http://proj-309-gb-5.cs.iastate.edu/addShopListItem.php";

    /**
     * The constant KEY_FOOD.
     * Used to access the Food attribute in the Food database.
     */
    public static final String KEY_FOOD = "Food";

    /**
     * Shopping List Layout
     */
    ShoppingListAdapter shoppingAdapter = null;

    /**
     * ArrayList of what has been checked
     */
    ArrayList<String> itemsToDelete = new ArrayList<String>();

    //TODO - Remove these when data from php is successful
    ArrayList<String> shoppingList = new ArrayList<String>();
    Boolean first = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        //Generate list View from ArrayList
        displayShoppingList();

        //Add Item Action
        Button btnAddItem = (Button) findViewById(R.id.sl_Add);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                addItem();
            }
        });

        //Delete Item(s) Action
        Button btnDeleteItem = (Button) findViewById(R.id.sl_Delete);
        btnDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                deleteItem();
            }
        });

        //Go Home Button Action
        Button btnHome = (Button) findViewById(R.id.sl_Home);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                goHome();
            }
        });

        //Log Out Button Action
        Button btnLogOut = (Button)findViewById(R.id.sl_LogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });
    }

    /**
     * This function allows the user to add an item to the shopping list.
     */
    private void displayShoppingList()
    {
        //ArrayList of shopping items
        //TODO - Populate via php file from ShoppingList Database
        //ArrayList<String> shoppingList = new ArrayList<String>();

        //TODO - Remove this when data from php is successful
        if(first) {
            shoppingList.add("Banana");
            shoppingList.add("Apple");
            shoppingList.add("Green Beans");
            first = false;
        }

        //create an ArrayAdaptar from the String Array
        shoppingAdapter = new ShoppingListAdapter(this,
                R.layout.shopping_list_view, shoppingList);
        ListView listView = (ListView) findViewById(R.id.sl_shopList);
        // Assign adapter to ListView
        listView.setAdapter(shoppingAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
            }
        });

    }

     private class ShoppingListAdapter extends ArrayAdapter<String> {

        private ArrayList<String> shoppingList;

        /**
         * Activity Object used to instantiate layout XML file for list view
         */
        private Activity context;

        private ShoppingListAdapter(Activity context, int textViewResourceId, ArrayList<String> shoppingList) {
            super(context, textViewResourceId, shoppingList);
            this.context = context;
            this.shoppingList = new ArrayList<String>();
            this.shoppingList.addAll(shoppingList);
        }

        private class ViewHolder {
            TextView code;
            CheckBox name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ShoppingListAdapter.ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater) context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.shopping_list_view, null);

                holder = new ShoppingListAdapter.ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.code);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);

                //If the checkbox is clicked, we need to add or removed the item from the list of items to delete
                holder.name.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v ;
                        String item = (String) cb.getText();

                        Boolean removeDelete = false;
                        Iterator<String> itemsToDeleteIterator = itemsToDelete.iterator();
                        while (itemsToDeleteIterator.hasNext()) {
                            if(itemsToDeleteIterator.next().equals(item)){
                                itemsToDelete.remove(item);
                                removeDelete = true;
                                //Toast.makeText(getApplicationContext(), "Item Removed from Delete: " + item,
                                        //Toast.LENGTH_LONG).show();
                            }
                        }
                        if(!removeDelete) {
                            itemsToDelete.add(item);
                            //Toast.makeText(getApplicationContext(), "Item Added to Delete: " + item,
                                    //Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
            else {
                holder = (ShoppingListAdapter.ViewHolder) convertView.getTag();
            }

            String item = shoppingList.get(position);
            holder.name.setText(item);

            return convertView;

        }

    }

    /**
     * This function allows the user to add an item to the shopping list.
     */
    private void addItem()
    {
        //TODO - Query Database to add
        EditText addFoodItem = (EditText) findViewById(R.id.addItemText);

        final String Food = addFoodItem.getText().toString().trim();

        //TODO - Eventually remove this line (here for practice)
        shoppingList.add(Food);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL_ADD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ShoppingList.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ShoppingList.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_FOOD, Food);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        //Reset Boxes for Next Addition
        addFoodItem.setText(null);

        //Reset Cursor Position
        addFoodItem.requestFocus();

        //Display new list
        displayShoppingList();

    }

    /**
     * This function allows the user to add an item to the shopping list.
     */
    private void deleteItem()
    {
        //ArrayList<String> shoppingList = shoppingAdapter.shoppingList;
        for(int i=0;i<itemsToDelete.size();i++){

            final String Food = itemsToDelete.get(i);

            //TODO - Eventually remove this line (here for practice)
            shoppingList.remove(Food);
            //Toast.makeText(getApplicationContext(), "Removed " + Food,
              //      Toast.LENGTH_LONG).show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL_DELETE,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(ShoppingList.this,response,Toast.LENGTH_LONG).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ShoppingList.this,error.toString(),Toast.LENGTH_LONG).show();
                        }
                    }){
                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> params = new HashMap<String, String>();
                    params.put(KEY_FOOD, Food);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }

        //Display new list & reset delete list
        itemsToDelete.clear();
        displayShoppingList();

    }

    /**
     * This function allows the user to navigate to the main home screen.
     */
    private void goHome()
    {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
        finish();
    }

    /**
     * This function allows the user to log out of the application
     */
    private void logOut()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        Toast.makeText(getApplicationContext(), "Logging out...",
                Toast.LENGTH_SHORT).show();

        finish();
    }
}
