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
import id.divascion.moviecatalogue.model.credits.CastOfTv;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder> {

    private Context context;
    private ArrayList<CastOfTv> cast;

    public CastAdapter(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public ArrayList<CastOfTv> getCast() {
        return cast;
    }

    public void setCast(ArrayList<CastOfTv> cast) {
        this.cast = cast;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CastAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_credit, viewGroup, false);
        return new CastAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CastAdapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        viewHolder.tvName.setText(getCast().get(i).getName());
        viewHolder.tvPosition.setText(getCast().get(i).getCharacter());
        Log.e("PROFILE URL", getCast().get(i).getProfilePath());
        if (getCast().get(i).getProfilePath().isEmpty() || getCast().get(i).getProfilePath() == null
                || getCast().get(i).getProfilePath().equals("") || getCast().get(i).getProfilePath().equals("null")) {
            Glide.with(context)
                    .load(BuildConfig.PROFILE_DEFAULT)
                    .into(viewHolder.ivPhoto);
            Log.e("PROFILE", "default");
        } else {
            String photo = BuildConfig.MOVIE_PROFILE + getCast().get(i).getProfilePath();
            Glide.with(context)
                    .load(photo)
                    .into(viewHolder.ivPhoto);
            Log.e("PROFILE", "load");
        }
    }

    @Override
    public int getItemCount() {
        return getCast().size();
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