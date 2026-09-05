package serpuloex;

import arc.graphics.g2d.Draw;
import arc.math.Mathf;
import mindustry.game.Team;
import mindustry.graphics.Layer;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.meta.BuildVisibility;

public class RoofBlock extends Block {
    public float roofHeight = 5f; // Добавочная высота над стандартным слоем
    public float damageReduction = 0.5f;
    public boolean shieldPenetration = false;

    public RoofBlock(String name) {
        super(name);
        solid = false; // Позволяет наземным юнитам проходить под крышей
        hasShadow = true;
        buildVisibility = BuildVisibility.shown;
    }

    // Запрещаем постройку/удаление крыш вне режима редактирования
    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation) {
        return RoofMode.isRoofMode() && super.canPlaceOn(tile, team, rotation);
    }

    @Override
    public boolean canBreak(Tile tile) {
        return RoofMode.isRoofMode() && super.canBreak(tile);
    }

    // Без этого переопределения движок использует стандартный Building,
    // и весь код из RoofBuild ниже (draw/canInteract/tapped) никогда не вызывается.
    @Override
    public Building newBuilding() {
        return new RoofBuild();
    }

    public class RoofBuild extends Building {
        @Override
        public void damage(float amount) {
            // Некорректное значение из HJSON не должно превращать урон в лечение.
            float damageMultiplier = Mathf.clamp(1f - damageReduction);
            super.damage(amount * damageMultiplier);
        }
        @Override
        public void draw() {
            // Рисуем поверх всего (например, слой летающих юнитов + наша высота)
            Draw.z(Layer.flyingUnitLow + roofHeight);
            super.draw();
            Draw.reset();
        }

        @Override
        public boolean canInteract() {
            // Требование: метод проверяет текущий режим
            return RoofMode.isRoofMode() && super.canInteract();
        }
        
        // Перехват тапов (нажатий) по зданию
        @Override
        public void tapped() {
            if (!RoofMode.isRoofMode()) return;
            super.tapped();
        }
    }
}
