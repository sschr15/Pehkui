package virtuoel.pehkui.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.entity.decoration.EndCrystalEntity;

@Mixin(EndCrystalEntity.class)
public abstract class EnderCrystalEntityMixin extends EntityMixin
{
	@ModifyArg(method = "damage", index = 4, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/world/explosion/Explosion$DestructionType;)Lnet/minecraft/world/explosion/Explosion;"))
	private float onDamageCreateExplosionProxy(float power)
	{
		return power * pehkui_getScaleData().getScale();
	}
}
