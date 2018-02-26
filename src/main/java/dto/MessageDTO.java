package dto;

public class MessageDTO {
    private String textOfMessage;
    private String userName;


    public String getTextOfMessage() {
        return textOfMessage;
    }

    public MessageDTO() {
    }

    public MessageDTO(String textOfMessage, String userName) {
        this.textOfMessage = textOfMessage;
        this.userName = userName;

    }

    public void setTextOfMessage(String textOfMessage) {
        this.textOfMessage = textOfMessage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
