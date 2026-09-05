package serpuloex;

import arc.graphics.Color;
import arc.scene.ui.TextButton;
import arc.scene.ui.layout.Table;
import mindustry.Vars;
import mindustry.ui.Styles;

public class RoofUI {
    private static TextButton roofButton;

    public static void init() {
        Table table = new Table();
        // Используем стандартный стиль Mindustry для кнопок
        roofButton = new TextButton("Roof Mode: OFF", Styles.defaultt);
        
        roofButton.clicked(RoofMode::toggleRoofMode);

        table.add(roofButton).size(180f, 50f);
        table.top().right();
        table.marginTop(60f).marginRight(20f); // Сдвигаем, чтобы не перекрыть стандартные кнопки

        // Добавляем таблицу в общий HUD
        Vars.ui.hudGroup.addChild(table);
    }

    // Вызывается каждый кадр для обновления визуала
    public static void draw() {
        if (roofButton == null) return;

        if (RoofMode.isRoofMode()) {
            roofButton.setText("Roof Mode: ON");
            roofButton.setColor(Color.acid); // Зеленый оттенок
        } else {
            roofButton.setText("Roof Mode: OFF");
            roofButton.setColor(Color.scarlet); // Красный оттенок
        }
    }
}
