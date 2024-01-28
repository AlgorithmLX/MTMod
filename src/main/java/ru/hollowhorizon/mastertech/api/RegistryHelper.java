package ru.hollowhorizon.mastertech.api;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import ru.hollowhorizon.mastertech.api.item.BlockItemBase;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class RegistryHelper {
    public static final List<Item> ITEMS = new ArrayList<>();
    public static final List<Block> BLOCKS = new ArrayList<>();

    public static <T extends Item> Supplier<T> registerItem(String id, Function<String, T> item) {
        T registered = item.apply(id);
        ITEMS.add(registered);
        return () -> registered;
    }

    public static <T extends Block> Supplier<T> registerBlock(String id, Function<String, T> block) {
        T registered = block.apply(id);
        registerItem(id, (name) -> new BlockItemBase(id, registered));
        BLOCKS.add(registered);
        return () -> registered;
    }
}
