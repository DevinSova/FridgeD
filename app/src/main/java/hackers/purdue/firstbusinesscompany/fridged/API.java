package hackers.purdue.firstbusinesscompany.fridged;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by bscholer on 10/15/16.
 */

public class API extends AppCompatActivity
{

    final String requestUrl = "http://food2fork.com/api/search";
    final String key = "5624d1c6fe47028abb818d87ec5c239c";

    private static Context context;
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


    public API(Context context, ArrayList<Ingredient> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }

    public API() {}

    public ArrayList<Recipe> getRecipes() {
        RequestQueue queue = Volley.newRequestQueue(context); // this = context
        queue.add(postRequest);
        return recipes;
    }

    ListView recipeList;
    BaseAdapter adapter;
    SharedPreferences recipeSharedPreferences;
    private ArrayList<Recipe> recipes;
    private ArrayList<String> recipesURLS;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_view);
        recipeList = (ListView) findViewById(R.id.recipeList);
        Intent intent = getIntent();
        ArrayList<String> fridgeArray = intent.getStringArrayListExtra("fridgeArray");
        this.ingredients = new ArrayList<Ingredient>();
        for(int i = 0; i < fridgeArray.size(); i++)
        {
            Ingredient newIngredient = new Ingredient(fridgeArray.get(i));
            this.ingredients.add(newIngredient);
        }
        //Now I have the ingredients arraylist.
        API api = new API(getApplicationContext(), this.ingredients);
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
         recipes = api.getRecipes();

        recipesURLS = new ArrayList<String>();


        recipeSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if (recipeSharedPreferences.contains("url")) {
            recipesURLS = new ArrayList<String>(Arrays.asList(recipeSharedPreferences.getString("url", "").split(",")));
        }
        else {
            recipesURLS = new ArrayList<>();
        }

        if(!(recipes == null)) {
            for (int i = 0; i < recipes.size(); i++) {
                String newURL = recipes.get(i).getUrl().toString();
                recipesURLS.add(newURL);
            }
        }
        else if(recipes.size() == 1)
            recipesURLS.add("No recipes found. Sorry fam");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, recipesURLS);
        recipeList.setAdapter(adapter);
        updateListView();
    }

    public void updateListView() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < recipesURLS.size(); i++) {
            builder.append(recipesURLS.get(i)).append(",");
        }
        recipeSharedPreferences.edit().putString("url", builder.toString()).apply();
        adapter.notifyDataSetChanged();
    }
}
