package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ru.yandex.practicum.delivery.ParcelBox;
import ru.yandex.practicum.delivery.StandardParcel;


public class ParcelBoxTest {

    @Test
    void testAddParcel_WithinLimit() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(10);
        StandardParcel parcel = new StandardParcel("Книга", 5, "Москва", (byte) 1);

        box.addParcel(parcel);

        assertEquals(1, box.getParcelCount(), "В коробке должна быть 1 посылка");
        assertEquals(5, box.getCurrentWeight(), "Вес коробки должен быть 5 кг");
        assertTrue(box.getAllParcels().contains(parcel), "Посылка должна быть в списке");
    }

    @Test
    void testAddParcel_ExceedsLimit() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(10);
        StandardParcel heavyParcel = new StandardParcel("Гиря", 15, "Омск", (byte) 1);

        box.addParcel(heavyParcel);

        assertEquals(0, box.getParcelCount(), "В коробке не должно быть посылок (вес превышен)");
        assertEquals(0, box.getCurrentWeight(), "Вес коробки должен остаться 0");
        assertFalse(box.getAllParcels().contains(heavyParcel), "Тяжелая посылка не должна быть в списке");
    }

    @Test
    void testAddParcel_BoundaryCase() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(10);

        StandardParcel parcel = new StandardParcel("Сейф", 10, "Екатеринбург", (byte) 1);

        box.addParcel(parcel);

        assertEquals(1, box.getParcelCount(), "Посылка весом ровно в лимит должна быть добавлена");

        assertEquals(10, box.getCurrentWeight(), "Вес коробки должен быть равен лимиту");
    }

    @Test
    void testAddMultipleParcels() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(20);

        StandardParcel p1 = new StandardParcel("Книга 1", 5, "Новосибирск", (byte) 1);
        StandardParcel p2 = new StandardParcel("Книга 2", 7, "Новосибирск", (byte) 1);
        StandardParcel p3 = new StandardParcel("Книга 3", 8, "Новосибирск", (byte) 1);

        box.addParcel(p1);
        box.addParcel(p2);
        box.addParcel(p3);

        assertEquals(3, box.getParcelCount(), "В коробке должны быть все 3 посылки");

        assertEquals(20, box.getCurrentWeight(), "Суммарный вес должен быть равен лимиту коробки");

        assertTrue(box.getAllParcels().contains(p1), "Первая посылка должна быть в коробке");
        assertTrue(box.getAllParcels().contains(p2), "Вторая посылка должна быть в коробке");
        assertTrue(box.getAllParcels().contains(p3), "Третья посылка должна быть в коробке");
    }
}
