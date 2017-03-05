package io.rollhax.nextripdomain.models;

import android.os.Parcelable;

public interface ITextValuePair extends Parcelable {
    interface Json {
        String TEXT = "Text";
        String VALUE = "Value";
    }

    String getText();

    String getValue();
}
