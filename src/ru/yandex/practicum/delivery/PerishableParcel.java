package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel {
    private int timeToLive;
    private static final int BASE_COST = 3;

    public PerishableParcel(String description, int weight, String deliveryAddress, byte sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    @Override
    public int getBaseCost() {
        return BASE_COST;
    }

    public boolean isExpired(int currentDay) {
        return (this.getSendDay() + this.timeToLive) < currentDay;
    }

    public int getTimeToLive() {
        return timeToLive;
    }
}
