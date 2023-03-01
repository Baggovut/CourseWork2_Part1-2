import exceptions.IncorrectArgumentException;
import exceptions.TaskNotFoundException;
import tasks.*;
import tasks.enums.Type;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

public class TasksDiaryMain {

    public static void main(String[] args) {
        TasksDiaryMain tasksDiaryMain1 = new TasksDiaryMain();
        TaskService taskService1 = null;
        String fileName = "taskService.db";
        int choice;
        Scanner scanner1 = new Scanner(System.in);
        while (true){
            if(taskService1 == null){
                System.out.println("\033[4mСписок задач не создан.\033[0m");
                System.out.println("[1] Вы можете загрузить его из файла");
                System.out.println("[2] Создать список вручную");
                System.out.println("[3] Или выйти из программы");
                choice = tasksDiaryMain1.readChoice(scanner1,1,3);
                if(choice == 1){
                    System.out.println("[1] Использовать текущее имя файла: \""+fileName+"\"");
                    System.out.println("[2] Задать новое имя");
                    choice = tasksDiaryMain1.readChoice(scanner1,1,2);
                    if(choice == 2){
                        System.out.println("Введите имя файла: ");
                        fileName = tasksDiaryMain1.readFileName();
                    }
                    taskService1 = tasksDiaryMain1.readTaskServiceFromFile(fileName);
                } else if (choice == 2) {
                    taskService1 = new TaskService();
                    System.out.println("Создан \033[4mпустой\033[0m список.");
                }
                else {
                    break;
                }
            }else{
                System.out.println("[1] Добавить задачу.");
                System.out.println("[2] Получить задачи на день.");
                System.out.println("[3] Удалить задачу по id.");
                System.out.println("[4] Изменить задачу.");
                System.out.println("[5] Получить сгруппированный список задач.");
                System.out.println("[6] Получить список удалённых задач.");
                System.out.println("[7] Сохранить список задач в файл.");
                System.out.println("[8] Выход.");
                choice = tasksDiaryMain1.readChoice(scanner1,1,8);
                if(choice == 1){
                    System.out.println("Введите название задачи: ");
                    String title1 = tasksDiaryMain1.readFileName();
                    System.out.println("Введите описание задачи: ");
                    String description1 = tasksDiaryMain1.readString();
                    System.out.println("Введите тип задачи: рабочая (0) или личная (1):");
                    int type1 = tasksDiaryMain1.readChoice(scanner1,0,1);
                    System.out.println("Введите повторяемость задачи:"
                            +"\n[1] однократная"
                            +"\n[2] ежедневная"
                            +"\n[3] еженедельная"
                            +"\n[4] ежемесячная"
                            +"\n[5] ежегодная"
                    );
                    int repeatability1 = tasksDiaryMain1.readChoice(scanner1,1,5);
                    System.out.println("Введите год для задачи:");
                    int year1 = tasksDiaryMain1.readChoice(
                            scanner1,
                            LocalDateTime.now().getYear(),
                            LocalDateTime.now().getYear()+100
                    );
                    System.out.println("Введите месяц для задачи:");
                    int month1 = tasksDiaryMain1.readChoice(
                            scanner1,
                            LocalDateTime.now().getMonthValue(),
                            12
                    );
                    System.out.println("Введите день для задачи:");
                    int day1 = tasksDiaryMain1.readChoice(
                            scanner1,
                            LocalDateTime.now().getDayOfMonth(),
                            LocalDateTime.now().getMonth().maxLength()
                    );
                    System.out.println("Введите час для задачи:");
                    int hours1 = tasksDiaryMain1.readChoice(scanner1, 0, 24);
                    System.out.println("Введите минуты для задачи:");
                    int minutes1 = tasksDiaryMain1.readChoice(scanner1,1,59);
                    try{
                        switch(repeatability1){
                            case 1:
                                taskService1.add(
                                        new OneTimeTask(
                                                title1,
                                                description1,
                                                (type1 == 0 ? Type.WORK : Type.PERSONAL),
                                                LocalDateTime.of(year1,month1,day1,hours1,minutes1)
                                        )
                                );
                                break;

                                case 2:
                                    taskService1.add(
                                            new DailyTask(
                                                    title1,
                                                    description1,
                                                    (type1 == 0 ? Type.WORK : Type.PERSONAL),
                                                    LocalDateTime.of(year1,month1,day1,hours1,minutes1)
                                            )
                                    );
                                    break;
                                    case 3:
                                        taskService1.add(
                                                new WeeklyTask(
                                                        title1,
                                                        description1,
                                                        (type1 == 0 ? Type.WORK : Type.PERSONAL),
                                                        LocalDateTime.of(year1,month1,day1,hours1,minutes1)
                                                )
                                        );
                                        break;
                                    case 4:
                                        taskService1.add(
                                                new MonthlyTask(
                                                        title1,
                                                        description1,
                                                        (type1 == 0 ? Type.WORK : Type.PERSONAL),
                                                        LocalDateTime.of(year1,month1,day1,hours1,minutes1)
                                                )
                                        );
                                        break;
                                    case 5:
                                        taskService1.add(
                                                new YearlyTask(
                                                        title1,
                                                        description1,
                                                        (type1 == 0 ? Type.WORK : Type.PERSONAL),
                                                        LocalDateTime.of(year1,month1,day1,hours1,minutes1)
                                                )
                                        );
                                        break;
                                }
                    }catch (TaskNotFoundException e){
                        e.printStackTrace();
                    }catch (IncorrectArgumentException e){
                        e.printStackTrace();
                    }

                } else if (choice == 2) {
                    System.out.println("Введите год для задачи: ");
                    int year2 = tasksDiaryMain1.readChoice(
                            scanner1,
                            LocalDateTime.now().getYear(),
                            LocalDateTime.now().getYear()+10
                    );
                    System.out.println("Введите месяц для задачи:");
                    int month2 = tasksDiaryMain1.readChoice(scanner1, 1, 12);
                    System.out.println("Введите день для задачи:");
                    int day2 = tasksDiaryMain1.readChoice(
                            scanner1,
                            LocalDateTime.of(year2,month2,1,0,1).getDayOfMonth(),
                            LocalDateTime.of(year2,month2,1,0,1).getMonth().maxLength()
                    );
                    LocalDate date2 = LocalDate.of(year2,month2,day2);
                    System.out.println("Список задач на: "+date2);
                    for(Task t2 : taskService1.getAllByDate(date2)){
                        System.out.println("ID: "+t2.getId()
                                +" Время: "+t2.getDateTime().getHour()+":"+t2.getDateTime().getMinute()+"\n"
                                +"Заголовок: "+t2.getTitle()+"\n"
                                +"Описание: "+t2.getDescription());
                    }
                    System.out.println();
                }else if (choice == 3) {
                    System.out.println("Введите ID задачи для удаления: ");
                    int id3 = tasksDiaryMain1.readChoice(scanner1,0, -1);
                    try {
                        taskService1.remove(id3);
                    }catch (TaskNotFoundException e){
                        e.printStackTrace();
                    }
                }else if (choice == 4) {
                    System.out.println("Введите ID изменяемой задачи: ");
                    int id4 = tasksDiaryMain1.readChoice(scanner1,0,-1);
                    System.out.println("[1] Изменить заголовок.");
                    System.out.println("[2] Изменить описание.");
                    choice = tasksDiaryMain1.readChoice(scanner1,1,2);
                    if (choice == 1){
                        System.out.println("Введите новый заголовок: ");
                        String title4 = tasksDiaryMain1.readString();
                        taskService1.updateTitle(id4,title4);
                    }else {
                        System.out.println("Введите новое описание: ");
                        String description4 = tasksDiaryMain1.readString();
                        taskService1.updateDescription(id4,description4);
                    }
                }else if (choice == 5) {
                    Map<LocalDate,Collection<Task>> map1 = taskService1.getAllGroupedByDate();
                    for(Map.Entry<LocalDate, Collection<Task>> e1 : map1.entrySet()){
                        System.out.println("Дата: "+e1.getKey());
                        for(Task t1 : e1.getValue()){
                            System.out.println(t1);
                        }
                        System.out.println();
                    }
                }else if (choice == 6){
                    System.out.println("Список удалённых задач: ");
                    if(taskService1.getRemovedTasks().size() > 0){
                        for (Task t6 : taskService1.getRemovedTasks()) {
                            System.out.println("ID: " + t6.getId() + ", время: " + t6.getDateTime() + ", название: " + t6.getTitle());
                        }
                    }else {
                        System.out.println("Список пуст.");
                    }
                    System.out.println();
                }else if (choice == 7) {
                    System.out.println("[1] Использовать текущее имя файла: \""+fileName+"\"");
                    System.out.println("[2] Задать новое имя ");
                    choice = tasksDiaryMain1.readChoice(scanner1,1,2);
                    if(choice==2){
                        System.out.println("Введите имя файла: ");
                        fileName = tasksDiaryMain1.readString();
                    }
                    tasksDiaryMain1.writeTaskServiceToFile(taskService1,fileName);
                }else if (choice == 8) {
                    break;
                }
            }
        }
        System.out.println("\nEND");

    }

