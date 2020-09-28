package TodoItems;

import java.time.LocalDate;

public class TodoItems {
    private String title;
    private String description;
    private LocalDate deadline;

    public TodoItems(String title, String description, LocalDate deadline){
         this.title = title;
         this.description = description;
         this.deadline = deadline;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(){
        this.title = title;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public LocalDate getDeadline(){
        return deadline;
    }
    public void setDeadline(LocalDate deadline){
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return title;
    }
}
