package pl.allergyfoodadvisor.api;


import android.os.AsyncTask;
import android.widget.Toast;

import pl.allergyfoodadvisor.R;
import pl.allergyfoodadvisor.api.interactions.APIInteraction;
import pl.allergyfoodadvisor.extras.BusProvider;
import pl.allergyfoodadvisor.extras.CommonMethods;
import pl.allergyfoodadvisor.main.AllergyAdvisor;
import retrofit.RetrofitError;

public class APIAsyncTask extends AsyncTask<APIInteraction, Void, Boolean> {
    private APIInteraction mInteraction;

    public APIAsyncTask() {

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (!CommonMethods.isNetworkAvailable()) {
            Toast.makeText(
                    AllergyAdvisor.getInstance(),
                    R.string.no_network_access,
                    Toast.LENGTH_LONG
            ).show();
            this.cancel(false);
        }
    }

    /**
     * NOTE: executed in the main thread
     *
     * @param result
     */
    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        if (result) {
            BusProvider.getInstance().getBus().post(mInteraction);
        } else {
            Toast toast = Toast.makeText(
                    AllergyAdvisor.getInstance(),
                    mInteraction.getErrorMessage(),
                    Toast.LENGTH_SHORT
            );
            toast.show();
        }
    }

    @Override
    protected Boolean doInBackground(APIInteraction... params) {
        mInteraction = params[0];
        Boolean result = false;
        try {
            result = mInteraction.invoke();
        } catch (RetrofitError e) {
            mInteraction.onError(e);
        }
        return result;
    }

}
