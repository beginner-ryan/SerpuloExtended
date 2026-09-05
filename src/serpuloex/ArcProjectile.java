package serpuloex;

import mindustry.entities.bullet.ArtilleryBulletType;

/**
 * Тип снаряда, который может переопределить настройку пробития щитов турели.
 * Отдельный публичный класс и конструктор без аргументов необходимы загрузчику
 * HJSON для разрешения {@code type: ArcProjectile}.
 */
public class ArcProjectile extends ArtilleryBulletType {
    /**
     * {@code null} означает «взять значение из ArcTurret».
     */
    public Boolean shieldPenetration;

    public ArcProjectile() {
        super();
    }

    public ArcProjectile(float speed, float damage, String sprite) {
        super(speed, damage, sprite);
    }
}
