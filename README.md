# lingyunProjectRapidFramework

#### 项目介绍

凌云网络科技软件项目脚手架

基于Google rapid-framework,它是一个java web快速开发脚手架。

本项目是为公司的总项目框架建立可拔插的项目组件，公司项目地址为： https://gitee.com/lingyuntec/lingyunOfficalSite

rapid-framework官方地址：http://rapid-framework.sourceforge.net 需要翻墙访问。

本项目采用gradle构建，希望gradle能在脚手架功能上提供一些帮助。

非常遗憾的一点，在windows下由于不支持特殊字符的文件夹，比如${className_dir?uncap_first}，从GitHub pull的时候会失败。
这个应该有办法解决，不过目前还是用Mac系统开发吧。

com.googlecode.rapid-framework使用4.0.x版本，与3.x版本相比有一些变化。我希望本项目以zero config为目标，去掉xml和properties配置。

