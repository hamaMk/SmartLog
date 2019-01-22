package elements.rogue.smartlog.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import elements.rogue.smartlog.R;
import elements.rogue.smartlog.persistance.LogDbHelper;
import elements.rogue.smartlog.recyclers.LogsListAdapter;
import elements.rogue.smartlog.types.Log;

public class LogsListActivity extends AppCompatActivity {

    public static List<Log> logs;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private FloatingActionButton fab;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs_list);

        //setup db
        database = new LogDbHelper(this).getWritableDatabase();

        Bundle extras = getIntent().getExtras();
        setTitle("Week " + extras.get("weekId").toString());

        recyclerView = findViewById(R.id.logs_list_recycler);
        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        fab = findViewById(R.id.logs_list_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogsListActivity.this, AddLogActivity.class));
            }
        });

        //data
       /* List<Log> logs = new ArrayList<>();
        logs.add(new Log("network problem1", "restarted the access point", "no comment"));
        logs.add(new Log("network problem2", "restarted the access point", "no comment"));
        logs.add(new Log("network problem3", "restarted the access point", "no comment"));
        logs.add(new Log("network problem4", "restarted the access point", "no comment"));
        logs.add(new Log("network problem5", "restarted the access point", "no comment"));
        logs.add(new Log("network problem6", "restarted the access point", "no comment"));
*/


        try {
            LogsListAdapter adapter = new LogsListAdapter(this, logs);
            recyclerView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logs_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exportToWord) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getData(){


    }
}
