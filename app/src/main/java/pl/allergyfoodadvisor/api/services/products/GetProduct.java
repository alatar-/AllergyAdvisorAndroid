package pl.allergyfoodadvisor.api.services.products;

import pl.allergyfoodadvisor.api.interactions.GetAPIInteraction;
import pl.allergyfoodadvisor.api.pojos.Product;
import pl.allergyfoodadvisor.main.AllergyAdvisor;

public final class GetProduct extends GetAPIInteraction {
    private Product product;

    public GetProduct() {
    }

    public Boolean invoke() {
        product = AllergyAdvisor.getAPI().getProduct();
        return true;
    }
}
