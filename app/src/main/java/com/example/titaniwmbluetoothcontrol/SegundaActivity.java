package com.example.titaniwmbluetoothcontrol;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.titaniwmbluetoothcontrol.adapter.MyFragmentsPageAdapter;
import com.example.titaniwmbluetoothcontrol.interfaces.TrocaDados;
import com.example.titaniwmbluetoothcontrol.interfaces.UserModel;
import com.google.android.material.tabs.TabLayout;

public class SegundaActivity extends AppCompatActivity implements TrocaDados {
    private static final int SELECT_DISCOVERED_DEVICE =1 ;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    FragmentManager gerenciadorFragment = getSupportFragmentManager();
    PareadosActivity pareados;

    private UserModel model;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);

        mViewPager.setAdapter(new MyFragmentsPageAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.titles_tab)));
        mTabLayout.setupWithViewPager(mViewPager);
        UserModel model = ViewModelProviders.of(this).get(UserModel.class);
        pareados = (PareadosActivity) model.getFragment();

    }



    @Override
    public void adicionarPareado(BluetoothDevice bt) {
        pareados.adicionaPareado(bt);

    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        finish();
    }
}
