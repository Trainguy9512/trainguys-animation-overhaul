package com.trainguy9512.animationoverhaul.mixin.debug;

//TODO: will have to clean this mess up later, dunno what changed with 1.20 and i don't really care toooo much about the debug gui right now so meh
/*
@Mixin(DebugScreenOverlay.class)
public abstract class MixinDebugScreenOverlay extends GuiComponent {

    @Shadow @Final private Minecraft minecraft;

    @Shadow @Final private Font font;

    @Shadow @Final private static int COLOR_GREY;

    @Shadow protected abstract int colorLerp(int i, int j, float f);

    @Shadow private HitResult block;

    @Shadow private HitResult liquid;

    @Inject(method = "drawSystemInformation", at = @At("HEAD"), cancellable = true)
    private void drawTimerDebugInfo(PoseStack poseStack, CallbackInfo ci){

        if(this.minecraft.options.fov().get() == 72){
            poseStack.translate(this.minecraft.getWindow().getGuiScaledWidth() / 4F, 0, 0);
            poseStack.scale(0.75F, 0.75F, 0.75F);

            drawTimerDebug(poseStack);

            poseStack.scale(1/0.75F, 1/0.75F, 1/0.75F);
            poseStack.translate(-this.minecraft.getWindow().getGuiScaledWidth() / 4F, 0, 0);
            ci.cancel();
        }
    }

    private void drawTimerDebug(PoseStack poseStack){
        boolean shouldRenderDebugTimers = true;
        Entity entity = AnimationOverhaulMain.debugEntity;

        entity = minecraft.player;

        for(Entity entity1 : minecraft.level.entitiesForRendering()){
            if(entity1.getType() == EntityType.CREEPER){
                entity = entity1;
            }
        }

        if(entity != null){

            AnimationDataContainer entityAnimationData = AnimatorDispatcher.INSTANCE.getEntityAnimationData(entity);
            TreeMap<String, AnimationDataContainer.Variable<?>> debugData = entityAnimationData.getDebugData();

            DecimalFormat format = new DecimalFormat("0.00");
            if(debugData.size() > 0){

                for(int i = 0; i < debugData.size(); i++){
                    String key = debugData.keySet().stream().toList().get(i);
                    AnimationDataContainer.Variable<?> data = debugData.get(key);

                    boolean isFloat = data.get() instanceof Float;
                    boolean isWithinRange = false;
                    if(isFloat){
                        float value = (float) data.get();
                        isWithinRange = value <= 1 && value >= 0;
                    }

                    String string;
                    int j = 9;
                    int m = 2 + j * i;
                    m += j + 4;
                    if(isWithinRange && !this.minecraft.options.renderFpsChart && isFloat){
                        string = key;
                    } else if(isFloat) {
                        string = key + " " + format.format(data.get());
                    } else {
                        string = key + " " + data.get().toString();
                    }
                    int k = this.font.width(isWithinRange ? string + " 0.00" : string);
                    int l = this.minecraft.getWindow().getGuiScaledWidth() - 2 - k;
                    Objects.requireNonNull(this.font);
                    fill(poseStack, l - 1, m - 1, l + k + 1, m + j - 1, -1873784752);
                    this.font.draw(poseStack, string, (float)l, (float)m, COLOR_GREY);

                    if(isWithinRange){
                        float value = (float) data.get();
                        k = this.font.width("0.00");
                        k *= value;
                        l = this.minecraft.getWindow().getGuiScaledWidth() - 2 - k;
                        int k2 = (int) (k / value);
                        int l2 = this.minecraft.getWindow().getGuiScaledWidth() - 2 - k;
                        fill(poseStack, l - 1, m, l + k, m + j - 2, -2);
                        fill(poseStack, l2 - 1, m, l2 + k2, m + j - 2, COLOR_GREY);
                    }
                }


                String string = "Selected entity: " + entity.getName().getString() + " (" + entity.getType().toShortString() + ")";
                int j = 9;
                int m = 2;
                int k = this.font.width(string);
                int l = this.minecraft.getWindow().getGuiScaledWidth() - 2 - k;
                Objects.requireNonNull(this.font);
                fill(poseStack, l - 1, m - 1, l + k + 1, m + j - 1, -1873784752);
                this.font.draw(poseStack, string, (float)l, (float)m, COLOR_GREY);

            } else {
                String string = "Animation timers not initiated!";
                Objects.requireNonNull(this.font);
                int j = 9;
                int k = this.font.width(string);
                int l = this.minecraft.getWindow().getGuiScaledWidth() - 2 - k;
                int m = 2;
                fill(poseStack, l - 1, m - 1, l + k + 1, m + j - 1, -1873784752);
                this.font.draw(poseStack, string, (float)l, (float)m, COLOR_GREY);
            }
        }
    }
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void testCompassGadget(PoseStack poseStack, CallbackInfo ci){
        if(this.minecraft.options.fov().get() == 73){
            ci.cancel();
        }
    }
}
*/