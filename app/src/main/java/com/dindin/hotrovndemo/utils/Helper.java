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
    public static List<Province> getProvinces(Context context) {
        Gson gson = new Gson();
        String jsObj = readStringFromAsset("data.json", context);
        Type listType = new TypeToken<List<Province>>() {
        }.getType();
        return gson.fromJson(jsObj, listType);
    }
}
