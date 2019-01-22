package elements.rogue.smartlog.activities;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import elements.rogue.smartlog.R;
import elements.rogue.smartlog.interfaces.DB;
import elements.rogue.smartlog.persistance.LogDbHelper;
import elements.rogue.smartlog.schema.DbSchema;
import elements.rogue.smartlog.types.Log;

public class AddLogActivity extends AppCompatActivity implements DB {

    private EditText add_situation;
    private EditText add_description;
    private EditText add_comment;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_log);

        //setup db
        database = new LogDbHelper(this).getWritableDatabase();

        add_situation = findViewById(R.id.add_situation);
        add_description = findViewById(R.id.add_description);
        add_comment = findViewById(R.id.add_comment);
    }

    @Override
    public void onBackPressed() {
        String situation = add_situation.getText().toString();
        String description = add_description.getText().toString();
        String comment = add_comment.getText().toString();

        if(description.length() > 5 && situation.length() < 3 ){
            Toast.makeText(this, "Put Situation", Toast.LENGTH_SHORT).show();
        }else{
            save(situation, description, comment);
        }

        this.finish();
    }


    private void save(String situation, String description, String comment){



        if (situation.length()>3) {
            Log log = new Log();
            log.setSituation(situation);
            log.setDescrition(description);
            log.setComment(comment);
            log.setDate(new SimpleDateFormat("yyyy.MM.dd").format(new Date()));

            database.insert(DbSchema.LogTable.NAME, null, getContentValues(log));
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Discarded", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public ContentValues getContentValues(Log log) {
        ContentValues values = new ContentValues();
        values.put(DbSchema.LogTable.Cols.SITUATION, log.getSituation());
        values.put(DbSchema.LogTable.Cols.DESCRIPTION, log.getDescrition());
        values.put(DbSchema.LogTable.Cols.COMMENT, log.getComment());
        values.put(DbSchema.LogTable.Cols.DATE, log.getDate());

        return values;
    }
}
