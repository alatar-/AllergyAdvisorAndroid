package pl.allergyfoodadvisor.api.services.products;

import java.util.List;

import pl.allergyfoodadvisor.api.interactions.GetAPIInteraction;
import pl.allergyfoodadvisor.api.pojos.Product;
import pl.allergyfoodadvisor.main.AllergyAdvisor;

public final class GetProductsService extends GetAPIInteraction {
    private List<Product> products;
    private String mSearchString;

    public GetProductsService(String name) {
        this.mSearchString = name;
    }

    public Boolean invoke() {
        products = AllergyAdvisor.getAPI().getProducts(this.mSearchString);
        return true;
    }

    public List<Product> getProducts() {
        return this.products;
    }
}
