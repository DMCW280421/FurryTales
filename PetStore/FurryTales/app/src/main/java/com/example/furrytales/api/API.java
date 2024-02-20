package com.example.furrytales.api;

import com.example.furrytales.entity.Cart;
import com.example.furrytales.entity.Customer;
import com.example.furrytales.entity.Order;
import com.example.furrytales.entity.Product;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API {

    public static final String BASE_URL = "http://192.168.233.182:8000";

    @POST("/customer/register")
    public Call<JsonObject> registerCustomer(@Body Customer customer);

    @POST("/customer/login")
    public Call<JsonObject> loginCustomer(@Body Customer customer);

    @GET("/customer/profile")
    Call<JsonObject> getCustomer(@Header("token") String token);

    @GET("/product/search")
    Call<JsonObject> getAllProducts(@Header("token") String token);

    @POST("/cart/add")
    Call<JsonObject> addToCart(@Header("token") String token, @Body Product product);

    @GET("/cart/display")
    Call<JsonObject> getCart(@Header("token") String token);

    @PUT("/cart/update")
    Call<JsonObject> updateCartItemQuantity(@Header("token")String token, @Body Cart cart);

    @DELETE("/cart/{cartID}")
    Call<JsonObject> deleteCartItem(@Header("token")String token, @Path("cartID") int cartID);

    @POST("/order/")
    Call<JsonObject> updateOrder(@Header("token")String token);
}
