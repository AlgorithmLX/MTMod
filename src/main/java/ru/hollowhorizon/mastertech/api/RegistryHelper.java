package ru.hollowhorizon.mastertech.api;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class RegistryHelper {
    public static final List<Item> ITEMS = new ArrayList<>();
    public static final List<Block> BLOCKS = new ArrayList<>();
    private final String modId;

    private RegistryHelper(String modId) {
        this.modId = modId;
    }

    public static RegistryHelper makeRegistry(String modId) {
        return new RegistryHelper(modId);
    }

    public <T extends Item> Supplier<T> registerItem(String id, ItemRegistryHolder<T> item) {
        ResourceLocation rl = new ResourceLocation(this.modId, id);
        item.setRegistryName(rl);
        ITEMS.add(item.get());
        return item;
    }

    public <T extends Block> Supplier<T> registerBlock(String id, BlockRegistryHolder<T> block) {
        ResourceLocation rl = new ResourceLocation(this.modId, id);
        block.setRegistryName(rl);
        BLOCKS.add(block.get());
        return block;
    }

    public interface ItemRegistryHolder<T extends Item> extends Supplier<T> {
        default void setRegistryName(ResourceLocation obj) {
            this.get().setRegistryName(obj);
        }
    }

    public interface BlockRegistryHolder<T extends Block> extends Supplier<T> {
        default void setRegistryName(ResourceLocation obj) {
            this.get().setRegistryName(obj);
        }
    }
}
