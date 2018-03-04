package service;

import entity.UserEntity;

public interface UserServiceInterface {
    void createUser(UserEntity userEntity);

    boolean checkIsLoginExist(String login);

    boolean checkIsEmailExist(String email);

    UserEntity findPassword(String userLogin, String userPassword);

    UserEntity getUser(int userId);
}
