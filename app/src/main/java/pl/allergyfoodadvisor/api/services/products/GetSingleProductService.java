package pl.allergyfoodadvisor.api.services.products;

import pl.allergyfoodadvisor.api.interactions.GetAPIInteraction;
import pl.allergyfoodadvisor.api.pojos.Product;
import pl.allergyfoodadvisor.main.AllergyAdvisor;

public final class GetSingleProductService extends GetAPIInteraction {
    private Product product;
    private String productId;

    public GetSingleProductService(String id) {
        this.productId = "5571bf896b07e160f95e015a";
    }

    public Boolean invoke() {
        product = AllergyAdvisor.getAPI().getProduct(this.productId);
        return true;
    }
}
