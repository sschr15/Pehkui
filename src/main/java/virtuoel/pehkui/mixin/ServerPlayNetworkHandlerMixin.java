package virtuoel.pehkui.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.entity.MovementType;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import virtuoel.pehkui.api.ScaleData;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin
{
	@Shadow ServerPlayerEntity player;
	
	@ModifyArg(method = "onVehicleMove", at = @At(value = "INVOKE", target = "net/minecraft/util/math/Box.contract(D)Lnet/minecraft/util/math/Box;"))
	private double onVehicleMoveContractProxy(double value)
	{
		final float scale = ScaleData.of(player).getScale();
		return scale < 1.0F ? value * scale : value;
	}
	
	@ModifyArg(method = "onVehicleMove", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;move(Lnet/minecraft/entity/MovementType;Lnet/minecraft/util/math/Vec3d;)V"))
	private Vec3d onVehicleMoveMoveProxy(MovementType type, Vec3d movement)
	{
		return movement.multiply(1.0F / ScaleData.of(player.getRootVehicle()).getScale());
	}
	
	@ModifyArg(method = "onPlayerMove", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;move(Lnet/minecraft/entity/MovementType;Lnet/minecraft/util/math/Vec3d;)V"))
	private Vec3d onPlayerMoveMoveProxy(MovementType type, Vec3d movement)
	{
		return movement.multiply(1.0F / ScaleData.of(player).getScale());
	}
}
