package virtuoel.pehkui.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;

@Mixin({
	AbstractDecorationEntity.class,
	AbstractMinecartEntity.class,
	EndCrystalEntity.class,
	FallingBlockEntity.class,
	TntEntity.class
})
public abstract class PreEntityTickMixin extends EntityMixin
{
	@Inject(at = @At("HEAD"), method = "tick")
	private void onTickPre(CallbackInfo info)
	{
		pehkui_getScaleData().tick();
	}
}
