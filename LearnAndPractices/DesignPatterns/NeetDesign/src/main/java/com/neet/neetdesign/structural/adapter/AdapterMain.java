package com.neet.neetdesign.structural.adapter;

public class AdapterMain {
    public static void main(String[] args) {
        JsonLogger jsonLogger = new LoggerAdapter(new XmlLogger());
        jsonLogger.logMessage("message hello message");
    }
}
