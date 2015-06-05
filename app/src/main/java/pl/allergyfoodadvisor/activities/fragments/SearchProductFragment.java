package pl.allergyfoodadvisor.activities.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.allergyfoodadvisor.R;
import pl.allergyfoodadvisor.activities.Cheeses;
import pl.allergyfoodadvisor.api.pojos.Product;
import pl.allergyfoodadvisor.api.services.products.GetProductsService;
import pl.allergyfoodadvisor.api.services.products.GetSingleProductService;
import pl.allergyfoodadvisor.extras.BusProvider;
import pl.allergyfoodadvisor.extras.RecyclerViewProductAdapter;

public class SearchProductFragment extends Fragment {
    private View mRootView;
    private List<Product> mProducts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_search_product, container, false);

        setupRecyclerView((RecyclerView) mRootView.findViewById(R.id.recyclerview)); //TODO: class should be already registered to bus

        FloatingActionButton fab = (FloatingActionButton) mRootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        this.mProducts = new ArrayList<Product>();
        recyclerView.setAdapter(new RecyclerViewProductAdapter(getActivity(),
                this.mProducts));
        onAPIResponse((GetProductsService) null);
    }

    private List<Product> getRandomSublist(String[] array, int amount) {
        ArrayList<Product> list = new ArrayList<>(amount);
        Random random = new Random();
        while (list.size() < amount) {
            Product x = new Product();
            x.name = array[random.nextInt(array.length)];
            list.add(x);
        }
        return list;
    }

    @Subscribe
    public void onAPIResponse(GetProductsService service) {
        //
        List<Product> prud = getRandomSublist(Cheeses.sCheeseStrings, 30);
        this.mProducts.clear();
        this.mProducts.addAll(prud);
    }

    @Subscribe
    public void onAPIResponse(GetSingleProductService service) {

    }
}
