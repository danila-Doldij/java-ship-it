package ru.yandex.practicum.delivery;

public class FragileParcel extends Parcel implements Trackable {
    private static final int BASE_COST = 4;
    private String currentLocation;

    public FragileParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
        this.currentLocation = "Склад отправителя";
    }

    @Override
    public void packageItem() {
        System.out.println("Посылка <<" + getDescription() + ">> обёрнута в защитную плёнку.");
        super.packageItem();
    }

    @Override
    public int getBaseCost() {
        return BASE_COST;
    }

    @Override
    public void reportStatus(String newLocation) {
        this.currentLocation = newLocation;
        System.out.println("Хрупкая посылка <<" + this.getDescription() + ">> изменила местоположение на " +
                          newLocation);
    }

    public String getCurrentLocation() {
        return currentLocation;
    }
}
