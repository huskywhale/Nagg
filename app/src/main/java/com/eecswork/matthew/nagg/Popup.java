package com.eecswork.matthew.nagg;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Matthew on 12/4/2014.
 */
public class Popup extends FragmentActivity {


    TextView date;
    TextView time;
    TextView dif;
    PopupWindow pw;
    public Popup(View rootView)
    {
        LayoutInflater inflater = (LayoutInflater) rootView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        WindowManager wm = (WindowManager) rootView.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display disp = wm.getDefaultDisplay();

        pw = new PopupWindow(inflater.inflate(R.layout.hover_window, null, false),disp.getWidth()-160,disp.getHeight()-300, true);
        pw.showAtLocation(rootView.findViewById(R.id.important_list), Gravity.CENTER, 0, 0);

        date =         ((TextView)pw.getContentView().findViewById(R.id.new_Date_Text));

        time =         ((TextView)pw.getContentView().findViewById(R.id.new_Time_Text));

        dif =         ((TextView)pw.getContentView().findViewById(R.id.new_Dif_Text));


        Button b = ((Button)pw.getContentView().findViewById(R.id.ok_button));


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button date_b = ((Button)pw.getContentView().findViewById(R.id.new_Date));
        Button time_b =((Button)pw.getContentView().findViewById(R.id.new_Time));
        Button dif_b = ((Button)pw.getContentView().findViewById(R.id.new_Dif));

        date_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //open dialog to ask for date
            }
        });

        time_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showTimePickerDialog(v);
            }
        });

        dif_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //open dialog to prompt for a number between 1 and 10
            }
        });

    }
    public void finish()
    {
        pw.dismiss();
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerMenu();
        newFragment.show(getFragmentManager(), "timePicker");
    }

    public static class TimePickerMenu extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Return to parent instance.
        }
    }
}
