package hackers.purdue.firstbusinesscompany.fridged;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bscholer on 10/15/16.
 */

public class API {

    private static Context context;
    final String requestUrl = "http://food2fork.com/api/search";
    final String key = "5624d1c6fe47028abb818d87ec5c239c";
    private ArrayList<Ingredient> ingredients;
    StringRequest postRequest = new StringRequest(Request.Method.POST, requestUrl, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                int count;
                JSONObject recipeReply = new JSONObject(response);
                count = recipeReply.getInt("count");
                JSONArray recipeArray = recipeReply.getJSONArray("recipes");
                JSONObject recipe;
                for (int i = 0; i < count; i++) {
                    recipe = recipeArray.getJSONObject(i);
                    Recipe recipeToAdd = new Recipe();
                    recipeToAdd.setPublisher(recipe.getString("publisher"));
                    recipeToAdd.setTitle(recipe.getString("title"));
                    recipeToAdd.setUrl(new URL(recipe.getString("source_url")));
                    recipeToAdd.setSocialRank(recipe.getDouble("social_rank"));
                    Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(
                            recipe.getString("image_url")).getContent());
                    recipeToAdd.setImage(bitmap);
                    recipes.add(recipeToAdd);
                }


            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(context, "It didn't work", Toast.LENGTH_LONG).show();
        }
    }
    ) {
        @Override
        protected Map<String, String> getParams() {
            Map<String, String> params = new HashMap<String, String>();
            params.put("key", key);
            String ingredientString = "";
            for (Ingredient ingredient : ingredients) {
                ingredientString += ingredient.toString();
            }
            params.put("q", ingredientString);

            return params;
        }
    };
    private ArrayList<Recipe> recipes;

    public API(Context context, ArrayList<Ingredient> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }

    public ArrayList<Recipe> getRecipes() {
        RequestQueue queue = Volley.newRequestQueue(context); // this = context
        queue.add(postRequest);
        return recipes;
    }
}
