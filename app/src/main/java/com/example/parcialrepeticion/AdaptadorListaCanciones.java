package com.example.parcialrepeticion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcialrepeticion.Modelo.ListaCanciones;

import java.util.ArrayList;
import java.util.LinkedList;

public class AdaptadorListaCanciones extends RecyclerView.Adapter<AdaptadorListaCanciones.ListaCancionesViewHolder> {
    private  ArrayList<ListaCanciones> mListData;
    private LayoutInflater mInflater;

    public AdaptadorListaCanciones(Context context, ArrayList<ListaCanciones> listData) {
        mInflater = mInflater.from(context);
        mListData = listData;

    }

    @NonNull
    @Override
    public ListaCancionesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.lista_canciones_row, parent, false);
        return new ListaCancionesViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaCancionesViewHolder holder, int position) {
        holder.uName.setText(mListData.get(position).getName());
        holder.uArtista.setText(mListData.get(position).getArtista());
        holder.uDuracion.setText(mListData.get(position).getDuration());
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public class ListaCancionesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView uName;
        private TextView uArtista;
        private TextView uDuracion;
        private AdaptadorListaCanciones adaptador;

        public ListaCancionesViewHolder(@NonNull View itemView, AdaptadorListaCanciones adaptador) {
            super(itemView);
            uName = itemView.findViewById(R.id.txt_name);
            uArtista = itemView.findViewById(R.id.txt_artista);
            uDuracion = itemView.findViewById(R.id.txt_duration);
            this.adaptador = adaptador;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            ListaCanciones element = mListData.get(position);
            mListData.set(position,element);
            adaptador.notifyDataSetChanged();
        }
    }

}




    /*public View getView(int position, View v, ViewGroup vg) {
        ViewHolder holder;
        if (v == null) {
            v = layoutInflater.inflate(R.layout.lista_canciones_row, null);
            holder = new ViewHolder();
            holder.uName = (TextView) v.findViewById(R.id.txt_name);
            holder.uArtista = (TextView) v.findViewById(R.id.txt_artista);
            holder.uDuration = (TextView) v.findViewById(R.id.txt_duration);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.uName.setText(listData.get(position).getName());
        holder.uArtista.setText(listData.get(position).getArtista());
        holder.uDuration.setText(listData.get(position).getDuration());
        return v;
    }
    static class ViewHolder {
        TextView uName;
        TextView uArtista;
        TextView uDuration;
    }*/

