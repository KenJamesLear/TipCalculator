package lear.scranton.edu.tipcalculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Button;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private double total;
    public static final String MY_TOTAL = "lear.scranton.edu.tipcalculator.my_total";
    public static final int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button one = (Button) findViewById(R.id.one_button);
        one.setOnClickListener(this); // calling onClick() method
        Button two = (Button) findViewById(R.id.two_button);
        two.setOnClickListener(this);
        Button three = (Button) findViewById(R.id.three_button);
        three.setOnClickListener(this);
        Button four = (Button) findViewById(R.id.four_button);
        four.setOnClickListener(this); // calling onClick() method
        Button five = (Button) findViewById(R.id.five_button);
        five.setOnClickListener(this); // calling onClick() method
        Button six = (Button) findViewById(R.id.six_button);
        six.setOnClickListener(this); // calling onClick() method
        Button seven = (Button) findViewById(R.id.seven_button);
        seven.setOnClickListener(this); // calling onClick() method
        Button eight = (Button) findViewById(R.id.eight_button);
        eight.setOnClickListener(this); // calling onClick() method
        Button nine = (Button) findViewById(R.id.nine_button);
        nine.setOnClickListener(this); // calling onClick() method
        Button back = (Button) findViewById(R.id.back_button);
        back.setOnClickListener(this); // calling onClick() method
        Button zero = (Button) findViewById(R.id.zero_button);
        zero.setOnClickListener(this); // calling onClick() method
        Button next = (Button) findViewById(R.id.next_button);
        next.setOnClickListener(this); // calling onClick() method

        total = 0;



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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {
        // default method for handling onClick Events..
        switch (v.getId()) {

            case R.id.one_button:
                addTotal(1.00);
                break;
            case R.id.two_button:
                addTotal(2.00);
                break;
            case R.id.three_button:
                addTotal(3.00);
                break;
            case R.id.four_button:
                addTotal(4.00);
                break;
            case R.id.five_button:
                addTotal(5.00);
                break;
            case R.id.six_button:
                addTotal(6.00);
                break;
            case R.id.seven_button:
                addTotal(7.00);
                break;
            case R.id.eight_button:
                addTotal(8.00);
                break;
            case R.id.nine_button:
                addTotal(9.00);
                break;
            case R.id.zero_button:
                addTotal(0.00);
                break;
            case R.id.back_button:
                backTotal();
                break;
            case R.id.next_button:
                startTheActivity();
                break;
        }
    }

    private void addTotal(double input)
    {
        if (total == 0){
            total = (input * .01);
        }
        else
        {
            total = (total * 10) + (input * .01);
        }
        TextView displayTotalCost = (TextView) findViewById(R.id.total_display_title);
        DecimalFormat money = new DecimalFormat("$0.00");
        displayTotalCost.setText("Total:" + money.format(total));

    }

    private void backTotal(){
        total = total *.10;
        if (total < .01)
            total = 0.00;
        TextView displayTotalCost = (TextView) findViewById(R.id.total_display_title);
        DecimalFormat money = new DecimalFormat("$0.00");
        displayTotalCost.setText("Total:"+ money.format(total));
    }

    public void startTheActivity() {
        Intent intent = new Intent(this, ActivityGuestsAndPercent.class);
        //Toast.makeText(this, "Entered:" + message, Toast.LENGTH_LONG).show();
        intent.putExtra(MY_TOTAL, total);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        savedInstanceState.putDouble("MyTotal", total);
        // etc.
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        total = savedInstanceState.getDouble("MyTotal");
        TextView displayTotalCost = (TextView) findViewById(R.id.total_display_title);
        DecimalFormat money = new DecimalFormat("$0.00");
        displayTotalCost.setText("Total:"+ money.format(total));
    }
}

