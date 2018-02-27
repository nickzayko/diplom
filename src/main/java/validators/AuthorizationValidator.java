package validators;

import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import service.UserService;

@Component
public class AuthorizationValidator implements Validator {

    @Autowired
    private UserService userService;

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
            if (!userService.checkIsLoginExist(user.getUserLogin())) {
                errors.rejectValue("userLogin", "doNotExistUser");
            } else {
                if (!userService.checkIsPasswordCorrect(user.getUserLogin(), user.getUserPassword())) {
                    errors.rejectValue("userPassword", "wrongPassword");
                }
            }
        }
    }
}
