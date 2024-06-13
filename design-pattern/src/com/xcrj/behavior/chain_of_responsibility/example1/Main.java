package com.xcrj.behavior.chain_of_responsibility.example1;

public class Main {
    public static void main(String[] args) {
        Logger chainOfLogger = ChainFactory.getChainOfLogger();
        chainOfLogger.log(Logger.INFO, "This is info level information");
        chainOfLogger.log(Logger.ERROR, "This is error level information");
    }
}
