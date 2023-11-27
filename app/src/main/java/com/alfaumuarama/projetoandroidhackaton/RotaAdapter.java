package com.alfaumuarama.projetoandroidhackaton;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.recyclerview.widget.RecyclerView;
import at.blogc.android.views.ExpandableTextView;

import java.util.List;

public class RotaAdapter extends RecyclerView.Adapter<RotaAdapter.RotaViewHolder> {

    private List<Rota> rotas;
    private SparseBooleanArray expandedItems;
    private static OnItemClickListener onItemClickListener;

    public RotaAdapter(List<Rota> rotas) {
        this.rotas = rotas;
        this.expandedItems = new SparseBooleanArray();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


    @Override
    public RotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rota_item, parent, false);
        return new RotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RotaViewHolder holder, int position) {
        Rota rota = rotas.get(position);

        String partidaDestino = "Partindo de " + rota.getPontoPartida() + " para " + rota.getCidadeDestino();
        holder.pontoPartidaDestino.setText(partidaDestino);

    }

    @Override
    public int getItemCount() {
        return rotas.size();
    }

    public static class RotaViewHolder extends RecyclerView.ViewHolder {
        ExpandableTextView pontoPartidaDestino;

        public RotaViewHolder(View itemView) {
            super(itemView);

            // Configurar um clique no item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onItemClickListener.onItemClick(position);
                        }
                    }
                }
            });
            pontoPartidaDestino = itemView.findViewById(R.id.pontoPartidaDestinoTextView);
        }
    }

    public Rota getItem(int position) {
        return rotas.get(position);
    }
}