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
package com.bellande_api.bellande_ai_system;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.bellande_api.bellande_ai_system.databinding.ActivityAiBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Bellande_Ai_System_Activity extends AppCompatActivity {
    private ActivityAiBinding binding;
    private Bellande_Ai_System_Service bellande_ai_system_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Load API configuration from configs.json
        Map<String, Object> config = loadConfigFromFile();
        String apiUrl = (String) config.get("url");
        String endpointPath = (String) ((Map<String, Object>) config.get("endpoint_path")).get("2d");
        String apiAccessKey = (String) config.get("Bellande_Framework_Access_Key");

        bellande_ai_system_service = new Bellande_Ai_System_Service(apiUrl, endpointPath, apiAccessKey);

        binding.btnSendInput.setOnClickListener(v -> handleUserInput());
    }

    private Map<String, Object> loadConfigFromFile() {
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.configs);
            InputStreamReader reader = new InputStreamReader(inputStream);
            Type type = new TypeToken<Map<String, Object>>() {}.getType();
            return new Gson().fromJson(reader, type);
        } catch (IOException e) {
            Log.e("Bellande_Ai_System_Activity", "Error reading config file: " + e.getMessage());
        }
        return null;
    }

    private void handleUserInput() {
        String inputText = binding.etInput.getText().toString();
        bellande_ai_system_service.getBellandeResponse(inputText).enqueue(new Callback<Bellande_Ai_System_Api.BellandeResponse>() {
            @Override
            public void onResponse(Call<Bellande_Ai_System_Api.BellandeResponse> call, Response<Bellande_Ai_System_Api.BellandeResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    binding.tvResponse.setText(response.body().response);
                }
            }

            @Override
            public void onFailure(Call<Bellande_Ai_System_Api.BellandeResponse> call, Throwable t) {
                Log.e("Bellande_Ai_System_Activity", "Error: " + t.getMessage());
            }
        });
    }
}
