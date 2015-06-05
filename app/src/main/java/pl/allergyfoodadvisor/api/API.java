package pl.allergyfoodadvisor.api;


import java.util.List;
import java.util.Map;

import pl.allergyfoodadvisor.api.pojos.Product;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Interface for the GoAll API
 * NOTE! @Path("lang") and @Header("Session-Id") are automatically added to each request by APIRequestInterceptor!
 */

public interface API {
//    @POST("/1.0/{lang}/users/")
//    Message<Integer> registerUser(@Body Map<String, Object> map);
//
//    @POST("/1.0/{lang}/users/login")
//    Message<DoubleMap<String>> loginUser(@Body Map<String, Object> map);
//
//    @POST("/1.0/{lang}/users/logout")
//    Message<String> logoutUser(@Body Map<String, Object> map);
//
//    @GET("/1.0/{lang}/users")
//    Message<SingleMap<Object>> getUserProfile();
//
//    @PUT("/1.0/{lang}/users")
//    Message<String> updateUser(@Body Map<String, Object> map);
//
    @GET("/products/{id}")
    Product getProduct(@Path("id") String id);

    @GET("/products")
    List<Product> getProducts(@Query("name") String name);

//    @POST("/1.0/{lang}/workouts/start/{id}")
//    Message<Workout> startWorkout(@Path("id") String id);
//
//    @POST("/1.0/{lang}/workouts/end/{id}")
//    Message<String> endWorkout(@Path("id") String id, @Body Map<String, Object> exercises);
//
//    @POST("/1.0/{lang}/goals")
//    Message<Integer> addNewGoal(@Body Map<String, Object> parameters);
}