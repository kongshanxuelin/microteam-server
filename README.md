microteam微信小程序配套的服务端代码，其中客户端代码：https://github.com/kongshanxuelin/microteam
- 运行：
maven build tomcat7:run

- 修改配置app.xml
修改微信小程序配置：

```
		<!-- wx app config -->
		<item name="wx.appid" value="xxxxx"/>
		<item name="wx.seckey" value="xxxxx"/>
		<item name="wx.mchid" value="xxxx"/>
		<item name="wx.mchkey" value="xxxxx"/>
		<item name="wx.paynotify" value="xxxxxx"/>
		<item name="wx.template.notify" value="xxxxx" />
```
