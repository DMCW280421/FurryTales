package fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.furrytales.R;
import com.example.furrytales.api.RetrofitClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utility.FurryTalesConstants;

public class ProfileFragment extends Fragment {

    TextView textName, textAddress, textEmail, textMobile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textName = view.findViewById(R.id.textName);
        textAddress = view.findViewById(R.id.textAddress);
        textEmail = view.findViewById(R.id.textEmail);
        textMobile = view.findViewById(R.id.textMobile);

        getCustomer();
    }

    private void getCustomer() {
        String token = getContext().getSharedPreferences(FurryTalesConstants.SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
                .getString(FurryTalesConstants.AUTH_TOKEN, "");
//        int customerid = getContext().getSharedPreferences(FurryTalesConstants.SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
//                .getInt(FurryTalesConstants.CUSTOMER_ID,0);
        RetrofitClient.getInstance().getApi().getCustomer(token).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject responseBody = response.body();
                    if (responseBody != null && responseBody.has("status")) {
                        Log.d("ProfileFragment", "Response Body : " + responseBody.toString());
                        String status = responseBody.get("status").getAsString();
                        if (status.equals("success")) {
                            JsonArray jsonArray = responseBody.getAsJsonArray("data");
                            if (jsonArray.size() > 0) {
                                JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
                                // Check if necessary fields are present before accessing them
                                if (jsonObject.has("name")) {
                                    textName.setText(jsonObject.get("name").getAsString());
                                }
                                if (jsonObject.has("address")) {
                                    textAddress.setText(jsonObject.get("address").getAsString());
                                }
                                if (jsonObject.has("email")) {
                                    textEmail.setText(jsonObject.get("email").getAsString());
                                }
                                if (jsonObject.has("mobile")) {
                                    textMobile.setText(jsonObject.get("mobile").getAsString());
                                }
                                return; // Exit method after setting the text fields
                            } else {
                                Log.e("ProfileFragment", "Empty data array in the response");
                            }
                        } else if (status.equals("error")) {
                            String errorMessage = responseBody.get("error").getAsString();
                            if ("invalid token".equalsIgnoreCase(errorMessage)) {
                                // Handle invalid token error, e.g., prompt user to log in again
                                // You might also consider refreshing the token if applicable
                                // Example: handleInvalidToken();
                            } else {
                                Log.e("ProfileFragment", "Error message: " + errorMessage);
                            }
                        } else {
                            Log.e("ProfileFragment", "Unexpected status: " + status);
                        }
                    } else {
                        Log.e("ProfileFragment", "Invalid response body");
                    }
                } else {
                    Log.e("ProfileFragment", "Failed to retrieve user profile. Response code: " + response.code());
                    // Handle other response scenarios if needed
                }
                // If the response is not successful or if there's missing data, display an error message
                Toast.makeText(getContext(), "Failed to retrieve user profile", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                // Handle network or server errors
                Log.e("ProfileFragment", "Failed to retrieve user profile. Response: " + t.getMessage(), t);

                Toast.makeText(getContext(), "Something went wrong while getting the user", Toast.LENGTH_SHORT).show();
            }
        });
    }
}