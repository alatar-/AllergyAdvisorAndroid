package pl.allergyfoodadvisor.extras.searchviews;

import android.support.v7.widget.SearchView;

import pl.allergyfoodadvisor.api.APIAsyncTask;
import pl.allergyfoodadvisor.api.services.products.GetProductsService;

public class ProductSearchViewOnQueryTextListener implements SearchView.OnQueryTextListener {
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText.length() > 0) {
            GetProductsService service = new GetProductsService(newText);
            (new APIAsyncTask()).execute(service);
        }
        return true;
    }
}
