package virtuoel.pehkui.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.vehicle.BoatEntity;

@Mixin({
	BoatEntity.class,
	RavagerEntity.class,
	SpiderEntity.class
})
public abstract class EntityMountedHeightOffsetMixin extends EntityMixin
{
	@Inject(at = @At("RETURN"), method = "getMountedHeightOffset", cancellable = true)
	private void onGetMountedHeightOffset(CallbackInfoReturnable<Double> info)
	{
		final float scale = pehkui_getScaleData().getScale();
		
		if (scale != 1.0F)
		{
			info.setReturnValue(info.getReturnValue() * scale);
		}
	}
}
