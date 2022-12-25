package http;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import data.Epic;
import data.SubTask;
import data.Task;
import manager.Managers;
import manager.TaskManager;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static manager.HTTPTaskForm.toTask;

/**
 * Класс сервера для эндпоинтов по TaskManager
 */
public class HttpTaskServer {

    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create();
        httpServer.bind(new InetSocketAddress(PORT), 0);
        httpServer.createContext("/tasks", new TaskHander());
        httpServer.start();

        System.out.println("Server start!!!");
    }

    /**
     * Класс для работы с сервером
     */
    static class TaskHander implements HttpHandler {
        private Gson gson = new Gson();
        private TaskManager taskManager = Managers.fileBackedTaskManagersDefault();

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            String path = exchange.getRequestURI().toString();
            String[] arrPath = path.split("/");
            switch (method) {
                case "GET": {
                    getMethod(exchange, arrPath);
                    break;
                }
                case "POST": {
                    postMethod(exchange, arrPath);
                    break;
                }
                case "DELETE": {
                    deleteMethod(exchange, arrPath);
                    break;
                }
                default: {
                    writeResponse(exchange, "Ошибка в методе", 400);
                    break;
                }
            }
            exchange.close();
        }

        /**
         * Метод при GET запросе
         * @param exchange - Http запрос
         * @param arrPath - экдпоинт
         * @throws IOException
         */
        private void getMethod(HttpExchange exchange, String[] arrPath) throws IOException {
            if (arrPath.length == 2) {
                writeResponse(exchange, taskManager.getPrioritizedTask().toString(), 200);
            } else if (arrPath[2].equals("task")) {
                if (arrPath.length == 3) {
                    writeResponse(exchange, taskManager.getStorageTask().toString(), 200);
                } else {
                    int idTask = Integer.parseInt(arrPath[3].substring(4));
                    writeResponse(exchange, taskManager.getTask(idTask).toString(), 200);
                }
            } else if (arrPath[2].equals("subtask")) {
                if (arrPath.length == 3) {
                    writeResponse(exchange, taskManager.getStorageSubtask().toString(), 200);
                } else if (arrPath[3].equals("epic")) {
                    int idTask = Integer.parseInt(arrPath[4].substring(4));
                    writeResponse(exchange, taskManager.getEpicSubtask(idTask).toString(), 200);
                } else {
                    int idTask = Integer.parseInt(arrPath[3].substring(4));
                    writeResponse(exchange, taskManager.getSubtask(idTask).toString(), 200);
                }
            } else if (arrPath[2].equals("epic")) {
                if (arrPath.length == 3) {
                    writeResponse(exchange, taskManager.getStorageEpic().toString(), 200);
                } else {
                    int idTask = Integer.parseInt(arrPath[3].substring(4));
                    writeResponse(exchange, taskManager.getEpic(idTask).toString(), 200);
                }
            } else {
                writeResponse(exchange, taskManager.getHistory().toString(), 200);
            }
        }

        /**
         * Метод при POST запросе
         * @param exchange - Http запрос
         * @param arrPath - экдпоинт
         * @throws IOException
         */
        private void postMethod(HttpExchange exchange, String[] arrPath) throws IOException {
            InputStream inputStream = exchange.getRequestBody();
            String jsonTask = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            Gson gsonBuild = new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .create();
            JsonElement jsonElement = JsonParser.parseString(jsonTask);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String type = jsonObject.get("typeOfTask").getAsString();
            if (arrPath[2].equals("task")) {
                Task task = gsonBuild.fromJson(jsonTask, Task.class);
                if (task.getId() != 0) {
                    taskManager.setUpdateTask(task);
                    writeResponse(exchange, "Задача обновлена", 201);
                } else {
                    taskManager.createTasks(toTask(jsonObject, type));
                    writeResponse(exchange, "Задача создана", 201);
                }
            } else if (arrPath[2].equals("subtask")) {
                SubTask subTask = gson.fromJson(jsonTask, SubTask.class);
                if (subTask.getId() != 0) {
                    taskManager.setUpdateSubtask(subTask);
                    writeResponse(exchange, "Подзадача обновлена", 201);
                } else {
                    taskManager.createSubtacks((SubTask) toTask(jsonObject, type));
                    writeResponse(exchange, "Подзадача создана", 201);
                }
            } else {
                Epic epic = gson.fromJson(jsonTask, Epic.class);
                if (epic.getId() != 0) {
                    taskManager.setUpdateEpic(epic);
                    writeResponse(exchange, "Задача-эпик обновлена", 201);
                } else {
                    taskManager.createEpic((Epic) toTask(jsonObject, type));
                    writeResponse(exchange, "Задача-эпик создана", 201);
                }
            }
        }

        /**
         * Метод при DELETE запросе
         * @param exchange - Http запрос
         * @param arrPath - экдпоинт
         * @throws IOException
         */
        private void deleteMethod(HttpExchange exchange, String[] arrPath) throws IOException {
            if (arrPath[2].equals("task")) {
                if(arrPath.length == 3) {
                    taskManager.deleteAllTasks();
                    writeResponse(exchange, "Все задачи удалены", 202);
                } else {
                    int idTask = Integer.parseInt(arrPath[3].substring(4));
                    taskManager.deleteTaskById(idTask);
                    writeResponse(exchange, "Задача с id "+idTask+" удалена", 202);
                }
            } else if (arrPath[2].equals("subtask")) {
                if(arrPath.length == 3) {
                    taskManager.deleteAllSubtasks();
                    writeResponse(exchange, "Все подзадачи удалены", 202);
                } else {
                    int idTask = Integer.parseInt(arrPath[3].substring(4));
                    taskManager.deleteSubtaskById(idTask);
                    writeResponse(exchange, "Подзадача с id "+idTask+" удалена", 202);
                }
            } else {
                if(arrPath.length == 3) {
                    taskManager.deleteAllEpics();
                    writeResponse(exchange, "Все задачи-эпики удалены", 202);
                } else {
                    int idTask = Integer.parseInt(arrPath[3].substring(4));
                    taskManager.deleteEpicById(idTask);
                    writeResponse(exchange, "Задача-эпик с id "+idTask+" удалена", 202);
                }
            }

        }

        private void writeResponse(HttpExchange exchange,
                                   String response,
                                   int responseCode) throws IOException {
            if (response.isBlank()) {
                exchange.sendResponseHeaders(responseCode, 0);
            } else {
                byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
                exchange.sendResponseHeaders(200, bytes.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(bytes);
                }
            }
            exchange.close();
        }
    }

    private static class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {
        private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy; HH:mm");

        @Override
        public void write(JsonWriter jsonWriter, LocalDateTime localDateTime) throws IOException {
            jsonWriter.value(LocalDateTime.MAX.format(formatter));
        }

        @Override
        public LocalDateTime read(JsonReader jsonReader) throws IOException {
            return LocalDateTime.parse(jsonReader.nextString(), formatter);
        }
    }
}