    public TaskService readTaskServiceFromFile(String fileName){
        TaskService readTaskService = null;
        try{
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            readTaskService = (TaskService) objectIn.readObject();
            objectIn.close();
            fileIn.close();
        }catch (FileNotFoundException e){
            System.out.println("Файл "+fileName+" не существует!\n");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return readTaskService;
    }
    public void writeTaskServiceToFile(TaskService taskService, String fileName){
        try{
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(taskService);
            objectOut.close();
            fileOut.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int readChoice(Scanner scan, int minValue, int maxValue){
        int choice;
        if(minValue <= maxValue) {
            System.out.println("Введите значение от " + minValue + " до " + maxValue + " включительно.");
        }else{
            System.out.println("Введите значение от " + minValue);
        }
        while(true){
            while(!scan.hasNextInt()){
                System.out.println("Введено недопустимое значение, попробуйте снова!");
                scan.nextLine();
            }
            choice=scan.nextInt();
            if(minValue <= maxValue) {
                if (choice >= minValue && choice <= maxValue) {
                    break;
                } else {
                    System.out.println("Значение должно быть от " + minValue + " до " + maxValue + " включительно.");
                    scan.nextLine();
                }
            }else {
                if (choice >= minValue){
                    break;
                }else {
                    System.out.println("Значение должно быть от " + minValue);
                    scan.nextLine();
                }
            }
        }
        return choice;
    }

    public String readString(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readFileName(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str;
        try {
            while(true){
                str = reader.readLine();
                if(!str.contains("?") && !str.contains(":") && !str.contains("*")){
                    break;
                }else{
                    System.out.println("Нельзя использовать символы: \"?\", \":\", \"*\"");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return str;
    }
}