package data;

/**
 * Класс простой задачи
 *
 * @author Max Vasilyev
 * @version 1.0
 */

public class Task {
    private String title;
    private String description;
    private String status;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    /**
     * Метод установки статуса задачи
     * @param numOfStatus - номер статуса
     */
    private void setStatus(int numOfStatus) {
        switch (numOfStatus) {
            case 1:
                status = StatusTasks.NEW.toString();
                break;
            case 2:
                status = StatusTasks.IN_PROGRESS.toString();
                break;
            case 3:
                status = StatusTasks.DONE.toString();
                break;
        }
    }


    public String getStatus() {
        return status;
    }


    public void setNumOfStatus(int numOfStatus) {
        setStatus(numOfStatus);
    }

    @Override
    public String toString() {
        return "Task {" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", id= " + id + '\'' +
                '}';
    }

}

