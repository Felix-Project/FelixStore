package com.felix.lib_store.base.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class AppItem implements Parcelable {
    private String appName;
    private String appCategoryName;
    private String appCategoryId;
    private String appIcon;
    private String appDetailUrl;
    private String appPkgName;
    private int allPage;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppCategoryName() {
        return appCategoryName;
    }

    public void setAppCategoryName(String appCategoryName) {
        this.appCategoryName = appCategoryName;
    }

    public String getAppCategoryId() {
        return appCategoryId;
    }

    public void setAppCategoryId(String appCategoryId) {
        this.appCategoryId = appCategoryId;
    }

    public String getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppDetailUrl() {
        return appDetailUrl;
    }

    public void setAppDetailUrl(String appDetailUrl) {
        this.appDetailUrl = appDetailUrl;
    }

    public String getAppPkgName() {
        return appPkgName;
    }

    public void setAppPkgName(String appPkgName) {
        this.appPkgName = appPkgName;
    }

    public int getAllPage() {
        return allPage;
    }

    public void setAllPage(int allPage) {
        this.allPage = allPage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.appName);
        dest.writeString(this.appCategoryName);
        dest.writeString(this.appCategoryId);
        dest.writeString(this.appIcon);
        dest.writeString(this.appDetailUrl);
        dest.writeString(this.appPkgName);
        dest.writeInt(this.allPage);
    }

    public AppItem() {
    }

    protected AppItem(Parcel in) {
        this.appName = in.readString();
        this.appCategoryName = in.readString();
        this.appCategoryId = in.readString();
        this.appIcon = in.readString();
        this.appDetailUrl = in.readString();
        this.appPkgName = in.readString();
        this.allPage = in.readInt();
    }

}
