package fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.furrytales.R;
import com.example.furrytales.adapter.ProductListAdapter;
import com.example.furrytales.api.API;
import com.example.furrytales.api.RetrofitClient;
import com.example.furrytales.entity.Product;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import utility.FurryTalesConstants;
public class ProductListFragment extends Fragment {
    RecyclerView recyclerView;
    List<Product> productList;

    ProductListAdapter productListAdapter;
//    private String authToken;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);
        productList = new ArrayList<>();
        productListAdapter = new ProductListAdapter(getContext(), productList);
        recyclerView.setAdapter(productListAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        super.onViewCreated(view, savedInstanceState);

//        authToken = retrieveAuthToken();
//        authToken = retrieveAuthToken();
//        if (authToken.isEmpty()) {
//            Log.e("ProductListFragment", "Auth token is empty.");
//        } else {
//            Log.d("ProductListFragment", "Auth token is not empty.");
//        }
        getAllProducts();
    }


//    // Method to retrieve authToken, you can modify it as per your implementation
//    private String retrieveAuthToken() {
//        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(FurryTalesConstants.SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
//        String authToken = sharedPreferences.getString(FurryTalesConstants.AUTH_TOKEN, "");
//        Log.d("AuthToken", "Retrieved token: " + authToken); // Log message to check if token is correctly retrieved
//        return authToken;
//    }


    private void getAllProducts() {
        String token = getContext().getSharedPreferences(FurryTalesConstants.SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
                .getString(FurryTalesConstants.AUTH_TOKEN, "");
        RetrofitClient.getInstance().getApi().getAllProducts(token).enqueue(new Callback<JsonObject>() {
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("Response ", String.valueOf(response));
                if (response.isSuccessful() && response.body() != null) {
                    JsonObject responseBody = response.body();
                    Log.d("ResponseBody ", String.valueOf(responseBody));
                    if (responseBody.has("status") && responseBody.get("status").getAsString().equals("success")) {
                        JsonArray array = responseBody.getAsJsonArray("data");
                        if (array != null && !array.isJsonNull()) {
                            productList.clear();
                            for (JsonElement element : array) {
                                JsonObject object = element.getAsJsonObject();
                                if (object != null && !object.isJsonNull()) {
                                    Product product = new Product();
                                    JsonElement idElement = object.get("productID");
                                    Log.d("idElement ", String.valueOf(idElement));
                                    if (idElement != null && !idElement.isJsonNull()) {
                                        product.setProductID(idElement.getAsInt());
                                       // Log.d("product.setProductID ", product.getProductID());
                                    }
                                    JsonElement titleElement = object.get("title");
                                    if (titleElement != null && !titleElement.isJsonNull()) {
                                        product.setTitle(titleElement.getAsString());
                                    }
                                    JsonElement imageElement = object.get("image");
                                    if (imageElement != null && !imageElement.isJsonNull()) {
                                        product.setImage(imageElement.getAsString());
                                    }
                                    JsonElement mrpElement = object.get("mrp");
                                    if (mrpElement != null && !mrpElement.isJsonNull()) {
                                        product.setMrp(Float.parseFloat(mrpElement.getAsString()));
                                    }
                                    JsonElement descriptionElement = object.get("description");
                                    if(descriptionElement != null && !descriptionElement.isJsonNull()){
                                        product.setDescription(descriptionElement.getAsString());
                                    }
                                    JsonElement discountElement = object.get("discount");
                                    if(discountElement != null && !discountElement.isJsonNull()){
                                        product.setDiscount(Float.parseFloat(discountElement.getAsString()));
                                    }
                                    JsonElement  categoryElement = object.get("category");
                                    if (categoryElement != null && ! categoryElement.isJsonNull()) {
                                        product.setCategory(categoryElement.getAsString());
                                    }
                                    JsonElement companyElement = object.get("company");
                                    if (companyElement != null && !companyElement.isJsonNull()) {
                                        product.setCompany(companyElement.getAsString());
                                    }
                                    Log.d("Product ", product.toString());
                                    productList.add(product);
                                }
                            }
                            productListAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getContext(), "something went wrong at product list", Toast.LENGTH_SHORT).show();

            }
        });
    }
}




















//        if(authToken == null || authToken.isEmpty()) {
//            Log.e("ProductListFragment", "Auth token is missing or empty.");
//            return;
//        }
//
//        Log.d("ProductListFragment", "getAllProducts: Fetching all products...");
//
//        // Create a new instance of OkHttpClient.Builder to add headers
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//
//        // Add interceptor to add token to each request as a header
//        httpClient.addInterceptor(new Interceptor() {
//            @Override
//            public okhttp3.Response intercept(Chain chain) throws IOException {
//                Request original = chain.request();
//                Request.Builder requestBuilder = original.newBuilder()
//                        .header("Authorization", "Bearer " + authToken); // Attach the auth token to the request header
//                Request request = requestBuilder.build();
//                return chain.proceed(request);
//            }
//        });
//
//        // Build the OkHttpClient instance
//        OkHttpClient client = httpClient.build();
//
//        // Create Retrofit instance with the custom OkHttpClient
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(API.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client) // Set the custom OkHttpClient
//                .build();
//
//        // Create an instance of the API interface using Retrofit
//        API api = retrofit.create(API.class);
//
//        // Make the API call to fetch all products
//        api.getAllProducts(authToken).enqueue(new Callback<JsonObject>() {
//            @Override
//            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                if (response.isSuccessful()) {
//                    Log.d("ProductListFragment", "API call successful");
//                    JsonObject responseBody = response.body();
//                    if (responseBody != null) {
//                        String status = responseBody.get("status").getAsString();
//                        if ("success".equals(status)) {
//                            JsonArray dataArray = responseBody.get("data").getAsJsonArray();
//                            productList.clear();
//                            for (JsonElement element : dataArray) {
//                                JsonObject productObject = element.getAsJsonObject();
//                                Product product = new Product();
//                                product.setProductID(productObject.get("id").getAsInt());
//                                product.setTitle(productObject.get("title").getAsString());
//                                product.setImage(productObject.get("image").getAsString());
//                                product.setMrp(productObject.get("Mrp").getAsFloat()); // Check the exact key for "Mrp"
//                                productList.add(product);
//                            }
//                            productListAdapter.notifyDataSetChanged();
//                            Log.d("ProductListFragment", "Auth Token: " + authToken);
//                        } else {
//                            Log.e("ProductListFragment", "API call returned unsuccessful status: " + status);
//                        }
//                    } else {
//                        Log.e("ProductListFragment", "Response body is null");
//                    }
//                } else {
//                    Log.e("ProductListFragment", "API call failed with code: " + response.code());
//                }
//
//                // Add the log statement here to check the value of the authentication token
//                Log.d("ProductListFragment", "Auth token: " + authToken);
//            }
//            @Override
//            public void onFailure(Call<JsonObject> call, Throwable t) {
//                Log.e("ProductListFragment", "API call failed: " + t.getMessage());
//                Toast.makeText(getContext(), "Something went wrong at Product List", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
