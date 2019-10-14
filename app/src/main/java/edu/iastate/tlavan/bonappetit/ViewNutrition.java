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

public class ViewNutrition extends AppCompatActivity implements View.OnClickListener{


    public static final String JSON_URL = "http://proj-309-GB-5.cs.iastate.edu/NutritionView.php";

    private Button showNutrition;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_nutrition);

        Button btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToViewFood();
            }
        });

        Button btnHome = (Button) findViewById(R.id.homeNutrition);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainActivity();
            }
        });


        showNutrition = (Button) findViewById(R.id.showNutrition);
        showNutrition.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.NutListView);

    }

    private void goToViewFood()
    {
        Intent intent = new Intent(this, ViewFood.class);
        startActivity(intent);
        finish();
    }

    private void goToMainActivity()
    {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
        finish();
    }



    private void sendRequest(){

        StringRequest stringRequest = new StringRequest(JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON(response);
            }
        },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(ViewNutrition.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParseNutrition pn = new ParseNutrition(json);
        pn.parseJSON();
        CreateNutritionListView cnlv = new CreateNutritionListView(this, ParseNutrition.ids,ParseNutrition.Calories,ParseNutrition.TotalFat,ParseNutrition.Cholesterol,ParseNutrition.Sodium,ParseNutrition.TotalCarbohydrates,ParseNutrition.Protien);
        listView.setAdapter(cnlv);
    }

    @Override
    public void onClick(View view) {
        sendRequest();
    }
}
