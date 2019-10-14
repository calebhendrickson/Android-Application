package edu.iastate.tlavan.bonappetit;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Add food.
 * This screen allows users to add food items to their food database.
 *
 * @author Tracy La Van
 */
public class AddFood extends AppCompatActivity {

    /**
     * The constant JSON_URL.
     * Used to access the Food database.
     */
    public static final String JSON_URL = "http://proj-309-gb-5.cs.iastate.edu/addFood.php";

    /**
     * The constant KEY_FOOD.
     * Used to access the Food attribute in the Food database.
     */
    public static final String KEY_FOOD = "Food";
    /**
     * The constant KEY_CATEGORY.
     * Used to access the Category attribute in the Food database.
     */
    public static final String KEY_CATEGORY = "Category";
    /**
     * The constant KEY_QUANTITY.
     * Used to access the Quantity attribute in the Food database.
     */
    public static final String KEY_QUANTITY = "Quantity";
    /**
     * Text box to all user to enter food name
     */
    private EditText addFood;
    /**
     * Text box to all user to enter food category
     */
    private Spinner spinner; //EditText addCategory;
    /**
     * Text box to all user to enter food quantity
     */
    private EditText addQuantity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addFood = (EditText) findViewById(R.id.addFood);
        //addCategory = (EditText) findViewById(R.id.addCategory);
        addQuantity = (EditText) findViewById(R.id.addQuantity);

        spinner = (Spinner) findViewById(R.id.choicesSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.CategoryChoicesSpinner, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //TextView spinnerText = (TextView) spinner.getChildAt(0);
        //spinnerText.setTextColor(Color.parseColor("FF303F9F"));


        //Add food to database action button
        Button btnAdd = (Button) findViewById(R.id.btnAddFood);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                addFoodToDatabase();
            }
        });

        //Go to main screen action button
        Button btnHome = (Button) findViewById(R.id.af_Home);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                goHome();
            }
        });

        //Log out action button
        Button btnLogOut = (Button)findViewById(R.id.af_LogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });
    }

    /**
     * This function adds the food to the database via PHP file
     */
    private void addFoodToDatabase() {
        final String Food = addFood.getText().toString().trim();
        //final String Category = addCategory.getText().toString().trim();
        final String Category = spinner.getSelectedItem().toString();
        final String Quantity = addQuantity.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(AddFood.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddFood.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_FOOD,Food);
                params.put(KEY_CATEGORY,Category);
                params.put(KEY_QUANTITY, Quantity);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        resetObjects();
    }

    /**
     * Reset Texts & Cursor
     */
    private void resetObjects()
    {
        //Reset Boxes for Next Addition
        EditText food = (EditText) findViewById(R.id.addFood);
        food.setText(null);

        //EditText category = (EditText) findViewById(R.id.addCategory);
        //category.setText(null);

        EditText quantity = (EditText) findViewById(R.id.addQuantity);
        quantity.setText(null);

        //Reset Spinner Position
        spinner.setSelection(0);

        //Reset Cursor Position
        food.requestFocus();
    }

    /**
     * This function allows the user to go to the main home screen
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
