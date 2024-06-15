package ru.hollowhorizon.mastertech.registry

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import ru.hollowhorizon.mastertech.MasterTech
import ru.hollowhorizon.mastertech.api.Register
import ru.hollowhorizon.mastertech.api.item.ItemBase
import ru.hollowhorizon.mastertech.item.IchorBag
import ru.hollowhorizon.mastertech.item.MTCoin

object MTRegistry {
    @JvmField
    val BLOCKS: MutableMap<Block, ItemBlock> = LinkedHashMap()
    @JvmField
    val ITEMS: MutableSet<Item> = LinkedHashSet()

    @field:Register("ichor")
    @JvmField
    val ICHOR: Item = ItemBase()

    @field:Register("ichor_bag")
    @JvmField
    val ICHOR_BAG: Item = IchorBag()

    @field:Register("mithrite")
    @JvmField
    val MITHRITE: Item = ItemBase()

    @field:Register("mithrite_ingot")
    @JvmField
    val MITHRITE_INGOT: Item = ItemBase()

    @field:Register("ichorium")
    @JvmField
    val ICHORIUM: Item = ItemBase()

    @field:Register("mt_coin")
    @JvmField
    val MASTER_COIN: Item = MTCoin()

    @JvmStatic
    fun init() {
        MasterTech.LOGGER.info("Initializing Registry...")
        try {
            MTRegistry::class.java.declaredFields.forEach {
                if (it.isAnnotationPresent(Register::class.java)) {
                    val annot = it.getAnnotation(Register::class.java)
                    val inst = it[null]
                    if (inst is Item) {
                        reg(inst, annot.name)
                    }

                    if (inst is Block) {
                        reg(inst, annot.name)
                    }
                }
            }
        } catch (e: Exception) {
            MasterTech.LOGGER.info("Failed to initialize registry", e)
            e.printStackTrace()
            return
        }
    }

    private fun reg(item: Item, fn: String) {
        val name = fn.lowercase()
        if (name.contains("mithrite")) {
            val allow = MasterTech.commonConfig.experiments.allowMithrite
            if (!allow) return
        } else if (name.contains("mt_coin")) {
            val allow = MasterTech.commonConfig.experiments.allowNewCoins
            if (!allow) return
        }

        ITEMS.add(item)

        item.setRegistryName(MasterTech.MODID, name).setTranslationKey(MasterTech.MODID + "." + name)
        MasterTech.LOGGER.debug("Item $name is registered.")
    }

    @JvmName("regBlock")
    private fun reg(block: Block, fn: String) {
        val blockItem = ItemBlock(block)
        BLOCKS[block] = blockItem
        ITEMS.add(blockItem)
        val name = fn.lowercase()
        block.setRegistryName(MasterTech.MODID, name).setTranslationKey(MasterTech.MODID + "." + name)
        blockItem.setRegistryName(MasterTech.MODID, name).setTranslationKey(MasterTech.MODID + "." + name)
        MasterTech.LOGGER.debug("Block $name is registered.")
    }

    @JvmStatic
    fun getMTItemByBlock(block: Block): ItemBlock? {
        return BLOCKS[block]
    }
}
