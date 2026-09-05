package serpuloex;

import arc.graphics.Color;
import arc.Core;
import arc.scene.ui.TextButton;
import arc.scene.ui.layout.Table;
import mindustry.Vars;
import mindustry.ui.Styles;

public class RoofUI {
    private static TextButton roofButton;
    private static boolean buttonStateInitialized;
    private static boolean lastRoofMode;

    public static void init() {
        // ClientLoad обычно приходит один раз, но защита не даст продублировать HUD
        // при повторной инициализации клиента.
        if (roofButton != null) return;

        Table table = new Table();
        // Используем стандартный стиль Mindustry для кнопок
        roofButton = new TextButton("", Styles.defaultt);
        
        roofButton.clicked(RoofMode::toggleRoofMode);

        table.add(roofButton).size(180f, 50f);
        table.setFillParent(true);
        table.top().right();
        table.marginTop(60f).marginRight(20f); // Сдвигаем, чтобы не перекрыть стандартные кнопки

        // Добавляем таблицу в общий HUD
        Vars.ui.hudGroup.addChild(table);
        draw();
    }

    // Вызывается каждый кадр для обновления визуала
    public static void draw() {
        if (roofButton == null) return;

        boolean roofModeEnabled = RoofMode.isRoofMode();
        if (buttonStateInitialized && roofModeEnabled == lastRoofMode) return;

        buttonStateInitialized = true;
        lastRoofMode = roofModeEnabled;

        if (roofModeEnabled) {
            roofButton.setText(Core.bundle.get("serpuloex.roof.mode.on"));
            roofButton.setColor(Color.acid); // Зеленый оттенок
        } else {
            roofButton.setText(Core.bundle.get("serpuloex.roof.mode.off"));
            roofButton.setColor(Color.scarlet); // Красный оттенок
        }
    }
}
