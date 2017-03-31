package org.tud.bp.fitup.Adapters;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.tud.bp.fitup.BusinessLayer.FitnessFragebogen;

import java.util.ArrayList;

/**
 * Created by Felix on 03.03.2017.
 */
public class FitnessFragebogenViewAdapter extends BaseAdapter {
    private Activity _context;
    private ArrayList<String> antworten;
    private int ImageId;
    private FitnessFragebogen fitnessFragebogen = null;
    private String subject;
    private Integer selectedIndex = -1;

    /**
     * Instantiates a new Fitness fragebogen view adapter.
     *
     * @param context
     */
    public FitnessFragebogenViewAdapter(Activity context) {
        antworten = new ArrayList<String>();
        antworten.add(context.getString(org.tud.bp.fitup.R.string.Ich_kann_diese_Taetigkeit_nicht));
        antworten.add(context.getString(org.tud.bp.fitup.R.string.Ich_habe_große_Probleme));
        antworten.add(context.getString(org.tud.bp.fitup.R.string.Ich_habe_maeßige_Probleme));
        antworten.add(context.getString(org.tud.bp.fitup.R.string.Ich_habe_leichte_Probleme));
        antworten.add(context.getString(org.tud.bp.fitup.R.string.Ich_habe_keine_Probleme));

        _context = context;
    }

    /**
      * @param fitnessFragebogen
     * @param subject
     */
    public void setFitnessFragebogen(FitnessFragebogen fitnessFragebogen, String subject) {
        this.fitnessFragebogen = fitnessFragebogen;
        this.subject = subject;
    }

    @Override
    public Object getItem(int position) {return antworten.get(position);}

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        String nt = antworten.get(position);
        if (view == null)
            view = _context.getLayoutInflater().inflate(org.tud.bp.fitup.R.layout.lst_stimmnungsabgabe_cell, null);

        TextView txtTitle = (TextView) view.findViewById(org.tud.bp.fitup.R.id.txtTitle);
        txtTitle.setText(nt);

        if (position == selectedIndex) {
            txtTitle.setBackgroundColor(Color.parseColor("#037f23"));
        } else {
            txtTitle.setBackgroundColor(Color.parseColor("#4b6df2"));
        }

        return view;
    }

    /**
     * @param position
     */
    public void setSelectedIndex(Integer position) {
        selectedIndex = position;
    }

    /**
     * @return the selected index
     */
    public Integer getSelectedIndex() {
        return selectedIndex;
    }

    @Override
    public int getCount() {
        return antworten.size();
    }
}



