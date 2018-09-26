package HM_2;

public interface Controller {
    void sendMessage(String msg);

    void closeConnection();

    void showUI(ClientUI clientUI);
}
