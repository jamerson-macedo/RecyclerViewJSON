package com.organizze.jmdevelopers.recyclerviewjson;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private Context mcontext;
    private ArrayList<Item> mitems;

    public ItemAdapter(Context context, ArrayList<Item> items) {
        mcontext = context;
        mitems = items;


    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mcontext).inflate(R.layout.exemploitem,viewGroup,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        Item itematual=mitems.get(i);
        String imagemURL=itematual.getmImagemURL();
        String nomecriador=itematual.getmCriador();
        int mdownloads=itematual.getmLikes();
        itemViewHolder.criador.setText(nomecriador);
        itemViewHolder.downloads.setText("Likes :"+mdownloads);
        Picasso.get().load(imagemURL).fit().centerInside().into(itemViewHolder.imagemprincipal);

    }

    @Override
    public int getItemCount() {
        return mitems.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagemprincipal;
        public TextView criador;
        public TextView downloads;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imagemprincipal = itemView.findViewById(R.id.fotoprincipal);
            criador = itemView.findViewById(R.id.textocriador);
            downloads = itemView.findViewById(R.id.textodownload);

        }
    }
}
