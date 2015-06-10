package pl.allergyfoodadvisor.activities.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import pl.allergyfoodadvisor.api.APIAsyncTask;
import pl.allergyfoodadvisor.api.pojos.Product;
import pl.allergyfoodadvisor.api.services.products.GetProductsService;
import pl.allergyfoodadvisor.api.services.products.GetSingleProductService;
import pl.allergyfoodadvisor.extras.BusProvider;
import pl.allergyfoodadvisor.extras.ProductSearchViewOnQueryTextListener;
import pl.allergyfoodadvisor.extras.RecyclerViewAllergensAdapter;
import pl.allergyfoodadvisor.extras.RecyclerViewProductAdapter;

public class SearchProductFragment extends Fragment {
    private View mRootView;
    private List<Product> mProducts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_search_product, container, false);

        setupRecyclerView((RecyclerView) mRootView.findViewById(R.id.recyclerview));
        setupSearchVeiw((SearchView) mRootView.findViewById(R.id.search_view));

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

    private void setupSearchVeiw(SearchView searchView) {
        ProductSearchViewOnQueryTextListener listener = new ProductSearchViewOnQueryTextListener();
        searchView.setOnQueryTextListener(listener);
        listener.onQueryTextChange(".");
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        this.mProducts = new ArrayList<Product>();
        recyclerView.setAdapter(new RecyclerViewProductAdapter(getActivity(),
                this.mProducts));

//        GetProductsService productsService = new GetProductsService("sda");
//        new APIAsyncTask().execute(productsService);
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
//        List<Product> prud = getRandomSublist(Cheeses.sCheeseStrings, 30);
        this.mProducts.clear();
        this.mProducts.addAll(service.getProducts());
        ((RecyclerView) mRootView.findViewById(R.id.recyclerview))
                .getAdapter().notifyDataSetChanged();
    }

    @Subscribe
    public void onAPIResponse(GetSingleProductService service) {

    }
}
