
### 场景

自定义一个ViewGroup， 使得所有的子view垂直摆放， 并且ViewGroup添加背景色。

- LibViewGroup1 当纵横都设置match_parent时

- LibViewGroup2 当纵横都是wrap_content时

- LibViewGroup3 考虑padding (ViewGroup设置内边距20dp)

- LibViewGroup4 考虑margin (给ViewGroup中的子view设置外边距。)

- LibViewGroup5 使得所有的子view瀑布式摆放,从左到右依次排开，不够换行

---

如果对View的宽高进行修改了，不要调用 super.onMeasure( widthMeasureSpec, heightMeasureSpec);
要调用 setMeasuredDimension( widthsize, heightsize)这个函数。

---

measureChildWithMargins()中那两个被赋值为0的两个参数 在android说明中说是为了在平行
or 垂直布局中设置固定间隔而用的

measureChildWithMargins(View child,
int parentWidthMeasureSpec,
int widthUsed = 0,
int parentHeightMeasureSpec,
int heightUsed = 0)

---

明晚继续：
https://www.jianshu.com/p/37f4b6e43159

