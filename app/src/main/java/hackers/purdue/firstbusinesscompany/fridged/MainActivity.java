package hackers.purdue.firstbusinesscompany.fridged;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class MainActivity extends AppCompatActivity {


    ListView fridgeItems;
    BaseAdapter adapter;
    SharedPreferences fridgeSharedPreferences;
    ArrayList<String> fridgeArray;
    String currentSort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fridgeSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (fridgeSharedPreferences.contains("fridges")) {
            fridgeArray = new ArrayList<String> (Arrays.asList(fridgeSharedPreferences.getString("fridges", "").split(",")));
        } else {
            fridgeArray = new ArrayList<>();
        }
        fridgeItems = (ListView) findViewById(R.id.fridgeList);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fridgeArray);
        currentSort = "Alphabetical(Ascending)";

        fridgeItems.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fridgeArray.add(0, "hi");
                fridgeArray.add(1, "");
                updateListView();
                fridgeArray.clear();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            setContentView(R.layout.settings);
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateListView() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < fridgeArray.size(); i++) {
            builder.append(fridgeArray.get(i)).append(",");
        }
        fridgeSharedPreferences.edit().putString("fridges", builder.toString()).apply();
        adapter.notifyDataSetChanged();
    }
}
