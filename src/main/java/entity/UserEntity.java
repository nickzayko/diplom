package entity;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UserEntity {

    @OneToMany(mappedBy = "userEntity", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<TopicEntity> topicEntityList;

    @OneToMany(mappedBy = "userEntity", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<MessageEntity> messageEntityListForUsers;

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;

    @Column(name = "user_name", length = 65)
    private String userName;

    @Column(name = "user_surname", length = 65)
    private String userSurname;

    @Column(name = "user_email", length = 65)
    private String userEmail;

    @Column(name = "user_login", length = 65, unique = true)
    private String userLogin;

    @Column(name = "user_password", length = 65)
    private String userPassword;

    public UserEntity(String userName, String userSurname, String userEmail, String userLogin, String userPassword) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.userEmail = userEmail;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }

    public UserEntity(List<TopicEntity> topicEntityList, String userName, String userSurname, String userEmail, String userLogin, String userPassword) {
        this.topicEntityList = topicEntityList;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userEmail = userEmail;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }

    public UserEntity(List<TopicEntity> topicEntityList, List<MessageEntity> messageEntityListForUsers, String userName, String userSurname, String userEmail, String userLogin, String userPassword) {
        this.topicEntityList = topicEntityList;
        this.messageEntityListForUsers = messageEntityListForUsers;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userEmail = userEmail;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }

    public UserEntity(String userLogin, String userPassword) {
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }

    public UserEntity() {
    }

    public List<TopicEntity> getTopicEntityList() {
        return topicEntityList;
    }

    public void setTopicEntityList(List<TopicEntity> topicEntityList) {
        this.topicEntityList = topicEntityList;
    }

    public List<MessageEntity> getMessageEntityListForUsers() {
        return messageEntityListForUsers;
    }

    public void setMessageEntityListForUsers(List<MessageEntity> messageEntityListForUsers) {
        this.messageEntityListForUsers = messageEntityListForUsers;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return idUser == that.idUser &&
                Objects.equals(topicEntityList, that.topicEntityList) &&
                Objects.equals(messageEntityListForUsers, that.messageEntityListForUsers) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(userSurname, that.userSurname) &&
                Objects.equals(userEmail, that.userEmail) &&
                Objects.equals(userLogin, that.userLogin) &&
                Objects.equals(userPassword, that.userPassword);
    }

    @Override
    public int hashCode() {

        return Objects.hash(topicEntityList, messageEntityListForUsers, idUser, userName, userSurname, userEmail, userLogin, userPassword);
    }
}
