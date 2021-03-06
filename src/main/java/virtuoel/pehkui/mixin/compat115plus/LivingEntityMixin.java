package virtuoel.pehkui.mixin.compat115plus;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.mixin.EntityMixin;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends EntityMixin
{
	@ModifyArg(method = "onKilledBy", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"))
	private Entity onKilledBySpawnEntityProxy(Entity entity)
	{
		final float scale = pehkui_getScaleData().getScale();
		
		if (scale != 1.0F)
		{
			final ScaleData data = ScaleData.of(entity);
			data.setScale(scale);
			data.setTargetScale(scale);
			data.markForSync();
		}
		
		return entity;
	}
	
	@ModifyArg(method = "dropXp", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"))
	private Entity dropXpModifyEntity(Entity entity)
	{
		final float scale = pehkui_getScaleData().getScale();
		
		if (scale != 1.0F)
		{
			final ScaleData data = ScaleData.of(entity);
			data.setScale(scale);
			data.setTargetScale(scale);
			data.markForSync();
		}
		
		return entity;
	}
}
