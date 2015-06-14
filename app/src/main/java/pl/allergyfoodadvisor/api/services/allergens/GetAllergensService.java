package pl.allergyfoodadvisor.api.services.allergens;

import java.util.List;

import pl.allergyfoodadvisor.api.interactions.GetAPIInteraction;
import pl.allergyfoodadvisor.api.pojos.Allergen;
import pl.allergyfoodadvisor.api.pojos.Product;
import pl.allergyfoodadvisor.main.AllergyAdvisor;

public class GetAllergensService extends GetAPIInteraction {
    private List<Allergen> mAllergens;
    private String mSearchString;

    public GetAllergensService(String name) {
        this.mSearchString = name;
    }

    public Boolean invoke() {
        mAllergens = AllergyAdvisor.getAPI().getAllergens(this.mSearchString);
        return true;
    }

    public List<Allergen> getAllergens() {
        return this.mAllergens;
    }
}
