# dt-front

## 如何更便捷的对接（以home为例）
> 注：以下步骤皆在dt-front目录下运行命令，配置好之后以后每次启动步骤3即可，步骤1酌情执行

#### 1.下载（更新）所有项目需要的包（初次或者跑起来提示缺少某模块时执行）：
npm run i-all

#### 2.配置好服务器代理：
修改dev.proxy.config文件
``` js
/*协商好端口，以免修改后发生冲突*/
{
    home: {
        host: 'localhost',    /*后台服务器地址*/
        port: 8010,           /*后台服务器端口*/
        path: '',             /*后台服务器url根路径,一般为空*/
        test: 'vue2-home/api' /*此参数后台不使用，无需修改*/
    },
    admin: {
        host: 'localhost',
        port: 8011,
        path: '',
        test: 'vue2-admin/api'
    }
}
```

#### 3.启动home的前端项目：
npm run dev-home

#### 4.测试是否有效（初次配置后需测试一下）：
以后台接口http://localhost:8010/index/test 为例 （get请求方便测试）<br>
在浏览器打开http://localhost:8000/api/index/test （看显示的内容是否为接口返回内容）<br>
原理是将home（ http://localhost:8000/api/ ）的请求代理到后台（ http://localhost:8010/ ）,后面路径一致


## 当前根目录命令

> 初始化所有前端项目（home、admin）<br />
npm run i-all

> 启动home项目的服务（prod）<br />
npm run home

> 启动admin项目的服务（prod）<br />
npm run admin

> 启动home项目的服务（dev）<br />
npm run dev-home

> 启动admin项目的服务（dev）<br />
npm run dev-admin

> 启动所有项目的服务（prod）<br />
npm run all

> 注：启动服务需先打包项目


## 项目命令（先cd进入项目根目录）

> 以开发状态启动项目<br />
npm run dev

> 打包项目<br />
npm run build

> 检测代码错误<br />
npm run lint


## 跑起来
#### 0.准备：
[安装node(最好是v7.0.0以上)](http://nodejs.cn/)，安装好后到dt-front按序执行以下命令

#### 1.下载所有项目需要的包：
npm run i-all

#### 2.cd到home目录打包项目(admin同)：
npm run build

#### 3.cd ../到dt-front目录把服务跑起来：
npm run all

#### 其他的参考命令列表
