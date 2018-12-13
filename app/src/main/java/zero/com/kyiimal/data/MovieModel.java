package zero.com.kyiimal.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import zero.com.kyiimal.utils.JsonUtils;
import zero.com.kyiimal.data.vos.MovieResponseVO;
import zero.com.kyiimal.data.vos.MovieVO;

public class MovieModel {

    private static MovieModel objInstance;

    private MovieModel() {}

    public static MovieModel getInstance() {
        if (objInstance == null){
            objInstance = new MovieModel();
        }
        return objInstance;
    }

    public List<MovieVO> getMovieList(Context context) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        Type collectionType = new TypeToken<MovieResponseVO>(){}.getType();
        Gson gson = gsonBuilder.create();
        List<MovieVO> movieVOList = new ArrayList<>();
        try {
            MovieResponseVO movieResponseVO = gson.fromJson(JsonUtils.loadBasicDataItem(context, "movie.json"), collectionType);
            movieVOList = movieResponseVO.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movieVOList;
    }

}
