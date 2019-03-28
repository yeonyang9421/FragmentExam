package kr.co.woobi.imyeon.fragmentexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = findViewById(R.id.pager);
        final MoviePosterPagerAdapter adapter = new MoviePosterPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://boostcourse-appapi.connect.or.kr:10000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);
        service.listMoiveList().enqueue(new Callback<ReadMovieList>() {
            @Override
            public void onResponse(Call<ReadMovieList> call, Response<ReadMovieList> response) {

                if (response.body() != null) {
                    List<MovieInfo> movieLists = response.body().result;

                    List<Fragment> movieFragmentList = new ArrayList<>();
                    for (MovieInfo movieInfo :  movieLists) {
                        movieFragmentList.add(MoviePosterFragment.newInstance(movieInfo));
                    }
                    adapter.setItems(movieFragmentList);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<ReadMovieList> call, Throwable t) {
//                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                Log.d(TAG, "onResponse: connect failed" + t.getLocalizedMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.first) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
            finish();
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        }
    }

    public interface OnBackKeyPressedListener {
        public void onBackKey();
    }

    private OnBackKeyPressedListener mOnBackKeyPressedListener;

    public void setOnKeyBackPressedListener(OnBackKeyPressedListener listener) {
        mOnBackKeyPressedListener = listener;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START) || (mOnBackKeyPressedListener != null)) {
            drawer.closeDrawer(GravityCompat.START);

            mOnBackKeyPressedListener.onBackKey();
        } else if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }
}