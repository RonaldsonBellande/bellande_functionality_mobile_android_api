/**
 * Copyright (C) 2024 Bellande Application UI UX Research Innovation Center, Ronaldson Bellande
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 **/

package com.bellande_api.bellande_computer_vision_2d;

import com.google.gson.annotations.SerializedName;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface bellande_computer_vision_2d_prediction_api {
    @Multipart
    @POST("prediction")
    Call<BellandeResponse> getBellandeResponse(@Body RequestBody requestBody);

    class RequestBody {
        private final MultipartBody.Part image;

        public RequestBody(MultipartBody.Part image) {
            this.image = image;
        }

        public MultipartBody.Part getImage() {
            return image;
        }
    }

    class BellandeResponse {
        @SerializedName("prediction")
        public String prediction;

        @SerializedName("confidence")
        public double confidence;
    }
}
