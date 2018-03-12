package service;

import dao.UserDaoImpl;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDaoImpl userDaoImpl;

    public void createUser(UserEntity userEntity) {
        userDaoImpl.saveUser(userEntity);
    }

    public boolean checkIsLoginExist(String login) {
        return userDaoImpl.checkIsLoginExist(login);
    }

    public boolean checkIsEmailExist(String email) {
        return userDaoImpl.checkIsEmailExist(email);
    }

    public UserEntity getUserByLoginAndPassword(String userLogin, String userPassword) {
        return userDaoImpl.getUserByLoginAndPassword(userLogin, userPassword);
    }

    public UserEntity getUser(int userId) {
        return userDaoImpl.getUser(userId);
    }

}
