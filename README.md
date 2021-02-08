# ActivityView
review project in Android Activity and View

## View review
- clipToPadding 决定在viewGroup绘制时，是否将其content(自身内容、children)，绘制到其padding位置(defalut is true)
    - true 寓意裁剪padding，代表不会绘制到其padding的位置（正常显示padding）
    - false 寓意不裁剪padding，代表会绘制 （content会显示在其padding的位置）