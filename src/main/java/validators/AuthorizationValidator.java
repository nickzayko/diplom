package validators;

import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import service.UserServiceImpl;

@Component
public class AuthorizationValidator implements Validator {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserEntity.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userLogin", "emptyLogin");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPassword", "emptyPassword");
        if (!errors.hasErrors()) {
            UserEntity user = (UserEntity) target;
            if (!userServiceImpl.checkIsLoginExist(user.getUserLogin())) {
                errors.rejectValue("userLogin", "doNotExistUser");
            } else {
                if (userServiceImpl.getUserByLoginAndPassword(user.getUserLogin(), user.getUserPassword()) == null) {
                    errors.rejectValue("userPassword", "wrongPassword");
                }
            }
        }
    }
}
