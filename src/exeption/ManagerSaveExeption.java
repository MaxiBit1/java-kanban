package exeption;

/**
 * Класс собственного ислючения
 * @author Max Vasilyev
 * @version 1.0
 */
public class ManagerSaveExeption extends RuntimeException {

    public ManagerSaveExeption(String message) {
        super(message);
    }
}
