package pl.allergyfoodadvisor.api.services.products;

import pl.allergyfoodadvisor.api.interactions.GetAPIInteraction;
import pl.allergyfoodadvisor.api.pojos.Product;
import pl.allergyfoodadvisor.main.AllergyAdvisor;

public final class GetProductService extends GetAPIInteraction {
    private Product product;

    public GetProductService() {
    }

    public Boolean invoke() {
        product = AllergyAdvisor.getAPI().getProduct("5571bf896b07e160f95e015a");
        return true;
    }
}
