package ru.yandex.practicum.delivery;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {
    private static final Scanner scanner = new Scanner(System.in);

    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> trackedParcels = new ArrayList<>();

    private static ParcelBox<StandardParcel> standardBox = new ParcelBox<>(35);
    private static ParcelBox<FragileParcel> fragileBox = new ParcelBox<>(15);
    private static ParcelBox<PerishableParcel> perishableBox = new ParcelBox<>(20);


    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    updateTrackingStatus();
                    break;
                case 5:
                    showBoxContents();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Обновить местоположение всех отслеживаемых посылок");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        System.out.println("Введите тип посылки (1 - Стандартная, 2 - Хрупкая, 3 - Скоропортящаяся):");
        String typeInput = scanner.nextLine();
        int type;
        try {
            type = Integer.parseInt(typeInput);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: нужно ввести число!");
            return;
        }

        System.out.println("Введите описание:");
        String description = scanner.nextLine();

        System.out.println("Введите вес:");
        String weightInput = scanner.nextLine();
        int weight;
        try {
            weight = Integer.parseInt(weightInput);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: вес должен быть числом!");
            return;
        }

        System.out.println("Введите адрес доставки:");
        String address = scanner.nextLine();

        System.out.println("Введите день отправки (число):");
        String sendDayInput = scanner.nextLine();
        byte sendDay;
        try {
            sendDay = Byte.parseByte(sendDayInput);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: день должен быть числом!");
            return;
        }

        Parcel newParcel = null;

        if (type == 1) {
            StandardParcel parcel = new StandardParcel(description, weight, address, sendDay);
            newParcel = parcel;

            standardBox.addParcel(parcel);

        } else if (type == 2) {
            FragileParcel parcel = new FragileParcel(description, weight, address, sendDay);
            newParcel = parcel;

            trackedParcels.add(parcel);

            fragileBox.addParcel(parcel);

        } else if (type == 3) {
            System.out.println("Введите срок годности (дни):");
            String ttlInput = scanner.nextLine();
            int timeToLive;
            try {
                timeToLive = Integer.parseInt(ttlInput);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: срок годности должен быть числом!");
                return;
            }

            PerishableParcel parcel = new PerishableParcel(description, weight, address, sendDay, timeToLive);
            newParcel = parcel;

            perishableBox.addParcel(parcel);

        } else {
            System.out.println("Неверный тип посылки!");
            return;
        }

        if (newParcel != null) {
            allParcels.add(newParcel);
            System.out.println("Посылка успешно создана и обработана!");
        }
    }

    private static void sendParcels() {
        if (allParcels.isEmpty()) {
            System.out.println("Нет посылок для отправки. Сначала добавьте их!");
            return;
        }

        System.out.println("\n--- Начинаем обработку всех посылок ---");

        for (Parcel parcel : allParcels) {
            parcel.packageItem();

            parcel.deliver();

            System.out.println("--------------------------------------");
        }

        System.out.println("Все посылки обработаны!\n");
    }

    private static void calculateCosts() {
        if (allParcels.isEmpty()) {
            System.out.println("Список посылок пуст. Нечего считать.");
            return;
        }

        int totalCost = 0;

        for (Parcel parcel : allParcels) {
            totalCost += parcel.calculateDeliveryCost();
        }

        System.out.println("Общая стоимость доставки всех посылок: " + totalCost + " руб.");
    }

    private static void updateTrackingStatus() {
        if (trackedParcels.isEmpty()) {
            System.out.println("Нет посылок для отслеживания.");
            return;
        }

        System.out.println("Введите новое местоположение для всех отслеживаемых посылок:");
        String newLocation = scanner.nextLine();

        System.out.println("\n--- Обновление статусов ---");
        for (Trackable item : trackedParcels) {
            item.reportStatus(newLocation);
        }

        System.out.println("---------------------------\n");
    }

    private static void showBoxContents() {
        System.out.println("Какую коробку показать?");
        System.out.println("1 — Стандартные посылки");
        System.out.println("2 — Хрупкие посылки");
        System.out.println("3 — Скоропортящиеся посылки");

        String input = scanner.nextLine();
        int boxChoice;

        try {
            boxChoice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: нужно ввести число 1, 2 или 3.");
            return;
        }

        List<? extends Parcel> parcelsToShow = null;

        if (boxChoice == 1) {
            parcelsToShow = standardBox.getAllParcels();
        } else if (boxChoice == 2) {
            parcelsToShow = fragileBox.getAllParcels();
        } else if (boxChoice == 3) {
            parcelsToShow = perishableBox.getAllParcels();
        } else {
            System.out.println("Неверный выбор коробки.");
            return;
        }

        System.out.println("\n--- Содержимое выбранной коробки ---");
        if (parcelsToShow.isEmpty()) {
            System.out.println("Коробка пуста.");
        } else {
            for (Parcel parcel : parcelsToShow) {
                System.out.println("Посылка: <<" + parcel.getDescription() + ">> | Вес: " + parcel.getWeight() +
                                  " кг | Адрес: " + parcel.getDeliveryAddress());
            }
        }
        System.out.println("-------------------------------------\n");
    }
}
