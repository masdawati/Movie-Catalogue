package id.divascion.moviecatalogue.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.divascion.moviecatalogue.BuildConfig;
import id.divascion.moviecatalogue.R;
import id.divascion.moviecatalogue.model.tv.Tv;

public class GridTvAdapter extends RecyclerView.Adapter<GridTvAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Tv> listTv;

    public GridTvAdapter(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public ArrayList<Tv> getListTv() {
        return listTv;
    }

    public void setListTv(ArrayList<Tv> listTv) {
        this.listTv = listTv;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GridTvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grid, viewGroup, false);
        return new GridTvAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridTvAdapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        final String name = getListTv().get(i).getName();
        viewHolder.tvName.setText(name);
        String photo = BuildConfig.MOVIE_POSTER + getListTv().get(i).getPoster();
        Glide.with(context)
                .load(photo)
                .into(viewHolder.ivPhoto);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListTv().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView ivPhoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_item_name_grid);
            ivPhoto = itemView.findViewById(R.id.iv_item_photo_grid);
        }
    }
}