package id.divascion.moviecatalogue.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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
import id.divascion.moviecatalogue.R;
import id.divascion.moviecatalogue.model.credits.CrewOfTv;

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<CrewOfTv> crew;

    public CrewAdapter(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public ArrayList<CrewOfTv> getCrew() {
        return crew;
    }

    public void setCrew(ArrayList<CrewOfTv> crew) {
        this.crew = crew;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_credit, viewGroup, false);
        return new CrewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CrewAdapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        viewHolder.tvName.setText(getCrew().get(i).getName());
        viewHolder.tvPosition.setText(getCrew().get(i).getJob());
        if (getCrew().get(i).getProfilePath().isEmpty() || getCrew().get(i).getProfilePath() == null
                || getCrew().get(i).getProfilePath().equals("") || getCrew().get(i).getProfilePath().equals("null")) {
            Glide.with(context)
                    .load(BuildConfig.PROFILE_DEFAULT)
                    .into(viewHolder.ivPhoto);
            Log.e("PROFILE", "default");
        } else {
            String photo = BuildConfig.MOVIE_PROFILE + getCrew().get(i).getProfilePath();
            Glide.with(context)
                    .load(photo)
                    .into(viewHolder.ivPhoto);
            Log.e("PROFILE", "load");
        }
    }

    @Override
    public int getItemCount() {
        return getCrew().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvPosition;
        ImageView ivPhoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.item_credit_name);
            tvPosition = itemView.findViewById(R.id.item_credit_position);
            ivPhoto = itemView.findViewById(R.id.item_credit_photos);
        }
    }
}