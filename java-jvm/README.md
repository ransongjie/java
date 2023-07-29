# java命令行
javac
```shell
javac -d 'src/com/xcrj/test/' -encoding 'UTF-8' 'src/com/xcrj/test/Test1.java'
javac -d 'D:/00workspace_github/jvm-basic/src/com/xcrj/test/' -encoding 'UTF-8' 'D:/00workspace_github/jvm-basic/src/com/xcrj/test/Test1.java'
```
java
```shell
java -classpath 'src/com/xcrj/test/' com.xcrj.test.Test1
java -classpath 'D:/00workspace_github/jvm-basic/src/com/xcrj/test/' com.xcrj.test.Test1
java -Xmx10m -Xms10m -classpath 'D:/00workspace_github/jvm-basic/src/com/xcrj/test/' com.xcrj.test.Test1
```
jps
```shell
# 查看在运行的java进程
jps
```
jstat
```shell
# 查看java进程内存情况
jstat -gc java进程id号
```
# 环境变量
## jdk8
```
JAVA_HOME
D:\00ProgramFiles\jdk\jdk1.8.0_301

CLASSPATH 
.:${JAVA_HOME}\lib\dt.jar:${JAVA_HOME}\lib\tools.jar

path
%JAVA_HOME%\bin
%JAVA_HOME%\jre\bin
```
## jdk11
```
JAVA_HOME
D:\00ProgramFiles\jdk\jdk-11.0.18

CLASSPATH 
.:${JAVA_HOME}\lib\dt.jar:${JAVA_HOME}\lib\tools.jar

path
%JAVA_HOME%\bin
%JAVA_HOME%\jre\bin
```
## jdk17
```
JAVA_HOME
D:\00ProgramFiles\jdk\jdk-17.0.6

path
%JAVA_HOME%\bin
```
# 调优
1. 合适GC
2. 合适Runtime
# 切换GC test/Test1.java
## jdk8
```shell
javac -d 'src/com/xcrj/test/' -encoding 'UTF-8' 'src/com/xcrj/test/Test1.java'

# 默认
# -XX:+UseParallelGC=Parallel Scavenge(新生代) + Serial Old(老年代)
java -XX:+PrintCommandLineFlags -classpath 'src/com/xcrj/test/' com.xcrj.test.Test1

# -XX:+UseConcMarkSweepGC, -XX:+UseParNewGC. CMS=CMS(老年代) + ParNew(新生代)
java -XX:+UseConcMarkSweepGC -XX:+PrintCommandLineFlags -classpath 'src/com/xcrj/test/' com.xcrj.test.Test1

# -XX:+UseG1GC
java -XX:+UseG1GC -XX:+PrintCommandLineFlags -classpath 'src/com/xcrj/test/' com.xcrj.test.Test1

# 不支持, 建议jdk17使用
java -XX:+UseZGC -XX:+PrintCommandLineFlags -classpath 'src/com/xcrj/test/' com.xcrj.test.Test1

# -XX:+UseSerialGC=Serial + Serial Old
# -XX:+UseSerialGC
java -XX:+UseSerialGC -XX:+PrintCommandLineFlags -classpath 'src/com/xcrj/test/' com.xcrj.test.Test1

# -XX:+UseParNewGC=ParNew + Serial Old
# -XX:+UseParNewGC
java -XX:+UseParNewGC -XX:+PrintCommandLineFlags -classpath 'src/com/xcrj/test/' com.xcrj.test.Test1

# -XX:+UseParallelGC=Parallel Scavenge + Parallel Old
# -XX:+UseParallelGC
java -XX:+UseParallelGC -XX:+PrintCommandLineFlags -classpath 'src/com/xcrj/test/' com.xcrj.test.Test1

# -XX:+UseParallelOldGC=Parallel Scavenge + Parallel Old
# -XX:+UseParallelOldGC
java -XX:+UseParallelOldGC -XX:+PrintCommandLineFlags -classpath 'src/com/xcrj/test/' com.xcrj.test.Test1
```
## jdk11
```shell
javac -d 'src/com/xcrj/test/' -encoding 'UTF-8' 'src/com/xcrj/test/Test1.java'

# -XX:+UseG1GC 默认
java -XX:+PrintCommandLineFlags -classpath 'src/com/xcrj/test/' com.xcrj.test.Test1

# -XX:+UseConcMarkSweepGC
java -XX:+UseConcMarkSweepGC -XX:+PrintCommandLineFlags -classpath 'src/com/xcrj/test/' com.xcrj.test.Test1

# -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC
java -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC -XX:+PrintCommandLineFlags -classpath 'src/com/xcrj/test/' com.xcrj.test.Test1

# -XX:+UseG1GC
java -XX:+UseG1GC -XX:+PrintCommandLineFlags -classpath 'src/com/xcrj/test/' com.xcrj.test.Test1

# experimental, 建议jdk17使用
java -XX:+UseZGC -XX:+PrintCommandLineFlags -classpath 'src/com/xcrj/test/' com.xcrj.test.Test1
```
## jdk17
```shell
javac -d 'src/com/xcrj/test/' -encoding 'UTF-8' 'src/com/xcrj/test/Test1.java'

# -XX:+UseG1GC 默认
java -XX:+PrintCommandLineFlags -classpath 'src/com/xcrj/test/' com.xcrj.test.Test1

# 不支持
java -XX:+UseConcMarkSweepGC -XX:+PrintCommandLineFlags -classpath 'src/com/xcrj/test/' com.xcrj.test.Test1

# -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC
java -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC -XX:+PrintCommandLineFlags -classpath 'src/com/xcrj/test/' com.xcrj.test.Test1

# -XX:+UseG1GC
java -XX:+UseG1GC -XX:+PrintCommandLineFlags -classpath 'src/com/xcrj/test/' com.xcrj.test.Test1

# -XX:+UseZGC
java -XX:+UseZGC -XX:+PrintCommandLineFlags -classpath 'src/com/xcrj/test/' com.xcrj.test.Test1
```
# 调整Runetime (运行时数据区)
