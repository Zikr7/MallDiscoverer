package com.example.mall;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import com.example.mall.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    ListAdapter listAdapter;
    ArrayList<ListData> dataArrayList = new ArrayList<>();
    ListData listData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        int[] imageList = {R.drawable.trx, R.drawable.pavi, R.drawable.klcc, R.drawable.mid, R.drawable.ioi, R.drawable.ou, R.drawable.bts, R.drawable.sunway, R.drawable.starhill, R.drawable.gardens, R.drawable.gardens};
        int[] addList = {R.string.trx, R.string.pavi,R.string.klcc,R.string.mid,R.string.ioi, R.string.ou, R.string.bts, R.string.sunway, R.string.starhill, R.string.gardens, R.string.gardens};
        int[] storeList = {R.string.trxStore, R.string.paviStore, R.string.klccStore,R.string.midStore,R.string.ioiStore, R.string.ouStore, R.string.btsStore, R.string.sunwayStore, R.string.starhillStore, R.string.gardensStore, R.string.gardensStore};
        int[] mallCaps = {R.string.trxCaps, R.string.paviCaps, R.string.klccCaps,R.string.midCaps,R.string.ioiCaps, R.string.ouCaps, R.string.btsCaps, R.string.sunwayCaps, R.string.starhillCaps, R.string.gardensCaps, R.string.gardensCaps};
        int[] mallPub = {R.string.trxPub, R.string.paviPub, R.string.klccPub,R.string.midPub,R.string.ioiPub, R.string.ouPub, R.string.btsPub, R.string.sunwayPub, R.string.starhillPub, R.string.gardensPub, R.string.gardensPub};
        int[] mallBut = {R.string.trxBut, R.string.paviBut, R.string.klccBut,R.string.midBut,R.string.ioiBut, R.string.ouBut, R.string.btsBut, R.string.sunwayBut, R.string.starhillBut, R.string.gardensBut, R.string.gardensBut};
        int[] mallLink = {R.string.trxLink, R.string.paviLink, R.string.klccLink,R.string.midLink,R.string.ioiLink, R.string.ouLink, R.string.btsLink, R.string.sunwayLink, R.string.starhillLink, R.string.gardensLink, R.string.gardensLink};
        String[] nameList = {"The Exchange TRX", "Pavilion Kuala Lumpur", "Suria KLCC", "Mid Valley Megamall", "IOI City Mall","1 Utama Shopping Mall", "Berjaya Times Square", "Sunway Pyramid", "Starhill", "The Gardens Mall", "The Gardens Mall"};
        String[] locList = {"KL", "KL", "KL","KL", "Puchong", "KD", "KL", "Subang", "KL", "KL", "30 mins"};

        for (int i = 0; i < imageList.length; i++){
            listData = new ListData(nameList[i], locList[i], addList[i], storeList[i], imageList[i], mallCaps[i], mallPub[i], mallBut[i], mallLink[i]);
            dataArrayList.add(listData);
        }
        listAdapter = new ListAdapter(requireActivity(), dataArrayList);
        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(requireActivity(), DetailedActivity.class);
                intent.putExtra("name", nameList[i]);
                intent.putExtra("location", locList[i]);
                intent.putExtra("address", addList[i]);
                intent.putExtra("store", storeList[i]);
                intent.putExtra("image", imageList[i]);
                intent.putExtra("description", mallCaps[i]);
                intent.putExtra("public", mallPub[i]);
                intent.putExtra("button", mallBut[i]);
                intent.putExtra("link", mallLink[i]);
                startActivity(intent);
            }
        });

        return binding.getRoot();
    }
}