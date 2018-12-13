package zero.com.kyiimal.adapters;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zero.com.kyiimal.R;
import zero.com.kyiimal.data.vos.YoutudeVO;

public class MovieTarilerAdapter extends RecyclerView.Adapter<MovieTarilerAdapter.MovieViewHolder> {

    private List<YoutudeVO> youtudeVOList;

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private YoutudeVO youtudeVO;

        @BindView(R.id.iv_movie_trailer)
        ImageView movieTrailer;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }

        @OnClick(R.id.fl_movie)
        public void onClickMovieVideo(View view) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + youtudeVO.getKey()));

            view.getContext().startActivity(webIntent);
        }

        public void bind(YoutudeVO youtudeVO) {
            this.youtudeVO = youtudeVO;
            movieTrailer.setImageResource(R.drawable.movie_place_holder);
        }
    }

    public void setNewData(List<YoutudeVO> youtudeVOS){
        this.youtudeVOList = youtudeVOS;
        notifyDataSetChanged();
    }

    public MovieTarilerAdapter(List<YoutudeVO> youtudeVOList) {
        this.youtudeVOList = youtudeVOList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        MovieViewHolder movieViewHolder = new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie_trailer,parent,false));
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
       holder.bind(youtudeVOList.get(position));
    }


    @Override
    public int getItemCount() {
        return this.youtudeVOList.size();
    }

}
