package pl.allergyfoodadvisor.main;

import android.app.Application;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.otto.Subscribe;

import pl.allergyfoodadvisor.api.API;
import pl.allergyfoodadvisor.api.APIRequestInterceptor;
import pl.allergyfoodadvisor.extras.BusProvider;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;

public class AllergyAdvisor extends Application {
    private static final String API_URL = "http://178.62.224.26:3531";

    private API mAPI = null;
    private static AllergyAdvisor sInstance = null;

    public AllergyAdvisor() {

    }

    static public API getAPI() {
        return sInstance.mAPI;
    }

    static public AllergyAdvisor getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAPI = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(new OkHttpClient()))
                .setRequestInterceptor(new APIRequestInterceptor())
//                .setErrorHandler(new GenericErrorHandler())
                .setEndpoint(API_URL)
                .build()
                .create(API.class);
        sInstance = this;
        BusProvider.getInstance().getBus().register(this); //listen for "global" events
    }

    @Subscribe
    public void onApiError(RetrofitError event) {
        Log.e("ALLERGYADVISOR", event.getMessage());
    }
}