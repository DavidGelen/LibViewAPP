
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

---
**第一点：** 

我们常说测量每个子View的宽和高是为了将每个子View的宽累加起来得到父View的宽，将每个子View的高累加起来得到父View的高。 
在此处，控件的宽就是屏幕的宽，所以我们不用去累加每个子View的宽，但是要利用子View的宽判断换行的时机。 
至于控件的高，还是需要将每个子View的高相累加。 

**第二点：** 
怎么判断需要换行显示新的tag呢？如果： 
这一行已占用的宽度 + 即将显示的子View的宽度 > 该行总宽度 
那么就要考虑换行显示该tag 

**第三点：** 
如果十个人站成一排，那么这个队伍的高度是由谁决定的呢？当然是这排人里个子最高的人决定的。
同样的道理，几个tag摆放在同一行，这一行的高度就是由最高的tag的值决定的；然后将每一行的高度相加就是View的总高了。
--------------------- 


























