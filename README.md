# android-java-recycleview-cardview-demo

https://developer.android.com/training/material/lists-cards.html

## Demo for recycle view and card view
RecyclerView 提供下列內建的版面配置管理員(layout manager)：

+ [LinearLayoutManager](https://developer.android.com/reference/android/support/v7/widget/LinearLayoutManager.html) 在垂直或水平捲動清單中顯示項目。
+ [GridLayoutManager](https://developer.android.com/reference/android/support/v7/widget/GridLayoutManager.html) 會在網格中顯示項目。
+ [StaggeredGridLayoutManager](https://developer.android.com/reference/android/support/v7/widget/StaggeredGridLayoutManager.html) 會在交錯網格中顯示項目。

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

2. Add recycle view to layout
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <android.support.v7.widget.RecyclerView
        android:id="@+id/rv_my_recycleview"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
</LinearLayout>
```

3. Add RecyclerView to activity
+ RecyclerView (recyclerView)
+ RecyclerView.Adapter (adapter for data)
+ RecyclerView.LayoutManager (layout manager for decide display pattern)
```java
public class MyActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_my_recycleview);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }
    ...
}
```