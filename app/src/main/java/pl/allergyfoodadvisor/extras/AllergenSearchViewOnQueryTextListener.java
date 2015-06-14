package pl.allergyfoodadvisor.extras;

import android.support.v7.widget.SearchView;

import pl.allergyfoodadvisor.api.APIAsyncTask;
import pl.allergyfoodadvisor.api.services.allergens.GetAllergensService;
import pl.allergyfoodadvisor.api.services.products.GetProductsService;

public class AllergenSearchViewOnQueryTextListener implements SearchView.OnQueryTextListener {
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText.length() > 0) {
            GetAllergensService service = new GetAllergensService(newText);
            (new APIAsyncTask()).execute(service);
        }
        return true;
    }
}
