package com.tylermuch.mcrl;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import jssc.SerialPort;

@Mod(modid = MCRLMod.MODID, version = MCRLMod.VERSION)
public class MCRLMod {

    public static final String MODID = "mcrl";
    public static final String VERSION = "1.0";

    @Mod.Instance(MCRLMod.MODID)
    public static MCRLMod instance;

    public static SerialPort serial = null;

    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent event) {

    }

    @Mod.EventHandler
    public void init (FMLInitializationEvent event) {

    }

    @Mod.EventHandler
    public void postInit (FMLPostInitializationEvent event) {

    }

    @Mod.EventHandler
    public void stopping (FMLServerStoppingEvent event) {
        
    }

}
