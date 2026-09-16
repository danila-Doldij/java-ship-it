package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import ru.yandex.practicum.delivery.StandardParcel;
import ru.yandex.practicum.delivery.FragileParcel;
import ru.yandex.practicum.delivery.PerishableParcel;
import ru.yandex.practicum.delivery.Parcel;


public class ParcelCostTest {

    @Test
    void testStandardParcelCost() {
        Parcel p = new StandardParcel("Книга", 10, "Москва", (byte) 1);
        assertEquals(20, p.calculateDeliveryCost(), "Стоимость стандартной посылки должна быть 20");
    }

    @Test
    void testFragileParcelCost() {
        Parcel p = new FragileParcel("Ваза", 5, "Новосибирск", (byte) 1);
        assertEquals(20, p.calculateDeliveryCost(), "Стоимость хрупкой посылки должна быть 20");
    }

    @Test
    void testPerishableParcelCost() {
        Parcel p = new PerishableParcel("Молоко", 8, "Омск", (byte) 1, 3);
        assertEquals(24, p.calculateDeliveryCost(), "Стоимость скоропортящейся посылки должна быть 24");
    }

    @Test
    void testZeroWeightCost() {
        Parcel p = new StandardParcel("Пустышка", 0, "Екатеринбург", (byte) 1);
        assertEquals(0, p.calculateDeliveryCost(), "Стоимость посылки с весом 0 должна быть 0");
    }
}

