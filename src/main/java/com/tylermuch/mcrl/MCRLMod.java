package com.tylermuch.mcrl;

import com.tylermuch.mcrl.util.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.*;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

@Mod(modid = MCRLMod.MODID, version = MCRLMod.VERSION)
public class MCRLMod {

    public static final String MODID = "mcrl";
    public static final String VERSION = "1.0";
    public static final String NAME = "MC RL";

    @Mod.Instance(MCRLMod.MODID)
    public static MCRLMod instance;

    public static SerialPort serial = null;
    public static String portName = null;

    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent event) {
        String[] ports = SerialPortList.getPortNames();
        if (0 == ports.length) {
            LogHelper.error("No serial ports found.");
            return;
        } else {
            portName = ports[0];
        }

        LogHelper.info("Using port: " + portName);
        serial = new SerialPort(portName);

        if (serial.isOpened()) {
            LogHelper.error(portName + " is already opened.");
            serial = null;
            return;
        }

        try {
            LogHelper.info("Opening " + portName);
            serial.openPort();
            LogHelper.info("Successfully opened " + portName);
            LogHelper.info("Setting port parameters. ");
            serial.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8,
                            SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            LogHelper.info("Done.");
        } catch (SerialPortException e) {
            e.printStackTrace();
        }

        FMLCommonHandler.instance().bus().register(new MCRLEventHandler());

    }

    @Mod.EventHandler
    public void init (FMLInitializationEvent event) {

        if (serial != null) {
            try {
                serial.addEventListener(new MCRLEventHandler());
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
        }

    }

    @Mod.EventHandler
    public void postInit (FMLPostInitializationEvent event) {

    }

    @Mod.EventHandler
    public void starting (FMLServerStartingEvent event) {
        if (serial == null) return;
        try {
            serial.writeString("World Name: " + event.getServer().getWorldName());
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }


    @Mod.EventHandler
    public void stopping (FMLServerStoppingEvent event) {
        if (serial == null) {
            return;
        }

        try {
            LogHelper.info("Closing " + portName);
            serial.closePort();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

}
