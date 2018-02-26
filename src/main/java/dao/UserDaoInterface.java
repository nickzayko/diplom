package dao;

import entity.UserEntity;

public interface UserDaoInterface {
    void saveUser(UserEntity userEntity);
    boolean checkIsLoginExist(String login);
    boolean checkIsEmailExist(String email);
    UserEntity findPassword(String login, String password);
    UserEntity getUser(int userId);
}
