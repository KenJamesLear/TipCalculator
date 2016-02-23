package lear.scranton.edu.tipcalculator;

/**
 * Created by teddylear on 2/16/2016.
 */

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;
import android.widget.Spinner;
import java.text.DecimalFormat;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ActivityGuestsAndPercent extends AppCompatActivity implements
                                        AdapterView.OnItemSelectedListener,
                                        OnSeekBarChangeListener {

    private double mMyTotal;
    private double mGuests;
    private double mPercent;
    private double mPerPerson;
    private SeekBar mBar;
    private TextView  mTextAction;
    public static final String MY_TOTAL = "lear.scranton.edu.tipcalculator.my_total";
    public static final String MY_GUESTS = "lear.scranton.edu.tipcalculator.my_guests";
    public static final String MY_PERCENT = "lear.scranton.edu.tipcalculator.my_percent";
    public static final int REQUEST_CODE = 200;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        double defaultDouble= -1.00;
        mMyTotal = intent.getDoubleExtra(MainActivity.MY_TOTAL, defaultDouble);
        TextView displayTotalCost = (TextView) findViewById(R.id.total_display_title_again);
        DecimalFormat money = new DecimalFormat("$0.00");
        displayTotalCost.setText(
                "Total:" + money.format(mMyTotal));

        mGuests = 1;
        mPercent = 0;
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.percentArray, android.R.layout.simple_spinner_dropdown_item);
        //adapter = ArrayAdapter.createFromResource(this, R.array.percentArray, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);


        mBar = (SeekBar)findViewById(R.id.percentBar);
        mBar.setOnSeekBarChangeListener(this);
        mTextAction = (TextView)findViewById(R.id.percentBarProgress);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {
        String item = parent.getItemAtPosition(pos).toString();
        mGuests = Double.parseDouble(item);
        Toast.makeText(this, "You Selected " + mGuests, Toast.LENGTH_SHORT).show();
        //mGuests = parent.getItemIdAtPosition(pos);
        /*Spinner mySpinner=(Spinner) findViewById(R.id.spinner);
        String text = mySpinner.getItemAtPosition(po*/
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Dummy
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.tenPercent:
                if (checked)
                   mPercent = .10;
                   mBar.setProgress(10);
                    break;
            case R.id.fifteenPercent:
                if (checked)
                    mPercent = .15;
                    mBar.setProgress(15);
                    break;
            case R.id.eighteenPercent:
                if (checked)
                    mPercent = .18;
                    mBar.setProgress(18);
                    break;
            case R.id.twentyFivePercent:
                if (checked)
                    mPercent = .25;
                    mBar.setProgress(25);
                    break;

        }
    }

    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        mTextAction.setText("Percent:" + mPercent +"%");
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        seekBar.setSecondaryProgress(seekBar.getProgress());
        int progress = seekBar.getProgress();
        mPercent = (double)progress/100;
        mTextAction.setText("Percent:" + mPercent +"%");
        Toast.makeText(this, "You Selected " + mPercent, Toast.LENGTH_SHORT).show();
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        mTextAction.setText("started touching");
    }

    public void startCalc(View view){
        Intent intent = new Intent(this, Main3Activity.class);
        //Toast.makeText(this, "Entered:" + message, Toast.LENGTH_LONG).show();
        intent.putExtra(MY_TOTAL, mMyTotal);
        intent.putExtra(MY_GUESTS,mGuests);
        intent.putExtra(MY_PERCENT,mPercent);
        startActivityForResult(intent, REQUEST_CODE);
    }





}
