package zero.com.kyiimal.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import zero.com.kyiimal.R;
import zero.com.kyiimal.data.vos.MovieVO;
import zero.com.kyiimal.activity.MovieDetailsActivity;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<MovieVO> movieList;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView ivMovie;

        private  Context context;

        private MovieVO movieVO;

        public ViewHolder(Context context, View itemView) {
            super(itemView);

            ivMovie = itemView.findViewById(R.id.iv_movie);

            this.context = context;
            itemView.setOnClickListener(this);

        }

        private void bindData(MovieVO movieVO){
            this.movieVO = movieVO;
            Glide.with(ivMovie.getContext())
                    .load("http://image.tmdb.org/t/p/w500" + movieVO.getPosterPath())
                    .into(ivMovie);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION){
                Intent intent = new Intent(context,MovieDetailsActivity.class);
                intent.putExtra("movieId", movieVO.getId());
                context.startActivity(intent);
                //Toast.makeText(context,"you clicked",Toast.LENGTH_LONG).show();
            }
        }
    }

    public MovieAdapter(List<MovieVO> movie) {
        movieList = new ArrayList<>();
    }

    public void setNewData(List<MovieVO> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        ViewHolder viewHolder = new ViewHolder(context,LayoutInflater.from(context).inflate(R.layout.list_item_movie,parent,false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(movieList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


}
