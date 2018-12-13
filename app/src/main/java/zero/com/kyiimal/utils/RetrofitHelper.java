package zero.com.kyiimal.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zero.com.kyiimal.network.ApiService;

public class RetrofitHelper {

    private static RetrofitHelper objInstance;

    private Retrofit retrofit;

    private ApiService apiService;


    private RetrofitHelper(){

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

    }

    public static RetrofitHelper getInstance() {
        if(objInstance == null){
            objInstance = new RetrofitHelper();
        }

        return objInstance;
    }

    public ApiService getApiService(){
        return apiService;
    }

}
