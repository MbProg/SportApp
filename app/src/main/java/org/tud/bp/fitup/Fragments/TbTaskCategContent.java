package org.tud.bp.fitup.Fragments;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.tud.bp.fitup.Adapters.TaskCategAdapter;
import org.tud.bp.fitup.BusinessLayer.TaskCategory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by M.Braei on 07.01.2017.
 */

public class TbTaskCategContent extends TabFragment {

    View view;
    ListView listView;
    List<TaskCategory> taskCategories;
    RecyclerView rv;

    TbTaskCategContent tbTaskCategContent = null;

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % spanCount;

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;
                if (position < spanCount) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            } else {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount) {
                    outRect.top = spacing;
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setTitle(getString(org.tud.bp.fitup.R.string.aktivitaeten));
        view = inflater.inflate(org.tud.bp.fitup.R.layout.tbtaskcontent, container, false);

        TaskCategory tc1 = new TaskCategory(getString(org.tud.bp.fitup.R.string.tagebucheintrag), getString(org.tud
                .bp.fitup.R.string
                .tagebucheintrag_desc), org.tud.bp.fitup.R.mipmap.ic_tagebuch_eintrag);
        TaskCategory tc2 = new TaskCategory(getString(org.tud.bp.fitup.R.string.stimmungsabgabe), getString(org.tud.bp.fitup.R.string
                .stimmungsabgabe_desc), org.tud.bp.fitup.R.mipmap.ic_stimmungs_abgabe);
        TaskCategory tc3 = new TaskCategory(getString(org.tud.bp.fitup.R.string.aktivitaetsfragebogen), getString(org.tud.bp.fitup.R.string
                .aktivitaetsfragebogen_desc), org.tud.bp.fitup.R.mipmap.ic_aktivitaets_fragebogen);
        TaskCategory tc4 = new TaskCategory(getString(org.tud.bp.fitup.R.string.fitnessfragebogen), getString(org.tud.bp.fitup.R.string
                .fitnessfragebogen_desc), org.tud.bp.fitup.R.mipmap.ic_fittnessfragebogen);
        TaskCategory tc5 = new TaskCategory(getString(org.tud.bp.fitup.R.string.trainingszeiten_und_studioadresse),
                getString(org.tud.bp.fitup.R.string
                .wann_und_wo_findet_training_statt), org.tud.bp.fitup.R.mipmap.ic_trainings_zeiten_ort);
        TaskCategory tc6 = new TaskCategory(getString(org.tud.bp.fitup.R.string.Challenges), getString(org.tud.bp.fitup.R.string
                .wann_und_wo_findet_training_statt), org.tud.bp.fitup.R.mipmap.ic_challenge);
        taskCategories = new LinkedList<TaskCategory>(Arrays.asList(tc1, tc2, tc3, tc4, tc5, tc6));

        rv = (RecyclerView) view.findViewById(org.tud.bp.fitup.R.id.recyclerTaskCateg);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);

        // set the recyclerview in a two column gridview to have squares as cards
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(mLayoutManager);
        rv.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        rv.setItemAnimator(new DefaultItemAnimator());

        rv.setAdapter(new TaskCategAdapter(taskCategories, this));
        tbTaskCategContent = this;
        return view;

    }


    @Override
    public void onStart() {


        super.onStart();

    }

}
