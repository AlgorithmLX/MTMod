package ru.hollowhorizon.mastertech.events

import net.minecraft.client.resources.I18n
import net.minecraftforge.event.entity.player.ItemTooltipEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import ru.hollowhorizon.mastertech.MasterTech
import ru.hollowhorizon.mastertech.registry.MTRegistry

@EventBusSubscriber(modid = MasterTech.MODID, value = [ Side.CLIENT ])
object HollowClientEventHandler {
    @JvmStatic
    @SubscribeEvent
    fun addTooltips(e: ItemTooltipEvent) {
        val stack = e.itemStack
        if (stack.item == MTRegistry.ICHOR) e.toolTip.add(I18n.format("msg.mastertech.ichor"))
        else if (stack.item == MTRegistry.ICHOR_BAG) e.toolTip.add(I18n.format("msg.mastertech.ichor_bag"))
    }
}