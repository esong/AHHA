package com.meet.ahha;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.util.Log;
import com.meet.ahha.Car;
import android.database.Cursor;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by lby20102 on 14-03-01.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_COMMENTS = "CarInfos";
    public static final String[] COLUMN_ID = new String[]{"id","Year", "MANUFACTURER","MODEL","VEHICLE_CLASS",
                                                          "ENGINE_SIZE","CYLINDERS","TRANSMISSION","FUEL",
                                                          "FUEL_CONSUMPTION_CITY","FUEL_CONSUMPTION_HWY","FUEL_PER_YEAR","CO2_EMISSIONS"
    };
    public static final String[] COLUMN_TYPE = new String[]{"integer primary key autoincrement", "int", "text", "text","text",
                                                           "float", "int", "text", "text",
                                                           "float", "float", "int", "int"
    };
    private static final String DATABASE_NAME = "CarInfos.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static String DATABASE_CREATE;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void constructCreateTableCmd()
    {
        DATABASE_CREATE = "create table "+TABLE_COMMENTS+"(";
        for(int i=0; i<COLUMN_ID.length; i++)
        {
            DATABASE_CREATE += COLUMN_ID[i]+" "+COLUMN_TYPE[i];
            if(i!=COLUMN_ID.length-1)
                DATABASE_CREATE+=", ";
            else
                DATABASE_CREATE+=");";
        }
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        constructCreateTableCmd();
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);
    }
}
