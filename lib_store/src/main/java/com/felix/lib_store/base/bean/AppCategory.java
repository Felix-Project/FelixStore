package com.felix.lib_store.base.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class AppCategory implements Parcelable {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
    }

    public AppCategory() {
    }

    protected AppCategory(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<AppCategory> CREATOR = new Parcelable.Creator<AppCategory>() {
        @Override
        public AppCategory createFromParcel(Parcel source) {
            return new AppCategory(source);
        }

        @Override
        public AppCategory[] newArray(int size) {
            return new AppCategory[size];
        }
    };
}
