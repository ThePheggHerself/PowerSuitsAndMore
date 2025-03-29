package phewitch.powersuits.common.entity.goals;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import phewitch.powersuits.common.entity.mobs.SuitSentry;

public class OwnerAttackedGoal extends TargetGoal {
    SuitSentry sentry;
    LivingEntity ownerLastHurtBy;
    int timestamp;

    public OwnerAttackedGoal(Mob pMob, SuitSentry sentry) {
        super(pMob, false);

        this.sentry = sentry;
    }

    @Override
    public boolean canUse() {
        if(sentry.getOwner() == null)
            return false;

        this.ownerLastHurtBy = sentry.getOwner().getLastHurtByMob();

        return canAttack(ownerLastHurtBy, TargetingConditions.DEFAULT);
    }

    @Override
    public void start() {
        this.mob.setTarget(ownerLastHurtBy);
        this.timestamp = sentry.getOwner().getLastHurtMobTimestamp();

        super.start();
    }
}
