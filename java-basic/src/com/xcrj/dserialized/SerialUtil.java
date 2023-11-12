package com.xcrj.dserialized;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerialUtil {
    public <T> byte[] serialize(T obj) {
        try (
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);) {
            oos.writeObject(obj);
            oos.flush();
            return bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public <T> byte[] serializeToFile(T obj) {
        try (
                FileOutputStream fos = new FileOutputStream(new File("user"));
                ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(obj);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @SuppressWarnings("unchecked")
    public <T> T deserialize(byte[] data) {
        try (
                ByteArrayInputStream bis = new ByteArrayInputStream(data);
                ObjectInputStream ois = new ObjectInputStream(bis);) {
            return (T) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> T deserializeFromFile(Class<T> clazz) {
        try (
                FileInputStream fis = new FileInputStream(new File("user"));
                ObjectInputStream ois = new ObjectInputStream(fis);) {
            return (T) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}