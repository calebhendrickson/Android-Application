package edu.iastate.tlavan.bonappetit;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * The type View food.
 * This screen shows a view of all the food in the user's Food database
 * @author Tracy La Van
 */
public class ViewFood extends AppCompatActivity {

    /**
     * The constant JSON_URL for View Food (Alphabetically) By Name
     */
    public static final String JSON_URL_NAME = "http://proj-309-gb-5.cs.iastate.edu/viewFood.php";

    /**
     * The constant JSON_URL for View Food (Alphabetically) By Category
     */
    public static final String JSON_URL_CAT = "http://proj-309-gb-5.cs.iastate.edu/viewFoodByCat.php";

    /**
     * The constant KEY_CHOICE.
     * Used distinguish between displaying alphabetically by name or by category.
     */
    public static final String KEY_CHOICE = "viewChoice";

    /**
     * The list view to show food contents of database
     */
    private ListView listViewFood;

    /**
     * Indicates view choice of listing food alphabetically by name (1) or by category (2)
     */
    private String viewChoice = "ByName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_food);

        //View Food by Name Button Action
        Button btnViewName = (Button) findViewById(R.id.btnViewFoodName);
        btnViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewChoice = "ByName";
                sendRequest();
            }
        });

        //View Food by Category Button Action
        Button btnViewCat = (Button) findViewById(R.id.btnViewFoodCat);
        btnViewCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewChoice = "ByCategory";
                sendRequest();
            }
        });

        listViewFood = (ListView) findViewById(R.id.viewFood);

        //Go to Nutrition Page screen button action
        Button btnViewNutrition = (Button) findViewById(R.id.vf_Nutrition);
        btnViewNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                goToNutritionPage();
            }
        });

        //Go to Main Screen Button Action
        Button btnHome = (Button) findViewById(R.id.vf_Home);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                goHome();
            }
        });

        //Log Out Button Action
        Button btnLogOut = (Button)findViewById(R.id.vf_LogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });
    }

    /**
     * This function sends the request to the database via PHP
     */
    private void sendRequest(){

        String JSON_URL;
        if(viewChoice.equals("ByName"))
            JSON_URL = JSON_URL_NAME;
        else if(viewChoice.equals("ByCategory"))
            JSON_URL = JSON_URL_CAT;
        else
            JSON_URL = "";

        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ViewFood.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_CHOICE,viewChoice);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    /**
     * This function parses the response from the database and gives a list view of the contents.
     */
    private void showJSON(String json){
        ParseFood pf = new ParseFood(json);
        pf.parseFood();
        FoodViewLayout fvl = new FoodViewLayout(this, ParseFood.Food,ParseFood.Category,ParseFood.Quantity);
        listViewFood.setAdapter(fvl);
    }

    /**
     * This function allows the user to go to the Nutrition Page
     */
    private void goToNutritionPage()
    {
        Intent intent = new Intent(this, ViewNutrition.class);
        startActivity(intent);
        finish();
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
