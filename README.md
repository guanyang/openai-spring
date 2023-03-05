### 使用步骤

#### 1、导入依赖

```xml
<dependency>
  <groupId>org.gy.framework</groupId>
    <artifactId>openai-spring-boot-starter</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

#### 2、配置秘钥

在application.yml中配置如下参数：

```yml
openai:
  token: 你的秘钥
  timeout: 5000 // 超时时间
```

