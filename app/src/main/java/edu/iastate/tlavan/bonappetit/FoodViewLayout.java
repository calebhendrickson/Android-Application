package edu.iastate.tlavan.bonappetit;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * The type Food view layout.
 * This class sets up the user's view of the food in the Food database
 * @author Tracy La Van
 */
public class FoodViewLayout extends ArrayAdapter<String> {

    /**
     * String for food name
     */
    private String[] food;
    /**
     * String for food category
     */
    private String[] category;
    /**
     * String for food quantity
     */
    private String[] quantity;
    /**
     * Activity Object used to instantiate layout XML file for list view
     */
    private Activity context;

    /**
     * Instantiates a new Food view layout.
     *
     * @param context  the context
     * @param food     the food
     * @param category the category
     * @param quantity the quantity
     */
    public FoodViewLayout(Activity context, String[] food, String[] category, String[] quantity) {
        super(context, R.layout.activity_food_view_layout, food);
        this.context = context;
        this.food = food;
        this.category = category;
        this.quantity = quantity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_food_view_layout, null, true);
        TextView textViewFood = (TextView) listViewItem.findViewById(R.id.textViewFood);
        TextView textViewCategory = (TextView) listViewItem.findViewById(R.id.textViewCategory);
        TextView textViewQuantity = (TextView) listViewItem.findViewById(R.id.textViewQuantity);

        textViewFood.setText(food[position]);
        textViewCategory.setText(category[position]);
        textViewQuantity.setText(quantity[position]);

        return listViewItem;
    }
}

