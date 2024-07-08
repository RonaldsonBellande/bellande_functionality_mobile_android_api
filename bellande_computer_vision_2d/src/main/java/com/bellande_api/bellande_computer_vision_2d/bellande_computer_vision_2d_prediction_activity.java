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

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.bellande_api.bellande_computer_vision_2d.bellande_computer_vision_2d_prediction_service;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.reflect.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Map;

public class bellande_computer_vision_2d_prediction_activity extends AppCompatActivity {
    private bellande_computer_vision_2d_prediction_service bellande_computer_vision_2d_prediction_service;

    public bellande_computer_vision_2d_prediction_activity(Context context) {
        Map<String, Object> config = loadConfigFromFile(context);
        String apiUrl = (String) config.get("url");
        String endpointPath = (String) ((Map<String, Object>) config.get("endpoint_path")).get("prediction");
        String apiAccessKey = (String) config.get("Bellande_Framework_Access_Key");

        bellande_computer_vision_2d_prediction_service = new bellande_computer_vision_2d_prediction_service(apiUrl, endpointPath, apiAccessKey);
    }
    public bellande_computer_vision_2d_prediction_service getBellandeComputerVision2dPredictionService() {
        return bellande_computer_vision_2d_prediction_service;
    }

    @SuppressLint("LongLogTag")
    private Map<String, Object> loadConfigFromFile(Context context) {
        try {
            InputStream inputStream = context.getAssets().open("configs.json");
            InputStreamReader reader = new InputStreamReader(inputStream);
            Type type = new TypeToken<Map<String, Object>>() {}.getType();
            return new Gson().fromJson(reader, type);
        } catch (IOException e) {
            Log.e("bellande_computer_vision_2d_prediction_activity", "Error reading config file: " + e.getMessage());
        }
        return null;
    }
}
