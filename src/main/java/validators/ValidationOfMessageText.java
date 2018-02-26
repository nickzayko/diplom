package validators;

import entity.MessageEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class ValidationOfMessageText implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return MessageEntity.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "textOfMessage", "emptyTextOfMessage");

    }
}
