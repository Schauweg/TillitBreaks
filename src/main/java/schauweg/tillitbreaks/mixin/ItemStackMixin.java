package schauweg.tillitbreaks.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import schauweg.tillitbreaks.config.TIBConfigManager;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow public abstract boolean isDamageable();

    @Inject(method = "isItemBarVisible", at = @At("RETURN"), cancellable = true)
    public void isItemBarVisible(CallbackInfoReturnable<Boolean> cb) {

        if (MinecraftClient.getInstance().player == null || MinecraftClient.getInstance().player.currentScreenHandler instanceof CreativeInventoryScreen.CreativeScreenHandler || !this.isDamageable())
            return;

        if (!TIBConfigManager.getConfig().isShowDurabilityBar()) {
            cb.setReturnValue(false);
        } else if (TIBConfigManager.getConfig().isShowDurabilityIfBarFull()) {
            cb.setReturnValue(true);
        }
    }
}
