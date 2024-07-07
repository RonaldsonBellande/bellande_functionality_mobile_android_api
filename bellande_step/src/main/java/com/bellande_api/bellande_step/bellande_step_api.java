package com.bellande_api.bellande_step;

import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Bellande_Step_Api {
    @POST("endpoint_path")
    Call<BellandeResponse> getBellandeResponse(@Body RequestBody requestBody);

    class RequestBody {
        @SuppressWarnings("unused")
        @SerializedName("input_text")
        private final String inputText;

        public RequestBody(String inputText) {
            this.inputText = inputText;
        }
    }

    class BellandeResponse {
        @SerializedName("response")
        public String response;
    }
}
