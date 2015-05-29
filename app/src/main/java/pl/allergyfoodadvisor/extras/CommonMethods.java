package pl.allergyfoodadvisor.extras;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import pl.allergyfoodadvisor.main.AllergyAdvisor;

public class CommonMethods {

    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivity = (ConnectivityManager) AllergyAdvisor.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

    static public int getResourceIdByName(String resourceClass, String resourceName) {
        return AllergyAdvisor.getInstance().getResources().getIdentifier(resourceName, resourceClass, AllergyAdvisor.getInstance().getPackageName());
    }

    /**
     * NOTE!!! To be used only as getResources provider (because of memory leaks)
     * REF: http://stackoverflow.com/questions/987072/using-application-context-everywhere
     */
    static public Resources getResources() {
        return AllergyAdvisor.getInstance().getApplicationContext().getResources();
    }
}
