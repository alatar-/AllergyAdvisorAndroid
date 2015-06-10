package pl.allergyfoodadvisor.activities.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.allergyfoodadvisor.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class SearchAllergenFragment extends Fragment {

    public SearchAllergenFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_allergens, container, false);
    }
}
