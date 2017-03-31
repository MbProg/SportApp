package org.tud.bp.fitup.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;

import com.firebase.client.DataSnapshot;
import com.github.mikephil.charting.charts.LineChart;

import java.security.InvalidParameterException;
import java.util.Date;

public class Activity_Trainings_rpt extends Activity_LineChartReports {

    @Override
    float convertValue(DataSnapshot object) {
        try {
            return ((Long) object.getValue()).floatValue();

        } catch (ClassCastException ex) {
            return ((Double) object.getValue()).floatValue();
        }
        //return 0;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(org.tud.bp.fitup.R.layout.activity_trainings_rpt);
        lineChart = (LineChart) findViewById(org.tud.bp.fitup.R.id.lineChart);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        //set an other menu xml
        inflater.inflate(org.tud.bp.fitup.R.menu.menu_context_report, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {

            case org.tud.bp.fitup.R.id.thirtydays:
                computeScores(addDays(new Date(), -30), new Date(), lineChart, "totalPoints", Activity_Trainings_rpt
                        .this.getString(org.tud.bp.fitup.R.string.erzielte_punkte));
                return true;
            case org.tud.bp.fitup.R.id.fourteendays:
                computeScores(addDays(new Date(), -14), new Date(), lineChart, "totalPoints", Activity_Trainings_rpt
                        .this.getString(org.tud.bp.fitup.R.string.erzielte_punkte));
                return true;
            case org.tud.bp.fitup.R.id.sevendays:
                computeScores(addDays(new Date(), -7), new Date(), lineChart, "totalPoints", Activity_Trainings_rpt
                        .this.getString(org.tud.bp.fitup.R.string.erzielte_punkte));
                return true;
            case org.tud.bp.fitup.R.id.alldays:
                computeDraw(null, new Date(), lineChart, "totalPoints", Activity_Trainings_rpt.this
                        .getString(org.tud.bp.fitup.R.string.erzielte_punkte));
                return true;
            default:
                throw new InvalidParameterException("The menu items is not declared");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        computeScores(addDays(new Date(), -7), new Date(), lineChart, "totalPoints", Activity_Trainings_rpt.this
                .getString(org.tud.bp.fitup.R.string.erzielte_punkte));
    }


}