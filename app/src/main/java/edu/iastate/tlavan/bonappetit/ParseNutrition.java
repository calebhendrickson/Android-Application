package edu.iastate.tlavan.bonappetit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by DeathxPain107 on 3/20/2017.
 */

public class ParseNutrition {
    public static String[] ids;
    public static String[] Calories;
    public static String[] TotalFat;
    public static String[] Cholesterol;
    public static String[] Sodium;
    public static String[] TotalCarbohydrates;
    public static String[] Protien;
    public static final String KEY_ID = "ID";
    public static final String KEY_CALORIES = "Calories";
    public static final String KEY_TOTALFAT = "TotalFat";
    public static final String KEY_CHOLESTEROL = "Cholesterol";
    public static final String KEY_SODIUM = "Sodium";
    public static final String KEY_TOTALCARBOHYDRATES = "TotalCarbohydrates";
    public static final String KEY_PROTIEN = "Protein";

    private JSONArray users;
    private String json;
    public  ParseNutrition(String json) { this.json = json; }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try{
            jsonObject = new JSONArray(json).getJSONObject(0);
            // users = jsonObject.getJSONArray(JSON_ARRAY);

            users = new JSONArray(json);
            ids = new String[users.length()];
            Calories = new String[users.length()];
            TotalFat = new String[users.length()];
            Cholesterol = new String[users.length()];
            Sodium = new String[users.length()];
            TotalCarbohydrates = new String[users.length()];
            Protien = new String[users.length()];

            for(int i=0; i<users.length(); i++){
                JSONObject jo = users.getJSONObject(i);
                ids[i] = jo.getString(KEY_ID);
                Calories[i] = jo.getString(KEY_CALORIES);
                TotalFat[i] = jo.getString(KEY_TOTALFAT);
                Cholesterol[i] = jo.getString(KEY_CHOLESTEROL);
                Sodium[i] = jo.getString(KEY_SODIUM);
                TotalCarbohydrates[i] = jo.getString(KEY_TOTALCARBOHYDRATES);
                Protien[i] = jo.getString(KEY_PROTIEN);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
}
