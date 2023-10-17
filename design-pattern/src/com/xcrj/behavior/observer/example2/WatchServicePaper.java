package com.xcrj.behavior.observer.example2;

import com.sun.nio.file.SensitivityWatchEventModifier;

import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

import static com.sun.jmx.mbeanserver.Util.cast;

/**
 * 观察者模式/目录监听服务
 * -WatchService观察者
 * -Watchable目标对象
 * <p>
 * Watchable目标对象：
 * -注册观察者和多个事件
 * -事件就是目标对象的状态
 * - Paths.get(baseDir).register(watchService,new WatchEvent.Kind[]{},SensitivityWatchEventModifier.HIGH);
 * <p>
 * WatchEvent：
 * -文件创建，文件删除，文件修改
 * <p>
 * WatchService观察者：
 * -通过守护线程定期轮询检测事件是否发生
 * - WatchKey key = watchService.poll(3, TimeUnit.SECONDS);
 */
public class WatchServicePaper {
    public static void main(String[] args) throws Exception {
        //监听目录(项目resource/config目录)，即运行时classes/config目录
//        String baseDir = Thread.currentThread().getContextClassLoader().getResource("config").getPath();
        /**
         * 目标
         */
        //xxx/design-parttern
        String baseDir = System.getProperty("user.dir");
        //监听文件
        String targetFile = "xcrj.txt";

        /**
         * 监听器
         */
        //构造监听服务
        WatchService watcher = FileSystems.getDefault().newWatchService();

        /**
         * 给目标注册监听器
         */
        //！！！创建、修改、删除事件，高频率(每隔2秒，默认10秒)监听
        Paths.get(baseDir).register(watcher, new WatchEvent.Kind[]{
                        StandardWatchEventKinds.ENTRY_CREATE,
                        StandardWatchEventKinds.ENTRY_MODIFY,
                        StandardWatchEventKinds.ENTRY_DELETE},
                SensitivityWatchEventModifier.HIGH);

        while (true) {
            //！！！监听器每隔3秒拉取key
            WatchKey key = watcher.poll(3, TimeUnit.SECONDS);

            if (key == null) {
                continue;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                //监听事件类型
                WatchEvent.Kind kind = event.kind();

                //跳过异常事件
                if (kind == StandardWatchEventKinds.OVERFLOW) {
                    continue;
                }

                //获取监听Path
                Path path = cast(event.context());

                //跳过非目标文件
                if (!targetFile.equals(path.toString())) {
                    continue;
                }

                //文件创建
                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    System.out.printf("文件创建事件, type:%s  path:%s \n", kind.name(), path);
                    continue;
                }

                //文件删除
                if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                    System.out.printf("文件删除事件, type:%s  path:%s \n", kind.name(), path);
                    continue;
                }


                Path fullPath = Paths.get(baseDir, path.toString());
                //读取文件内容
                String content = new String(Files.readAllBytes(fullPath), Charset.forName("utf8"));

                //输出事件类型、文件路径及内容
                System.out.printf("type:%s  path:%s  content:%s\n", kind.name(), path, content);
            }

            //处理监听key后，监听key需要复位，便于下次监听
            key.reset();
        }
    }
}

