package ru.hollowhorizon.mastertech.proxy

import net.minecraft.item.Item
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import ru.hollowhorizon.mastertech.api.IProxy

class ServerProxy : IProxy {
    override fun preInit(e: FMLPreInitializationEvent) {}

    override fun init(e: FMLInitializationEvent) {}

    override fun postInit(e: FMLPostInitializationEvent) {}

    override fun getRender(item: Item, meta: Int, id: String) {}
}
