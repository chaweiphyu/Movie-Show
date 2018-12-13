package zero.com.kyiimal.data.vos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieTailerResponseVO {

    @SerializedName("id")
    private Long id;

    @SerializedName("results")
    private List<YoutudeVO> result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<YoutudeVO> getResult() {
        return result;
    }

    public void setResult(List<YoutudeVO> result) {
        this.result = result;
    }
}
