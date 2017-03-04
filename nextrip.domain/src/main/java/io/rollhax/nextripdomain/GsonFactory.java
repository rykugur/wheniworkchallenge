package io.rollhax.nextripdomain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.rollhax.nextripdomain.models.Stop;
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
                
                // register deserializers
                .registerTypeAdapter(Stop.class, new Stop.CustomDeserializer())
                // register serializers
                .registerTypeAdapter(Stop.class, new Stop.CustomSerializer())

                .create();
    }
}
