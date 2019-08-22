package com.developer.andi.tugasuas;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAyatAdapter extends RecyclerView.Adapter<ListAyatAdapter.AyatHolder> {
    private ArrayList<Ayatpilihan> listayat;
    public ListAyatAdapter(ArrayList<Ayatpilihan> listayat) {
        this.listayat = listayat;
    }


    @NonNull
    @Override
    public ListAyatAdapter.AyatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new AyatHolder(inflater.inflate(R.layout.item_ayat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListAyatAdapter.AyatHolder holder, int position)
    {
        holder.bind(listayat.get(position));
    }
    @Override
    public int getItemCount() {
        return listayat.size();
    }
    public class AyatHolder extends RecyclerView.ViewHolder {

        TextView tvnamasurat,tvasma,tvayat,tvditurunkan,tvarti;
        public AyatHolder(@NonNull View itemView) {
            super(itemView);
            tvnamasurat = itemView.findViewById(R.id.tv_nama_surat);
            tvasma = itemView.findViewById(R.id.tv_asma);
            tvayat = itemView.findViewById(R.id.tv_ayat);
            tvditurunkan = itemView.findViewById(R.id.tv_diturunkan);
            tvarti = itemView.findViewById(R.id.tv_artinya);
        }

        public void bind(Ayatpilihan ayatpilihan) {

            tvnamasurat.setText(ayatpilihan.nama);
            tvasma.setText(ayatpilihan.asma);
            tvayat.setText(ayatpilihan.ayat);
            tvditurunkan.setText(ayatpilihan.type);
            tvarti.setText(ayatpilihan.arti);

        }
    }
}
