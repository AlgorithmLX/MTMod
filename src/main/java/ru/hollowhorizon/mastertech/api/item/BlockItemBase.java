package ru.hollowhorizon.mastertech.api.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import ru.hollowhorizon.mastertech.MasterTech;
import ru.hollowhorizon.mastertech.api.IModeled;

public class BlockItemBase extends ItemBlock implements IModeled {
    public BlockItemBase(String id, Block p_i45328_1_) {
        super(p_i45328_1_);
        setRegistryName(id);
        setCreativeTab(MasterTech.tab);
        setUnlocalizedName(String.format("%s.%s", MasterTech.MODID, id));
    }

    @Override
    public void registerModels() {
        MasterTech.proxy.getRender(this, 0, "inventory");
    }
}
