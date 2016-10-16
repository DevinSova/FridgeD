package hackers.purdue.firstbusinesscompany.fridged;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Neosk on 10/16/2016.
 */

public class addItem extends AppCompatActivity
{
    EditText input;
    String newItem;
    Button done;
    Button cancel;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prompt);
        input = (EditText) findViewById(R.id.input);
        done = (Button) findViewById(R.id.donebutton);

    }
    public void onDoneClick(View view)
    {
        Intent sendIntent = new Intent();
        newItem = input.getText().toString();
        sendIntent.putExtra("edittextvalue", newItem);
        finish();

    }
}
