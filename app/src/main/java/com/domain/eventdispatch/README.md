
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

