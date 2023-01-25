package com.clarkster.originsorbrework.item.custom;

import io.github.apace100.origins.component.OriginComponent;
import io.github.apace100.origins.networking.ModPackets;
import io.github.apace100.origins.origin.Origin;
import io.github.apace100.origins.origin.OriginLayer;
import io.github.apace100.origins.origin.OriginLayers;
import io.github.apace100.origins.origin.OriginRegistry;
import io.github.apace100.origins.registry.ModComponents;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrbOfRebirth extends Item {
    public OrbOfRebirth(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.of(("\u00a7b\u00a7oGaze into the orb to be reborn anew...")));
        tooltip.add(Text.of(("\u00A77Allows you to reselect your Origin.")));
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if(!world.isClient) {
            Identifier targetLayer = new Identifier("origins", "origin");
            OriginLayer originLayer = OriginLayers.getLayer(targetLayer);

            OriginComponent originComponent = ModComponents.ORIGIN.get(user);
            PacketByteBuf buffer = new PacketByteBuf(Unpooled.buffer());

            if (originLayer.isEnabled()) originComponent.setOrigin(originLayer, Origin.EMPTY);

            originComponent.checkAutoChoosingLayers(user, false);
            originComponent.sync();

            buffer.writeBoolean(false);
            ServerPlayNetworking.send((ServerPlayerEntity) user, ModPackets.OPEN_ORIGIN_SCREEN, buffer);

        }
        if(!user.isCreative()) {
            stack.decrement(1);
        }
        return TypedActionResult.consume(stack);
    }
}
