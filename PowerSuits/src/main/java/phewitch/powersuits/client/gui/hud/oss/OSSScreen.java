package phewitch.powersuits.client.gui.hud.oss;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import phewitch.powersuits.PowerSuits;
import phewitch.powersuits.client.data.ClientData;
import phewitch.powersuits.common.OSS.OSSManager;

import java.util.ArrayList;
import java.util.List;

public class OSSScreen extends AbstractContainerScreen<OSSMenu> {
    static final ResourceLocation texture = new ResourceLocation(PowerSuits.MODID, "textures/gui/oss.png");

    int scrollOff;

    private final List<OSSOrderSuitButton> suitButtons = new ArrayList<>();

    public OSSScreen(OSSMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageWidth = 276;
        this.inventoryLabelX = 107;
    }

    @Override
    protected void init() {
        super.init();
        renderButtons();
    }

    public void renderButtons(){
        if(!this.suitButtons.isEmpty()){
            for (var button : suitButtons){
                button.visible = false;
            }

            this.suitButtons.clear();
        }

        int width = (this.width - this.imageWidth) / 2;
        int height = (this.height - this.imageHeight) / 2;
        int distance = height + 16 + 2;
        var index = 0;
        var suits = ClientData.getOSSSuits();

        if(!suits.isEmpty()) {
            for(var suit : suits){
                this.suitButtons.add(
                        this.addRenderableWidget(new OSSOrderSuitButton(width + 5, distance + (index * 16), index, suit, (callback) -> {
                            if (callback instanceof OSSOrderSuitButton ossB) {
                                this.postButtonClick(ossB);
                            }
                        }))
                );

                index++;
            }
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
        var suits = ClientData.getOSSSuits();
        if (!suits.isEmpty()) {
            int width = (this.width - this.imageWidth) / 2;
            int height = (this.height - this.imageHeight) / 2;
            int x = width + 5 + 6;
            int y = height + 16 + 5;

            this.renderScroller(pGuiGraphics, width, height, suits);
            int i1 = 0;

            for(var suit : suits) {
                if (!this.canScroll(suits.size()) || i1 >= this.scrollOff && i1 < 7 + this.scrollOff) {
                    //pGuiGraphics.drawString(this.font, suit, x, y, ChatFormatting.WHITE.getColor());
                    y += 16;
                    ++i1;
                } else {
                    ++i1;
                }
            }

            for(OSSOrderSuitButton button : this.suitButtons) {
                if(button.name != null)
                    button.visible = true;
            }

            RenderSystem.enableDepthTest();
        }

        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    private boolean canScroll(int pNumOffers) {
        return pNumOffers > 7;
    }

    private void renderScroller(GuiGraphics pGuiGraphics, int pPosX, int pPosY, List<String> suits) {
        int i = suits.size() + 1 - 7;
        if (i > 1) {
            int j = 139 - (27 + (i - 1) * 139 / i);
            int k = 1 + j / i + 139 / i;
            int l = 113;
            int i1 = Math.min(113, this.scrollOff * k);
            if (this.scrollOff == i - 1) {
                i1 = 113;
            }

            pGuiGraphics.blit(texture, pPosX + 94, pPosY + 18 + i1, 0, 0.0F, 199.0F, 6, 27, 512, 256);
        } else {
            pGuiGraphics.blit(texture, pPosX + 94, pPosY + 18, 0, 6.0F, 199.0F, 6, 27, 512, 256);
        }

    }

    void postButtonClick(OSSOrderSuitButton button){
        OSSManager.RequestSuitFromOSS(button.name);
        renderButtons();
    }

    class OSSOrderSuitButton extends Button {
        final int index;
        final String name;

        public OSSOrderSuitButton(int x, int y, int index, String text, Button.OnPress onPress){
            super(x, y, 88, 16, Component.literal(text), onPress, DEFAULT_NARRATION);
            this.name = text;
            this.index = index;
            this.visible = true;
        }

        public void renderToolTip(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {

        }
    }
}
