package ru.hollowhorizon.mastertech.item;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import ru.hollowhorizon.mastertech.MasterTech;

public class MTCoin extends Item {
    public MTCoin() {
        setCreativeTab(MasterTech.tab);
        setMaxStackSize(256);
        addPropertyOverride(new ResourceLocation("little"), (s, w, e) -> 0F);
        addPropertyOverride(new ResourceLocation("medium"), (s, w, e) -> s.getCount() >= 4 && s.getCount() < 10 ? 1F : 0F);
        addPropertyOverride(new ResourceLocation("medium_plus"), (s, w, e) -> s.getCount() >= 10 && s.getCount() < 30 ? 2F : 0F);
        addPropertyOverride(new ResourceLocation("ultra"), (s, w, e) -> s.getCount() >= 30 && s.getCount() < 60 ? 3F : 0F);
        addPropertyOverride(new ResourceLocation("hard"), (s, w, e) -> s.getCount() >= 60 && s.getCount() < 128 ? 3F : 0F);
    }
}
