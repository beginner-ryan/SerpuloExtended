package serpuloex;

import mindustry.world.blocks.defense.turrets.ItemTurret;

public class ArcTurret extends ItemTurret {
    public boolean shieldPenetration = true;

    public ArcTurret(String name) {
        super(name);
    }
    
    @Override
    public void init() {
        super.init();
        
        // Настройка снаряда имеет приоритет; если она не задана, используем настройку турели.
        if (ammoTypes != null) {
            ammoTypes.each((item, bullet) -> {
                if (bullet instanceof ArcProjectile) {
                    ArcProjectile projectile = (ArcProjectile) bullet;
                    boolean penetratesShields = projectile.shieldPenetration == null
                        ? shieldPenetration
                        : projectile.shieldPenetration;

                    // Не поглощаемый снаряд проходит через силовые поля.
                    bullet.absorbable = !penetratesShields;
                }
            });
        }
    }
}
