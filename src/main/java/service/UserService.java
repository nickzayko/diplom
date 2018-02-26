package service;

import dao.UserDao;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService implements UserServiceInterface {

    @Autowired
    UserDao userDao;

    public void createUser (UserEntity userEntity){
        userDao.saveUser(userEntity);
    }

    public boolean checkIsLoginExist (String login){
        return userDao.checkIsLoginExist(login);
    }

    public boolean checkIsEmailExist(String email) {
        return userDao.checkIsEmailExist(email);
    }

    public UserEntity findPassword(String userLogin, String userPassword) {
        return userDao.findPassword(userLogin, userPassword);
    }

    public UserEntity getUser(int userId) {
        return userDao.getUser(userId);
    }
}
