package ru.hollowhorizon.mastertech.api.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import ru.hollowhorizon.mastertech.MasterTech;
import ru.hollowhorizon.mastertech.api.model.IModeled;

public class BlockItemBase extends ItemBlock implements IModeled {
    public BlockItemBase(Block p_i45328_1_) {
        super(p_i45328_1_);
        setCreativeTab(MasterTech.tab);
    }

    @Override
    public void registerModels() {
        MasterTech.proxy.getRender(this, 0, "inventory");
    }
}
