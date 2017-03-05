package mappers;

import io.reactivex.functions.Function;
import io.rollhax.nextripdomain.models.TextValuePair;
import io.rollhax.nextripdomain.types.DirectionType;

public class DirectionTypeMapper implements Function<TextValuePair, DirectionType> {
    @Override
    public DirectionType apply(TextValuePair textValuePair) throws Exception {
        return DirectionType.from(textValuePair);
    }
}
