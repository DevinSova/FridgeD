package hackers.purdue.firstbusinesscompany.fridged;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDisplay extends BaseAdapter {

    ImageView image;
    TextView title;
    TextView publisher;
    Context context;
    ArrayList<Recipe> recipes;
    private static LayoutInflater inflater = null;

    public RecipeDisplay(Context context, ArrayList<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_recipe_display, container, false);
//
//        image = (ImageView) view.findViewById(R.id.image);
//        title = (TextView) view.findViewById(R.id.title);
//        publisher = (TextView) view.findViewById(R.id.publisher);
//
//        image.setImageBitmap(recipe.getImage());
//        title.setText(recipe.getTitle());
//        publisher.setText(recipe.getPublisher());
//
//        return view;
//    }



//    public void onClick(View v) {
//        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipe.getUrl()));
//        startActivity(browserIntent);
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null)
            view = inflater.inflate(R.layout.fragment_recipe_display, null);

        image = (ImageView) view.findViewById(R.id.image);
        title = (TextView) view.findViewById(R.id.title);
        publisher = (TextView) view.findViewById(R.id.publisher);

        image.setImageBitmap(recipes.get(position).getImage());
        title.setText(recipes.get(position).getTitle());
        publisher.setText(recipes.get(position).getPublisher());

        return view;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return recipes.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return recipes.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
}
