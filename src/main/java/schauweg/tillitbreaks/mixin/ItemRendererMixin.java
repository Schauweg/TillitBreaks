package schauweg.tillitbreaks.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    private final float scale = 0.5F;

    @Shadow
    public float zOffset;

    @Inject(method = "renderGuiItemOverlay(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V", at = @At("TAIL"))
    public void onRenderGuiItemOverlay(TextRenderer renderer, ItemStack stack, int x, int y, String countLabel, CallbackInfo ci){

        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        if (player.currentScreenHandler instanceof CreativeInventoryScreen.CreativeScreenHandler)
            return;

        if (stack.isDamageable()){

            MatrixStack matrixTextInfo = new MatrixStack();
            matrixTextInfo.push();
            matrixTextInfo.translate(x, y, (double)(this.zOffset + 200.0F));
            matrixTextInfo.scale(0.5F, 0.5F, 0F);
            float scaleOffset = 1F / scale;
            int fontHeight = renderer.fontHeight;

            String curDur = String.valueOf(stack.getMaxDamage() - stack.getDamage());
            int textWidth = renderer.getWidth(curDur);

            renderer.draw(matrixTextInfo, curDur, 16*scaleOffset-textWidth-1, 16*scaleOffset-fontHeight-5, -1);


            if (stack.getItem() == Items.BOW || stack.getItem() == Items.CROSSBOW){

                PlayerInventory inventory = player.inventory;
                int arrowCounter = 0;
                for (int i = 0; i < inventory.size(); i++) {
                    ItemStack is = inventory.getStack(i);
                    if (is.getItem() == Items.ARROW){
                        arrowCounter += is.getCount();
                    }
                }
                String totalArrows = String.valueOf(arrowCounter);

                if (stack.hasEnchantments() && arrowCounter > 0) {
                    Map<Enchantment, Integer> map = EnchantmentHelper.get(stack);
                    for (Map.Entry<Enchantment, Integer> entry : map.entrySet()) {
                        Enchantment enchantment = entry.getKey();
                        //Get enchantmentname for a specific level
                        if (enchantment.equals(Enchantments.INFINITY)) {
                            totalArrows = "∞";
                            break;
                        }
                    }
                }
                textWidth = renderer.getWidth(totalArrows);
                renderer.draw(matrixTextInfo, totalArrows, 16*scaleOffset-textWidth-1, 0, -1);
            }
            matrixTextInfo.push();
        }
    }
}

