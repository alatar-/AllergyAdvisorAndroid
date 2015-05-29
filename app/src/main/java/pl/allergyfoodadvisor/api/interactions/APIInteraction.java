package pl.allergyfoodadvisor.api.interactions;

import android.util.Log;

import pl.allergyfoodadvisor.api.pojos.Failure;
import retrofit.RetrofitError;

abstract public class APIInteraction {
    protected int status = 200;
    protected Failure error;

    public APIInteraction() {

    }

    abstract public Boolean invoke();

    public String getErrorMessage() {
        return this.error.message;
    }

    public void onError(RetrofitError cause) {
        this.status = cause.getResponse().getStatus();
        this.error = (Failure) cause.getBodyAs(Failure.class);
        if (this.error == null) {
            Log.w("API", "it's likely that structure of successfull response is invalid e.g. Message<String>");
        }
        Log.d("API", "request failed with code " + this.status);
        Log.d("API", "code message: " + error.code);
        Log.d("API", "message: " + error.message);
    }
}
