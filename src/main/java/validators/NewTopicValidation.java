package validators;

import entity.TopicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import service.ChatServiceImpl;

@Component
public class NewTopicValidation implements Validator {

    @Autowired
    private ChatServiceImpl chatServiceImpl;

    @Override
    public boolean supports(Class<?> clazz) {
        return TopicEntity.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "topicName", "emptyNewTopicName");
        if (!errors.hasErrors()) {
            TopicEntity topic = (TopicEntity) target;
            if (chatServiceImpl.isTopicExist(topic.getTopicName())) {
                errors.rejectValue("topicName", "thisChatExist");
            }
        }
    }
}
