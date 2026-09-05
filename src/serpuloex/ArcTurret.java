package serpuloex;

import mindustry.entities.bullet.ArtilleryBulletType;
import mindustry.world.blocks.defense.turrets.ItemTurret;

public class ArcTurret extends ItemTurret {
    public boolean shieldPenetration = true;

    public ArcTurret(String name) {
        super(name);
    }
    
    @Override
    public void init() {
        super.init();
        
        // Проходим по всем типам снарядов этой турели и применяем логику пробития
        if (ammoTypes != null) {
            ammoTypes.each((item, bullet) -> {
                if (bullet instanceof ArcProjectile) {
                    ((ArcProjectile) bullet).shieldPenetration = this.shieldPenetration;
                    // Главный параметр движка: если снаряд не поглощаемый, он игнорирует щит
                    bullet.absorbable = !this.shieldPenetration;
                }
            });
        }
    }

    // Расширенный тип пули для кастомной логики ArcProjectile
    public static class ArcProjectile extends ArtilleryBulletType {
        public boolean shieldPenetration = true;

        public ArcProjectile(float speed, float damage, String sprite) {
            super(speed, damage, sprite);
            this.absorbable = !shieldPenetration; // По умолчанию пробивает щиты
        }
    }
}
