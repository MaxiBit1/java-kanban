package manager;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import data.*;
import http.KVTaskClient;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static manager.HTTPTaskForm.toTask;


/**
 * Класс сервера Менеджера
 */
public class HttpTaskManager extends FileBackedTaskManager {

    KVTaskClient kvTaskClient;
    String strJson;
    int key = 1;
    private Gson gson = new Gson();

    public HttpTaskManager() throws IOException {
        kvTaskClient = new KVTaskClient();
        strJson = kvTaskClient.load(String.valueOf(key));
        if (!strJson.isBlank()) {
            fromJson();
        }
    }

    @Override
    public void createTasks(Task task) {
        super.createTasks(task);
        kvTaskClient.put(String.valueOf(key), saveHTTPJson());
    }

    @Override
    public void createEpic(Epic epic) {
        super.createEpic(epic);
        kvTaskClient.put(String.valueOf(key), saveHTTPJson());
    }

    @Override
    public void createSubtacks(SubTask subTask) {
        super.createSubtacks(subTask);
        kvTaskClient.put(String.valueOf(key), saveHTTPJson());
    }

    @Override
    public void deleteAllTasks() {
        super.deleteAllTasks();
        kvTaskClient.put(String.valueOf(key), saveHTTPJson());
    }

    @Override
    public void deleteAllEpics() {
        super.deleteAllEpics();
        kvTaskClient.put(String.valueOf(key), saveHTTPJson());
    }

    @Override
    public void deleteAllSubtasks() {
        super.deleteAllSubtasks();
        kvTaskClient.put(String.valueOf(key), saveHTTPJson());
    }

    @Override
    public void deleteTaskById(int indification) {
        super.deleteTaskById(indification);
        kvTaskClient.put(String.valueOf(key), saveHTTPJson());
    }

    @Override
    public void deleteEpicById(int indification) {
        super.deleteEpicById(indification);
        kvTaskClient.put(String.valueOf(key), saveHTTPJson());
    }

    @Override
    public void deleteSubtaskById(int indification) {
        super.deleteSubtaskById(indification);
        kvTaskClient.put(String.valueOf(key), saveHTTPJson());
    }

    @Override
    public void setEpicStatus(Epic epic) {
        super.setEpicStatus(epic);
        kvTaskClient.put(String.valueOf(key), saveHTTPJson());
    }

    @Override
    public void setUpdateTask(Task task) {
        super.setUpdateTask(task);
        kvTaskClient.put(String.valueOf(key), saveHTTPJson());
    }

    @Override
    public void setUpdateEpic(Epic epic) {
        super.setUpdateEpic(epic);
        kvTaskClient.put(String.valueOf(key), saveHTTPJson());
    }

    @Override
    public void setUpdateSubtask(SubTask subTask) {
        super.setUpdateSubtask(subTask);
        kvTaskClient.put(String.valueOf(key), saveHTTPJson());
    }


    /**
     * Получение значения из JSON
     */
    private void fromJson() {
        Task task;
        JsonElement jsonElement = JsonParser.parseString(strJson);
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String type = jsonObject.get("typeOfTask").getAsString();
            task = toTask(jsonObject, type);
            whatCreateTask(task);
        } else {
            Type collectionToken = new TypeToken<ArrayList<String>>() {
            }.getType();
            List<String> jsonArray = gson.fromJson(strJson, collectionToken);
            for (String str : jsonArray) {
                JsonElement element = JsonParser.parseString(str);
                JsonObject jsonObject = element.getAsJsonObject();
                String type = jsonObject.get("typeOfTask").getAsString();
                task = toTask(jsonObject, type);
                whatCreateTask(task);
            }
        }
    }

    /**
     * Превращение задачи в JSON
     * @param task - задача
     * @return - JSON
     */
    private String createHTTPTasksJSON(Task task) {
        Gson gsonBuilder = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter())
                .create();
        return gsonBuilder.toJson(task);
    }

    /**
     * Сохранение листа JSON
     * @return - лист JSON
     */
    private String saveHTTPJson() {
        List<String> listJSON = new ArrayList<>();
        for (int cons = 1; cons <= indificator; cons++) {
            for (Integer indT : storageTask.keySet()) {
                if (indT == cons) {
                    listJSON.add(createHTTPTasksJSON(storageTask.get(indT)));
                    break;
                }
            }
            for (Integer indT : storageEpic.keySet()) {
                if (indT == cons) {
                    listJSON.add(createHTTPTasksJSON(storageEpic.get(indT)));
                    break;
                }
            }
            for (Integer indT : storageSubtask.keySet()) {
                if (indT == cons) {
                    listJSON.add(createHTTPTasksJSON(storageSubtask.get(indT)));
                    break;
                }
            }
        }
        if (listJSON.size() == 1) {
            return listJSON.get(0);
        }
        return gson.toJson(listJSON);
    }


}


class LocalDateAdapter extends TypeAdapter<LocalDateTime> {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy; HH:mm");

    @Override
    public void write(JsonWriter jsonWriter, LocalDateTime localDateTime) throws IOException {
        jsonWriter.value(localDateTime.format(formatter));
    }

    @Override
    public LocalDateTime read(JsonReader jsonReader) throws IOException {
        return LocalDateTime.parse(jsonReader.nextString(), formatter);
    }
}


