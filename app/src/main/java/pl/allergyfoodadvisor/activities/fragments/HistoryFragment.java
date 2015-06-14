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
import pl.allergyfoodadvisor.extras.recyclerviews.RecyclerViewHistoryAdapter;

public class HistoryFragment extends Fragment {
    private View mRootView;
    private List<Product> mProducts;
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_history, container, false);

        mProducts = new ArrayList<Product>();

        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerview2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(new RecyclerViewHistoryAdapter(getActivity(),
                this.mProducts));

        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        List<String> products = DataManager.getInstance().getHistory();
        for (String product: products) {
            Product x = new Product();
            x._id = product.split("\\|")[0];
            x.name = product.split("\\|")[1];

            mProducts.add(0, x);
            mRecyclerView.getAdapter().notifyItemInserted(0);
        }
        BusProvider.getInstance().getBus().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().getBus().unregister(this);
    }

    @Subscribe
    public void onAPIResponse(GetSingleProductService service) {

    }
}
