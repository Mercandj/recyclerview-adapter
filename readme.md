# RecyclerView - Adapter Delegate Multi Type

[![](https://jitpack.io/v/Mercandj/recyclerview-adapter.svg)](https://jitpack.io/#Mercandj/recyclerview-adapter)

## Why?

On Android, it's not built-in to support multi type on RecyclerView.

## What?

A light weight library to support multi type and decoupling Rows/Cells from the list.

## How to integrate?

**Step 1.** In project root `build.gradle` or `build.gradle.kts`, add:

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' } // Groovy: build.gradle
        maven(url = "https://jitpack.io") // Kotlin: build.gradle.kts
    }
}
```

**Step 2.** Add the dependency in app `build.gradle` or `build.gradle.kts`:

```groovy
dependencies {
    implementation("com.github.Mercandj:recyclerview-adapter:1.00.03")
}
```

**Step 3.** Prepare multi types

```kotlin
class CatRowViewAdapterDelegate :
    AbsListItemAdapterDelegate<Any, Any, CatRowViewAdapterDelegate.ViewHolder>() {

    override fun isForViewType(item: Any, items: List<Any>, position: Int) = item is CatRowViewModel

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = CatRowView(parent.context)
        view.layoutParams = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(item: Any, viewHolder: ViewHolder, payloads: List<Any>) {
        viewHolder.view.setViewModel(item as CatRowViewModel)
    }

    class ViewHolder(val view: CatRowView) : RecyclerView.ViewHolder(view)
}
```

```kotlin
data class CatRowViewModel(val name: String)

class CatRowView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.cat_row_view, this)
    }

    fun setViewModel(viewModel: CatRowViewModel) {
        findViewById<TextView>(R.id.cat_row_view_name).text = viewModel.name
    }
}
```

**Step 4.** Create your multi-type adapter

```kotlin
class AnimalsActivityAdapter : ListDelegationAdapter<List<Any>>() {

    init {
        delegatesManager.addDelegate(CatRowViewAdapterDelegate())
            .addDelegate(DogRowViewAdapterDelegate())
    }

    fun setViewModels(viewModels: List<Any>) {
        if (items == viewModels) {
            return
        }
        items = viewModels
        notifyDataSetChanged()
    }
}
```

and affect it to the recycler view

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.animals_activity)
    val recyclerView: RecyclerView = findViewById(R.id.animals_activity_recycler_view)
    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.adapter = adapter
    adapter.setViewModels(
      viewModels = listOf(
        CatRowViewModel("Pimousse"),
        CatRowViewModel("Matcha"),
        DogRowViewModel("Nelson") // Do the same as the Cat but for dogs
      )
    )
}
```

## Sources

- Copied and adapted from this commit:
  - Copied from [`"com.hannesdorfmann:adapterdelegates4"`](https://github.com/sockeqwe/AdapterDelegates)
  - [sockeqwe AdapterDelegates](https://github.com/sockeqwe/AdapterDelegates/commit/e855771c3ed8b256287e46350ebc3ada2085c41a)
  - [sockeqwe AdapterDelegates fork](https://github.com/Mercandj/AdapterDelegates/commit/6d8b261504e766f30f040a6b9e3892d819a5c537)
  - Thank you to "https://github.com/sockeqwe" for the great library (on Apache-2.0 license)
- Why a new library instead of a PullRequest to "com.hannesdorfmann:adapterdelegates4"?
  - The initial goal was to have up-to-date RecyclerView dependency to be able to set
    the `stateRestorationPolicy`
  - Why? CF this great article `Restore RecyclerView scroll position`
    - [Link](https://medium.com/androiddevelopers/restore-recyclerview-scroll-position-a8fbdc9a9334)
- What have been changed from "com.hannesdorfmann:adapterdelegates4"
  - Bump recyclerView
  - Move from Java to Kotlin
  - Remove fancy kotlin DSL support
  - Change the build system from groovy to kotlin
  - Simplify the sample
