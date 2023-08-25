package com.xcrj.creation.prototype.example0;

/**
 * 火神
 */
public class FireGod implements Cloneable {
    public FireGod() {
        System.out.println("火神本体");
    }

    @Override
    protected FireGod clone() {
        FireGod fireGod = null;
        try {
            fireGod = (FireGod) super.clone();
            System.out.println("克隆火神分身");
        } catch (CloneNotSupportedException cnsex) {
            System.out.println("克隆火神分身失败");
        }

        return fireGod;
    }
}
