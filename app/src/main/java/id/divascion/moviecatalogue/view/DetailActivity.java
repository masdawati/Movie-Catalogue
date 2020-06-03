package id.divascion.moviecatalogue.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import id.divascion.moviecatalogue.BuildConfig;
import id.divascion.moviecatalogue.R;
import id.divascion.moviecatalogue.presenter.Tv;

public class DetailActivity extends AppCompatActivity {

    TextView tvName;
    TextView tvDescription;
    ImageView ivPhoto;
    RatingBar rating;
    TextView tvDate;
    TextView tvLanguage;
    TextView ratingText;
    TextView castOne;
    TextView castTwo;
    TextView castThree;
    TextView crewOne;
    TextView crewTwo;
    CircleImageView photoOne;
    CircleImageView photoTwo;
    CircleImageView photoThree;;
    CircleImageView photoFour;;
    CircleImageView photoFive;
    Tv tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        this.tv = getIntent().getParcelableExtra("tv");

        loadView();
    }

    private void loadView() {
        tvName = findViewById(R.id.tv_detail_name);
        tvDescription = findViewById(R.id.tv_detail_desc);
        ivPhoto = findViewById(R.id.iv_detail_photo);
        rating = findViewById(R.id.tv_rating);
        tvDate = findViewById(R.id.tv_detail_date);
        tvLanguage = findViewById(R.id.tv_detail_language);
        ratingText = findViewById(R.id.tv_rating_text);
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

}

