package com.developer.andi.tugasuas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Ayat_PilihanActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ListAyatAdapter mAdapter;
    private ArrayList<Ayatpilihan> lisAyat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayat__pilihan);

        recyclerView = findViewById(R.id.recyeler_view_ayat_pilihan);
        progressBar = findViewById(R.id.progress_bar_ayat_pilihan);

        loadData();
        lisAyat = new ArrayList<Ayatpilihan>();
        mAdapter = new ListAyatAdapter(lisAyat);
        LinearLayoutManager mLayoutManager = new
                LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new
                DividerItemDecoration(this,
                mLayoutManager.getOrientation()));
        recyclerView.setAdapter(mAdapter);
    }
    private void loadData() {
        try {
            ClientAsyncTask3 task = new ClientAsyncTask3(this, new
                    ClientAsyncTask3.OnPostExecuteListener() {
                        @Override
                        public void onPostExecute(String result) {
                            Log.d("AyatData", result);
                            try {
                                JSONObject jsonObj = new JSONObject(result);
                                JSONArray jsonArray = jsonObj.getJSONArray("hasil");
                                lisAyat.clear();
                                for (int i=0; i<jsonArray.length(); i++) {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                     Ayatpilihan ayat = new Ayatpilihan();
                                     ayat.nama = obj.getString("nama");
                                     ayat.asma = obj.getString("asma");
                                     ayat.ayat = obj.getString("ayat");
                                     ayat.arti = obj.getString("arti");
                                     ayat.type = obj.getString("type");
                                    lisAyat.add(ayat);
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
