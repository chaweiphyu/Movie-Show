package zero.com.kyiimal.fragments;


import android.app.ProgressDialog;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import zero.com.kyiimal.R;
import zero.com.kyiimal.data.MovieModel;
import zero.com.kyiimal.data.vos.MovieResponseVO;
import zero.com.kyiimal.data.vos.MovieVO;
import zero.com.kyiimal.adapters.MovieAdapter;
import zero.com.kyiimal.network.ApiService;
import zero.com.kyiimal.utils.RetrofitHelper;
import zero.com.kyiimal.utils.ShareConstant;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment {

    private RecyclerView rvMovie;

    private MovieAdapter mAdapter;

    private ProgressDialog progressDialog;

    public PopularFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_popular, container, false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading....");
        progressDialog.setCancelable(false);
        progressDialog.show();

        rvMovie = convertView.findViewById(R.id.rvMovie);

        mAdapter = new MovieAdapter(new ArrayList<MovieVO>());
        rvMovie.setLayoutManager(new GridLayoutManager(this.getContext(),3));
        rvMovie.setAdapter(mAdapter);

        getPopularMovie();

        return convertView;
    }

    public void getPopularMovie(){
        ApiService apiService = RetrofitHelper.getInstance().getApiService();

        apiService.getPopularMovie(ShareConstant.API_KEY,ShareConstant.LANGUAGE,ShareConstant.PAGE)
                .enqueue(new Callback<MovieResponseVO>() {
                    @Override
                    public void onResponse(Call<MovieResponseVO> call, Response<MovieResponseVO> response) {

                        progressDialog.hide();
                        mAdapter.setNewData(response.body().getData());

                    }

                    @Override
                    public void onFailure(Call<MovieResponseVO> call, Throwable t) {
                        progressDialog.hide();
                    }
                });

    }

}
