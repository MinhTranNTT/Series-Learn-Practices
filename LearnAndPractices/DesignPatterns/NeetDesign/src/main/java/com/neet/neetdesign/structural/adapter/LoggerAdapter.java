package com.neet.neetdesign.structural.adapter;

public class LoggerAdapter implements JsonLogger {
    private final XmlLogger xmlLogger;

    public LoggerAdapter(XmlLogger xmlLogger) {
        this.xmlLogger = xmlLogger;
    }

    @Override
    public void logMessage(String message) {
        xmlLogger.log(message);
    }
}
