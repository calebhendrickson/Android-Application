package edu.iastate.tlavan.bonappetit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * The type Home Screen (Main 2 activity).
 * @author Tracy La Van
 */
public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Go to add food screen button action
        Button btnAddFood = (Button) findViewById(R.id.h_addFood);
        btnAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                goToAddFood();
            }
        });

        //Go to view food screen button action
        Button btnViewFood = (Button) findViewById(R.id.h_viewFood);
        btnViewFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                goToViewFood();
            }
        });

        //Go to shopping list screen button action
        Button btnShopList = (Button) findViewById(R.id.h_ShopList);
        btnShopList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                goToShopList();
            }
        });

        //Go to Create Recipe screen button action
        Button btnCreateRecipe = (Button) findViewById(R.id.h_CreateRecipe);
        btnCreateRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                goToCreateRecipe();
            }
        });

        //Go to View Recipe screen button action
        Button btnViewRecipe = (Button) findViewById(R.id.h_ViewRecipe);
        btnViewRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                goToViewRecipe();
            }
        });

        Button btnLogOut = (Button)findViewById(R.id.h_LogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });
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

    /**
     * This function allows the user to go to the Add Food page
     */
    private void goToAddFood()
    {
        Intent intent = new Intent(this, AddFood.class);
        startActivity(intent);
        finish();
    }

    /**
     * This function allows the user to go to the View Food page
     */
    private void goToViewFood()
    {
        Intent intent = new Intent(this, ViewFood.class);
        startActivity(intent);
        finish();
    }

    /**
     * This function allows the user to go to the Shopping List page
     */
    private void goToShopList()
    {
        Intent intent = new Intent(this, ShoppingList.class);
        startActivity(intent);
        finish();
    }

    /**
     * This function allows the user to go to the View Recipe page
     */
    private void goToViewRecipe()
    {
        Intent intent = new Intent(this, ViewRecipe.class);
        startActivity(intent);
        finish();
    }
    /**
     * This function allows the user to go to the Create Recipes page
     */
    private void goToCreateRecipe()
    {
        Intent intent = new Intent(this, CreateRecipe.class);
        startActivity(intent);
        finish();
    }

}
