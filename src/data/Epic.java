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
    }

    /** Метод заполенния листа подзадач
     * @param indOfSubtask - индификатор подзадачи
     */
    public void addNumOfSubtask(int indOfSubtask){
        subtackIDs.add(indOfSubtask);
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

    @Override
    public String toString() {
        return "Epic {" +
                "title='" + super.getTitle() + '\'' +
                ", description='" + super.getDescription() + '\'' +
                ", status='" + super.getStatus() + '\'' +
                ", id= " + super.getId() + '\'' +
                ", idSubtask= " + subtackIDs.toString() + '\'' +
                '}';
    }
}
