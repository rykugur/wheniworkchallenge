package io.rollhax.nextripdomain.models;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

public class TextValuePair implements ITextValuePair {

    @SerializedName(Json.TEXT)
    private String mText;
    @SerializedName(Json.VALUE)
    private String mValue;

    //region ITextValuePair
    @Override
    public String getText() {
        return mText;
    }

    @Override
    public String getValue() {
        return mValue;
    }
    //endregion

    //region Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    private TextValuePair(Parcel in) {
        mText = in.readString();
        mValue = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mText);
        parcel.writeString(mValue);
    }

    public static final Creator<TextValuePair> CREATOR = new Creator<TextValuePair>() {
        @Override
        public TextValuePair createFromParcel(Parcel parcel) {
            return new TextValuePair(parcel);
        }

        @Override
        public TextValuePair[] newArray(int size) {
            return new TextValuePair[size];
        }
    };
    //endregion
}
