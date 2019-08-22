package com.developer.andi.tugasuas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProdukHalalActivity extends AppCompatActivity {
  private SearchView searchView;
  private RecyclerView recyclerView;
  private ProgressBar progressBar;
  private ListProdukAdapter mAdapter;
  private ArrayList <Produk> listproduk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_halal);

        searchView = findViewById(R.id.sc_produkhalal);
        recyclerView = findViewById(R.id.recyeler_view);
        progressBar = findViewById(R.id.progress_bar);

        listproduk = new ArrayList<Produk>();
        mAdapter = new ListProdukAdapter(listproduk);
        LinearLayoutManager mLayoutManager = new
                LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new
                DividerItemDecoration(this,
                mLayoutManager.getOrientation()));
        recyclerView.setAdapter(mAdapter);
        searchView.setOnQueryTextListener(new
                                                  SearchView.OnQueryTextListener() {
                                                      @Override
                                                      public boolean onQueryTextSubmit(String query) {
                                                          loadData(query);
                                                          return false;
                                                      }
                                                      @Override
                                                      public boolean onQueryTextChange(String query) {
                                                          return false;
                                                      }
                                                  });
    }

    private void loadData(String kw) {
        Log.d("KW_DATA", kw);
        try {
            String url = "?menu=nama_produk&query="+kw;
            ClientAsyncTask2 task = new ClientAsyncTask2(this, url, new
                    ClientAsyncTask2.OnPostExecuteListener() {
                        @Override
                        public void onPostExecute(String result) {
                            Log.d("HalalData", result);
                            try {
                                JSONObject jsonObj = new JSONObject(result);
                                JSONArray jsonArray = jsonObj.getJSONArray("data");
                                listproduk.clear();
                                for (int i=0; i<jsonArray.length(); i++) {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    Produk produk = new Produk();
                                    produk.nama = obj.getString("nama_produk");
                                    produk.no_sertifikat =
                                            obj.getString("nomor_sertifikat");
                                    produk.produsen =
                                            obj.getString("nama_produsen");
                                    produk.berlaku =
                                            obj.getString("berlaku_hingga");
                                    listproduk.add(produk);
                                }
                                mAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            task.setmProgress(this.progressBar);
            task.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
