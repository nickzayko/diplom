package validators;

        import entity.UserEntity;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Component;
        import org.springframework.validation.Errors;
        import org.springframework.validation.ValidationUtils;
        import org.springframework.validation.Validator;
        import service.UserServiceImpl;


@Component
public class RegistrationValidator implements Validator {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserEntity.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "emptyName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userSurname", "emptySurname");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userEmail", "emptyEmail");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userLogin", "emptyLogin");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPassword", "emptyPassword");

        if (!errors.hasErrors()) {
            UserEntity user = (UserEntity) object;
            if (userServiceImpl.checkIsEmailExist(user.getUserEmail())) {
                errors.rejectValue("userEmail", "emailExist");
            } else {
                if (userServiceImpl.checkIsLoginExist(user.getUserLogin())) {
                    errors.rejectValue("userLogin", "loginExist");
                }
            }
        }
    }
}
