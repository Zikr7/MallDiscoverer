package com.example.mall;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mall.databinding.ActivityDetailedBinding;

public class DetailedActivity extends AppCompatActivity {

    Button button, web;

    String linkString;

    ActivityDetailedBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = this.getIntent();
        if (intent != null){
            String name = intent.getStringExtra("name");
            int description = intent.getIntExtra("description", R.string.trxCaps);
            int address = intent.getIntExtra("address", R.string.trx);
            int store = intent.getIntExtra("store", R.string.trxStore);
            int image = intent.getIntExtra("image", R.drawable.trx);
            int public1 = intent.getIntExtra("public", R.string.trxPub);
            int but = intent.getIntExtra("button", R.string.trxBut);
            int link = intent.getIntExtra("link", R.string.trxLink);

            String butString = getString(but);
            linkString = getString(link);

            SharedPreferences preferences = getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("button", butString);
            editor.apply();

            binding.detailName.setText(name);
            binding.detailTime.setText(description);
            binding.detailDesc.setText(store);
            binding.detailAdd.setText(address);
            binding.detailImage.setImageResource(image);
            binding.detailPub.setText(public1);
        }

        web = findViewById(R.id.weblink);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl(linkString);
            }
        });

        button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(DetailedActivity.this, MainActivity2.class);
                String url = getString(R.string.trxBut);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
    }

    void gotoUrl(String s) {
        try{
            Uri uri = Uri.parse(s);
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "No Website", Toast.LENGTH_SHORT).show();
        }
    }
}