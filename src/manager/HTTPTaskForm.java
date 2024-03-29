package manager;

import com.google.gson.JsonObject;
import data.*;

/**
 * Класс представления из JSON в задачи
 */
public class HTTPTaskForm {

    /**
     * Представление из JSON в задачи
     *
     * @param jsonObject - объект JSON
     * @param type       - тип задачи
     * @return - задача
     */
    public static Task toTask(JsonObject jsonObject, String type) {
        Task task = null;
        switch (TypeOfTasks.valueOf(type)) {
            case TASK: {
                task = new Task(jsonObject.get("title").getAsString(), jsonObject.get("description").getAsString());
                setTaskAtribute(jsonObject, task);
                break;
            }
            case SUBTASK: {
                task = new SubTask(jsonObject.get("title").getAsString(), jsonObject.get("description").getAsString());
                setTaskAtribute(jsonObject, task);
                setSubtaskEpicId((SubTask) task, jsonObject);
                break;
            }
            case EPIC: {
                task = new Epic(jsonObject.get("title").getAsString(), jsonObject.get("description").getAsString());
                setTaskAtribute(jsonObject, task);
                break;
            }
        }
        return task;
    }

    private static void setTaskAtribute(JsonObject jsonObject, Task task) {
        if(task.getTypeOfTask() != TypeOfTasks.EPIC) {
            task.setStatus(StatusTasks.valueOf(jsonObject.get("status").getAsString()));
        }
        if(jsonObject.get("id").getAsInt() != 0) {
            task.setId(jsonObject.get("id").getAsInt());
        }
        if (!jsonObject.get("startTime").getAsString().equals("0")) {
            task.setStartTime(jsonObject.get("startTime").getAsString());
        }
        if(jsonObject.get("duration").getAsLong() != 0) {
            task.setDuration(jsonObject.get("duration").getAsLong());
        }
    }

    private static void setSubtaskEpicId(SubTask subTask, JsonObject jsonObject) {
        subTask.setEpicId(jsonObject.get("epicId").getAsInt());
    }
}
