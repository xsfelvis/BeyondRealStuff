package com.xsf.realstuff.launcher.data.network.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.xsf.realstuff.launcher.data.database.entity.Image;

import org.greenrobot.greendao.annotation.Transient;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/4/21 0021.
 */

public class Result implements Parcelable {
    /**
     * _id : 58d4e454421aa93abd1fd15a
     * createdAt : 2017-03-24T17:18:12.745Z
     * desc : RecyclerView侧滑菜单
     * images : ["http://img.gank.io/99a9d510-195d-4d50-a310-13b098c0c776"]
     * publishedAt : 2017-03-29T11:48:49.343Z
     * source : web
     * type : Android
     * url : http://www.jianshu.com/p/af9f940d8d1c
     * used : true
     * who : pss
     */

    private String id;
    private String ganhuo_id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    @Transient
    private List<String> images;

    private List<Image> img;


    public static Result objectFromData(String str) {

        return new Gson().fromJson(str, Result.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }


    public boolean getUsed() {
        return this.used;
    }


    public String getGanhuo_id() {
        return this.ganhuo_id;
    }

    public void setGanhuo_id(String ganhuo_id) {
        this.ganhuo_id = ganhuo_id;
    }

    public Result() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.ganhuo_id);
        dest.writeString(this.createdAt);
        dest.writeString(this.desc);
        dest.writeString(this.publishedAt);
        dest.writeString(this.source);
        dest.writeString(this.type);
        dest.writeString(this.url);
        dest.writeByte(this.used ? (byte) 1 : (byte) 0);
        dest.writeString(this.who);
        dest.writeStringList(this.images);
        dest.writeList(this.img);
    }

    protected Result(Parcel in) {
        this.id = in.readString();
        this.ganhuo_id = in.readString();
        this.createdAt = in.readString();
        this.desc = in.readString();
        this.publishedAt = in.readString();
        this.source = in.readString();
        this.type = in.readString();
        this.url = in.readString();
        this.used = in.readByte() != 0;
        this.who = in.readString();
        this.images = in.createStringArrayList();
        this.img = new ArrayList<Image>();
        in.readList(this.img, Image.class.getClassLoader());
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel source) {
            return new Result(source);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };
}
