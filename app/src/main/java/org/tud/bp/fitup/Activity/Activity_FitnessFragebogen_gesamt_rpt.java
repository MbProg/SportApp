package org.tud.bp.fitup.Activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;

import org.tud.bp.fitup.DataAccessLayer.DAL_Utilities;
import org.tud.bp.fitup.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Date;

public class Activity_FitnessFragebogen_gesamt_rpt extends Activity_LineChartReports {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__fitness_fragebogen_gesamt_rpt);
        lineChart = (LineChart) findViewById(R.id.lineChart);
    }
    // hook method of our template method computeDrawFive
    float convertValue(DataSnapshot object) {
        // first try with double because it is the default value in firebase
        // otherwise we try it with Long
        try {
            return ((Double) object.getValue()).floatValue();

        } catch (ClassCastException ex) {
            return ((Long) object.getValue()).floatValue();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context_report, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {

            case R.id.thirtydays:
                computeDrawFive(addDays(new Date(), -30), new Date(), lineChart, Activity_FitnessFragebogen_gesamt_rpt.this
                        .getString(R.string.gesamtscore_fitnessfragebogen));

                return true;
            case R.id.fourteendays:
                computeDrawFive(addDays(new Date(), -14), new Date(), lineChart, Activity_FitnessFragebogen_gesamt_rpt.this
                        .getString(R.string.gesamtscore_fitnessfragebogen));
                return true;
            case R.id.sevendays:
                computeDrawFive(addDays(new Date(), -7), new Date(), lineChart, Activity_FitnessFragebogen_gesamt_rpt.this
                        .getString(R.string.gesamtscore_fitnessfragebogen));
                return true;
            case R.id.alldays:
                computeDrawFive(null, new Date(), lineChart,  Activity_FitnessFragebogen_gesamt_rpt.this
                        .getString(R.string.gesamtscore_fitnessfragebogen));
                return true;
            default:
                throw new InvalidParameterException("The menu items is not declared");
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        computeDrawFive(addDays(new Date(), -7), new Date(), lineChart, Activity_FitnessFragebogen_gesamt_rpt.this.getString
                (R.string.gesamtscore_fitnessfragebogen));
    }


    /**
     * @param Startdate  set that start interval for the data. Set this to null if you want all data be displayed
     * @param EndDate
     * @param lineChart
     * @param legend
     */
    protected void computeDrawFive(final Date Startdate, final Date EndDate, final LineChart lineChart, final String
            legend) {
        try {
            // first show the progress dialog
            pd = new ProgressDialog(this);
            pd.setMessage(getString(R.string.wird_geladen));
            pd.show();

            // then run
            final ArrayList<String> xAXES = new ArrayList<>();
            final ArrayList<Entry> yAXES_Gesamt = new ArrayList<>();
            URL url = new URL(DAL_Utilities.getDatabaseURL(this) + "users/" + ActivityMain.getMainUser(this).getName() +
                    "/FitnessFragebogen/");
            final Firebase root = new Firebase(url.toString());

            root.addListenerForSingleValueEvent(new ValueEventListener() {

                                                    // Hier kriegst du den Knoten date zurueck
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        String x = "";
                                                        float y_Gesamt = 0;

                                                        float max_y = 0;
                                                        float min_y = 0;
                                                        boolean flag = false;
                                                        int j = 0;

                                                        // dataSnapshot.getKey() declares which strategy the
                                                        // notification belongs to (Stimmungsabgabe....)
                                                        // the child.key of dataSnapshop declare the unique datetime
                                                        // of the notification
                                                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                                                            // Here I get the time
                                                            final String sDate = child.getKey();// Date
                                                            Date d = new Date();

                                                            final String sTime = "00:00:00";
                                                            d = DAL_Utilities.ConvertFirebaseKeyStringToDateTime
                                                                    (sDate + " " + sTime);

                                                            if (Startdate == null || (!d.before(Startdate) && !d
                                                                    .after(EndDate))) {
                                                                int i = 0;
                                                                i++;
                                                                for (DataSnapshot child2L : child.getChildren()) {
                                                                    // Now convert the string date to real date
                                                                    // create the object and insert it in the list
                                                                     if (child2L.getKey().equals
                                                                            ("Score_Gesamt")) {
                                                                        y_Gesamt += convertValue(child2L);
                                                                        flag = true;
                                                                    }
                                                                }

                                                                // get the average of on day
                                                                if (flag) {
                                                                    // if one of them wasn't filled in an iteration,
                                                                    // we consider it like zero! Therefore i as a
                                                                    // devider for all
                                                                    y_Gesamt = y_Gesamt / i;
                                                                    // Now we have the days
                                                                    x = DAL_Utilities.ConvertDateToString
                                                                            (DAL_Utilities
                                                                                    .ConvertFirebaseKeyStringToDateTime(sDate + " 00:00:00"));
                                                                    max_y =  Math.max(y_Gesamt, max_y);
                                                                    min_y = Math.min(y_Gesamt,min_y);
                                                                    xAXES.add(x);
                                                                    yAXES_Gesamt.add(new Entry(y_Gesamt, j));


                                                                    flag = false;
                                                                    j++;
                                                                    y_Gesamt =  0;
                                                                }
                                                            }
                                                        }
                                                        if (xAXES.size() != 0) {

                                                            ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

                                                            // set the datasets of our chart
                                                            LineDataSet lineDataSet3 = new LineDataSet(yAXES_Gesamt,
                                                                    Activity_FitnessFragebogen_gesamt_rpt.this.getString(R
                                                                            .string.gesamt));
                                                            lineDataSet3.setDrawCircles(true);
                                                            lineDataSet3.setColor(Color.RED);
                                                            lineDataSets.add(lineDataSet3);

                                                            lineChart.invalidate();
                                                            // set the visual properties of the chart
                                                            lineChart.getAxisLeft().resetAxisMinValue();
                                                            lineChart.getAxisLeft().setAxisMinValue((float) Math
                                                                    .floor(min_y) - 0.5f);
                                                            lineChart.getAxisLeft().setAxisMaxValue((float) Math.ceil
                                                                    (max_y) + 0.5f);
                                                            lineChart.getAxisRight().setTextColor(Color.WHITE);
                                                            lineChart.setExtraLeftOffset(15);
                                                            lineChart.setExtraRightOffset(10);
                                                            lineChart.setData(new LineData(xAXES, lineDataSets));
                                                            lineChart.setDragEnabled(true);
                                                            lineChart.setScaleEnabled(true);
                                                            lineChart.setDescription("");
                                                            lineChart.setHighlightPerDragEnabled(true);
                                                            lineChart.getAxisRight().setDrawGridLines(false);
                                                            lineChart.getLegend().setEnabled(false);


                                                        }
                                                        // after all the data process has been done
                                                        // close the progress dialog
                                                        pd.dismiss();

                                                    }

                                                    @Override
                                                    public void onCancelled(FirebaseError firebaseError) {

                                                    }
                                                }
            );
        } catch (Exception ex) {
            Log.e("Exc", ex.getMessage());
        }

    }


}

