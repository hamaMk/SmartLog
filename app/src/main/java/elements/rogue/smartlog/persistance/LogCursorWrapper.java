package elements.rogue.smartlog.persistance;

import android.database.Cursor;
import android.database.CursorWrapper;

import elements.rogue.smartlog.schema.DbSchema.LogTable;
import elements.rogue.smartlog.types.Log;

public class LogCursorWrapper extends CursorWrapper {

    public LogCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Log getLog() {
        String situation = getString(getColumnIndex(LogTable.Cols.SITUATION));
        String description = getString(getColumnIndex(LogTable.Cols.DESCRIPTION));
        String comment = getString(getColumnIndex(LogTable.Cols.COMMENT));
        String date = getString(getColumnIndex(LogTable.Cols.DATE));

        return new Log(situation, description, comment, date);
    }
}
