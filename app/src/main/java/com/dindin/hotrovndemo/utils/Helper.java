package com.dindin.hotrovndemo.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
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
        Type listType = new TypeToken<List<InfoAddress>>() {
        }.getType();
        return gson.fromJson(jsObj, listType);
    }

    public static List<City> getCities(Context context) {
        Gson gson = new Gson();
        String jsObj = readStringFromAsset("100.json", context);
        Type listType = new TypeToken<List<City>>() {
        }.getType();
        return gson.fromJson(jsObj, listType);
    }

    public static List<InfoAddress> getListCity(Context context) {
        List<InfoAddress> infoAddresses = new ArrayList<>();
        List<City> cities = getCities(context);
        for (City city : cities) {
            infoAddresses.addAll(city.getInfoAddresses());
        }
        return infoAddresses;
    }

    public static List<InfoAddress> getListDistrict(Context context) {
        List<InfoAddress> infoAddresses = new ArrayList<>();
        List<District> districts = getDistricts(context);
        for (District district : districts) {
            infoAddresses.addAll(district.getInfoAddresses());
        }
        return infoAddresses;
    }

    public static List<District> getDistricts(Context context) {
        Gson gson = new Gson();
        String jsObj = readStringFromAsset("1000.json", context);
        Type listType = new TypeToken<List<District>>() {
        }.getType();
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
        Type listType = new TypeToken<List<Field>>() {
        }.getType();
        return gson.fromJson(jsObj, listType);
    }

    public static boolean isValidDate(int day, int month, int year) {
        return day <= getDaysOfMonth(month, year);
    }

    public static int getDaysOfMonth(int month, int year) {
        int daysOfMonth;
        boolean leapYear = isLeapYear(year);

        if (month == 4 || month == 6 || month == 9 || month == 11)
            daysOfMonth = 30;
        else if (month == 2) {
            daysOfMonth = (leapYear) ? 29 : 28;
        } else {
            daysOfMonth = 31;
        }
        return daysOfMonth;
    }

    public static boolean isLeapYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
    }
}
