package pl.allergyfoodadvisor.api.interactions;

import android.util.Log;

import pl.allergyfoodadvisor.api.pojos.Failure;
import retrofit.RetrofitError;

abstract public class APIInteraction {
    protected int mStatus = 200;
    protected Failure mError;

    public APIInteraction() {

    }

    abstract public Boolean invoke();

    public String getErrorMessage() {
        return this.mError.message;
    }

    public void onError(RetrofitError cause) {
        this.mStatus = cause.getResponse().getStatus();
        this.mError = (Failure) cause.getBodyAs(Failure.class);
        if (this.mError == null) {
            Log.w("API", "it's likely that structure of successfull response is invalid e.g. Message<String>");
        }
        Log.d("API", "request failed with code " + this.mStatus);
        Log.d("API", "code message: " + mError.code);
        Log.d("API", "message: " + mError.message);
    }
}
