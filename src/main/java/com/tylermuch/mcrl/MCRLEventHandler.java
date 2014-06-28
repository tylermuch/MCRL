package com.tylermuch.mcrl;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class MCRLEventHandler implements SerialPortEventListener{

    private static boolean hasMoved = false;
    private static enum DIRECTION { FORWARD, BACKWARD, LEFT, RIGHT, JUMP}
    private static DIRECTION dir = null;


    @Override
    public void serialEvent (SerialPortEvent serialPortEvent) {
        switch (serialPortEvent.getEventType()) {
            case SerialPortEvent.RXCHAR:
                System.out.print("RXCHAR [" + serialPortEvent.getEventValue() + "]: ");
                byte b = -1;
                try {
                    b = MCRLMod.serial.readBytes(1)[0];
                    System.out.println(b);
                } catch (SerialPortException e) {
                    e.printStackTrace();
                }

                switch (b) {
                    case 0:
                        System.out.println("Received FORWARD");
                        dir = DIRECTION.FORWARD;
                        hasMoved = true;
                        break;
                    case 1:
                        System.out.println("Received BACKWARD");
                        dir = DIRECTION.BACKWARD;
                        hasMoved = true;
                        break;
                    case 2:
                        System.out.println("Received LEFT");
                        dir = DIRECTION.LEFT;
                        hasMoved = true;
                        break;
                    case 3:
                        System.out.println("Received RIGHT");
                        dir = DIRECTION.RIGHT;
                        hasMoved = true;
                        break;
                    case 4:
                        System.out.println("Received JUMP");
                        dir = DIRECTION.JUMP;
                        hasMoved = true;
                        break;
                    default:
                        System.out.println("Received something else.");
                }

                break;
            default:
        }
    }

    @SubscribeEvent
    public void onPlayerTick (TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            if (hasMoved) {
                System.out.println("HAS MOVED");
                hasMoved = false;
                event.player.jump();

            }
        }
    }
}
