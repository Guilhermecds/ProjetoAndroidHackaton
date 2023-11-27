package com.alfaumuarama.projetoandroidhackaton;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetalhesRotaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_rota);

        // Obter dados passados pela Intent
        String pontoPartida = getIntent().getStringExtra("pontoPartida");
        String cidadeDestino = getIntent().getStringExtra("cidadeDestino");
        String detalhes = getIntent().getStringExtra("detalhes");
        String diaPartida = getIntent().getStringExtra("diaPartida");

        // Exibir os dados nos TextViews
        TextView pontoPartidaTextView = findViewById(R.id.pontoPartidaDetalhesTextView);
        TextView cidadeDestinoTextView = findViewById(R.id.cidadeDestinoDetalhesTextView);
        TextView detalhesTextView = findViewById(R.id.detalhesDetalhesTextView);
        TextView diaPartidaTextView = findViewById(R.id.diaPartidaDetalhesTextView);

        pontoPartidaTextView.setText(pontoPartida);
        cidadeDestinoTextView.setText(cidadeDestino);
        detalhesTextView.setText(detalhes);
        diaPartidaTextView.setText(diaPartida);

        ImageView voltarImageView = findViewById(R.id.voltar);

        voltarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}