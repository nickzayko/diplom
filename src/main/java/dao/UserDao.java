package dao;

import entity.UserEntity;

public interface UserDao {
    void saveUser(UserEntity userEntity);

    boolean checkIsLoginExist(String login);

    boolean checkIsEmailExist(String email);

    UserEntity getUserByLoginAndPassword(String login, String password);

    UserEntity getUser(int userId);
}
