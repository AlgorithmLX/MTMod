package ru.hollowhorizon.mastertech;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import ru.hollowhorizon.mastertech.api.IProxy;
import ru.hollowhorizon.mastertech.registry.MTRegistry;

import javax.annotation.Nonnull;

@Mod(
        modid = MasterTech.MODID,
        name = MasterTech.NAME,
        version = MasterTech.VERSION
)
public class MasterTech {
    public static final String MODID = "mastertech";
    public static final String NAME = "MasterTech: Hollow Beginning";
    public static final String VERSION = "1.0";
    public static final CreativeTabs tab = new CreativeTabs(MODID) {
        @Override
        public @Nonnull ItemStack getTabIconItem() {
            return new ItemStack(MTRegistry.ICHOR);
        }
    };

    public static Logger LOGGER;

    @SidedProxy(
            clientSide = "ru.hollowhorizon.mastertech.proxy.ClientProxy",
            serverSide = "ru.hollowhorizon.mastertech.proxy.ServerProxy",
            modId = MODID
    )
    public static IProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER = event.getModLog();
        MTRegistry.init();
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}
