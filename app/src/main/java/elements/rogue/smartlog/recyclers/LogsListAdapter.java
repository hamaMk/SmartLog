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
import elements.rogue.smartlog.activities.LogViewActivity;
import elements.rogue.smartlog.types.Log;

public class LogsListAdapter extends RecyclerView.Adapter<LogsListAdapter.LogsListViewHolder> {

    private Context context;
    private List<Log> logs;

    public LogsListAdapter() {
    }

    public LogsListAdapter(Context context, List<Log> logs) {
        this.context = context;
        this.logs = logs;
        LogViewActivity.logs = logs;
    }

    @NonNull
    @Override
    public LogsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_logs_list, parent, false);
        return new LogsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogsListViewHolder holder, final int position) {

        holder.logs_list_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LogViewActivity.class);
                intent.putExtra("position", String.valueOf(position));
                context.startActivity(intent);
            }
        });
        holder.txtLog.setText(logs.get(position).getSituation());
    }

    @Override
    public int getItemCount() {
        return logs.size();
    }

    //view holder class
    public class LogsListViewHolder extends RecyclerView.ViewHolder{

        protected ConstraintLayout logs_list_row;
        private TextView txtLog;

        public LogsListViewHolder(@NonNull View itemView) {
            super(itemView);
            logs_list_row = itemView.findViewById(R.id.logs_list_row);
            txtLog = itemView.findViewById(R.id.txtLog);
        }
    }
}
