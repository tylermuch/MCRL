package com.tylermuch.mcrl.util;

import jssc.SerialPort;
import jssc.SerialPortException;

public class MicroComms {

    public static void sendByte (final byte toSend, final SerialPort dest) {
        (new Thread() {
            public void run() {
                try {
                    dest.writeByte(toSend);
                } catch (SerialPortException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
