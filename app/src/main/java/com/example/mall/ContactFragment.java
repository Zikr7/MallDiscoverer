package com.example.mall;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ContactFragment extends Fragment {

    public static DataHelper dbcenter;
    public static Cursor cursor;
    public static String[] register;
    public static ListView ListView01;
    public static ContactFragment cf;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);

        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle("Your Feedback");

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), CreateBiodataActivity.class);
                startActivity(intent);
            }
        });

        cf = this;
        dbcenter = new DataHelper(requireContext());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RefreshList(); // Call RefreshList() here after the view has been created
    }

    public void RefreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM contact", null);
        register = new String[cursor.getCount()];
        cursor.moveToFirst();

        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            register[cc] = cursor.getString(2).toString();
        }

        ListView01 = getView().findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, register));
        ListView01.setSelected(true);

        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                final String selection = register[arg2];
                final CharSequence[] dialogitem = {"View Feedback", "Update Feedback", "Delete Feedback"};

                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Selection");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                Intent i = new Intent(requireContext(), ViewBiodataActivity.class);
                                i.putExtra("name", selection);
                                startActivity(i);
                                break;
                            case 1:
                                Intent in = new Intent(requireContext(), UpdateBiodataActivity.class);
                                in.putExtra("name", selection);
                                startActivity(in);
                                break;
                            case 2:
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from contact where subject = '" + selection + "'");
                                Toast.makeText(requireContext(), "Data Successfully Removed", Toast.LENGTH_SHORT).show();
                                ContactFragment.cf.RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter<?>) ListView01.getAdapter()).notifyDataSetInvalidated();
    }
}
