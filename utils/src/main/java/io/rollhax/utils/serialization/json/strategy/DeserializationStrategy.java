package io.rollhax.utils.serialization.json.strategy;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import io.rollhax.utils.serialization.json.annotations.SkipAutomaticDeserialization;

public class DeserializationStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getAnnotation(SkipAutomaticDeserialization.class) != null;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
}
