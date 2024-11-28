import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        while (true){
            Scanner scan = new Scanner(System.in);
            System.out.println("Выберите действие:");
            System.out.println("1. Создать задачу");
            System.out.println("2. Получить список задач");
            System.out.println("3. Удалить задачу");
            System.out.println("4. Выход из программы");
            System.out.print("] ");
            int option = scan.nextInt();
            if (option == 4) {
                break;
            }
            switch (option) {
                case 1:
                    System.out.println("_____________________________");
                    createTask(scan);
                    System.out.println("_____________________________");
                    break;
                    case 2:
                        System.out.println("_____________________________");
                        printTasks();
                        System.out.println("_____________________________");
                        break;
                        case 3:
                            System.out.println("_____________________________");
                            deleteTask(scan);
                            System.out.println("_____________________________");
                            break;
                default:
                    System.out.println("Неизвестная команда");
                    break;
            }
        }
    }

    static void createTask(Scanner scan){
        System.out.print("Название задачи: ");
        scan.nextLine();
        String name = scan.nextLine();
        System.out.print("Описание задачи: ");
        String description = scan.nextLine();
        System.out.print("Дней на выполнение: ");
        int days = scan.nextInt();
        Task task = new Task(name, description, days);
        task.createTask();
        System.out.println("Задача создана!");
    }

    static Task createTaskFromString(String line){
        Task task = new Task();
        task.setName(line.split(";")[0]);
        task.setDescription(line.split(";")[1]);;
        task.setDateOfCreation(LocalDate.parse(line.split(";")[2]));
        task.setDayDuration(Integer.parseInt(line.split(";")[3]));
        return task;
    }

    static void printTasks(){
        System.out.println("Текущие задачи:\n");
        try(FileReader fileReader = new FileReader("tasks.txt")) {
            BufferedReader buffer = new BufferedReader(fileReader);
            String line = buffer.readLine();
            int lineCount = 1;
            while (line != null) {
                Task task = createTaskFromString(line);
                System.out.println("Задача " + lineCount);
                System.out.println(task);
                line = buffer.readLine();
                lineCount++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static void deleteTask(Scanner scan){
        System.out.println("Введите номер удаляемой задачи: ");
        int deleteId = scan.nextInt();
        List<Task> tasks = new ArrayList<>();
        try(FileReader fileReader = new FileReader("tasks.txt")) {
            BufferedReader buffer = new BufferedReader(fileReader);
            String line = buffer.readLine();
            int lineCount = 1;
            while (line != null) {
                Task task = createTaskFromString(line);
                tasks.add(task);
                line = buffer.readLine();
                lineCount++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        tasks.remove(deleteId-1);
        try(FileWriter writer = new FileWriter("tasks.txt", false)){
            writer.write("");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        for (Task task : tasks) {
            task.createTask();
        }
    }
}