package lear.scranton.edu.tipcalculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import org.w3c.dom.Text;

import java.text.DecimalFormat;


public class Main3Activity extends AppCompatActivity implements View.OnClickListener{

    private double mMyTotal;
    private double mMyPercent;
    private double mMyGuests;
    private double mTip;
    private double mPerPerson;
    private TextView mDisplayTotalCost;
    private TextView mDisplayTotalTip;
    private TextView mDisplayPerPerson;
    private DecimalFormat money;
    private double realTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        mDisplayTotalCost = (TextView) findViewById(R.id.total_final);
        mDisplayTotalTip = (TextView) findViewById(R.id.tip);
        mDisplayPerPerson = (TextView) findViewById(R.id.per_person);
        money = new DecimalFormat("$0.00");

        Intent intent = getIntent();
        double defaultDouble= -1.00;
        mMyTotal = intent.getDoubleExtra(ActivityGuestsAndPercent.MY_TOTAL, defaultDouble);
        mMyGuests = intent.getDoubleExtra(ActivityGuestsAndPercent.MY_GUESTS, defaultDouble);
        mMyPercent = intent.getDoubleExtra(ActivityGuestsAndPercent.MY_PERCENT, defaultDouble);
        mTip = mMyTotal * mMyPercent;
        realTotal = mTip + mMyTotal;
        mDisplayTotalCost.setText("Total:" + money.format(realTotal));
        mDisplayTotalTip.setText("Tip Total:" + money.format(mTip));
        TextView mDisplayTotalGuests =  (TextView) findViewById(R.id.guests_display);
        mDisplayTotalGuests.setText("Number of Guests:" + (int) mMyGuests);
        mPerPerson = realTotal/mMyGuests;
        mDisplayPerPerson.setText("Amount Per Person:" + money.format(mPerPerson));

        Button one = (Button) findViewById(R.id.round_up);
        one.setOnClickListener(this);
        Button two = (Button) findViewById(R.id.round_down);
        two.setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.round_up:
                if ((mPerPerson != 0))
                {
                    double temp = Math.round(mPerPerson);
                    if (temp < mPerPerson)
                        temp+=1;
                    mPerPerson = temp;
                    //mTip= mPerPerson*mMyGuests;
                    realTotal = mPerPerson * mMyGuests;
                    mDisplayPerPerson.setText("Amount Per Person:" + money.format(mPerPerson));
                    mDisplayTotalCost.setText("Total:" + money.format(realTotal));
                    mDisplayTotalTip.setText("Tip Total:" + money.format(realTotal-mMyTotal));
                }
            case R.id.round_down:
                if ((mPerPerson != 0))
                {
                    double temp = Math.round(mPerPerson);
                    if (temp > mPerPerson)
                        temp-=1;
                    mPerPerson = temp;
                    //mTip= mPerPerson*mMyGuests;
                    realTotal = mPerPerson * mMyGuests;
                    mDisplayPerPerson.setText("Amount Per Person:" + money.format(mPerPerson));
                    mDisplayTotalCost.setText("Total:" + money.format(realTotal));
                    mDisplayTotalTip.setText("Tip Total:" + money.format(realTotal-mMyTotal));
                }

        }
    }
}
