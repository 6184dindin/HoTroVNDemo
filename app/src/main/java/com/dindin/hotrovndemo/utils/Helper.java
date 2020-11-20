package com.dindin.hotrovndemo.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class Helper {
    public static String readStringFromAsset(String fileName, Context context) {
        String json = null;
        try {
            InputStream in = context.getAssets().open(fileName);
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * phương thức chuyển ArrayObject trong json thành List object
     */
    public static List<InfoAddress> getProvinces(Context context) {
        Gson gson = new Gson();
        String jsObj = readStringFromAsset("0.json", context);
        Type listType = new TypeToken<List<InfoAddress>>() {}.getType();
        return gson.fromJson(jsObj, listType);
    }

    public static List<City> getCities(Context context) {
        Gson gson = new Gson();
        String jsObj = readStringFromAsset("100.json", context);
        Type listType = new TypeToken<List<City>>() {}.getType();
        return gson.fromJson(jsObj, listType);
    }

    public static List<District> getDistricts(Context context) {
        Gson gson = new Gson();
        String jsObj = readStringFromAsset("1000.json", context);
        Type listType = new TypeToken<List<District>>() {}.getType();
        return gson.fromJson(jsObj, listType);
    }

    public static String[] getNameInfoAddress(List<InfoAddress> infoAddresses) {
        String[] stringsName = new String[infoAddresses.size()];
        if (!infoAddresses.isEmpty()) {
            for (int i = 0; i < infoAddresses.size(); i++) {
                stringsName[i] = infoAddresses.get(i).getName();
            }
        }
        return stringsName;
    }

    public static List<Field> getFields(Context context) {
        Gson gson = new Gson();
        String jsObj = readStringFromAsset("Fields.json", context);
        Type listType = new TypeToken<List<Field>>() {}.getType();
        return gson.fromJson(jsObj, listType);
    }

}
