package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ru.yandex.practicum.delivery.PerishableParcel;


public class PerishableParcelTest {
    @Test
    void testNotExpired_StandardScenario() {
        PerishableParcel p = new PerishableParcel("Сыр", 2, "Воронеж",
                (byte) 1, 5);
        assertFalse(p.isExpired(3),
                "Посылка не должна быть просрочена, если текущий день 3, а срок до 6");
    }

    @Test
    void testExpired_StandardScenario() {
        PerishableParcel p = new PerishableParcel("Йогурт", 1,
                "Тула", (byte) 1, 2);
        assertTrue(p.isExpired(5),
                "Посылка должна быть просрочена, если текущий день 5, а срок истек на 3-й день");
    }

    @Test
    void testBoundaryCase_OnExpirationDay() {
        PerishableParcel p = new PerishableParcel("Молоко", 1,
                "Казань", (byte) 1, 5);
        assertFalse(p.isExpired(6),
                "Посылка не должна считаться просроченной ровно в день истечения срока");
    }
}

