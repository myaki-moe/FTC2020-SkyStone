# 项目概述


 FTC7860队伍2020赛季使用程序，包括自动程序和手动程序。

如果您是新入门FTC的队伍，希望这个项目能给您带来些许帮助。

---

# 如何使用

### 下载项目

使用GIT下载：https://github.com/Ny-Cat/FTC2020-SkyStone.git

或者压缩包：点击网页右侧绿色Clone or download按钮

---

### 下载最新版JDK

官方下载：https://www.oracle.com/technetwork/java/javase/downloads/index.html

注意：一定要下载JDK，安装时选默认路径以后出问题的几率会小些

---

### 下载Android Studio

官方下载（可能会被墙）：https://developer.android.google.cn/studio/

国内下载：http://www.android-studio.org/

---

### 使用Android Studio打开下载的项目文件夹

开始愉快的开发（编程 × 写BUG √）

---



# 项目简单介绍

由于新赛季刚开始，目前主要工作时设计机械结构，所以程序方面只能先行编写底层框架，等待机械结构完成再与机械联调。

# 目前设想

### 电机

底盘使用万向轮
4个电机
使用双环串级PID控制（位置环和速度环）
加入IMU消除打滑导致的车头角度偏移

吸纳使用万向轮
2个电机
使用单级PID速度环控制

抬升使用丝杆
1个电机
使用单级PID位置环控制

抬机械臂
1个电机

### 舵机

夹取石块
1个舵机

移动地基
2个舵机

旋转石头
1个舵机

放置顶石
1个舵机

### 传感器

2m距离传感器
IMU
颜色传感器
触碰传感器
摄像头

---

# 【完】
