package com.meet.ahha;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteReadOnlyDatabaseException;

import com.meet.ahha.DatabaseHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lby20102 on 14-03-01.
 */
public class Database {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private int carId;
    private Context context;

    public Database(Context context)
    {
        this.context = context;
        carId = 0;
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException
    {
        database = dbHelper.getWritableDatabase();
        try {
            ReadDataFromCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close()
    {
        dbHelper.close();
    }

    private void ReadDataFromCSV() throws IOException {
        File[] listOfFiles = context.getFilesDir().listFiles();

        for(File file : listOfFiles)
        {
            if(file.getName().endsWith(".csv"))
                ReadDataFromCSV(file.getAbsolutePath());
        }
    }

    private void ReadDataFromCSV(String fileName) throws IOException {
        FileReader file = new FileReader(fileName);
        BufferedReader buffer = new BufferedReader(file);
        String line = "";
        String tableName ="CarInfos";
        String columns = getColumnsName();
        String str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
        String str2 = ");";

        database.beginTransaction();
        while ((line = buffer.readLine()) != null) {
            StringBuilder sb = new StringBuilder(str1);
            String[] str = line.split(",");
            sb.append(carId + ", ");
            sb.append(str[0] + ", ");
            sb.append("'"+str[1] + "', ");
            sb.append("'"+str[2] + "', ");
            sb.append("'"+str[3] + "', ");
            sb.append(str[4] + ", ");
            sb.append(str[5] + ", ");
            sb.append("'"+str[6] + "', ");
            sb.append("'"+str[7] + "', ");
            sb.append(str[8] + ", ");
            sb.append(str[9] + ", ");
            sb.append(str[12] + ", ");
            sb.append(str[13] + "");
            sb.append(str2);
            carId++;
            database.execSQL(sb.toString());
        }
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    private String getColumnsName()
    {
        String rtnString = "";
        for (int i = 0; i < DatabaseHelper.COLUMN_ID.length; i++ )
        {
            rtnString += DatabaseHelper.COLUMN_ID[i];
            if(i!=DatabaseHelper.COLUMN_ID.length-1)
                rtnString+=", ";
        }
        return rtnString;
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
        String selectQuery = "SELECT * FROM " + DatabaseHelper.TABLE_COMMENTS + " WHERE id > 0" +
                (year=="*" ? "" : " AND Year = \"" + year +"\"") +
                (manufacturer=="*" ? "" : " AND MANUFACTURER = \"" + manufacturer +"\"") +
                (model=="*" ? "" : " AND MODEL = \"" + model + "\"") +
                " LIMIT 50";
        Cursor cursor = database.rawQuery(selectQuery, null);

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
