package pl.allergyfoodadvisor.activities.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import pl.allergyfoodadvisor.R;
import pl.allergyfoodadvisor.api.pojos.Allergen;
import pl.allergyfoodadvisor.api.pojos.Product;
import pl.allergyfoodadvisor.api.services.products.GetSingleProductService;
import pl.allergyfoodadvisor.extras.BusProvider;
import pl.allergyfoodadvisor.extras.DataManager;
import pl.allergyfoodadvisor.extras.ProductSearchViewOnQueryTextListener;
import pl.allergyfoodadvisor.extras.RecyclerViewAllergensAdapter;
import pl.allergyfoodadvisor.extras.RecyclerViewHistoryAdapter;
import pl.allergyfoodadvisor.extras.RecyclerViewProductAdapter;

public class MyAllergensFragment extends Fragment {
    private View mRootView;
    private List<Allergen> mMyAllergens;
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_my_allergens, container, false);

        mMyAllergens = new ArrayList<Allergen>();

        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerview_alrg);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(new RecyclerViewAllergensAdapter(getActivity(),
                this.mMyAllergens));

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
