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


public class Main3Activity extends AppCompatActivity {

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


    }

        public void roundUpfunc(View view){
            if ((mPerPerson != 0)) {
                double temp = Math.round(mPerPerson);
                if (temp < mPerPerson)
                    temp += 1;
                //mPerPerson = temp;
                //mTip= mPerPerson*mMyGuests;
                realTotal = temp * mMyGuests;
                mDisplayPerPerson.setText("Amount Per Person:" + money.format(temp));
                mDisplayTotalCost.setText("Total:" + money.format(realTotal));
                mDisplayTotalTip.setText("Tip Total:" + money.format(realTotal - mMyTotal));
                }
            }

        public void roundDownfunc(View view){
            if ((mPerPerson != 0) && mTip > 1)
            {
                double temp = Math.round(mPerPerson);
                if (temp > mPerPerson)
                    temp-=1;

                //mTip= mPerPerson*mMyGuests;
                //realTotal = mPerPerson * mMyGuests;
                realTotal = temp * mMyGuests;
                mDisplayPerPerson.setText("Amount Per Person:" + money.format(temp));
                mDisplayTotalCost.setText("Total:" + money.format(realTotal));
                mDisplayTotalTip.setText("Tip Total:" + money.format(realTotal-mMyTotal));
            }
        }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putDouble("mMyTotal", mMyTotal);
        savedInstanceState.putDouble("mMyPercent", mMyPercent);
        savedInstanceState.putDouble("mMyGuest", mMyGuests);
        savedInstanceState.putDouble("mMyTotal", mTip);
        savedInstanceState.putDouble("mPerPerson", mPerPerson);
        savedInstanceState.putDouble("realTotal", realTotal);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mTip = savedInstanceState.getDouble("mTip",mTip);
        mPerPerson = savedInstanceState.getDouble("mPerPerson",mPerPerson);

        //realTotal = savedInstanceState.getDouble("realTotal");
        //mDisplayTotalCost.setText("Total:" + money.format(realTotal));
       // mDisplayTotalTip.setText("Tip Total:" + money.format(mTip));
       // TextView mDisplayTotalGuests =  (TextView) findViewById(R.id.guests_display);
       // mDisplayTotalGuests.setText("Number of Guests:" + (int) mMyGuests);
       // mPerPerson = realTotal/mMyGuests;
       // mDisplayPerPerson.setText("Amount Per Person:" + money.format(mPerPerson));
    }
}
