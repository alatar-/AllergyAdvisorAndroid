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
import java.util.Random;

import pl.allergyfoodadvisor.R;
import pl.allergyfoodadvisor.api.pojos.Allergen;
import pl.allergyfoodadvisor.api.services.allergens.GetAllergensService;
import pl.allergyfoodadvisor.extras.recyclerviews.RecyclerViewMyAllergenAdapter;
import pl.allergyfoodadvisor.extras.searchviews.AllergenSearchViewOnQueryTextListener;
import pl.allergyfoodadvisor.extras.BusProvider;

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
        mRecyclerView.setAdapter(new RecyclerViewMyAllergenAdapter(getActivity(),
                this.mMyAllergens));

        AllergenSearchViewOnQueryTextListener listener = new AllergenSearchViewOnQueryTextListener();
        ((SearchView) mRootView.findViewById(R.id.search_view_alrg)).setOnQueryTextListener(listener);
        listener.onQueryTextChange(".");

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

    private List<Allergen> getRandomSublist(String[] array, int amount) {
        ArrayList<Allergen> list = new ArrayList<>(amount);
        Random random = new Random();
        while (list.size() < amount) {
            Allergen x = new Allergen();
            x.name = array[random.nextInt(array.length)];
            list.add(x);
        }
        return list;
    }

    @Subscribe
    public void onAPIResponse(GetAllergensService service) {
//        List<Product> prud = getRandomSublist(Cheeses.sCheeseStrings, 30);
        this.mMyAllergens.clear();
        this.mMyAllergens.addAll(service.getAllergens());
        ((RecyclerView) mRootView.findViewById(R.id.recyclerview_alrg))
                .getAdapter().notifyDataSetChanged();
    }
}
