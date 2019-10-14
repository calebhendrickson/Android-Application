package edu.iastate.tlavan.bonappetit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class CreateRecipe extends AppCompatActivity implements View.OnClickListener {

    public static final String JSON_URL = "http://proj-309-GB-5.cs.iastate.edu/createRecp.php";

    public static final String LOCAL_JSON_URL = "http://proj-309-GB-5.cs.iastate.edu/viewRecpLocal.php";

    public static final String SENDING_JSON_URL = "http://proj-309-GB-5.cs.iastate.edu/createRecpSend.php";

    public static final String LOCAL_SENDING_JSON_URL = "http://proj-309-GB-5.cs.iastate.edu/createRecpSendLocal.php";


    /**
     * The constant KEY_RecipeName.
     * Used to access the RecipeName attribute in the recipeName database.
     */
    public static final String KEY_RECIPENAME = "RecipeName";
    /**
     * The constant KEY_Ingred1.
     * Used to access the Ingred1 attribute in the recipeName database.
     */
    public static final String KEY_INGRED1 = "Ingred1";
    /**
     * The constant KEY_Ingred2.
     * Used to access the Ingred2 attribute in the recipeName database.
     */
    public static final String KEY_INGRED2 = "Ingred2";
    /**
     * The constant KEY_Ingred3.
     * Used to access the Ingred3 attribute in the recipeName database.
     */
    public static final String KEY_INGRED3 = "Ingred3";
    /**
     * The constant KEY_Ingred3.
     * Used to access the Ingred3 attribute in the recipeName database.
     */
    public static final String KEY_RECIPERATE = "RecipeRate";
    /**
     * The constant KEY_UploadOnlineSwitch
     * Used to send recipes to public database
     */
    public static final String KEY_UPLOADONLINESWITCH = "UploadOnlineSwitch";

    private EditText addRecipeName;
    private EditText addIngred1;
    private EditText addIngred2;
    private EditText addIngred3;
    private RatingBar addRecipeRate;
    private Switch addUploadOnlineSwitch;
    private int flag = 0;

    private Button preMade;
    private Button addButton;
    private Button logoutButton;
    private ListView listView;
    //private Button addButton;
    //private ProgressDialog mDialog;
    //private TextView mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        //initViews();

        Button btnCancel = (Button) findViewById(R.id.cancelButton);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainActivity();
            }
        });

        preMade = (Button) findViewById(R.id.premadeButton);
        preMade.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);

        addRecipeName = (EditText) findViewById(R.id.recipeName);
        addIngred1 = (EditText) findViewById(R.id.ingred1);
        addIngred2 = (EditText) findViewById(R.id.ingred2);
        addIngred3 = (EditText) findViewById(R.id.ingred3);
        addRecipeRate = (RatingBar) findViewById(R.id.recipeRate);
        addUploadOnlineSwitch = (Switch) findViewById(R.id.uploadOnlineSwitch);

        addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(this);

        logoutButton = (Button) findViewById(R.id.CR_Logout);
        logoutButton.setOnClickListener(this);
    }


    /*Code to go back to Main Screen*/
    private void goToMainActivity()
    {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
        finish();
    }

    private void sendPostPHP() {

        final String RecipeName = addRecipeName.getText().toString().trim();
        final String Ingred1 = addIngred1.getText().toString().trim();
        final String Ingred2 = addIngred2.getText().toString().trim();
        final String Ingred3 = addIngred3.getText().toString().trim();
        final String RecipeRate = String.valueOf(addRecipeRate.getRating());


        if (addUploadOnlineSwitch != null)
            addUploadOnlineSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton uploadOnline, boolean isChecked) {
                    if (isChecked) {
                        flag = 1;
                    }
                }
            });


        if (flag == 0) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, LOCAL_SENDING_JSON_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(CreateRecipe.this, response, Toast.LENGTH_LONG).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(CreateRecipe.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(KEY_RECIPENAME, RecipeName);
                    params.put(KEY_INGRED1, Ingred1);
                    params.put(KEY_INGRED2, Ingred2);
                    params.put(KEY_INGRED3, Ingred3);
                    params.put(KEY_RECIPERATE, RecipeRate);
                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

        }

        if (flag == 1) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, SENDING_JSON_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(CreateRecipe.this, response, Toast.LENGTH_LONG).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(CreateRecipe.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(KEY_RECIPENAME, RecipeName);
                    params.put(KEY_INGRED1, Ingred1);
                    params.put(KEY_INGRED2, Ingred2);
                    params.put(KEY_INGRED3, Ingred3);
                    params.put(KEY_RECIPERATE, RecipeRate);
                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

        }
        flag = 0;
    }

    private void sendRequest(){

        if (addUploadOnlineSwitch != null)
            addUploadOnlineSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton uploadOnline, boolean isChecked) {
                    if (isChecked) {
                        flag = 1;
                    }
                    else{flag = 0;}
                }
            });
        //else{flag = 0;}

        if(flag == 0){

            StringRequest stringRequest = new StringRequest(LOCAL_JSON_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    showJSON(response);
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(CreateRecipe.this, error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }

        if(flag == 1) {

            StringRequest stringRequest = new StringRequest(JSON_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    showJSON(response);
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(CreateRecipe.this, error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }

    private void showJSON(String json){

        if(flag == 0) {
            ParseRecipePremadeLocal pj = new ParseRecipePremadeLocal(json);
            pj.parseJSON();
            CreateRecipeListView crlv = new CreateRecipeListView(this, ParseRecipePremadeLocal.ids, ParseRecipePremadeLocal.recpNames, ParseRecipePremadeLocal.ingred1s, ParseRecipePremadeLocal.ingreds2s, ParseRecipePremadeLocal.ingreds3s, ParseRecipePremadeLocal.recipeRates);
            listView.setAdapter(crlv);
        }

        if(flag == 1){
            ParseRecipePremade pj = new ParseRecipePremade(json);
            pj.parseJSON();
            CreateRecipeListView crlv = new CreateRecipeListView(this, ParseRecipePremade.ids, ParseRecipePremade.recpNames, ParseRecipePremade.ingred1s, ParseRecipePremade.ingreds2s, ParseRecipePremade.ingreds3s, ParseRecipePremade.recipeRates);
            listView.setAdapter(crlv);
            // flag = 0;
        }
    }

    private void logOut()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        Toast.makeText(getApplicationContext(), "Logging out...",
                Toast.LENGTH_SHORT).show();

        finish();
    }

    @Override
    public void onClick(View v){

        switch(v.getId()){

            case R.id.premadeButton:
                sendRequest();
                break;

            case R.id.addButton:
                sendPostPHP();
                break;

            case R.id.CR_Logout:
                logOut();
                break;

            default:
                break;
        }
    }

}
