package exeption;

/**
 * Класс собственного ислючения
 * @author Max Vasilyev
 * @version 1.0
 */
public class ManagerSaveExeption extends Exception {
    String message;
    public ManagerSaveExeption(String message) {
        super(message);
        this.message = message;
    }

    public String getMessageError() {
        return message + ": " + getMessage();
    }
}
