package zero.com.kyiimal.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zero.com.kyiimal.R;
import zero.com.kyiimal.data.vos.GenresVO;
import zero.com.kyiimal.data.vos.MovieDetailResponseVO;

public class MovieGenresAdapter extends RecyclerView.Adapter<MovieGenresAdapter.MovieViewHolder> {

    List<GenresVO> movieGenresList;

    private TextView movieType;

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        public MovieViewHolder(View itemView) {

            super(itemView);

            movieType = itemView.findViewById(R.id.tv_genres);

        }

        private void bind(GenresVO moveGenres) {
            movieType.setText(moveGenres.getName());
        }
    }

    public MovieGenresAdapter() {
        movieGenresList = new ArrayList<>();
    }

    public void setNewData(List<GenresVO> movieList){
        this.movieGenresList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        MovieViewHolder movieViewHolder = new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_moive_genres, parent, false));

        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        holder.bind(movieGenresList.get(position));

    }

    @Override
    public int getItemCount() {
        return movieGenresList.size();
    }


}
