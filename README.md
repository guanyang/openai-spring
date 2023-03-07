### 简介
- 该工程是集成`ChatGPT`的SDK实现的starter，应用接入只需要进入`openai-spring-boot-starter`即可
- 在yml文件中配置自己的openai的`apiKey`即可快速试用

### 工程模块说明
- openai-spring-boot-starter: starter工具包，可以直接引入使用
- openai-starter-test: 测试工程，直接直接打包docker镜像使用

### 使用步骤
#### 编译打包
- 如果提示`launcher-maven-plugin`插件不存在，请下载[spring-launcher-parent](https://github.com/guanyang/spring-launcher-parent)工程源码
- 进入launcher源码根目录，执行如下命令，将插件生成到本地
```
mvn clean install -Dmaven.test.skip=true
```

#### 导入依赖

```xml
<dependency>
  <groupId>org.gy.framework</groupId>
    <artifactId>openai-spring-boot-starter</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

#### 配置秘钥

在application.yml中配置如下参数：

```yml
openai:
  token: 你的秘钥
  timeout: 5000 // 超时时间
```
#### 使用示例
- 参考`org.gy.framework.openai.OpenAiTest`示例方法
```java
    @Test
    public void testQA(){
        List<CompletionChoice> questionAnswer = OpenAiUtils.getQuestionAnswer("重庆今天的天气怎么样？");
        for (CompletionChoice completionChoice : questionAnswer) {
            System.out.println(completionChoice.getText());
        }
    }
```
### 容器化部署
#### mvn打包
```shell
$> APP_MODE_NAME=openai-starter-test
$> mvn -T 4 -B -pl ${APP_MODE_NAME} -am clean package -Dmaven.test.skip=true -U -e
```
- 将`APP_MODE_NAME`替换成对应模块名称

#### 构建docker镜像
```shell
$> APP_MODE_NAME=openai-starter-test
$> WORKSPACE=$PWD
$> cd ${WORKSPACE}/${APP_MODE_NAME}
$> DOCKER_TAG_NAME=guanyangsunlight/openai-spring:openai-starter-test-0.0.1
$> docker build -t ${DOCKER_TAG_NAME} --pull=true --file=${WORKSPACE}/${APP_MODE_NAME}/target/Dockerfile .
```
- 将`APP_MODE_NAME`替换成对应模块名称
- `WORKSPACE`自动读取当前路径，需要切换到工程根路径，或者自行指定路径
- 将`DOCKER_TAG_NAME`替换成自定义的`TAG`

#### openai-starter-test启动说明
- 启动命令：`docker run -dt --name openai-starter-test -p 8088:8088 guanyangsunlight/openai-spring:openai-starter-test-0.0.1 bash -c "/usr/local/bin/launcher.sh start -n openai-starter-test -jo '-Dfile.encoding=UTF-8 -Duser.timezone=Asia/Shanghai -XX:InitialRAMPercentage=50.0 -XX:MaxRAMPercentage=75.0 -XX:+UseG1GC -XX:MaxGCPauseMillis=150' -a '--server.port=8088 --openai.token=xxx'"`
- 探活入口：`http://127.0.0.1:8088/hello`
- openai token配置优先级：`--openai.token=xxx` > `-DOPENAI_TOKEN=xxx` > 系统变量`OPENAI_TOKEN=xxx`

#### api访问说明
- 访问入口：http://127.0.0.1:8088/api/openai/OpenAi01?text=重庆的天气
- 返回示例
```
{
    "requestId":"29e7ed781aad4d06ad17c3946023c160",
    "error":0,
    "msg":"success",
    "data":[
        {
            "text":" 根据中国天气网的预报，重庆明天的天气将是晴转多云，最高气温31℃，最低气温20℃，风力3-4级。",
            "index":0,
            "logprobs":null,
            "finish_reason":"stop"
        }
    ]
}
```
