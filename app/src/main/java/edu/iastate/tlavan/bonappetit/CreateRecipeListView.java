package edu.iastate.tlavan.bonappetit;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by npecka on 2/19/2017.
 */

public class CreateRecipeListView extends ArrayAdapter<String> {
    private String[] ids;
    private String[] recpNames;
    private String[] ingred1s;
    private String[] ingred2s;
    private String[] ingred3s;
    private String[] recipeRates;
    private Activity context;

    public CreateRecipeListView(Activity context, String[] ids, String[] recpNames, String[] ingred1s, String[] ingred2s, String[] ingred3s, String[] recipeRates){
        super(context, R.layout.create_recipe_list_view, ids);
        this.context = context;
        this.ids = ids;
        this.recpNames = recpNames;
        this.ingred1s = ingred1s;
        this.ingred2s = ingred2s;
        this.ingred3s = ingred3s;
        this.recipeRates = recipeRates;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.create_recipe_list_view, null, true);
        TextView textViewId = (TextView) listViewItem.findViewById(R.id.textViewId);
        TextView textViewRecpName = (TextView) listViewItem.findViewById(R.id.textViewRecpName);
        TextView textViewIngred1 = (TextView) listViewItem.findViewById(R.id.textViewIngred1);
        TextView textViewIngred2 = (TextView) listViewItem.findViewById(R.id.textViewIngred2);
        TextView textViewIngred3 = (TextView) listViewItem.findViewById(R.id.textViewIngred3);
        TextView textViewRecipeRate = (TextView) listViewItem.findViewById(R.id.RecipeRate);

        textViewId.setText(ids[position]);
        textViewRecpName.setText(recpNames[position]);
        textViewIngred1.setText(ingred1s[position]);
        textViewIngred2.setText(ingred2s[position]);
        textViewIngred3.setText(ingred3s[position]);
        textViewRecipeRate.setText(recipeRates[position]);

        return listViewItem;
    }
}
