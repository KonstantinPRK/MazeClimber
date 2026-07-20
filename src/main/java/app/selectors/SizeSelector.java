package app.selectors;

import app.configuration.CurrentSize;
import app.console.Messenger;
import app.core.MazeSession;
import app.configuration.SizeRestrictions;
import org.springframework.stereotype.Component;

/**
 * Отвечает за выбор размерности лабиринта (высоты и ширины).
 * Использует ограничения размеров {@link SizeRestrictions} и взаимодействует
 * с пользователем через {@link Messenger}.
 *
 * @author unknown
 * @version 1.0
 */
@Component
public class SizeSelector implements Selector {
    private final SizeRestrictions sizeRestrictions;
    private final Messenger msg;
    private final String newSize = "Изменить размерность",
            currentSize = "Использовать текущую размерность";
    private boolean isNeedNewOption = true;


    /**
     * Конструктор, инициализирующий ограничения размеров и мессенджер.
     *
     * @param sizeRestrictions ограничения на минимальный и максимальный размер
     * @param msg              компонент для вывода сообщений и получения ввода пользователя
     */
    public SizeSelector(SizeRestrictions sizeRestrictions, Messenger msg) {
        this.sizeRestrictions = sizeRestrictions;
        this.msg = msg;
    }


    /**
     * Устанавливает размер лабиринта для текущей сессии.
     * Если сессия уже запускалась, предлагает изменить размер или оставить текущий.
     * При необходимости вызывает интерактивный выбор высоты и ширины.
     *
     * @param session текущий объект сессии лабиринта
     */
    @Override
    public void setOption(MazeSession session) {
        if (session.numOfLaunches() > 0) isNewOrOldOption(session);
        if (isNeedNewOption) selectOption(session);
    }


    /**
     * Выводит информацию о текущей высоте и ширине лабиринта и запрашивает
     * у пользователя, хочет ли он изменить размерность или оставить текущую.
     * Результат сохраняется в поле {@link #isNeedNewOption}.
     *
     * @param session текущая сессия, из которой берутся текущие размеры
     */
    private void isNewOrOldOption(MazeSession session) {
        msg.showInformation("Текущие параметры (размер лабиринта): ");
        msg.applyCurrentOptions("Высота", ": ", String.valueOf(session.size().height()));
        msg.applyCurrentOptions("Ширина", ": ", String.valueOf(session.size().width()));

        msg.showInformation("Какой размерности должен быть новый лабиринт ?");
        isNeedNewOption = msg.requestRespond(newSize, currentSize) == newSize;
    }


    /**
     * Запрашивает у пользователя высоту и ширину в пределах допустимого диапазона,
     * создаёт объект {@link CurrentSize} и сохраняет его в сессию.
     *
     * @param session текущая сессия, в которую будет сохранён новый размер
     */
    private void selectOption(MazeSession session) {
        msg.showInformation("Выберите высоту лабиринта. ");
        int height = msg.requestRespond(sizeRestrictions.minSize(), sizeRestrictions.maxSize());

        msg.showInformation("Выберите ширину лабиринта. ");
        int width = msg.requestRespond(sizeRestrictions.minSize(), sizeRestrictions.maxSize());

        session.setSize(new CurrentSize(height, width));
    }
}