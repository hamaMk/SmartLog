package elements.rogue.smartlog.recyclers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import elements.rogue.smartlog.R;
import elements.rogue.smartlog.activities.LogsListActivity;
import elements.rogue.smartlog.types.Weekly;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private Context context;
    private List<Weekly> weeklyList;


    public HomeAdapter(Context context, List<Weekly> weeklyList) {

        this.context = context;
        this.weeklyList = weeklyList;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_home, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, final int position) {

        holder.home_row.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return true;
            }
        });
        holder.home_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogsListActivity.logs = weeklyList.get(position).getLogs();

                Intent intent = new Intent(context, LogsListActivity.class);
                intent.putExtra("weekId", String.valueOf(weeklyList.get(position).getWeek()));
                context.startActivity(intent);
            }
        });
        try {

            String date = weeklyList.get(position).getLog(0).getDate();
            int day = Integer.valueOf(date.substring(date.lastIndexOf(".") + 1));
            int week = 0;
            //week 1 -7
            if (day <= 7)week = 1;

                //week 2 7-14
            else if(day > 7 && day <= 14)week = 2;

                //week 3 14-21
            else if (day > 14 && day <= 21) week = 3;

                //week 4 21-
            else if (day >21)week = 4;

            holder.txtWeek.setText(String.valueOf(weeklyList.get(position).getWeek()));
            holder.txtEntries.setText(String.valueOf(weeklyList.get(position).getLogs().size()));
            holder.txtDate.setText(String.valueOf(week));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return weeklyList.size();
    }

    //change list at run time
    public void setWeeklyList(List<Weekly> weeklyList) {
        this.weeklyList = weeklyList;
    }

    // view holder
    public class HomeViewHolder extends RecyclerView.ViewHolder{

        protected ConstraintLayout home_row;
        protected TextView txtWeek;
        protected TextView txtEntries;
        protected TextView txtDate;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            home_row = itemView.findViewById(R.id.home_row);
            txtWeek = itemView.findViewById(R.id.txtWeek);
            txtEntries = itemView.findViewById(R.id.txtEntries);
            txtDate = itemView.findViewById(R.id.txtDate);
        }
    }
}
