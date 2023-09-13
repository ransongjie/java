package com.xcrj.gen;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        generate(300 * 10000);
    }

    private static String rand36Str() {
        long time = System.currentTimeMillis();
        int random = (int) (Math.random() * Integer.MAX_VALUE);
        UUID uuid = new UUID(time, random);//随机种子
        return uuid.toString();
    }

    private static String rand36Str(int num) {
        StringBuilder sb = new StringBuilder();
        UUID uuid;
        for (int i = 0; i < num; i++) {
            uuid = UUID.randomUUID();
            sb.append(uuid.toString());
        }
        return sb.toString();
    }

    private static void generate(int size) {
        String row = "INSERT INTO researcher(`code`,`name`,`title`,`location`) VALUE(%s);";
//        System.out.println(String.format(sql,IdWorker.getId()));
        String path = "./test_slow_researcher.sql";
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (
                FileOutputStream fos = new FileOutputStream(path);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
        ) {
            for (int i = 0; i < size; i++) {
                StringBuilder sb = new StringBuilder();
                String code = rand36Str(1);
                String name = rand36Str(1);
                String title = rand36Str(2);
                String location = rand36Str(3);
                sb.append("'").append(code).append("'").append(",")
                        .append("'").append(name).append("'").append(",")
                        .append("'").append(title).append("'").append(",")
                        .append("'").append(location).append("'");

                bos.write(String.format(row, sb.toString()).getBytes());

                if (i < size - 1) {
                    bos.write("\n".getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
