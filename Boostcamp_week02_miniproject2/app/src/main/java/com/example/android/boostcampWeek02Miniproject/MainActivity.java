package com.example.android.boostcampWeek02Miniproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.vp_tab) TabLayout tabLayout;
    @BindView(R.id.rv) RecyclerView rv;
    @BindView(R.id.progressBar2) ProgressBar pb;

    RecyclerView.LayoutManager layoutManager;
    CustomAdapter adapter;
    private int layoutType = 0;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<CardItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
        ButterKnife.bind(this);
        initView();
        initRecyclerView(items);
        firebaseDataUpdate();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        //activity, drawerLayout,Toolbar,string ,string
        //drawer와 toolbar를 연결시켜주는 객체.
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initRecyclerView(ArrayList<CardItem> items) {
        Collections.sort(items, new CustomComparator(0));
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        adapter = new CustomAdapter(items);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                adapter.sort(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    void firebaseDataUpdate() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("cv_items");
        pb.setVisibility(View.VISIBLE);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pb.setVisibility(View.GONE);
                items.clear();

                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    CardItem c = s.getValue(CardItem.class);
                    items.add(c);
                }
                adapter.updateItem(items);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @OnClick(R.id.layout_button)
    public void click(ImageView v) {
        switch (layoutType) {
            case 0:
                layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                v.setImageResource(R.drawable.grid);
                layoutType = 1;
                break;
            case 1:
                layoutManager = new GridLayoutManager(this, 3);
                v.setImageResource(R.drawable.linear);
                layoutType = 2;
                break;
            case 2:
                layoutManager = new LinearLayoutManager(this);
                v.setImageResource(R.drawable.stagger);
                layoutType = 0;
                break;
        }
        rv.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

        }
        return false;
    }
}
