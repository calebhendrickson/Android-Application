package edu.iastate.tlavan.bonappetit;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by DeathxPain107 on 3/20/2017.
 */


public class CreateNutritionListView extends ArrayAdapter<String> {
    private String[] ids;
    private String[] Calories;
    private String[] TotalFat;
    private String[] Cholesterol;
    private String[] Sodium;
    private String[] TotalCarbohydrates;
    private String[] Protein;
    private Activity context;

    public CreateNutritionListView(Activity context, String[] ids, String[] Calories, String[] TotalFat, String[] Cholesterol, String[] Sodium, String[] TotalCarbohydrates, String[] Protien){
        super(context, R.layout.create_nutrition_list_view, ids);
        this.context = context;
        this.ids = ids;
        this.Calories = Calories;
        this.TotalFat = TotalFat;
        this.Cholesterol = Cholesterol;
        this.Sodium = Sodium;
        this.TotalCarbohydrates = TotalCarbohydrates;
        this.Protein = Protien;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.create_nutrition_list_view, null, true);
        TextView textViewId = (TextView) listViewItem.findViewById(R.id.textViewNutID);
        TextView textViewCalories = (TextView) listViewItem.findViewById(R.id.textViewCalories);
        TextView textViewTotalFat = (TextView) listViewItem.findViewById(R.id.textViewTotalFat);
        TextView textViewCholesterol = (TextView) listViewItem.findViewById(R.id.textViewCholesterol);
        TextView textViewSodium = (TextView) listViewItem.findViewById(R.id.textViewSodium);
        TextView textViewTotCarbs = (TextView) listViewItem.findViewById(R.id.textViewTotCarbs);
        TextView textViewProtien = (TextView) listViewItem.findViewById(R.id.textViewProtien);



        textViewId.setText(ids[position]);
        textViewCalories.setText(Calories[position]);
        textViewTotalFat.setText(TotalFat[position]);
        textViewCholesterol.setText(Cholesterol[position]);
        textViewSodium.setText(Sodium[position]);
        textViewTotCarbs.setText(TotalCarbohydrates[position]);
        textViewProtien.setText(Protein[position]);

        return listViewItem;
    }
}
