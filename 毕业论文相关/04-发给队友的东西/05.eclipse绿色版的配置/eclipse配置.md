# eclipse配置

[TOC]

## 1.代码自动补全

打开 Eclipse -> Window -> Perferences
找到 Java -> Editor -> Content Assist
在Auto Activation 段中设置 Auto activation triggers for Java的内容为（不区分大小写）：

```
.abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ
```

<img src="https://pic1.zhimg.com/v2-03ffe67ac560682fbafc660ff13ba04c_r.jpg" alt="preview"  />

## 2.去除空格提示

把这个jar包覆盖到eclipse/plugin下，替换原有的同名jar包即可。

里面还有原先的同名jar包，万一复制之后出了问题，把文件夹里这个jar包复制进去，就能恢复原状。

如果出了问题，除了复制原先的jar之后，再进行的操作我放在文档最后面说（一般不会出问题的）

## 3.调整字体大小和背景颜色

### 调整字体大小：

推荐用Consola New字体，14号大小，加粗格式

**1.java编辑器文本字体：**

Eclipse->Windows[窗口]->Preferences[首选项]->General[常规]->Appearance[外观]->Colors  and Fonts[颜色和字体]->Java->Java Editor Text Font[JAVA编辑器文本字体]

**2.控制台字体：**

打开window - preferences-- general - appearance - colors and fonts --debug - console font 即可设置

### 调整背景颜色：

windows -> perferences -> General -> Appearance

![preview](https://pic2.zhimg.com/v2-83f742fee398c3bcd1a6487475737f99_r.jpg)

## 4.设置默认jdk

window -> Preferences -> Java -> Installed JREs -> Add

![preview](https://pic2.zhimg.com/v2-5835faf19e27822496cecd3df94ab195_r.jpg)

## 附：上面去除空格出问题后的步骤

先把原先的同名jar包文件夹里面的，复制到原来的那个路径。然后执行下面的操作：

打开window->show   view，选择Plug-ins，再找到org.eclipse.jface.text，右键单击，选择import as－> Source   Project，导入完成后，在你的workspace就可以看到这个project了。如果没有src这个文件夹，说明你使用的版本中没有带源代码

详细图解（完全应用于4.7版本，其他版本基本相同）
1.首先打开window->show view，选择other，输入plug，找到Plug-ins，点击ok

![img](https://pic1.zhimg.com/80/v2-74d5e53199e6c8a2be85e4a886ae099c_720w.jpg)

2.控制台同样位置会出现Plug-ins，

![img](https://pic1.zhimg.com/80/v2-9b24715831e0027516e277a348f4ce2c_720w.jpg)

找到org.eclipse.jface.text，

![img](https://pic4.zhimg.com/80/v2-fcc43a3a4d9083815adcfba831d97df3_720w.jpg)

右键单击，选择import as－> Source Project，

3.如图

![img](https://pic3.zhimg.com/80/v2-1fad8a508c6fce563fe3a62417707bda_720w.jpg)

找到CompletionProposalPopup.java

对这个文件进行修改

使用ctrl+f查找，输入“triggers, key”(没有双引号)。

![img](https://pic1.zhimg.com/80/v2-1fd7f3923e4bdb0bc9e6e41a4affba40_720w.jpg)

点击find

![img](https://pic3.zhimg.com/80/v2-3137ef43f935b6744895b2fb2e27e9c6_720w.jpg)

修改代码块if里的内容，if里**加入（不是改成，是加入）**

```text
key!=0x20&&key!='='&&key!=';'&&key!='['&&key!='('&&
```

0x20表示空格，"("防止方法定义时出现提示选中问题

![img](https://pic1.zhimg.com/80/v2-3532f90e07a456a697abfc87eab1a214_720w.jpg)

成功修改之后即可保存

4.导出
右键点击你的workspace里的工程org.eclipse.jface.text，

![img](https://pic3.zhimg.com/80/v2-627cd522a6758105e7ab6ee9ed446012_720w.jpg)

选择Export－>Deployable plugins and fragments，

![img](https://pic4.zhimg.com/80/v2-7215e2913aebb609fd46c63e94f4fa5b_720w.jpg)

点击Next，选择Destination选项卡，选择Archive file，文件名自定义，然后Finish。

![img](https://pic3.zhimg.com/80/v2-079d2486174867fedc236bc08a7cc2ae_720w.jpg)

然后就会在你eclispe所在的目录下产生一个自定义文件名的zip，关闭eclispe，解压这个zip文件，，覆盖所有

![img](https://pic2.zhimg.com/80/v2-333a9b2b12258c9db5288a40face829d_720w.jpg)

![img](https://pic4.zhimg.com/80/v2-02d4e9b6004b801a9584b0f085ac2d5b_720w.jpg)

然后重新启动Eclipse。完成。