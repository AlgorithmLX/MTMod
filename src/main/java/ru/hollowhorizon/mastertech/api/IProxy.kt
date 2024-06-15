package ru.hollowhorizon.mastertech.api

import net.minecraft.item.Item
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

interface IProxy {
    fun preInit(e: FMLPreInitializationEvent)

    fun init(e: FMLInitializationEvent)

    fun postInit(e: FMLPostInitializationEvent)

    fun getRender(item: Item, meta: Int, id: String)

    fun getDefaultItemRenderer(item: Item) {}
}
