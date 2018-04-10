# android-java-recycleview-cardview-demo

https://developer.android.com/training/material/lists-cards.html

## Demo for recycle view and card view
RecyclerView 提供下列內建的版面配置管理員(layout manager)：

+ [LinearLayoutManager](https://developer.android.com/reference/android/support/v7/widget/LinearLayoutManager.html) 在垂直或水平捲動清單中顯示項目。
+ [GridLayoutManager](https://developer.android.com/reference/android/support/v7/widget/GridLayoutManager.html) 會在網格中顯示項目。
+ [StaggeredGridLayoutManager](https://developer.android.com/reference/android/support/v7/widget/StaggeredGridLayoutManager.html) 會在交錯網格中顯示項目。

## 程式碼
### 1. 加入Gradle
 一開始需要在Gradle加入這2行
```gradle
  compile 'com.android.support:cardview-v7:21.0.+'
  compile 'com.android.support:recyclerview-v7:21.0.+'
```
後面的版本請根據appcompat版本作修改才可以正常運作
```gradle
def LibVersion="{26.1.0}"

implementation 'com.android.support:appcompat-v7:${LibVersion}'

dependencies {
    ...
    compile 'com.android.support:cardview-v7:${LibVersion}'
    compile 'com.android.support:recyclerview-v7:${LibVersion}'
}
```

### 2. Add recycle view to layout
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

### 3. Add RecyclerView to activity
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

### 4. Create adapter

```java
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private String[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                               .inflate(R.layout.my_text_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ...
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
```