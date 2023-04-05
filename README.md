# 简介

简化ViewBinding 初始化，通过读取范型获取 ViewBinding Class 反射进行初始化
用于 Activity，Fragment，RecycleView-Adapter 等需要初始化ViewBinding的地方

在使用的项目：

* [https://github.com/Western-parotia/ConvenientRecyclerViewAdapter](https://github.com/Western-parotia/ConvenientRecyclerViewAdapter)
* [https://github.com/Western-parotia/AndroidBaseArchitecture](https://github.com/Western-parotia/AndroidBaseArchitecture)

## 更新日志：

## 引用：

```kotlin
 implementation("com.foundation.widget:view-binding-helper:1.0.6")
```

## API：

```kotlin
/**
 * 获取ViewBinding的class
 *
 * @param myClass 当前类的class（getClass()）
 */
fun <T : ViewBinding> getViewBindingClass(..): Class<T>? {}

/**
 * 获取ViewBinding的class，直接指定泛型位置
 *
 * @param myClass 当前类的class（getClass()）
 * @param typeIndex 泛型的位置，如：你的泛型在第二个位置传1
 */
fun <T : ViewBinding> getViewBindingClassFromIndex(..): Class<T>? {}

/**
 * 根据类获取vb实例
 * @param obj 当前带ViewBinding泛型的实例类
 */
fun <T : ViewBinding> getViewBindingInstance(..): T {}
```
