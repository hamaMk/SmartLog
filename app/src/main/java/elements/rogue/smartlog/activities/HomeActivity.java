package elements.rogue.smartlog.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import elements.rogue.smartlog.R;
import elements.rogue.smartlog.dialogs.AddWeekDialog;
import elements.rogue.smartlog.persistance.LogCursorWrapper;
import elements.rogue.smartlog.persistance.LogDbHelper;
import elements.rogue.smartlog.recyclers.HomeAdapter;
import elements.rogue.smartlog.schema.DbSchema;
import elements.rogue.smartlog.types.Log;
import elements.rogue.smartlog.types.Weekly;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private FloatingActionButton fab;
    private SQLiteDatabase database;
    private List<Log> mLogs;
    private List<Weekly> mWeeklyList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //setup db
        database = new LogDbHelper(this).getWritableDatabase();

        Toolbar toolbar = findViewById(R.id.home_main_toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.home_recycler);
        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
//        txtWeek = findViewById(R.id.txtWeek);

        fab = findViewById(R.id.home_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* AddWeekDialog dialog = new AddWeekDialog();
                dialog.show(getSupportFragmentManager(), "addweek");*/

               startActivity(new Intent(HomeActivity.this, AddLogActivity.class));
            }
        });

        //data

       /* List<Weekly> weeklyList = new ArrayList<>();

        List<Log> logs = new ArrayList<>();
        logs.add(new Log("network problem", "restarted the access point", "no comment"));
        logs.add(new Log("network problem", "restarted the access point", "no comment"));
        logs.add(new Log("network problem", "restarted the access point", "no comment"));
        logs.add(new Log("network problem", "restarted the access point", "no comment"));
        logs.add(new Log("network problem", "restarted the access point", "no comment"));
        logs.add(new Log("network problem", "restarted the access point", "no comment"));

        weeklyList.add(new Weekly(logs, new Date(), 1));
        weeklyList.add(new Weekly(logs, new Date(), 2));
        weeklyList.add(new Weekly(logs, new Date(), 3));
        weeklyList.add(new Weekly(logs, new Date(), 4));
        weeklyList.add(new Weekly(logs, new Date(), 5));*/

        mLogs = getData();
        //list of dates
        List<String> years = new ArrayList<>();
        List<String> months = new ArrayList<>();
        for (Log log: mLogs){
            String year = log.getDate().substring(0, 4);
            String month = log.getDate().substring(log.getDate().indexOf("."), log.getDate().lastIndexOf("."));


            if (!years.contains(year))years.add(year);
            if (!months.contains(month))months.add(month);
        }

        List<Weekly> weeksList = new ArrayList<>();
        for (String month: months){

            Weekly week1 = new Weekly();
            Weekly week2 = new Weekly();
            Weekly week3 = new Weekly();
            Weekly week4 = new Weekly();
           for (Log log: mLogs){

               int day = Integer.valueOf(log.getDate().substring(log.getDate().lastIndexOf(".") + 1));

               try {
                   //week 1 -7
                   if (day <= 7)week1.addLog(log);

                  //week 2 7-14
                   else if(day > 7 && day <= 14)week2.addLog(log);

                  //week 3 14-21
                   else if (day > 14 && day <= 21) week3.addLog(log);

                  //week 4 21-
                   else if (day >21)week4.addLog(log);
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
            weeksList.add(week1);
            weeksList.add(week2);
            weeksList.add(week3);
            weeksList.add(week4);
        }
        //set adapter
        HomeAdapter adapter = new HomeAdapter(this, weeksList);
        recyclerView.setAdapter(adapter);
    }

    private List<Log> getData(){

        List<Log> logs = new ArrayList<>();
        LogCursorWrapper cursor = queryCrimes(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                logs.add(cursor.getLog());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return logs;
    }

    private LogCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = database.query(
                DbSchema.LogTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new LogCursorWrapper(cursor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exportToWord) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
