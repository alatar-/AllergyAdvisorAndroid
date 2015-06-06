package pl.allergyfoodadvisor.api.services.products;

import pl.allergyfoodadvisor.api.interactions.GetAPIInteraction;
import pl.allergyfoodadvisor.api.pojos.Product;
import pl.allergyfoodadvisor.main.AllergyAdvisor;

public final class GetSingleProductService extends GetAPIInteraction {
    private Product mProduct;
    private String mProductId;

    public GetSingleProductService(String id) {
        this.mProductId = "5571bf896b07e160f95e015a";
    }

    public Boolean invoke() {
        mProduct = AllergyAdvisor.getAPI().getProduct(this.mProductId);
        return true;
    }
}
