package com.developer.andi.tugasuas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
 Button btnjadwalsholat, btnprodukhalal,btnsurat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Menu");

        btnjadwalsholat = findViewById(R.id.btn_jadwalsholat);
        btnprodukhalal = findViewById(R.id.btn_produk_halal);
        btnsurat = findViewById(R.id.btn_ayat_pilihan);

        btnjadwalsholat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ijadwalsholat= new Intent(MainActivity.this,JadwalSholatActivity.class );
                startActivity(ijadwalsholat);
            }
        });
        btnprodukhalal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iprodukhalal= new Intent(MainActivity.this,ProdukHalalActivity.class );
                startActivity(iprodukhalal);
            }
        });
         btnsurat.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent idoaharian= new Intent(MainActivity.this,Ayat_PilihanActivity.class );
                 startActivity(idoaharian);
             }
         });


    }
}
