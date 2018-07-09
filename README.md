实现列表icon动画切换效果
====
在项目需求中，为实现类似于某猫首页的发现品牌专项中部分商品icon有上下切换的效果，而做出的总结：<br>
---
分析<br>
    * 1、在icon图片上下切换效果时，想到属性动画Translation(移动)效果<br>
    * 2、上下切换只有一张图片处于Translation(移动)效果<br>
    * 3、图片是通过接口网络请求返回的(本示例为简化使用的是本地图片代替)<br>

解决<br>
    * 1、针对两张图片切换效果，需要自定义控件，自定义控件ViewGroup包含两张ImageView图片，在自定义控件中管理view的开始、结束、停止动画。<br>
    * 2、针对通过网络请求返回的图片，需要确保每张图片都下载成功，之后以发送广播形式通知所有图片已下载成功。<br>
    * 3、接收到下载成功广播后，使用Handler方式处理并循环开始自定义切换效果。当一个切换效果结束后，再次调用Handler继续循环。<br><br><br><br>
![gif](https://github.com/SearchSunny/AndroidAnimationFrame/blob/master/app/src/main/gif/icon.gif)
