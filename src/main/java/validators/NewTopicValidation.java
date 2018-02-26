package validators;

import entity.TopicEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class NewTopicValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return TopicEntity.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "topicName", "emptyNewTopicName");

    }
}
