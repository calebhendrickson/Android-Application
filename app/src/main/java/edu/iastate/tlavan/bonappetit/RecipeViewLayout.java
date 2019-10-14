package edu.iastate.tlavan.bonappetit;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * The type Recipe view layout.
 * @author chendric
 */
public class RecipeViewLayout extends ArrayAdapter<String> {

    private String[] recipeName;
    private String[] ingred1;
    private String[] ingred2;
    private String[] ingred3;
    private Activity context;

    /**
     * Instantiates a new Recipe view layout.
     *
     * @param context the context
     * @param recipeName the recipe Names
     * @param ingred1   the ingred 1
     * @param ingred2   the ingred 2
     * @param ingred3   the ingred 3
     */
    public RecipeViewLayout(Activity context, String[] recipeName, String[] ingred1, String[] ingred2, String[] ingred3){
        super(context, R.layout.activity_recipe_view_layout, recipeName);
        this.context = context;
        this.recipeName = recipeName;
        this.ingred1 = ingred1;
        this.ingred2 = ingred2;
        this.ingred3 = ingred3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_recipe_view_layout, null, true);
        TextView textViewRecipe = (TextView) listViewItem.findViewById(R.id.textViewRecipe);
        TextView textViewRecipeIngred1 = (TextView) listViewItem.findViewById(R.id.textViewRecipeIngred1);
        TextView textViewRecipeIngred2 = (TextView) listViewItem.findViewById(R.id.textViewRecipeIngred2);
        TextView textViewRecipeIngred3 = (TextView) listViewItem.findViewById(R.id.textViewRecipeIngred3);

        textViewRecipe.setText(recipeName[position]);
        textViewRecipeIngred1.setText(ingred1[position]);
        textViewRecipeIngred2.setText(ingred2[position]);
        textViewRecipeIngred3.setText(ingred3[position]);

        return listViewItem;
    }
}

