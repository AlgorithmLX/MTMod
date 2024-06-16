package ru.hollowhorizon.mastertech

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.Logger
import ru.hollowhorizon.mastertech.MasterTech
import ru.hollowhorizon.mastertech.MasterTech.Companion
import ru.hollowhorizon.mastertech.api.IProxy
import ru.hollowhorizon.mastertech.data.MTConfig
import ru.hollowhorizon.mastertech.registry.MTRegistry


@Mod(modid = MasterTech.MODID, name = MasterTech.NAME, version = MasterTech.VERSION)
class MasterTech {
    companion object {
        const val MODID: String = "mastertech"
        const val NAME: String = "MasterTech: Hollow Beginning"
        const val VERSION: String = "1.0"

        lateinit var LOGGER: Logger

        @SidedProxy(clientSide = "ru.hollowhorizon.mastertech.proxy.ClientProxy", serverSide = "ru.hollowhorizon.mastertech.proxy.ServerProxy", modId = MODID)
        lateinit var proxy: IProxy

        @JvmField
        val tab: CreativeTabs = object : CreativeTabs(MODID) {
            override fun createIcon(): ItemStack {
                return ItemStack(MTRegistry.ICHOR)
            }
        }
    }

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        LOGGER = event.modLog
        MTRegistry.init()
        proxy.preInit(event)
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        proxy.init(event)
    }

    @Mod.EventHandler
    fun postInit(e: FMLPostInitializationEvent) {
        proxy.postInit(e)
    }
}
