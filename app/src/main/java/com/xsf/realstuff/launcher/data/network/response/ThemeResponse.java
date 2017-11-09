package com.xsf.realstuff.launcher.data.network.response;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class ThemeResponse extends Response{


    @SerializedName("results")
    private List<Result> results;

    public static ThemeResponse objectFromData(String str) {

        return new Gson().fromJson(str, ThemeResponse.class);
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

}
