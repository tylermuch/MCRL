package com.tylermuch.mcrl;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class MCRLEventHandler implements SerialPortEventListener{

    @Override
    public void serialEvent (SerialPortEvent serialPortEvent) {
        switch (serialPortEvent.getEventType()) {
            case SerialPortEvent.RXCHAR:
                System.out.print("RXCHAR [" + serialPortEvent.getEventValue() + "]: ");
                try {
                    System.out.println(MCRLMod.serial.readBytes(1)[0]);
                } catch (SerialPortException e) {
                    e.printStackTrace();
                }
                break;
            case SerialPortEvent.RXFLAG:
                System.out.println("RXFLAG");
                break;
            default:
        }
    }
}
