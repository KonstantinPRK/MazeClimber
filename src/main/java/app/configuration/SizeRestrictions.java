package app.configuration;

import org.springframework.stereotype.Component;

/**
 * Компонент, хранящий ограничения на минимальный и максимальный размер лабиринта.
 * Используется для валидации вводимых пользователем значений.
 *
 * @author unknown
 * @version 1.0
 */
@Component
public class SizeRestrictions {
    private int minSize = 3;
    private int maxSize = 20;


    /**
     * Возвращает минимально допустимый размер лабиринта (по высоте и ширине).
     *
     * @return минимальный размер (включительно)
     */
    public int minSize() {
        return minSize;
    }


    /**
     * Возвращает максимально допустимый размер лабиринта (по высоте и ширине).
     *
     * @return максимальный размер (включительно)
     */
    public int maxSize() {
        return maxSize;
    }
}