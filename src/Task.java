/**
 * Класс простой задачи
 * @author Max Vasilyev
 * @version 1.0
 */

public class Task {
    private String title;
    private String description;
    private String status;

    /** Метод получения название задачи
     * @return возвращает название задачи
     */
    public String getTitle() {
        return title;
    }

    /** Метод присвоения названия задачи
     * @param title - название задачи
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /** Метод присвоения описания задачи
     * @param description - описание задачи
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /** Метод получения описания задачи
     * @return возвращение описания задачи
     */
    public String getDescription() {
        return description;
    }

    /** Метод установки статуса задачи
     * @param numOfStatus - номер статуса
     */
    private void setStatus(int numOfStatus) {
        switch (numOfStatus){
            case 1:
                status = "NEW";
                break;
            case 2:
                status = "IN_PROGRESS";
                break;
            case 3:
                status = "DONE";
                break;
        }
    }

    /** Метод получения статуса задачи
     * @return возвращает название статуса задачи
     */
    public String getStatus(){
        return status;
    }

    /** Метод установки номера статуса задачи
     * @param numOfStatus - номер статуса задачи
     */
    public void setNumOfStatus(int numOfStatus) {
        setStatus(numOfStatus);
    }

    /** Переопределеный метод toString() */
    @Override
    public String toString() {
        return "Task {" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

