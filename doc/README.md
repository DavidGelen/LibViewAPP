
https://github.com/Justson/AgentWeb

https://github.com/xudjx/webprogress

---

Canvas 的辅助类方法：范围裁切和几何变换

使用不同的绘制方法来控制遮盖关系

Canvas.drawColor(@ColorInt int color) 颜色填充,在整个绘制区域统一涂上指定的颜色。

drawPath() 一般是在绘制组合图形时才会用到的

https://github.com/HusterYP/UI

---

## 父 View 的尺寸限制

由来：开发者的要求（布局文件中 layout_ 打头的属性）经过父 View 处理计算后的更精确的要求；
限制的分类：

- UNSPECIFIED：不限制

- AT_MOST：限制上限

- EXACTLY：限制固定值

全新定义自定义 View 尺寸的方式：

1，重新 onMeasure()，并计算出 View 的尺寸；

2，使用 resolveSize() 来让子 View 的计算结果符合父 View 的限制（当然，如果你想用自己的方式来满足父 View 的限制也行）。















