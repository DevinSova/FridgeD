package hackers.purdue.firstbusinesscompany.fridged;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static android.R.attr.button;
import static android.R.attr.prompt;


public class MainActivity extends AppCompatActivity {


    ListView fridgeItems;
    BaseAdapter adapter;
    SharedPreferences fridgeSharedPreferences;
    ArrayList<String> fridgeArray;
    char currentSort;
    EditText stringInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fridgeSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (fridgeSharedPreferences.contains("fridges")) {
            fridgeArray = new ArrayList<String>(Arrays.asList(fridgeSharedPreferences.getString("fridges", "").split(",")));
        }
        else {
            fridgeArray = new ArrayList<>();
        }
        fridgeItems = (ListView) findViewById(R.id.fridgeList);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fridgeArray);
        currentSort = 'A';


        fridgeItems.setAdapter(adapter);
        //////////////////////////////////////////////////////////////////////////////
        LayoutInflater input = LayoutInflater.from(getApplicationContext());
        View inputView = input.inflate(R.layout.prompt, null);

        AlertDialog.Builder inputDialogBuilder = new AlertDialog.Builder(getApplicationContext());
        //Set prompt.xml to the builder
        inputDialogBuilder.setView(prompt);
        stringInput = (EditText) inputView.findViewById(R.id.input);

        //Setup the message
        inputDialogBuilder.setCancelable(false).setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fridgeArray.add(stringInput.getText().toString());
            }
        });
        inputDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog inputDialog = inputDialogBuilder.create();

        //////////////////////////////////////////////////////////////////////////////

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDialog.show();
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
        if (id == R.id.action_sort) {
            if(currentSort == 'A') {
                currentSort = 'D';
                Collections.sort(fridgeArray, String.CASE_INSENSITIVE_ORDER);
                Collections.reverse(fridgeArray);
                updateListView();
            }
            else{
                currentSort = 'A';
                Collections.sort(fridgeArray, String.CASE_INSENSITIVE_ORDER);
                updateListView();
            }
        }

        else if(id == R.id.recipies) {

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