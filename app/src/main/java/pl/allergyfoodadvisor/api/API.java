package pl.allergyfoodadvisor.api;

import java.util.List;

import pl.allergyfoodadvisor.api.pojos.Product;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Interface for the AllergyFoodAdvisor API
 */
public interface API {
    @GET("/products/{id}")
    Product getProduct(@Path("id") String id);

    @GET("/products")
    List<Product> getProducts(@Query("name") String name);
}