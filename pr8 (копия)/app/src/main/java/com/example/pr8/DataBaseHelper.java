package com.example.pr8;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "flights.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_FLIGHTS = "flights";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DESTINATION = "destination";
    private static final String COLUMN_AIRLINE = "airline";
    private static final String COLUMN_DEPARTURE_TIME = "departure_time";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_FLIGHTS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DESTINATION + " TEXT, " +
                COLUMN_AIRLINE + " TEXT, " +
                COLUMN_DEPARTURE_TIME + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FLIGHTS);
        onCreate(db);
    }

    public void addFlight(Flight flight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DESTINATION, flight.getDestination());
        values.put(COLUMN_AIRLINE, flight.getAirline());
        values.put(COLUMN_DEPARTURE_TIME, flight.getDepartureTime());

        db.insert(TABLE_FLIGHTS, null, values);
        db.close();
    }

    public List<Flight> getAllFlights() {
        List<Flight> flightList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FLIGHTS, null);

        if (cursor.moveToFirst()) {
            do {
                String destination = cursor.getString(cursor.getColumnIndex(COLUMN_DESTINATION));
                String airline = cursor.getString(cursor.getColumnIndex(COLUMN_AIRLINE));
                String departureTime = cursor.getString(cursor.getColumnIndex(COLUMN_DEPARTURE_TIME));
                flightList.add(new Flight(destination, airline, departureTime));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return flightList;
    }

    public void deleteAllFlights() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_FLIGHTS);
        db.close();
    }
}
