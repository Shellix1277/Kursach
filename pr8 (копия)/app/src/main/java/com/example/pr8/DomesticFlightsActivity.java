package com.example.pr8;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DomesticFlightsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_domestic_flights);

        // Пример данных о рейсах
        String[] domesticFlights = {"Flight 1: Moscow - St. Petersburg", "Flight 2: Ekaterinburg -  Moscow", "Flight 3: Kaliningrad - Moscow"};

        // Найдем TextView в макете и установим текст с информацией о рейсах
        TextView domesticFlightsTextView = findViewById(R.id.domesticFlightsTextView);
        StringBuilder flightsInfo = new StringBuilder();
        for (String flight : domesticFlights) {
            flightsInfo.append(flight).append("\n");
        }
        domesticFlightsTextView.setText(flightsInfo.toString());
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // вызываем метод для обработки нажатия "Назад"
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
