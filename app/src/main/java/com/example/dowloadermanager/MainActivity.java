package com.example.dowloadermanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.dowloadermanager.adapter.FicheroAdapter;
import com.example.dowloadermanager.model.Ficheros;
import com.example.dowloadermanager.service.FicheroService;
import com.example.dowloadermanager.service.RetrofitHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FicheroAdapter ficheroAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Dowloader Manager");
        recyclerView = findViewById(R.id.reciclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        ficheroAdapter = new FicheroAdapter();
        recyclerView.setAdapter(ficheroAdapter);

        try {
            getFicheros();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public  void getFicheros() throws Exception {

        Call<List<Ficheros>> call = RetrofitHelper.getService(FicheroService.class).getFicheros();
        call.enqueue(new Callback<List<Ficheros>>() {
            @Override
            public void onResponse(Call<List<Ficheros>> call, Response<List<Ficheros>> response) {
                if (response.isSuccessful()) {
                    List<Ficheros> ficheros = response.body();
                    ficheroAdapter.setData(ficheros);

                }
            }

            @Override
            public void onFailure(Call<List<Ficheros>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error al realizar la petición. " + t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });

    }
}