package edu.iastate.tlavan.bonappetit;

import android.content.Intent;
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

/**
 * The type View recipes.
 * This screen shows a view of all the recipes in the user's recipe database
 * @author Caleb Hendrickson
 */
public class ViewRecipe extends AppCompatActivity /*implements View.OnClickListener*/{

    /**
     * The constant JSON_URL.
     */
    public static final String JSON_URL = "http://proj-309-gb-5.cs.iastate.edu/viewRecipe.php";

    //private Button buttonGet;

    /**
     * The list view to show food contents of database
     */
    private ListView listRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        //View All Recipes Button Action
        Button buttonGetAllRecipes = (Button) findViewById(R.id.buttonGetAllRecipes);
        buttonGetAllRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest();
            }
        });

        //View Recipe by Name Button Action
        //TODO - Need to update the sendRequest() for this action (send a different request / different list view?)
        Button buttonGetOneRecipe = (Button) findViewById(R.id.buttonGetOneRecipe);
        buttonGetOneRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sendRequest();
            }
        });

        listRecipes = (ListView) findViewById(R.id.listRecipes);

        //Go to Main Screen Button Action
        Button btnHome = (Button) findViewById(R.id.vr_Home);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                goHome();
            }
        });

        //Log Out Button Action
        Button btnLogOut = (Button)findViewById(R.id.vr_LogOut);
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
    private void sendRequest() {

        StringRequest stringRequest = new StringRequest(JSON_URL,
                new  Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ViewRecipe.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParseViewRecipe pj = new ParseViewRecipe(json);
        pj.parseViewRecipe();
        RecipeViewLayout rvl = new RecipeViewLayout(this, ParseViewRecipe.recipeName, ParseViewRecipe.ingred1, ParseViewRecipe.ingred2, ParseViewRecipe.ingred3);
        listRecipes.setAdapter(rvl);
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
