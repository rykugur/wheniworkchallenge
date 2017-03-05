package mappers;

import io.reactivex.functions.Function;
import io.rollhax.nextripdomain.models.IStop;
import io.rollhax.nextripdomain.models.Stop;
import io.rollhax.nextripdomain.models.TextValuePair;

public class StopMapper implements Function<TextValuePair, IStop> {
    @Override
    public IStop apply(TextValuePair textValuePair) throws Exception {
        return new Stop(textValuePair);
    }
}
