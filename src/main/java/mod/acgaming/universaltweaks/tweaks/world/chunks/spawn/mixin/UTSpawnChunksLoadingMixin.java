package mod.acgaming.universaltweaks.tweaks.world.chunks.spawn.mixin;

import net.minecraft.world.World;

import mod.acgaming.universaltweaks.config.UTConfigTweaks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Targets isSpawnChunk rather than canDropChunk so mods re-checking spawn chunks on unload (e.g. Nether API) are covered too
@Mixin(World.class)
public abstract class UTSpawnChunksLoadingMixin
{
    @Inject(method = "isSpawnChunk", at = @At("HEAD"), cancellable = true)
    public void utIsSpawnChunk(int x, int z, CallbackInfoReturnable<Boolean> cir)
    {
        if (!UTConfigTweaks.WORLD.SPAWN_CHUNKS.utSpawnChunksLoadingToggle) cir.setReturnValue(false);
    }
}
