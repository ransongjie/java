# Spring boot 启动后执行特定方法

- 不耗时的逻辑，Bean的`@PostConstruct`方法中
- 耗时长的逻辑，若放在`@PostConstruct`方法中，请使用独立线程执行
- 初始化逻辑，`CommandLineRunner` 或 `ApplicationRunner` 的实现组件中
- `ApplicationListener<ApplicationStartedEvent>`和`ApplicationListener<ApplicationReadyEvent>`

# 执行顺序

```text
@PostConstruct
ApplicationListener<ApplicationStartedEvent>
CommandLineRunner1
CommandLineRunner2
ApplicationRunner
ApplicationListener<ApplicationReadyEvent>
MyApp running
```

# Bean中@PostConstruct

- 仅仅执行1次

# CommandLineRunner

- `@Order(value = 1)` 值越小优先级越低

# ApplicationRunner

# ApplicationListener<ApplicationStartedEvent/ApplicationReadyEvent>