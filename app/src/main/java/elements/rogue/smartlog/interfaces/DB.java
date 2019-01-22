package elements.rogue.smartlog.interfaces;

import android.content.ContentValues;

import elements.rogue.smartlog.types.Log;

public interface DB {

    ContentValues getContentValues(Log log);
}
