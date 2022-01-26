![image](https://img2022.cnblogs.com/blog/1673511/202201/1673511-20220126172609399-2091073804.png)

搜索关键词：红外 LED 吸顶灯 遥控器 变色 智能 灯 具 无极调光 灯泡遥控手柄

这种遥控器的结构较为简单，其中含有一个不含识别码的记忆模块TL HW12（拆开壳就看得到了），因此可以通过接收器完全获取其中的按键发送的红外数据。在这里我使用了arduino进行截取，获取了以下的红外信号数据。所有信号都已经对应图进行了标注。
下属数据截止本文发布时实测有效

```java
private int[] rawData_nightlight = {8730,4420, 530,570, 530,570, 530,570, 480,620, 480,570, 530,570, 530,570, 530,1620, 530,1670, 480,1670, 530,1620, 530,1620, 530,1670, 480,1670, 530,1620, 530,570, 530,1620, 530,570, 530,1620, 530,570, 530,570, 530,570, 530,520, 530,620, 480,620, 480,1670, 480,620, 480,1670, 480,1670, 530,1620, 530,1670, 480,1620, 530};  		
private int[] rawData_On = {8730,4420, 480,570, 530,570, 530,570, 530,570, 480,620, 480,620, 480,570, 530,1670, 480,1670, 530,1620, 530,1620, 530,1670, 480,1670, 530,1620, 530,1620, 530,570, 530,1670, 480,570, 530,570, 530,570, 530,570, 480,620, 480,620, 480,570, 530,570, 530,1620, 530,1670, 480,1670, 530,1620, 530,1620, 530,1670, 480,1670, 530};  				
private int[] rawData_Off = {8730,4420, 530,570, 480,620, 480,620, 480,570, 530,570, 530,570, 530,570, 480,1670, 530,1620, 530,1670, 480,1670, 530,1620, 530,1620, 530,1670, 480,1670, 530,570, 480,1670, 530,570, 530,570, 480,620, 480,570, 530,570, 530,570, 530,570, 530,570, 480,1670, 530,1620, 530,1620, 530,1670, 480,1670, 530,1620, 530,1670, 480};  				
private int[] rawData_arrow_up = {8730,4420, 480,570, 530,570, 530,570, 530,570, 530,570, 530,570, 480,620, 480,1670, 480,1670, 530,1620, 530,1670, 480,1670, 530,1620, 530,1620, 530,1670, 480,620, 480,570, 530,1670, 480,620, 480,570, 530,1670, 480,570, 530,570, 530,570, 530,1620, 530,570, 530,1670, 480,1670, 480,620, 480,1670, 530,1620, 530,1620, 530};  		
private int[] rawData_arrow_left = {8730,4420, 530,570, 530,570, 480,620, 480,570, 530,570, 530,570, 530,570, 530,1620, 530,1620, 530,1670, 480,1670, 530,1620, 530,1620, 530,1670, 480,1670, 530,570, 480,620, 480,1670, 530,1620, 530,1620, 530,1670, 480,620, 480,570, 530,570, 530,1670, 480,570, 530,570, 530,570, 530,570, 480,1670, 530,1620, 530,1670, 480};  		
private int[] rawData_lightness = {8730,4370, 530,620, 480,570, 530,570, 530,570, 530,570, 530,570, 480,620, 480,1670, 480,1670, 530,1620, 530,1670, 480,1670, 530,1620, 530,1620, 530,1670, 480,620, 480,1670, 480,1670, 530,1620, 530,1670, 480,1620, 580,1620, 530,1620, 530,1670, 480,620, 480,570, 530,570, 530,570, 530,570, 480,620, 480,620, 480,570, 530};  		
private int[] rawData_arrow_right = {8730,4420, 480,620, 480,570, 530,570, 530,570, 530,570, 530,570, 480,620, 480,1670, 480,1670, 530,1620, 530,1620, 530,1670, 530,1620, 530,1620, 530,1670, 480,620, 480,570, 530,1670, 480,620, 480,570, 530,1670, 480,570, 530,570, 530,570, 530,1620, 530,570, 530,1670, 480,1670, 480,620, 480,1670, 480,1670, 530,1620, 530};  		
private int[] rawData_arrow_down = {8780,4370, 530,570, 530,570, 530,570, 530,570, 480,620, 480,570, 530,570, 530,1670, 480,1670, 480,1670, 530,1620, 530,1670, 480,1670, 480,1670, 530,1620, 530,570, 530,570, 530,1620, 530,1670, 480,1670, 530,1620, 530,570, 530,570, 480,620, 480,1670, 530,570, 480,620, 480,570, 530,570, 530,1670, 480,1670, 480,1670, 530};  		
private int[] rawData_auxiliary1 = {8730,4420, 530,570, 530,570, 530,570, 480,620, 480,570, 530,570, 530,570, 530,1620, 530,1620, 530,1670, 480,1670, 530,1620, 530,1670, 480,1670, 530,1620, 530,570, 530,570, 530,1620, 530,570, 530,1620, 530,1670, 480,570, 530,570, 530,570, 530,1620, 530,570, 530,1620, 530,570, 530,570, 530,1620, 530,1670, 480,1670, 480};  		
private int[] rawData_auxiliary2 = {8730,4370, 530,620, 480,570, 530,570, 530,570, 530,570, 480,620, 480,620, 480,1670, 480,1670, 530,1620, 530,1670, 480,1670, 480,1670, 530,1620, 530,1670, 480,570, 530,570, 530,1670, 480,570, 530,1670, 480,1670, 480,620, 480,620, 480,570, 530,1670, 480,570, 530,1670, 480,620, 480,570, 530,1670, 480,1670, 530,1620, 530};  		
private int[] rawData_auxiliary3 = {8730,4420, 530,570, 530,570, 530,570, 530,570, 480,620, 480,570, 530,570, 530,1670, 480,1670, 480,1670, 530,1620, 530,1670, 480,1670, 480,1670, 530,1620, 530,570, 530,570, 530,1620, 530,570, 530,1670, 480,1670, 480,620, 480,570, 530,570, 530,1670, 480,570, 530,1670, 480,620, 480,570, 530,1670, 480,1670, 530,1620, 530};  		
private int[] rawData_warmth_decrease = {8730,4420, 530,570, 530,570, 480,620, 480,620, 480,570, 530,570, 530,570, 530,1620, 530,1670, 480,1670, 480,1670, 530,1620, 530,1670, 480,1670, 530,1620, 530,570, 530,1620, 530,1670, 480,570, 530,570, 530,570, 530,570, 530,570, 480,620, 480,620, 480,570, 530,1670, 480,1670, 480,1670, 530,1620, 530,1670, 480,1670, 480};  	
private int[] rawData_warmth_increase = {8730,4370, 530,620, 480,570, 530,570, 530,570, 530,570, 530,570, 480,620, 480,1670, 480,1670, 530,1620, 530,1670, 480,1670, 530,1620, 530,1620, 530,1670, 480,620, 480,570, 530,1670, 480,620, 530,520, 530,570, 530,570, 530,570, 530,570, 480,1670, 530,570, 480,1670, 530,1620, 530,1670, 480,1670, 530,1620, 530,1620, 530};  	
private int[] rawData_segment = {8730,4420, 480,570, 530,570, 530,570, 530,570, 480,620, 480,570, 530,570, 530,1670, 480,1670, 480,1670, 530,1620, 530,1670, 480,1670, 480,1670, 530,1620, 530,570, 530,570, 530,1620, 530,1670, 480,570, 530,570, 530,570, 530,570, 480,620, 480,1670, 530,570, 480,620, 480,1670, 480,1670, 530,1620, 530,1670, 480,1670, 480};  			
```


可以通过上述红外信号设计一个红外发射app，用于取代遥控器。