import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Task {
    private String name;
    private String description;
    private LocalDate dateOfCreation;
    private int dayDuration;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public int getDayDuration() {
        return dayDuration;
    }

    public void setDayDuration(int dayDuration) {
        this.dayDuration = dayDuration;
    }

    public Task(String name, String description, int dayDuration) {
        this.name = name;
        this.description = description;
        this.dateOfCreation = LocalDate.now();
        this.dayDuration = dayDuration;
    }

    public Task(){}

    public void createTask(){
        try(FileWriter writer = new FileWriter("tasks.txt", true)){
            writer.write(name + ";");
            writer.write(description + ";");
            writer.write(dateOfCreation + ";");
            writer.write(dayDuration + "\n");

        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String toString(){
        return "Название: " + name + "\nОписание: " + description + "\nДата создания: " + dateOfCreation + "\nВремя на выполнение: " + dayDuration+ " дней\n";
    }


}

