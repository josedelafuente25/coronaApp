package com.jose.coronaapp.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jose.coronaapp.R;
import com.jose.coronaapp.objetos.RegionApp;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> {

    Context context;
    ArrayList<RegionApp> lista;

    public MyAdapter(Context context, ArrayList<RegionApp> lista) {
        this.context = context;
        this.lista = lista;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_recycler, viewGroup, false);
        return new Holder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.getNombre().setText(String.valueOf(lista.get(i).getNOMBRECORTO()));
        holder.getRecuperados().setText(String.valueOf(lista.get(i).getT_REC()));
        holder.getConfirmados().setText(String.valueOf(lista.get(i).getT_CONF()));
        holder.getFallecidos().setText(String.valueOf(lista.get(i).getT_FALL()));
        holder.getActivos().setText(String.valueOf(lista.get(i).getN_ACT()));
    }
    @Override
    public int getItemCount() {
        return lista.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView nombre, confirmados, recuperados, fallecidos, activos;

        public Holder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombreRegion);
            confirmados = itemView.findViewById(R.id.confirmados);
            recuperados = itemView.findViewById(R.id.recuperados);
            fallecidos = itemView.findViewById(R.id.fallecidos);
            activos = itemView.findViewById(R.id.activos);

        }
        public TextView getNombre() {
            return nombre;
        }
        public TextView getConfirmados() {
            return confirmados;
        }
        public TextView getRecuperados() {
            return recuperados;
        }
        public TextView getFallecidos() {
            return fallecidos;
        }
        public TextView getActivos() {
            return activos;
        }
    }
}
