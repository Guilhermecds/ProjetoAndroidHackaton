package com.alfaumuarama.projetoandroidhackaton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class RotaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RotaAdapter rotaAdapter;
    private DrawerLayout drawerLayout;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rota);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        apiService = new Retrofit.Builder()
                .baseUrl("http://192.168.1.22:3000/rotas/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(ApiService.class);

        // Carregar rotas da API
        carregarRotasDaAPI();

        drawerLayout = findViewById(R.id.drawer_layout_rota);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                return handleNavigationItemSelected(item);
            }
        });

        rotaAdapter = new RotaAdapter(new ArrayList<>());
        recyclerView.setAdapter(rotaAdapter);

        rotaAdapter.setOnItemClickListener(new RotaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Rota rota = rotaAdapter.getItem(position);

                Intent intent = new Intent(RotaActivity.this, DetalhesRotaActivity.class);

                intent.putExtra("pontoPartida", rota.getPontoPartida());
                intent.putExtra("cidadeDestino", rota.getCidadeDestino());
                intent.putExtra("detalhes", rota.getDetalhes());
                intent.putExtra("diaPartida", rota.getDiaPartida());

                startActivity(intent);
            }
        });
    }

    private boolean handleNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.menu_sair) {
            clearSharedPreferences();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return true;
        } else {
            return false;
        }
    }

    private void clearSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    private void carregarRotasDaAPI() {
        Call<List<Rota>> call = apiService.getRotas();
        call.enqueue(new Callback<List<Rota>>() {
            @Override
            public void onResponse(Call<List<Rota>> call, Response<List<Rota>> response) {
                if (response.isSuccessful()) {
                    List<Rota> rotas = response.body();
                    rotaAdapter = new RotaAdapter(rotas);
                    recyclerView.setAdapter(rotaAdapter);
                } else {
                    Toast.makeText(RotaActivity.this, "Erro ao carregar rotas da API", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Rota>> call, Throwable t) {
                // Tratar falhas na requisição
                Toast.makeText(RotaActivity.this, "Falha na requisição: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}