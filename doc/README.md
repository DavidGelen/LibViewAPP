
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

---

canvas.translate //记住是相对于当前位置位移

[学习View就看他：](http://www.gcssloop.com/customview/CustomViewIndex/)

---

## 《开发者艺术探索》

1，如果只是监听滑动，建议在onTouchEvent中处理，如果需要监听双击这种行为，那么就是用GestureDetector

2，view 的 ScrollTo/ScrollBy 方法来进行滑动时，其过程是瞬间完成的，用户体验不好

3，Scroll本身无法让View弹性滑动，他需要配合View的computeScroll来完成滑动

---

自定义View需要考虑warp_content和padding的情况，重写onMeasure和onDraw

自定义ViewGroup需要合适地处理ViewGroup的测量，布局这两个过程，并同时测量子View的测量和布局的过程，
考虑ViewGroup的padding和子元素的margin

自定义系统的控件（比如LinnerLayout,RelativeLayout,FrameLayout就不需要考虑上面的情况）

---

LibHorizontalScrollViewEx类似于ViewPager支持水平滑动的Layout，功能和ViewPager相似

VelocityTracker是一个帮助追踪触摸事件速率的追踪器，可以追踪fliinging和其他触摸手势。

---

//不让父类拦截down事件
getParent().requestDisallowInterceptTouchEvent(true);

---

### View相对屏幕的距离

View相对屏幕的距离，是指View的左上角相对于手机屏幕左上角的坐标:

int[] position = new int[2];
view.getLocationOnScreen(position);
System.out.println("(" + position[0] + "," + position[1] + ")");

如果当前Activity是普通的Activity，则用这个方法得到的position数组中的第二个参数（Y坐标值）
表示可见的状态栏的高度 + 可见的标题栏的高度 + View上端到标题栏下端的距离；

如果当前Activity是对话框式的Activity，则Y坐标值表示可见的标题栏的高度 + View上端到标题栏下端的距离。

注意：这里的“可见”表示的是能看到的，如果一个Activity中的状态栏或标题栏被隐藏了，则其高度用0表示。

getTop()、getBottom()、getLeft()和getRight()是控件初始位置距离父View容器上、下、左、右边的距离；

View.getX、View.getY是控件最后视觉位置(如果有移动则是移动过后的位置)距离父View父容器左边、上边的距离。

---

ViewGroup类的onLayout()函数是abstract型，继承者必须实现

---

- onDraw()函数一般由系统布局管理器来调用，在View第一次加载的时候会调用一次，
或者在系统认为需要重绘的时候也会被调用。当然，你也可以在程序中手动触发该View的重绘，
通过调用View的invalidate()函数或者postInvalidate()函数即可，前者用于UI线程，后者用于非UI线程。

- onDraw()的参数Canvas我们可以理解成系统提供给我们的一块内存区域，
所有的绘制都是在这块内存中进行的，绘制完成后系统会显示到屏幕中去。
该Canvas对象提供了各种绘制点、线、矩形、圆、位图的方法，基本可以满足各种绘制要求。

- drawRect函数需要提供四个坐标，其中，前两个参数代表是被绘制矩形的起始点坐标，
后两个参数则是相对于起点的斜对角坐标，注意，原点坐标（0，0）在屏幕的左上角。

- 注意，onDraw()每次被调用的时候，原来画布中的内容会被清空。

---

3.3.1：draw是比較简单的，他的作用是将View绘制到屏幕上面，View的绘制过程由如下几个步骤

- 绘制背景

- 绘制自己

- 绘制children

- 绘制装饰

**3.3.2：setwilINotDraw()**

作用：决定onDraw()方法是否会执行。
无论对于View还是ViewGroup而言，对于自定义而言，
需要调用onDraw()方法进行绘制东西的话，
就要在构造方法中明确设置setwilINotDraw（false）;
不需要调用onDraw()方法的话，也要在构造方法中明确设置setwilINotDraw（true）,系统会进行相应优化。

---

**View的layout()方法用于View确定自己本身在其父View的位置** 

**ViewGroup的onLayout()方法用于确定子View的位置**

---
### Shader 实现图像的渐变效果

- BitmapShader———图像渲染

- LinearGradient——–线性渲染

- RadialGradient——–环形渲染

- SweepGradient——–扫描渲染

- ComposeShader——组合渲染






































































