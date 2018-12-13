package no.difi.vefa.validator;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import no.difi.vefa.validator.api.Trigger;
import no.difi.vefa.validator.api.TriggerInfo;
import no.difi.vefa.validator.api.ValidatorException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Singleton
class TriggerFactory {

    private Map<String, Trigger> triggers = new HashMap<>();

    @Inject
    public TriggerFactory(List<Trigger> triggers) {
        for (Trigger trigger : triggers) {
            this.triggers.put(trigger.getClass().getAnnotation(TriggerInfo.class).value(), trigger);
        }
    }

    public Trigger get(String identifier) throws ValidatorException {
        if (triggers.containsKey(identifier))
            return triggers.get(identifier);

        throw new ValidatorException(String.format("Trigger '%s' not found.", identifier));
    }
}
