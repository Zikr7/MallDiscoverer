package com.example.mall;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mall.databinding.ActivityViewBiodataBinding;

public class ViewBiodataActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityViewBiodataBinding binding;
    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton2;
    TextView text1, text2, text3, text4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_biodata);
        dbHelper = new DataHelper(this);

        text1 = (TextView) findViewById(R.id.textView1);
        text2 = (TextView) findViewById(R.id.textView2);
        text3 = (TextView) findViewById(R.id.textView3);
        text4 = (TextView) findViewById(R.id.textView4);

        Log.d("ViewBiodataActivity", "nameeeee: " + getIntent().getStringExtra("name"));

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM contact WHERE subject = '" + getIntent().getStringExtra("name") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0).toString());
            text2.setText(cursor.getString(1).toString());
            text3.setText(cursor.getString(2).toString());
            text4.setText(cursor.getString(3).toString());

            Log.d("ViewBiodataActivity", "Email: " + cursor.getString(0));
            Log.d("ViewBiodataActivity", "Name: " + cursor.getString(1));
            Log.d("ViewBiodataActivity", "Subject: " + cursor.getString(2));
            Log.d("ViewBiodataActivity", "Message: " + cursor.getString(3));

        }else{
            Log.d("ViewBiodataActivity", "Error!!!");

        }

        ton2 = (Button) findViewById(R.id.button1);
        ton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_view_biodata);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}