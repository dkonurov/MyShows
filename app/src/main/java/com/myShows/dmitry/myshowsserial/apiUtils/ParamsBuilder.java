package com.myShows.dmitry.myshowsserial.apiUtils;

import com.myShows.dmitry.myshowsserial.enums.EnumMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ParamsBuilder {
    private static final String DEFAULT_API_STRING = "http://api.myshows.ru/";
    public static final String LOGIN_METHOD = "profile/login";
    public static final String PROFILE_METHOD = "profile/";
    public static final String MY_SHOWS_METHOD = "profile/shows/";
    public static final String EPISODE_UNWATCHED = "profile/episodes/unwatched/";
    public static final String EPISODE_NEXT ="profile/episodes/next/";
    public static final String EPISODE_CHECK = "profile/episodes/check/";
    public static final String EPISODE_UNCHECKED = "profile/uncheck/";
    public static final String SHOWS_METHOD = "shows/";

    private EnumMethod mEnumMethod;
    private Map<String, String> mMapParams = new HashMap<String, String>();
    private List<String> mMethodList = new ArrayList<String>();
    private Map<String, String> mMapMethod = new HashMap<String, String>();

    public ParamsBuilder(EnumMethod enumMethod) {
        mEnumMethod = enumMethod;
    }


    public void addMethod(String key, String value) {
        mMethodList.add(value);
        mMapMethod.put(key, value);
    }

    public void addParams(String name, String value) {
        mMapParams.put(name, value);
    }

    public String getUrl() {
        StringBuilder builder = new StringBuilder(DEFAULT_API_STRING);
        for (String method : mMethodList) {
            builder.append(method);
        }
        if (mMapParams.size() != 0) {
            builder.append("?");
        }
        for (Iterator<Map.Entry<String, String>> iterator = mMapParams.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, String> entry = iterator.next();
            builder.append(entry.getKey());
            builder.append("=");
            builder.append(entry.getValue());
            if (iterator.hasNext()) {
                builder.append("&");
            }
        }
        return builder.toString();
    }

    public EnumMethod getEnumMethod() {
        return mEnumMethod;
    }

    public String getParamsByString(String key) {
        return mMapParams.get(key);
    }

    public String getMethodByString(String key) {
        return mMapMethod.get(key);
    }
}
