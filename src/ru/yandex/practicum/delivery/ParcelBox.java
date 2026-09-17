package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    private int maxWeight;
    private int currentWeight;
    private List<T> parcels;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
        this.currentWeight = 0;
        this.parcels = new ArrayList<>();
    }

    public boolean addParcel(T parcel) {

        int newTotalWeight = currentWeight + parcel.getWeight();

        if (newTotalWeight > maxWeight) {
            System.out.println("Ошибка: Превышен максимальный вес коробки! Вес посылки: " + parcel.getWeight() +
                               ", свободно места: " + (maxWeight - currentWeight));
            return false;
        }

        parcels.add(parcel);
        currentWeight += parcel.getWeight();
        System.out.println("Посылка <<" + parcel.getDescription() + ">> успешно добавлена в коробку. Текущий вес: " +
                           currentWeight + "/" + maxWeight);
        return true;
    }

    public List<T> getAllParcels() {
        return parcels;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int getParcelCount() {
        return parcels.size();
    }
}

