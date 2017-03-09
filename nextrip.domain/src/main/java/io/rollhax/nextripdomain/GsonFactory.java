package io.rollhax.nextripdomain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;

import io.rollhax.nextripdomain.models.Departure;
import io.rollhax.utils.serialization.json.strategy.DeserializationStrategy;
import io.rollhax.utils.serialization.json.strategy.SerializationStrategy;

public class GsonFactory {

    private static Gson DEFAULT;

    public static Gson getDefault() {
        if (DEFAULT == null) {
            DEFAULT = buildGson();
        }

        return DEFAULT;
    }

    private static Gson buildGson() {
        return new GsonBuilder()
                .addDeserializationExclusionStrategy(new DeserializationStrategy())
                .addSerializationExclusionStrategy(new SerializationStrategy())
                .setDateFormat(DateFormat.FULL)

                // register deserializers
                .registerTypeAdapter(Departure.class, new Departure.CustomDeserializer())
                // register serializers
                .registerTypeAdapter(Departure.class, new Departure.CustomSerializer())

                .create();
    }
}
