package zero.com.kyiimal.data.vos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponseVO {

    @SerializedName("results")
    private List<MovieVO> data;

    public List<MovieVO> getData() {
        return data;
    }

    public void setData(List<MovieVO> data) {
        this.data = data;
    }
}
