package service;

import dao.UserDao;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

    @Autowired
    UserDao userDao;

    public void createUser (UserEntity userEntity){
        userDao.saveUser(userEntity);
    }

    public boolean isLoginExist (String login){
        return userDao.isLoginExist(login);
    }

    public boolean isEmailExist(String email) {
        return userDao.isEmailExist(email);
    }

    public UserEntity isPasswordCorrect(String userLogin, String userPassword) {
        return userDao.isPasswordCorrect(userLogin, userPassword);
    }

    public UserEntity getUserEntityFromDataBase(int userId) {
        return userDao.getUserEntityFromDataBase(userId);
    }
}
