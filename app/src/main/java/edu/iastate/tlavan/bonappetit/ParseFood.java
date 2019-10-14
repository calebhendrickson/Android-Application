package edu.iastate.tlavan.bonappetit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The type Parse food.
 * This class parses the view food query from the Food database.
 * @author Tracy La Van
 */
public class ParseFood {
    /**
     * The Food string.
     */
    public static String[] Food;
    /**
     * The Category string.
     */
    public static String[] Category;
    /**
     * The Quantity string.
     */
    public static String[] Quantity;
    /**
     * The constant JSON_ARRAY.
     */
    public static final String JSON_ARRAY = "result";
    /**
     * The constant KEY_FOOD.
     * Used to access the Food attribute in the Food database.
     */
    public static final String KEY_FOOD = "Food";
    /**
     * The constant KEY_CATEGORY.
     * Used to access the Category attribute in the Food database.
     */
    public static final String KEY_CATEGORY = "Category";
    /**
     * The constant KEY_QUANTITY.
     * Used to access the Quantity attribute in the Food database.
     */
    public static final String KEY_QUANTITY = "Quantity";
    /**
     * JSONArray used to fetch query
     */
    private JSONArray foodQuery = null;
    /**
     * JSON string used in query
     */
    private String json;

    /**
     * Instantiates a new Parse food.
     *
     * @param json the json
     */
    public ParseFood(String json){
        this.json = json;
    }

    /**
     * Parse food query returned from Food Database.
     */
    protected void parseFood(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            foodQuery = jsonObject.getJSONArray(JSON_ARRAY);

            Food = new String[foodQuery.length()];
            Category = new String[foodQuery.length()];
            Quantity = new String[foodQuery.length()];

            for(int i=0;i<foodQuery.length();i++){
                JSONObject jo = foodQuery.getJSONObject(i);
                Food[i] = jo.getString(KEY_FOOD);
                Category[i] = jo.getString(KEY_CATEGORY);
                Quantity[i] = jo.getString(KEY_QUANTITY);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

