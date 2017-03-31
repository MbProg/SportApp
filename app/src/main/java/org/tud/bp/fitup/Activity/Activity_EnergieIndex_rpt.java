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

public class Activity_EnergieIndex_rpt extends Activity_LineChartReports {

    LineChart lineChart;

    // hook method of our template method computeDraw
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(org.tud.bp.fitup.R.layout.activity__energie_index_rpt);
        lineChart = (LineChart) findViewById(org.tud.bp.fitup.R.id.lineChart);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(org.tud.bp.fitup.R.menu.menu_context_report, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case org.tud.bp.fitup.R.id.thirtydays:
                computeDraw(addDays(new Date(), -30), new Date(), lineChart, "Energieindex",
                        Activity_EnergieIndex_rpt.this.getString(org.tud.bp.fitup.R.string.werte_fuer_energieindex));
                return true;
            case org.tud.bp.fitup.R.id.fourteendays:
                computeDraw(addDays(new Date(), -14), new Date(), lineChart, "Energieindex",
                        Activity_EnergieIndex_rpt.this.getString(org.tud.bp.fitup.R.string.werte_fuer_energieindex));
                return true;
            case org.tud.bp.fitup.R.id.sevendays:
                computeDraw(addDays(new Date(), -7), new Date(), lineChart, "Energieindex", Activity_EnergieIndex_rpt
                        .this.getString(org.tud.bp.fitup.R.string.werte_fuer_energieindex));
                return true;
            case org.tud.bp.fitup.R.id.alldays:
                computeDraw(null, new Date(), lineChart, "Score Energieindex", Activity_EnergieIndex_rpt.this
                        .getString(org.tud.bp.fitup.R.string.werte_fuer_energieindex));
                return true;
            default:
                throw new InvalidParameterException("The menu items is not declared");
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        computeDraw(addDays(new Date(), -7), new Date(), lineChart, "Energieindex", Activity_EnergieIndex_rpt.this
                .getString(org.tud.bp.fitup.R.string.werte_fuer_energieindex));
    }


}


