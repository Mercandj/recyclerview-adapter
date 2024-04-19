# Adapter delegate

## Why?

On Android, it's not built-in to support multi type on RecyclerView.

## What?

A light weight library to support multi type and decoupling Rows/Cells from the list.

## Sources

- Copied and adapted from this commit:
    - https://github.com/sockeqwe/AdapterDelegates/commit/e855771c3ed8b256287e46350ebc3ada2085c41a
    - https://github.com/Mercandj/AdapterDelegates/commit/6d8b261504e766f30f040a6b9e3892d819a5c537
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