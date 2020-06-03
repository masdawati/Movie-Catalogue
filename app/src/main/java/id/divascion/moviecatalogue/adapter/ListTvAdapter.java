package id.divascion.moviecatalogue.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

import id.divascion.moviecatalogue.BuildConfig;
import id.divascion.moviecatalogue.view.DetailActivity;
import id.divascion.moviecatalogue.R;
import id.divascion.moviecatalogue.presenter.Tv;

public class ListTvAdapter extends RecyclerView.Adapter<ListTvAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Tv> listTv;

    public ListTvAdapter(Context context) {
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        viewHolder.tvName.setText(getListTv().get(i).getName());
        viewHolder.tvDescription.setText(getListTv().get(i).getDescription());
        viewHolder.tvDate.setText(getListTv().get(i).getDate());
        String photo = BuildConfig.MOVIE_POSTER+getListTv().get(i).getPoster();
        Glide.with(context)
                .load(photo)
                .into(viewHolder.ivPhoto);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("tv", getListTv().get(i));
                Log.e("Title", getListTv().get(i).getName());
                getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListTv().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvDescription;
        ImageView ivPhoto;
        TextView tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvDescription = itemView.findViewById(R.id.tv_item_desc);
            ivPhoto = itemView.findViewById(R.id.iv_item_photo);
            tvDate = itemView.findViewById(R.id.tv_item_date);
        }
    }
}

