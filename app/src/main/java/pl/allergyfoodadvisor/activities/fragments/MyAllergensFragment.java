package pl.allergyfoodadvisor.activities.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import pl.allergyfoodadvisor.R;
import pl.allergyfoodadvisor.api.pojos.Product;
import pl.allergyfoodadvisor.api.services.products.GetSingleProductService;
import pl.allergyfoodadvisor.extras.BusProvider;
import pl.allergyfoodadvisor.extras.DataManager;
import pl.allergyfoodadvisor.extras.RecyclerViewHistoryAdapter;

public class MyAllergensFragment extends Fragment {
    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_my_allergens, container, false);

        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().getBus().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().getBus().unregister(this);
    }
}
