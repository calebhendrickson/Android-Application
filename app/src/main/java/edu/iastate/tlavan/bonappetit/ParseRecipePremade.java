package edu.iastate.tlavan.bonappetit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by npecka on 2/19/2017.
 */

public class ParseRecipePremade {
    public static String[] ids;
    public static String[] recpNames;
    public static String[] ingred1s;
    public static String[] ingreds2s;
    public static String[] ingreds3s;
    public static String[] recipeRates;
    public static final String KEY_ID = "id";
    public static final String KEY_RECPNAME = "recipeName";
    public static final String KEY_INGRED1 = "ingred1";
    public static final String KEY_INGRED2 = "ingred2";
    public static final String KEY_INGRED3 = "ingred3";
    public static final String KEY_RECIPERATE = "recipeRate";

    private JSONArray users;
    private String json;
    public ParseRecipePremade(String json) { this.json = json; }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try{
            jsonObject = new JSONArray(json).getJSONObject(0);
            // users = jsonObject.getJSONArray(JSON_ARRAY);

            users = new JSONArray(json);
            ids = new String[users.length()];
            recpNames = new String[users.length()];
            ingred1s = new String[users.length()];
            ingreds2s = new String[users.length()];
            ingreds3s = new String[users.length()];
            recipeRates = new String[users.length()];

            for(int i=0; i<users.length(); i++){
                JSONObject jo = users.getJSONObject(i);
                ids[i] = jo.getString(KEY_ID);
                recpNames[i] = jo.getString(KEY_RECPNAME);
                ingred1s[i] = jo.getString(KEY_INGRED1);
                ingreds2s[i] = jo.getString(KEY_INGRED2);
                ingreds3s[i] = jo.getString(KEY_INGRED3);
                recipeRates[i] = jo.getString(KEY_RECIPERATE);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
}
