package serpuloex;

import arc.Events;
import mindustry.game.EventType.ClientLoadEvent;
import mindustry.game.EventType.Trigger;
import mindustry.mod.Mod;

public class SerpuloEx extends Mod {

    public SerpuloEx() {
        // Ждем загрузки клиента, чтобы Vars.ui был проинициализирован
        Events.on(ClientLoadEvent.class, e -> {
            RoofUI.init(); // Создаем кнопку

            // Обновляем состояние кнопки каждый кадр
            Events.run(Trigger.uiDrawEnd, () -> {
                RoofUI.draw();
            });
        });
    }

    @Override
    public void loadContent() {
        // Весь контент (блоки/предметы) описан в .hjson и подхватывается
        // автоматически по полю "type" — явные new RoofBlock(...) / new ArcTurret(...) не нужны.
    }
}
