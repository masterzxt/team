# 项目描述
1. business-common 枚举和常量以及bean （bean层） 
1. business-dao 对数据库操作 moudle   （dao 层）
1. business-service 业务逻辑处理      （业务处理层）
1. business-web 对外开放的服务         (控制层，服务层)

# 项目运行
* maven 生成对应的jar 和 war 包 
```$xslt
 maven clean install
```
* business-web 运行 Application.main [ps:vm options 加上： -Dspring.profiles.active=dev]
![示例图](http://7xo6kd.com1.z0.glb.clouddn.com/upload-ueditor-image-20170711-1499756378710052966.png)
```$xslt
http://localhost:18888
```
* swagger API
```$xslt
http://localhost:18888/swagger-ui.html
```