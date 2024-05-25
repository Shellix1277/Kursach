package com.example.pr8;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class InternationalFlightsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_international_flights);

        // Пример данных о рейсах
        String[] internationalFlights = {"Flight 1: Moscow - New York", "Flight 2: London - Tokyo", "Flight 3: Paris - Sydney"};

        // Найдем TextView в макете и установим текст с информацией о рейсах
        TextView internationalFlightsTextView = findViewById(R.id.internationalFlightsTextView);
        StringBuilder flightsInfo = new StringBuilder();
        for (String flight : internationalFlights) {
            flightsInfo.append(flight).append("\n");
        }
        internationalFlightsTextView.setText(flightsInfo.toString());
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

