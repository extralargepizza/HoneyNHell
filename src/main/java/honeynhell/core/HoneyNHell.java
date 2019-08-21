/*******************************************************************************
 * Copyright 2014-2019, the Honey 'n' Hell Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package honeynhell.core;

import honeynhell.init.ModGeneration;
import honeynhell.init.ModVanillaCompat;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(value = HoneyNHell.MOD_ID)
public class HoneyNHell
{
    public static final String MOD_ID = "honeynhell";

    public static HoneyNHell instance;
    public static CommonProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public static Logger logger = LogManager.getLogger(MOD_ID);

    public HoneyNHell()
    {
    	instance = this;

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::loadComplete);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        ModVanillaCompat.setup();
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {

    }

    private void loadComplete(final FMLLoadCompleteEvent event)
    {
        ModGeneration.setup();
    }
}
