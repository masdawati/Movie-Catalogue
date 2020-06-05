package id.divascion.moviecatalogue.ui;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import id.divascion.moviecatalogue.BuildConfig;
import id.divascion.moviecatalogue.R;
import id.divascion.moviecatalogue.adapter.CastAdapter;
import id.divascion.moviecatalogue.adapter.CrewAdapter;
import id.divascion.moviecatalogue.model.Api;
import id.divascion.moviecatalogue.model.Network;
import id.divascion.moviecatalogue.model.credits.CastOfTv;
import id.divascion.moviecatalogue.model.credits.CrewOfTv;
import id.divascion.moviecatalogue.model.tv.Tv;

public class DetailActivity extends AppCompatActivity {

    private ProgressBar pb;
    private Tv tv;
    private ArrayList<CastOfTv> cast;
    private ArrayList<CrewOfTv> crew;
    private ArrayList<CastOfTv> tempCast;
    private ArrayList<CrewOfTv> tempCrew;
    private RecyclerView rvCast;
    private RecyclerView rvCrew;
    private CrewAdapter adapterCrew;
    private CastAdapter adapterCast;
    private TextView castEmpty;
    private TextView crewEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.tv = getIntent().getParcelableExtra("tv");
        pb = findViewById(R.id.pb_detail);
        pb.setVisibility(View.VISIBLE);
        castEmpty = findViewById(R.id.tv_detail_cast_empty);
        crewEmpty = findViewById(R.id.tv_detail_crew_empty);
        rvCast = findViewById(R.id.rv_cast);
        rvCrew = findViewById(R.id.rv_crew);
        cast = new ArrayList<>();
        crew = new ArrayList<>();
        tempCast = new ArrayList<>();
        tempCrew = new ArrayList<>();
        showRecyclerList();
        loadView();
        loadData();
    }

    private void showRecyclerList() {
        adapterCast = new CastAdapter(this);
        adapterCrew = new CrewAdapter(this);
        adapterCast.setCast(cast);
        adapterCrew.setCrew(crew);
        rvCast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvCrew.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvCast.setAdapter(adapterCast);
        rvCrew.setAdapter(adapterCrew);
    }

    private void loadView() {
        TextView tvName = findViewById(R.id.tv_detail_name);
        TextView tvDescription = findViewById(R.id.tv_detail_desc);
        ImageView ivPhoto = findViewById(R.id.iv_detail_photo);
        RatingBar rating = findViewById(R.id.tv_rating);
        TextView tvDate = findViewById(R.id.tv_detail_date);
        TextView tvLanguage = findViewById(R.id.tv_detail_language);
        TextView ratingText = findViewById(R.id.tv_rating_text);
        tvName.setText(tv.getName());
        tvDescription.setText(tv.getDescription());
        String photo = BuildConfig.MOVIE_POSTER + tv.getPoster();
        Glide.with(this)
                .load(photo)
                .into(ivPhoto);
        float rate = (float) (tv.getRating() / 10) * 5;
        ratingText.setText(String.valueOf(rate));
        rating.setRating(rate);
        tvDate.setText(tv.getDate());
        tvLanguage.setText(tv.getLanguage());
    }

    private void loadData() {
        String id = Integer.toString(tv.getId());
        URL url = Api.getCredits(id);
        Log.e("url", url.toString());
        new TvAsyncTask().execute(url);
    }

    @SuppressLint("StaticFieldLeak")
    private class TvAsyncTask extends AsyncTask<URL, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            String result = null;
            try {
                result = Network.getFromNetwork(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pb.setVisibility(View.GONE);

            try {
                tempCrew.clear();
                tempCast.clear();
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArrayCast = jsonObject.getJSONArray("cast");
                JSONArray jsonArrayCrew = jsonObject.getJSONArray("crew");
                for (int i = 0; i < jsonArrayCrew.length(); i++) {
                    JSONObject object = jsonArrayCrew.getJSONObject(i);
                    CrewOfTv crew = new CrewOfTv(object);
                    tempCrew.add(crew);
                }
                for (int i = 0; i < jsonArrayCast.length(); i++) {
                    JSONObject object = jsonArrayCast.getJSONObject(i);
                    CastOfTv cast = new CastOfTv(object);
                    tempCast.add(cast);
                }
                crew.addAll(tempCrew);
                cast.addAll(tempCast);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (crew.size() > 0) {
                rvCrew.setVisibility(View.VISIBLE);
                crewEmpty.setVisibility(View.GONE);
                adapterCrew.setCrew(crew);
            } else {
                rvCrew.setVisibility(View.GONE);
                crewEmpty.setVisibility(View.VISIBLE);
            }
            if (cast.size() > 0) {
                rvCast.setVisibility(View.VISIBLE);
                castEmpty.setVisibility(View.GONE);
                adapterCast.setCast(cast);
            } else {
                rvCast.setVisibility(View.GONE);
                castEmpty.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

