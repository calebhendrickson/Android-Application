package edu.iastate.tlavan.bonappetit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static edu.iastate.tlavan.bonappetit.ParseFood.JSON_ARRAY;

/**
 * The type Parse View Recipe.
 * This class parses the view recipe query from the recipe database.
 *
 * @author Caleb Hendrickson
 */
public class ParseViewRecipe {

    /**
     * The Recipes.
     */
    public static String[] recipeName;
    /**
     * The Ingr 1.
     */
    public static String[] ingred1;
    /**
     * The Ingr 2.
     */
    public static String[] ingred2;
    /**
     * The Ingr 3.
     */
    public static String[] ingred3;

    /**
     * The constant KEY_Recipes.
     */
    public static final String KEY_recipeName = "recipeName";
    /**
     * The constant KEY_Ingr1.
     */
    public static final String KEY_ingred1 = "ingred1";
    /**
     * The constant KEY_Ingr2.
     */
    public static final String KEY_ingred2 = "ingred2";
    /**
     * The constant KEY_Ingr3.
     */
    public static final String KEY_ingred3 = "ingred3";

    /**
     * JSONArray used to fetch query
     */
    private JSONArray recipeQuery = null;
    /**
     * JSON string used in query
     */
    private String json;

    /**
     * Instantiates a new Parse food.
     *
     * @param json the json
     */
    public ParseViewRecipe(String json) {
        this.json = json;
    }

    /**
     * Parse food query returned from Food Database.
     */
    protected void parseViewRecipe() {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(json);
            recipeQuery = jsonObject.getJSONArray(JSON_ARRAY);
            //foodQuery = jsonObject.getJSONArray(JSON_ARRAY);
            //jsonObject = new JSONArray(json).getJSONObject(0);
            // users = jsonObject.getJSONArray(JSON_ARRAY);

            //jsonObject = new JSONObject(json);
            //recipeQuery = jsonObject.getJSONArray(JSON_ARRAY);
            //recipeQuery = new JSONArray(json);

            //users = new JSONArray(json);

            recipeName = new String[recipeQuery.length()];
            ingred1 = new String[recipeQuery.length()];
            ingred2 = new String[recipeQuery.length()];
            ingred3 = new String[recipeQuery.length()];

            for(int i = 0; i < recipeQuery.length(); i++){
                JSONObject jo = recipeQuery.getJSONObject(i);
                recipeName[i] = jo.getString(KEY_recipeName);
                ingred1[i] = jo.getString(KEY_ingred1);
                ingred2[i] = jo.getString(KEY_ingred2);
                ingred3[i] = jo.getString(KEY_ingred3);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
