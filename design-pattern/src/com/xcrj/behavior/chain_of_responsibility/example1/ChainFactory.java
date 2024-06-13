package com.xcrj.behavior.chain_of_responsibility.example1;

public class ChainFactory {
    public static Logger getChainOfLogger(){
        Logger infoLogger=new InfoLogger(Logger.INFO);
        Logger debugLogger=new DebugLogger(Logger.DEBUG);
        Logger errorLogger=new ErrorLogger(Logger.ERROR);
        
        errorLogger.setNextLogger(debugLogger);
        debugLogger.setNextLogger(infoLogger);
        return errorLogger;
    }
}
