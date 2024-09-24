package io.github.chromonym.exspectriments.renderers;

import io.github.chromonym.exspectriments.entities.PigmentExtractorBlockEntity;
import net.minecraft.block.SaplingBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

public class PigmentExtractorRenderer implements BlockEntityRenderer<PigmentExtractorBlockEntity> {

    public PigmentExtractorRenderer(BlockEntityRendererFactory.Context ctx) {}

    @Override
    public void render(PigmentExtractorBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        MinecraftClient client = MinecraftClient.getInstance();
        ItemStack tree = entity.getStack(0);
        if (!tree.isEmpty()) {
            if (tree.getItem() instanceof BlockItem item) {
                if (item.getBlock() instanceof SaplingBlock sapling) {
                    matrices.push();
                    matrices.scale(0.9f, 0.9f, 0.9f);
                    matrices.translate(0.05, 0.125, 0.05);
                    client.getBlockRenderManager().renderBlockAsEntity(sapling.getDefaultState(), matrices, vertexConsumers, light, overlay);
                    matrices.pop();
                }
            }
        }
    }
    
}
