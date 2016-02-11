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

public class MainActivity extends AppCompatActivity {

    private double percent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onRadioButtonClicked(View button){
        boolean checked = ((RadioButton) button).isChecked();
        switch(button.getId()) {
            case R.id.radio_ten:
                if (checked)
                    percent = .1;
                    break;
            case R.id.radio_fifteen:
                if (checked)
                    percent = .15;
                    break;
            case R.id.radio_eighteen:
                if (checked)
                    percent = .18;
                    break;
            case R.id.radio_twenty_five:
                if (checked)
                    percent = .25;
                    break;
        }
    }

    public void sendMessage(View view) {
        EditText billTotal = (EditText) findViewById(R.id.bill_total_input);
        EditText peopleAmount = (EditText) findViewById(R.id.num_people);
        double realBillTotal= Double.parseDouble(billTotal.getText().toString());
        double realPeopleAmount = Double.parseDouble(peopleAmount.getText().toString());
        if(realBillTotal != 0 && realPeopleAmount != 0){
            double tip = realBillTotal * percent;
            double total = realBillTotal + tip;
            double perPerson = total / realPeopleAmount;

            TextView displayTotalCost = (TextView) findViewById(R.id.total_cost);
            TextView displayperPerson = (TextView) findViewById(R.id.perPersonAmount);

            displayTotalCost.setText("Total Cost:" + total);
            displayperPerson.setText("Per Person Cost:" + perPerson);
        }
    }

    public void onReset(View view){
        TextView displayTotalCost = (TextView) findViewById(R.id.total_cost);
        TextView displayperPerson = (TextView) findViewById(R.id.perPersonAmount);
        EditText total = (EditText) findViewById(R.id.bill_total_input);
        EditText people = (EditText)findViewById(R.id.num_people);

        displayTotalCost.setText("Total Cost:");
        displayperPerson.setText("Per Person Cost:");
        total.setText("");
        people.setText("");

    }


}

