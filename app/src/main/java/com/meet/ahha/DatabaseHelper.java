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

//For reference....
//    String Year;
//    String Manufacturer;
//    String Model;
//    String Vehicle_Class;
//    float Engine_Size;
//    int Cylinder;
//    String Transmission;
//    String FuelType;
//    float City_Fuel_Consumption;
//    float HWY_Fuel_Consumption;
//    int Fuel_Per_Year;
//    int CO2Emission;
    public List<Car> getCars(String year, String manufacturer, String model)
    {
        List<Car> cars = new ArrayList<Car>();
        String selectQuery = "SELECT * FROM " + TABLE_COMMENTS + "WHERE" +
                (year=="*" ? "" : "Year = \"" + year +"\"") +
                (manufacturer=="*" ? "" : "MANUFACTURER = \"" + manufacturer +"\"") +
                (model=="*" ? "" : "MODEL = \"" + model + "\"") +
                "LIMIT 50";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Car car = new Car(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getFloat(5),
                        cursor.getInt(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getFloat(9),
                        cursor.getFloat(10),
                        cursor.getInt(11),
                        cursor.getInt(12)
                        );
                cars.add(car);
            } while (cursor.moveToNext());
        }
        return cars;

    }


}
