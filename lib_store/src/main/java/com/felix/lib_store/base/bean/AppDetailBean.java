package com.felix.lib_store.base.bean;

import android.os.Parcel;

import java.util.List;

public class AppDetailBean extends AppItem {
    private String appCompanyTip;
    private List<String> appScreenshotList;
    private String appDescription;
    private String appFeature;
    private int appRemarkNum;
    private int appStartNum;

    private int appSize;
    private String appLastUpdateDate;
    private String appVersionName;
    private List<String> appPermissionList;
    private String appDownloadUrl;

    public String getAppCompanyTip() {
        return appCompanyTip;
    }

    public void setAppCompanyTip(String appCompanyTip) {
        this.appCompanyTip = appCompanyTip;
    }

    public List<String> getAppScreenshotList() {
        return appScreenshotList;
    }

    public void setAppScreenshotList(List<String> appScreenshotList) {
        this.appScreenshotList = appScreenshotList;
    }

    public String getAppDescription() {
        return appDescription;
    }

    public void setAppDescription(String appDescription) {
        this.appDescription = appDescription;
    }

    public String getAppFeature() {
        return appFeature;
    }

    public void setAppFeature(String appFeature) {
        this.appFeature = appFeature;
    }

    public int getAppRemarkNum() {
        return appRemarkNum;
    }

    public void setAppRemarkNum(int appRemarkNum) {
        this.appRemarkNum = appRemarkNum;
    }

    public int getAppStartNum() {
        return appStartNum;
    }

    public void setAppStartNum(int appStartNum) {
        this.appStartNum = appStartNum;
    }

    public int getAppSize() {
        return appSize;
    }

    public void setAppSize(int appSize) {
        this.appSize = appSize;
    }

    public String getAppLastUpdateDate() {
        return appLastUpdateDate;
    }

    public void setAppLastUpdateDate(String appLastUpdateDate) {
        this.appLastUpdateDate = appLastUpdateDate;
    }

    public String getAppVersionName() {
        return appVersionName;
    }

    public void setAppVersionName(String appVersionName) {
        this.appVersionName = appVersionName;
    }

    public List<String> getAppPermissionList() {
        return appPermissionList;
    }

    public void setAppPermissionList(List<String> appPermissionList) {
        this.appPermissionList = appPermissionList;
    }

    public String getAppDownloadUrl() {
        return appDownloadUrl;
    }

    public void setAppDownloadUrl(String appDownloadUrl) {
        this.appDownloadUrl = appDownloadUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.appCompanyTip);
        dest.writeStringList(this.appScreenshotList);
        dest.writeString(this.appDescription);
        dest.writeString(this.appFeature);
        dest.writeInt(this.appRemarkNum);
        dest.writeInt(this.appStartNum);
        dest.writeInt(this.appSize);
        dest.writeString(this.appLastUpdateDate);
        dest.writeString(this.appVersionName);
        dest.writeStringList(this.appPermissionList);
        dest.writeString(this.appDownloadUrl);
    }

    public AppDetailBean() {
    }

    protected AppDetailBean(Parcel in) {
        super(in);
        this.appCompanyTip = in.readString();
        this.appScreenshotList = in.createStringArrayList();
        this.appDescription = in.readString();
        this.appFeature = in.readString();
        this.appRemarkNum = in.readInt();
        this.appStartNum = in.readInt();
        this.appSize = in.readInt();
        this.appLastUpdateDate = in.readString();
        this.appVersionName = in.readString();
        this.appPermissionList = in.createStringArrayList();
        this.appDownloadUrl = in.readString();
    }

    public static final Creator<AppDetailBean> CREATOR = new Creator<AppDetailBean>() {
        @Override
        public AppDetailBean createFromParcel(Parcel source) {
            return new AppDetailBean(source);
        }

        @Override
        public AppDetailBean[] newArray(int size) {
            return new AppDetailBean[size];
        }
    };
}
