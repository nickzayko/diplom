package service;

import entity.UserEntity;

public interface UserService {
    void createUser(UserEntity userEntity);

    boolean checkIsLoginExist(String login);

    boolean checkIsEmailExist(String email);

    UserEntity getUserByLoginAndPassword(String userLogin, String userPassword);

    UserEntity getUser(int userId);
}
