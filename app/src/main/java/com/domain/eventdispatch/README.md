
## 主要是关于事件分发的知识点

**事实上，在一般情况下，ViewGroup并不会遍历其View的dispatchTouchEvent()方法，
而像是”知道“被点击的子View的最短路径一样，通过该路径去分发事件，并让事件抵达被点击的组件。
对于这个树状图，其余兄弟节点的组件的dispatchTouchEvent()方法并不会被调用。这是一个非常重要的结论。**

[以上结论参考来源](https://www.jianshu.com/p/54c78f55d13b)

### 总结：

- 在一般情况下，可以认为事件分发是以‘最短路径’来分发的。

---

**用RelativeLayout去拦截里面的子View的点击事件**

**在看了源码分析之后，找到原因：**

-  当ViewGroup有子View的时候，一定能拦截点击事件的入口是onInterceptTouchEvent()。

-  当ViewGroup有子View能接收点击事件的时候，不会调用ViewGroup任何自己的点击事件监听方法（无论内部还是外部设置的监听器）。

- 当ViewGroup没有子View能接收点击事件时，则会调用super.dispatchTouchEvent()，
此时将ViewGroup当做View来看，按照View的那一套来。

**我的RelativeLayout里面的子View可以接收点击事件，因此点击事件会直接传给他们，无法通过外部设置监听器去拦截。**

---

**View处理Touch事件的总体流程:**

    dispatchTouchEvent() —> onTouch() —> onTouchEvent() —> onClick() 
    
**Touch事件最先传入dispatchTouchEvent()中；如果该View存在TouchListener那么会调用该监听器中的onTouch()。
在此之后如果Touch事件未被消费，则会执行到View的onTouchEvent()方法，在该方法中处理ACTION_UP事件时，
若该View存在ClickListener则会调用该监听器中的onClick()。**

**onTouch()与onTouchEvent()以及click三者的区别和联系** 

- 2.1 onTouch()与onTouchEvent()都是处理触摸事件的API 

- 2.2 onTouch()属于TouchListener接口中的方法，是View暴露给用户的接口便于处理触摸事件，
而onTouchEvent()是Android系统自身对于Touch处理的实现 。

- 2.3 先调用onTouch()后调用onTouchEvent()。而且只有当onTouch()未消费Touch事件才有可能调用到onTouchEvent()。
即onTouch()的优先级比onTouchEvent()的优先级更高。 

- 2.4 在onTouchEvent()中处理ACTION_UP时会利用ClickListener执行Click事件。所以Touch的处理是优先于Click的
 
- 2.5 简单地说三者执行顺序为：onTouch()–>onTouchEvent()–>onClick()
View没有事件的拦截(onInterceptTouchEvent( ))，ViewGroup才有，请勿混淆 

---

2.1 判断disallowIntercept(禁止拦截)标志位，请参见代码第22行 
ViewGroup可以拦截Touch事件，但是它的子View可调用getParent().requestDisallowInterceptTouchEvent(true)
禁止其父View的拦截。
其实，从这个较长的方法名也可以看出来它的用途——禁止事件拦截；在该方法内部会改变FLAG_DISALLOW_INTERCEPT的值。
--------------------- 

在此请注意： 
ViewGroup中的requestDisallowInterceptTouchEvent( )方法可以用来禁止或允许ViewGroup拦截Touch事件，
但是它对于ACTION_DOWN是无效的。 
也就是说子View可以禁止父View拦截ACTION_MOVE和ACTION_UP但是无法禁止父View拦截ACTION_DOWN。
因为在ACTION_DOWN时会调用resetTouchState()重置了FLAG_DISALLOW_INTERCEPT的值导致子View对该值设置失效。
所以，对于ACTION_DOWN事件ViewGroup总会调用onInterceptTouchEvent()判读是否要拦截Touch事件
--------------------- 




















































