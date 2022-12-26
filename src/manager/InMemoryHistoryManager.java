package manager;

import data.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс реализующий логику истории просмотра задач
 *
 * @author Max Vasilyev
 * @version 1.0
 */
public class InMemoryHistoryManager implements HistoryManager {

    /**
     * Переменная головы списка
     */
    private Node<Task> head;
    /**
     * Переменная хвоста списка
     */
    private Node<Task> tail;
    /**
     * Хэш-таблица истории просмотра
     */
    Map<Integer, Node<Task>> nodeMap = new HashMap<>();

    @Override
    public void add(Task task) {
        if (nodeMap.containsKey(task.getId())) {
            remove(task.getId());
        }
        Node<Task> zapice = linkLast(task);
        nodeMap.put(task.getId(), zapice);
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    @Override
    public void remove(int id) {
        removeNode(nodeMap.remove(id));
    }

    /**
     * Запись узла в конец списка CustomLinkedList
     *
     * @param task - задача
     * @return - получение узла
     */
    private Node<Task> linkLast(Task task) {
        final Node<Task> oldTail = tail;
        final Node<Task> newNode = new Node<>(task, oldTail, null);
        tail = newNode;
        if (oldTail == null) {
            head = newNode;
        } else {
            oldTail.next = newNode;
        }
        return newNode;
    }

    /**
     * Получения упорядочного списка ArrayList для отчетности
     *
     * @return - упорядочного списка
     */
    private List<Task> getTasks() {
        List<Task> historyList = new ArrayList<>();
        Node<Task> node = head;
        do {
            historyList.add(node.data);
            node = node.next;
        } while (node != null);
        return historyList;
    }

    /**
     * Метод удаления узла
     *
     * @param node - узел
     */
    private void removeNode(Node<Task> node) {
        if (node.prev == null && node.next != null) {
            node.next.prev = null;
            head = node.next;
        } else if (node.next == null && node.prev != null) {
            node.prev.next = null;
            tail = node.prev;
        } else if (node.next == null && node.prev == null) {
            head = null;
            tail = null;
        } else {
            Node<Task> oldTail = node.prev;
            Node<Task> oldHead = node.next;
            node.prev.next = oldHead;
            node.next.prev = oldTail;
        }
        node.data = null;
        node.prev = null;
        node.next = null;
    }

    /**
     * Класс узла
     *
     * @param <Task> - обобщенный тип данных
     */
    public static class Node<Task> {

        /**
         * пременная данных
         */
        public Task data;
        /**
         * переменная ссылки на предыдущие данные
         */
        public Node<Task> prev;
        /**
         * переменная ссылки на слудующие данные
         */
        public Node<Task> next;

        public Node(Task data, Node<Task> prev, Node<Task> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

    }
}

