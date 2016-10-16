package hackers.purdue.firstbusinesscompany.fridged;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
    InputMethodManager keyboardManager;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prompt);
        input = (EditText) findViewById(R.id.input);
        done = (Button) findViewById(R.id.donebutton);
        keyboardManager= (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
        keyboardManager.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);

    }
    public void onDoneClick(View view)
    {
        keyboardManager.hideSoftInputFromWindow(view.getWindowToken(),0);
        Intent sendIntent = new Intent();
        newItem = input.getText().toString();
        sendIntent.putExtra("addItemString", newItem);
        setResult(RESULT_OK, sendIntent);
        finish();
    }
}
