# android-java-recycleview-cardview-demo

https://developer.android.com/training/material/lists-cards.html

## Demo for recycle view and card view
RecyclerView 提供下列內建的版面配置管理員(layout manager)：

+ LinearLayoutManager 在垂直或水平捲動清單中顯示項目。
+ GridLayoutManager 會在網格中顯示項目。
+ StaggeredGridLayoutManager 會在交錯網格中顯示項目。

## Integration
1. Add to gradle
```gradle
def LibVersion="{26.1.0}"

implementation 'com.android.support:appcompat-v7:${LibVersion}'

dependencies {
    ...
    compile 'com.android.support:cardview-v7:${LibVersion}'
    compile 'com.android.support:recyclerview-v7:${LibVersion}'
}
```