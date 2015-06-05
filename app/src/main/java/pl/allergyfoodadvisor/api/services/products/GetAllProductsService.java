package pl.allergyfoodadvisor.api.services.products;

import java.util.List;

import pl.allergyfoodadvisor.api.interactions.GetAPIInteraction;
import pl.allergyfoodadvisor.api.pojos.Product;
import pl.allergyfoodadvisor.main.AllergyAdvisor;

public final class GetAllProductsService extends GetAPIInteraction {
    private List<Product> products;

    public GetAllProductsService() {
    }

    public Boolean invoke() {
        products = AllergyAdvisor.getAPI().getProducts();
        return true;
    }
}
