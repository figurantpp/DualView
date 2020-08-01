package com.figurantp.dualview;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Entry {

    private String key;
    private String value;

    public static String BUNDLE_KEY   = "entry_bundle_key";
    public static String BUNDLE_VALUE = "entry_bundle_value";

    public Entry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public Entry() {}

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public Entry setKey(String key) {
        this.key = key;
        return this;
    }

    public Entry setValue(String value) {
        this.value = value;
        return this;
    }

    public static List<Entry> getExampleList(Context context)
    {
        List<Entry> result = new ArrayList<>();


        result.add(new Entry("Key0", "Value0"));

        context.getString(R.string.txtValuePlaceHolder);

        result.add(new Entry("Key1", "Value"));
        result.add(new Entry().setKey("Key2").setValue("Value2"));

        return  result;
    }

    public static Entry fromBundle(Bundle bundle) {
        return new Entry(bundle.getString(BUNDLE_KEY), bundle.getString(BUNDLE_VALUE));
    }

    public Bundle toBundle()
    {
        Bundle result = new Bundle();

        result.putString(BUNDLE_KEY, key);
        result.putString(BUNDLE_VALUE, value);

        return result;
    }

}
