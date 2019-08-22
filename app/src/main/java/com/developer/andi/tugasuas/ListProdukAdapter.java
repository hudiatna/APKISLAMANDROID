package com.developer.andi.tugasuas;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ListProdukAdapter extends RecyclerView.Adapter<ListProdukAdapter.ProdukHolder>
{
    private ArrayList<Produk> listProduk;
    public ListProdukAdapter(ArrayList<Produk> listProduk) {
        this.listProduk = listProduk;
    }

    @NonNull
    @Override
    public ListProdukAdapter.ProdukHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ProdukHolder(inflater.inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListProdukAdapter.ProdukHolder holder, int position) {
        holder.bind(listProduk.get(position));
    }

    @Override
    public int getItemCount() {
        return listProduk.size();
    }

    public class ProdukHolder extends RecyclerView.ViewHolder {
        private TextView mProduk;
        private TextView mSertifikat;
        private TextView mProdusen;
        private TextView mBerlaku;

        public ProdukHolder(@NonNull View itemView) {
            super(itemView);
            mProduk = (TextView)itemView.findViewById(R.id.tv_nama);
            mSertifikat =
                    (TextView)itemView.findViewById(R.id.tv_no_sertifikat);
            mProdusen =
                    (TextView)itemView.findViewById(R.id.tv_produsen);
            mBerlaku =
                    (TextView)itemView.findViewById(R.id.tv_berlaku);
        }

        public void bind(Produk produk) {

            mProduk.setText(produk.nama);
            mSertifikat.setText(produk.no_sertifikat);
            mProdusen.setText(produk.produsen);
            mBerlaku.setText(produk.berlaku);
        }
    }
}



