package pl.allergyfoodadvisor.extras.searchviews;

import android.support.v7.widget.SearchView;

import pl.allergyfoodadvisor.api.APIAsyncTask;
import pl.allergyfoodadvisor.api.services.allergens.GetAllergensService;

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
