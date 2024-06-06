package com.xcrj;

/**
 * jdk 14
 * 自动break，switch的结果赋值给变量
 * switch 表达式和箭头标签
 * switch case -> 替代 switch case : break
 * Java 14 中成为正式功能
 */
public class Switch1 {
    //赋值不便
    //手动break
    public void old1(int number) {
        String a = "";
        switch (number) {
            case 0:
                a = "no gifts";
                break;
            case 1:
                a = "only one gift";
                break;
            default:
                a = number + " gifts";
        }
    }

    //赋值方便
    //自动break
    public void new1(int number) {
        String a = switch (number) {
            case 0 -> "no gifts";
            case 1 -> "only one gift";
            default -> number + " gifts";
        };
    }

    //复杂逻辑可使用 yield
    public void new2(int number) {
        String a = switch (number) {
            case 0 -> "no gifts";
            case 1 -> "only one gift";
            default -> {
                if (number < 0) {
                    yield "no gifts";
                } else {
                    yield number + " gifts";
                }
            }
        };
    }

    //原switch也可以使用yield
    public String old2(int number) {
        return switch (number) {
            case 0:
                yield "no gifts";
            case 1:
                yield "only one gift";
            default: {
                if (number < 0) {
                    yield "no gifts";
                } else {
                    yield number + " gifts";
                }
            }
        };
    }
}
