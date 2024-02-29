package ru.hollowhorizon.mastertech.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import ru.hollowhorizon.mastertech.api.IProxy;
import ru.hollowhorizon.mastertech.registry.MTItemRegistry;

public class ServerProxy implements IProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        MTItemRegistry.init();
    }

    @Override
    public void init(FMLInitializationEvent e) {}

    @Override
    public void postInit(FMLPostInitializationEvent e) {}

    @Override
    public void getRender(Item item, int meta, String id) {}
}
