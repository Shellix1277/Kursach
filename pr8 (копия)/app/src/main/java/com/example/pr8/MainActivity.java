package com.example.pr8;

import android.os.Bundle;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FlightAdapter adapter;
    private List<Flight> flightList;
    private DataBaseHelper databaseHelper;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DataBaseHelper(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (!isUserLoggedIn()) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }


        // Удаление всех данных перед добавлением для тестирования
        databaseHelper.deleteAllFlights();

        // Инициализация данных
        databaseHelper.addFlight(new Flight("Moscow", "Pobeda", "12:00"));
        databaseHelper.addFlight(new Flight("New York", "Aeroflot", "14:30"));
        databaseHelper.addFlight(new Flight("London", "British Airways", "16:00"));
        databaseHelper.addFlight(new Flight("Paris", "Air France", "18:00"));
        databaseHelper.addFlight(new Flight("Berlin", "Lufthansa", "20:00"));
        databaseHelper.addFlight(new Flight("Tokyo", "Japan Airlines", "22:00"));
        databaseHelper.addFlight(new Flight("Dubai", "Emirates", "00:00"));
        databaseHelper.addFlight(new Flight("Sydney", "Qantas", "02:00"));
        databaseHelper.addFlight(new Flight("Toronto", "Air Canada", "04:00"));
        databaseHelper.addFlight(new Flight("Beijing", "Air China", "06:00"));

        flightList = databaseHelper.getAllFlights();

        adapter = new FlightAdapter(flightList, this);
        recyclerView.setAdapter(adapter);


        // Настройка поиска
        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });

        // Настройка бокового меню
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Обработка нажатий на элементы бокового меню
                if (item.getItemId() == R.id.menu_international) {
                    // Открытие активности для международных рейсов
                    startActivity(new Intent(MainActivity.this, InternationalFlightsActivity.class));
                } else if (item.getItemId() == R.id.menu_domestiс) {
                    // Открытие активности для внутренних рейсов
                    startActivity(new Intent(MainActivity.this, DomesticFlightsActivity.class));
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }
    private boolean isUserLoggedIn() {
        return SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn();
    }

    // Метод для фильтрации списка рейсов
    private void filter(String text) {
        List<Flight> filteredList = new ArrayList<>();
        for (Flight item : flightList) {
            if (item.getDestination().toLowerCase().contains(text.toLowerCase()) ||
                    item.getAirline().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Обработка нажатия на кнопку "Гамбургер" для открытия бокового меню
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            // Обработка нажатия кнопки "Назад" на панели действий
            onBackPressed(); // вызываем метод для обработки нажатия "Назад"
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
