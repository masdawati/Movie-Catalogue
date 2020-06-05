package id.divascion.moviecatalogue.ui;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import id.divascion.moviecatalogue.R;
import id.divascion.moviecatalogue.adapter.GridTvAdapter;
import id.divascion.moviecatalogue.model.Api;
import id.divascion.moviecatalogue.model.Network;
import id.divascion.moviecatalogue.model.tv.Tv;

public class TvViewGridFragment extends Fragment implements SearchView.OnQueryTextListener {
    private static final String ARG_PARAM1 = "CODE";

    private ProgressBar pb;
    private RecyclerView rv;
    private ArrayList<Tv> listTv;
    private ArrayList<Tv> tempTv;
    private GridTvAdapter listTvAdapter;
    private SearchView searchView;

    private String code;

    public TvViewGridFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            code = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_view_grid, container, false);
        rv = view.findViewById(R.id.rv_grid);
        pb = view.findViewById(R.id.pb_grid);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint("Search...");
        searchView.setOnQueryTextListener(this);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        searchView.clearFocus();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (!newText.isEmpty()) {
            listTv.clear();
            newText = newText.toLowerCase();
            for (int i = 0; i < tempTv.size(); i++) {
                String title = tempTv.get(i).getName().toLowerCase();
                if (title.contains(newText)) {
                    listTv.add(tempTv.get(i));
                }
            }
        } else {
            listTv.clear();
            listTv.addAll(tempTv);
        }
        listTvAdapter.setListTv(listTv);
        return true;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        listTv = new ArrayList<>();
        tempTv = new ArrayList<>();
        showRecyclerList();
        loadData();
    }

    private void showRecyclerList() {
        listTvAdapter = new GridTvAdapter(getActivity());
        listTvAdapter.setListTv(listTv);
        rv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rv.setAdapter(listTvAdapter);
    }

    private void loadData() {
        URL url;
        switch (code) {
            case "airing":
                url = Api.getAiring();
                Log.e("url", url.toString());
                new TvAsyncTask().execute(url);
                break;
            case "popular":
                url = Api.getPopular();
                Log.e("url", url.toString());
                new TvAsyncTask().execute(url);
                break;
            case "top":
                url = Api.getTopRated();
                Log.e("url", url.toString());
                new TvAsyncTask().execute(url);
                break;
            default:
                Toast.makeText(getActivity(), "Error, code is empty.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class TvAsyncTask extends AsyncTask<URL, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);
            rv.setVisibility(View.GONE);
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
            rv.setVisibility(View.VISIBLE);
            pb.setVisibility(View.GONE);

            try {
                tempTv.clear();
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    Tv tvList = new Tv(object);
                    listTv.add(tvList);
                }
                tempTv.addAll(listTv);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            listTvAdapter.setListTv(listTv);
        }

    }
}
