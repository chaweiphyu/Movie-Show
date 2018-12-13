package zero.com.kyiimal.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import zero.com.kyiimal.R;
import zero.com.kyiimal.adapters.MovieGenresAdapter;
import zero.com.kyiimal.adapters.MovieTarilerAdapter;
import zero.com.kyiimal.data.vos.MovieDetailResponseVO;
import zero.com.kyiimal.data.vos.MovieTailerResponseVO;
import zero.com.kyiimal.data.vos.YoutudeVO;
import zero.com.kyiimal.network.ApiService;
import zero.com.kyiimal.utils.RetrofitHelper;
import zero.com.kyiimal.utils.ShareConstant;

public class MovieDetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @BindView(R.id.iv_movie_detail)
    ImageView movieImage;

    @BindView(R.id.tv_tag_line)
    TextView tagline;

    @BindView(R.id.tv_movie_desc)
    TextView description;

    @BindView(R.id.tv_release_date)
    TextView releasedDate;

    @BindView(R.id.tv_movie_title)
    TextView movieTitle;

    @BindView(R.id.tv_time)
    TextView time;

    @BindView(R.id.rc_movie_genres)
    RecyclerView movieGenres;

    @BindView(R.id.rc_movie_trailer)
    RecyclerView moveTrailer;

    private long movieId;

    private MovieGenresAdapter mgAdapter;

    private MovieTarilerAdapter mtAdapter;

    private MovieDetailResponseVO movieDetailResponseVO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this, this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        movieId = getIntent().getLongExtra("movieId", 0);
        Toast.makeText(getApplicationContext(), "moiveId = " + movieId, Toast.LENGTH_LONG).show();

        mgAdapter = new MovieGenresAdapter();

        mtAdapter = new MovieTarilerAdapter(new ArrayList<YoutudeVO>());

        movieGenres.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        movieGenres.setAdapter(mgAdapter);

        moveTrailer.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        moveTrailer.setAdapter(mtAdapter);

        getMovieDetail(movieId);

        getYoubeMovie(movieId);

    }

    private void getYoubeMovie(long movieId) {

        ApiService apiService = RetrofitHelper.getInstance().getApiService();
        apiService.getMovieTrailer(movieId,ShareConstant.API_KEY,ShareConstant.LANGUAGE).enqueue(new Callback<MovieTailerResponseVO>() {
            @Override
            public void onResponse(Call<MovieTailerResponseVO> call, Response<MovieTailerResponseVO> response) {
                mtAdapter.setNewData(response.body().getResult());
            }

            @Override
            public void onFailure(Call<MovieTailerResponseVO> call, Throwable t) {

            }
        });

    }

    private void getMovieDetail(long movieId) {
        ApiService apiService = RetrofitHelper.getInstance().getApiService();
        apiService.getNowMovieDetails(movieId, ShareConstant.API_KEY, ShareConstant.LANGUAGE).enqueue(new Callback<MovieDetailResponseVO>() {
            @Override
            public void onResponse(Call<MovieDetailResponseVO> call, Response<MovieDetailResponseVO> response) {

                Glide.with(movieImage.getContext()).load("http://image.tmdb.org/t/p/w500" + response.body().getPosterPath()).into(movieImage);

                tagline.setText(response.body().getTagline());

                description.setText(response.body().getOverview());

                releasedDate.setText("Released : " + response.body().getReleaseDate());

                movieTitle.setText(response.body().getTitle());

                int runtime = response.body().getRuntime();

                int movieHour = runtime / 60;

                int movieMin = runtime % 60;

                time.setText(movieHour + "hr  " + movieMin + "mins");

                mgAdapter.setNewData(response.body().getGenres());
            }

            @Override
            public void onFailure(Call<MovieDetailResponseVO> call, Throwable t) {
                Log.i("failmovie", t.getLocalizedMessage());
            }
        });
    }

}
