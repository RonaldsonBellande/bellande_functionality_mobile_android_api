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
 */

package com.bellande_api.bellande_computer_vision_2d;
import com.bellande_api.bellande_computer_vision_2d.bellande_computer_vision_2d_prediction_api;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class bellande_computer_vision_2d_prediction_service {
    private final bellande_computer_vision_2d_prediction_api bellande_computer_vision_2d_prediction_api;

    public bellande_computer_vision_2d_prediction_service(String apiUrl, String endpointPath, String apiAccessKey) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiUrl + endpointPath)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        bellande_computer_vision_2d_prediction_api = retrofit.create(bellande_computer_vision_2d_prediction_api.class);
    }

    public Call<bellande_computer_vision_2d_prediction_api.BellandeResponse> getBellandeResponse(String inputText) {
        bellande_computer_vision_2d_prediction_api.RequestBody requestBody = new bellande_computer_vision_2d_prediction_api.RequestBody(inputText);
        return bellande_computer_vision_2d_prediction_api.getBellandeResponse(requestBody);
    }
}