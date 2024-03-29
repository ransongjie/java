```
/**
 * jdk 17
 * 控制类的继承
 * 为了避免开发人员错误地重用一些代码，我们需要对类的继承扩展能力进行限制，以确系统的可控。
 * sealed 子类传递了密封性；final 的子类确认密封性；non-sealed 显式放弃密封性
 *
 * 密封类DogService中必须permits DogServiceImpl1, DogServiceImpl2, DogServiceImpl3，所有实现类
 */
```