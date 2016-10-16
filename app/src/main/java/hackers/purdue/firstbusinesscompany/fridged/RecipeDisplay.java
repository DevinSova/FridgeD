package hackers.purdue.firstbusinesscompany.fridged;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDisplay extends Fragment {

    ImageView image;
    TextView title;
    TextView publisher;
    Recipe recipe;


    public RecipeDisplay() {
        // Required empty public constructor
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return this.recipe;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_display, container, false);

        image = (ImageView) view.findViewById(R.id.image);
        title = (TextView) view.findViewById(R.id.title);
        publisher = (TextView) view.findViewById(R.id.publisher);

        image.setImageBitmap(recipe.getImage());
        title.setText(recipe.getTitle());
        publisher.setText(recipe.getPublisher());

        return view;
    }

    public void onClick(View v) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipe.getUrl()));
        startActivity(browserIntent);
    }

}
