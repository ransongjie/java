package com.xcrj.behavior.chain_of_responsibility.example1;

//抽象类
public abstract class Logger {
    public static final int INFO = 1;
    public static final int DEBUG = 2;
    public static final int ERROR = 3;

    protected Logger nextLogger;
    protected int level;//protected 子类中可以使用this.level赋值

    public void setNextLogger(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void log(int level, String message) {
        //符合level条件进行输出
        if (level >= this.level) {
            this.write(message);
        }
        //否则交给下1个logger
        if (this.nextLogger != null) {
            this.nextLogger.log(level, message);
        }
    }

    abstract protected void write(String message);
}
