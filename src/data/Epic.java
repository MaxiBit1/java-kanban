package data;

import java.util.ArrayList;


/**
 * Класс задач-эпиков
 * @author Max Vasilyev
 * @version 1.0
 */
public class Epic extends Task{

    private ArrayList<Integer> subtackIDs;
    public Epic(String title, String description) {
        super(title, description);
        subtackIDs = new ArrayList<>();
        setTypeOfTask(TypeOfTasks.EPIC);
    }

    /** Метод удаление из листа определенный номер подзадачи
      * @param indificator - индификатор
     */
    public void removeNumOfSubtask(int indificator){
        subtackIDs.remove(indificator);
    }

    /**
     * Метод удаления всего листа
     */
    public void deleteAllList(){
        subtackIDs.clear();
    }

    public ArrayList<Integer> getSubtackIDs() {
        return subtackIDs;
    }

}
