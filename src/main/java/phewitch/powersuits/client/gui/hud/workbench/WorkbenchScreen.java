package phewitch.powersuits.client.gui.hud.workbench;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import phewitch.powersuits.PowerSuits;
import phewitch.powersuits.common.item.suits.armorbase.datatypes.SuitFeatures;

import java.util.ArrayList;
import java.util.List;

public class WorkbenchScreen extends AbstractContainerScreen<WorkbenchMenu> {
    static final ResourceLocation texture = new ResourceLocation(PowerSuits.MODID, "textures/gui/workbench.png");

    int scrollOff;

    SuitFeatures currentSuit = null;

    private final List<SuitFeatures> templates = new ArrayList<>(List.of(

    ));
    private final List<WBSuitButton> suitButtons = new ArrayList<>();

    public WorkbenchScreen(WorkbenchMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageWidth = 336;
        this.imageHeight = 224;
        this.inventoryLabelX = 1000;
    }

    @Override
    protected void init() {
        super.init();
        renderButtons();
    }

    public void renderButtons(){
        int width = (this.width - this.imageWidth) / 2;
        int height = (this.height - this.imageHeight) / 2;
        int distance = height + 5 + 2;
        var index = 0;

        for (var temp : templates){
            this.suitButtons.add(
                    this.addRenderableWidget(new WBSuitButton(width + 7, distance + (index * 16), index, temp, (callback) -> {
                        if (callback instanceof WBSuitButton ossB) {
                            this.suitButtonClick(ossB);
                        }
                    }))
            );
            index++;
        }
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, texture);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        pGuiGraphics.blit(texture, x, y, 0, 0, imageWidth, imageHeight, 512, 256);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

        int width = (this.width - this.imageWidth) / 2;
        int height = (this.height - this.imageHeight) / 2;
        int x = width + 5 + 6;
        int y = height + 16 + 5;

        if (!suitButtons.isEmpty()) {
            this.renderScroller(pGuiGraphics, width, height);
//            int i1 = 0;
//
//            for(var suit : suitButtons) {
//                if (!this.canScroll(suitButtons.size()) || i1 >= this.scrollOff && i1 < 7 + this.scrollOff) {
//                    //pGuiGraphics.drawString(this.font, suit, x, y, ChatFormatting.WHITE.getColor());
//                    y += 16;
//                    ++i1;
//                } else {
//                    ++i1;
//                }
//            }
            RenderSystem.enableDepthTest();
        }

        if(currentSuit != null){
            pGuiGraphics.drawString(this.font, "Name: " + currentSuit.DisplayName(), x + 62, y - 10, ChatFormatting.WHITE.getColor());
            pGuiGraphics.drawString(this.font, "Power: " + currentSuit.maxPower, x + 62, y, ChatFormatting.WHITE.getColor());
            pGuiGraphics.drawString(this.font, "Charge Type: " + currentSuit.chargeType, x + 62, y  + 10, ChatFormatting.WHITE.getColor());
            pGuiGraphics.drawString(this.font, "Recharge: " + currentSuit.recharge + "/s", x + 62, y  + 20, ChatFormatting.WHITE.getColor());
            //pGuiGraphics.drawString(this.font, "Name: " + currentSuit.name, x + 62, y  + 30, ChatFormatting.WHITE.getColor());
        }

        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    private boolean canScroll(int pNumOffers) {
        return pNumOffers > 7;
    }

    private void renderScroller(GuiGraphics pGuiGraphics, int pPosX, int pPosY) {
        int i = suitButtons.size() + 1 - 7;
        if (i > 1) {
            int j = 139 - (27 + (i - 1) * 139 / i);
            int k = 1 + j / i + 139 / i;
            int l = 113;
            int i1 = Math.min(113, this.scrollOff * k);
            if (this.scrollOff == i - 1) {
                i1 = 113;
            }

            pGuiGraphics.blit(texture, pPosX + 57, pPosY + 6 + i1, 0, 0.0F, 229, 6, 27, 512, 256);
        } else {
            pGuiGraphics.blit(texture, pPosX + 57, pPosY + 6, 0, 6.0F, 229, 6, 27, 512, 256);
        }

    }

    void suitButtonClick(WBSuitButton button){
        currentSuit = button.template;
    }

    class WBSuitButton extends Button {
        final int index;
        final String name;
        final SuitFeatures template;

        public WBSuitButton(int x, int y, int index, SuitFeatures template, Button.OnPress onPress){
            super(x, y, 48, 16, Component.literal(template.getModelName()), onPress, DEFAULT_NARRATION);
            this.name = template.getModelName();
            this.index = index;
            this.visible = true;
            this.template = template;
        }

        public void renderToolTip(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {

        }
    }
}
