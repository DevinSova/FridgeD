package hackers.purdue.firstbusinesscompany.fridged;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {

    ArrayList<Ingredient> ingredients;
    ArrayList<Recipe> recipes;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Bundle extras = getIntent().getExtras();

        list = (ListView) findViewById(R.id.recipieList);

        if(extras != null) {
            ingredients = (ArrayList<Ingredient>) extras.get("ingredients");
        } else {
            ingredients = new ArrayList<Ingredient>();
            ingredients.add(new Ingredient("Chicken"));
            ingredients.add(new Ingredient("Milk"));
            ingredients.add(new Ingredient("Cheese"));
        }

        API api = new API(getApplicationContext(), ingredients);
        recipes = api.getRecipes();
        recipes = new ArrayList<Recipe>();
        recipes.add(new Recipe("Tim", "Yummy Chicken",  BitmapFactory.decodeResource(this.getResources(), R.drawable.test_food_photo), "Google.com", 100));
        recipes.add(new Recipe("Bill", "Yummy Chicken",  BitmapFactory.decodeResource(this.getResources(), R.drawable.test_food_photo), "Google.com", 100));
        RecipeDisplay recipeDisplay = new RecipeDisplay(this, recipes);
        list.setAdapter(recipeDisplay);
    }
}
