package dev.brodino.summoningritualaltarfix.mixin;

import com.almostreliable.summoningrituals.altar.AltarRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AltarRenderer.class)
public abstract class AltarRendererMixin {

    @Unique
    private static final long START_NANOS = System.nanoTime();

    @Redirect(
        method = "render*",
        at = @At(
            value = "INVOKE",
            target = "Lcom/almostreliable/summoningrituals/util/MathUtils;singleRotation(Ljava/lang/Number;)F",
            ordinal = 0,
            remap = false
        )
    )
    private float summoningritualaltarfix$useClientClock(Number degree) {
        var elapsedSeconds = (System.nanoTime() - START_NANOS) / 1_000_000_000f;
        return (20f * elapsedSeconds) % 360f;
    }
}
