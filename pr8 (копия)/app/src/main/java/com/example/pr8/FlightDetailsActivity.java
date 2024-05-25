package com.example.pr8;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FlightDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_details);

        // Получаем данные о рейсе из интента
        String destination = getIntent().getStringExtra("destination");
        String airline = getIntent().getStringExtra("airline");
        String departureTime = getIntent().getStringExtra("departureTime");

        // Находим текстовые поля в макете
        TextView textViewDestination = findViewById(R.id.textViewDestination);
        TextView textViewAirline = findViewById(R.id.textViewAirline);
        TextView textViewDepartureTime = findViewById(R.id.textViewDepartureTime);

        // Устанавливаем значения текстовых полей
        textViewDestination.setText("Destination: " + destination);
        textViewAirline.setText("Airline: " + airline);
        textViewDepartureTime.setText("Departure Time: " + departureTime);

        // Находим кнопку "Назад"
        Button buttonBack = findViewById(R.id.buttonBack);
        // Устанавливаем слушатель кликов для кнопки "Назад"
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Закрываем текущую активность и возвращаемся на предыдущий экран
                finish();
            }
        });
    }
}
