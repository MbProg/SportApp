package org.tud.bp.fitup.Adapters;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.tud.bp.fitup.Activity.ActivityMotivationMessage;
import org.tud.bp.fitup.Activity.Activity_lst_Challenge;
import org.tud.bp.fitup.Activity.ActivityDiary;
import org.tud.bp.fitup.Activity.ActivitySettingInitializer;
import org.tud.bp.fitup.Activity.Activity_lst_bsafragebogen;
import org.tud.bp.fitup.Activity.Activity_lst_fitnessfragebogen;
import org.tud.bp.fitup.Activity.Activity_lst_stimmungsabfrage;
import org.tud.bp.fitup.BusinessLayer.TaskCategory;
import org.tud.bp.fitup.Fragments.TabFragment;
import org.tud.bp.fitup.R;

import java.util.List;

/**
 * Created by M.Braei on 25.03.2017.
 */

public class TaskCategAdapter extends RecyclerView.Adapter<TaskCategAdapter.TaskCategHolder> {
    List<TaskCategory> taskCategories;
    TabFragment context;

    public class TaskCategHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cv;
        TextView txtTitle;
        TextView txtSubText;
        ImageView imageView;

        TaskCategHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitleTaskCateg);
            txtSubText = (TextView) itemView.findViewById(R.id.txtSubtextTaskCateg);
            cv = (CardView) itemView.findViewById(R.id.card_view_task_categ);
            imageView = (ImageView) itemView.findViewById(R.id.imgViewTaskCateg);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (taskCategories.get(getAdapterPosition()).getTitle().equals(context.getString(R.string
                    .tagebucheintrag))) {
                Intent open = new Intent(context.getActivity(), ActivityDiary.class);
                context.startActivity(open);

            } else if (taskCategories.get(getAdapterPosition()).getTitle().equals(context.getString(R.string
                    .stimmungsabgabe))) {
                Intent open = new Intent(context.getActivity(), Activity_lst_stimmungsabfrage.class);
                context.startActivity(open);

            } else if (taskCategories.get(getAdapterPosition()).getTitle().equals(context.getString(R.string
                    .fitnessfragebogen))) {
                Intent open = new Intent(context.getActivity(), Activity_lst_fitnessfragebogen.class);
                context.startActivity(open);
            } else if (taskCategories.get(getAdapterPosition()).getTitle().equals(context.getString(R.string
                    .aktivitaetsfragebogen))) {
                Intent open = new Intent(context.getActivity(), Activity_lst_bsafragebogen.class);
                context.startActivity(open);
            } else if (taskCategories.get(getAdapterPosition()).getTitle().equals(context.getString(R.string
                    .bewegen_sie_sich))) {
                Intent open = new Intent(context.getActivity(), ActivityMotivationMessage.class);
                context.startActivity(open);
            } else if (taskCategories.get(getAdapterPosition()).getTitle().equals(context.getString(R.string
                    .trainingszeiten_und_studioadresse))) {
                Intent open = new Intent(context.getActivity(), ActivitySettingInitializer.class);
                context.startActivity(open);
            } else if (taskCategories.get(getAdapterPosition()).getTitle().equals(context.getString(R.string
                    .Challenges))) {
                Intent open = new Intent(context.getActivity(), Activity_lst_Challenge.class);
                context.startActivity(open);
            }


        }
    }

    public TaskCategAdapter(List<TaskCategory> taskCategories, TabFragment context) {
        this.taskCategories = taskCategories;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return taskCategories.size();
    }

    @Override
    public TaskCategAdapter.TaskCategHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_taskcateg, viewGroup, false);
        TaskCategAdapter.TaskCategHolder bvh = new TaskCategAdapter.TaskCategHolder(v);
        return bvh;
    }

    @Override
    public void onBindViewHolder(final TaskCategAdapter.TaskCategHolder holder, final int position) {
        TaskCategAdapter.TaskCategHolder taskCategHolder = (TaskCategAdapter.TaskCategHolder) holder;
        taskCategHolder.txtTitle.setText(taskCategories.get(position).getTitle());
        taskCategHolder.txtSubText.setText(taskCategories.get(position).getSubText());
        taskCategHolder.imageView.setImageResource(taskCategories.get(position).getImage());


    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
