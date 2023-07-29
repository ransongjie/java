import com.xcrj03.Apple;
import com.xcrj03.impl.YanTaiApple;

module module1 {
    // 导出包
    exports com.xcrj01;
    exports com.xcrj03;
    // 接口的服务提供类
    provides Apple with YanTaiApple;
}
