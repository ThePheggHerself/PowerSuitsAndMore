package phewitch.powersuits.common.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import phewitch.powersuits.common.entity.mobs.SuitSentry;
import phewitch.powersuits.common.entity.projectiles.ChestLaserProjectile;
import phewitch.powersuits.common.entity.projectiles.LaserProjectile;
import phewitch.powersuits.PowerSuits;

public class EntityManager {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, PowerSuits.MODID);

    public static final RegistryObject<EntityType<LaserProjectile>> LASER_PROJECTILE =
            ENTITY_TYPES.register("laser_projectile", () -> EntityType.Builder.<LaserProjectile>of(LaserProjectile::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("laser_projectile"));

    public static final RegistryObject<EntityType<ChestLaserProjectile>> CHEST_LASER_PROJECTILE =
            ENTITY_TYPES.register("chest_laser_projectile", () -> EntityType.Builder.<ChestLaserProjectile>of(ChestLaserProjectile::new, MobCategory.MISC)
                    .sized(1f, 1f).build("chest_laser_projectile"));

    public static final RegistryObject<EntityType<SuitSentry>> SENTRY =
            ENTITY_TYPES.register("sentry", () -> EntityType.Builder.<SuitSentry>of(SuitSentry::new, MobCategory.MISC)
                    .sized(1f, 1f).build("sentry"));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
