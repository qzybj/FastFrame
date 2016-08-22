# FastliBrary

用于快速搭建App，包含基本框架部分

#Log:
none

# include introduction
1.main UI
2.common adapter view fragment
3.build channel apk call gradle command " assembleRelease"
4.

# specification
1.项目目录结构如下：
	--core		核心(包含工具类，自定义View)
	--common	通用(包含工具类，自定义View)
	--net		网络请求工具
	--aosp		开源项目
	--module	模块化功能
	--MainActivity	主界面
	--CApplication	初始化Application

--Res资源文件命名规则：
所属项目_所属类别_所属模块_所属功能(本项目用不需要添加所属项目)

--module下功能模块包名规则：
   interfaces		接口定义
   broadcastreceivers	广播
   annotation		注解定义
   bean			模型
   ui			UI界面
   view			自定义View
   adapter		适配器
   utils		工具类

--module下功能模块包名规则：
