package org.stage5.osgi.data.impl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.stage5.osgi.data.DataProvider;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LentaDataProvider implements DataProvider {
    private static final String url = "https://api.lenta.ru/lists/latest";

    @Override
    public List<String> getTitles() {
        ArrayList<String> titles = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new URL(url).openConnection().getInputStream(),
                            StandardCharsets.UTF_8
                    )
            );

            JSONTokener tokener = new JSONTokener(reader);
            JSONArray headlines = new JSONObject(tokener).getJSONArray("headlines");

            for (Object entry : headlines) {
                JSONObject jsonObject = (JSONObject) entry;

                if (jsonObject.get("type").equals("news")) {
                    titles.add((String) jsonObject.getJSONObject("info").get("title"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return titles;
    }

    @Override
    public String getName() {
        return "lenta";
    }
}
