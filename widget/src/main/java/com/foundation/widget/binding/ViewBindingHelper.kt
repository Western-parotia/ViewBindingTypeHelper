package com.foundation.widget.binding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

object ViewBindingHelper {
    /**
     * 缓存已找到的class
     * viewBinding是有限的，所以不需要清
     */
    private val cacheClass = HashMap<String, Class<out ViewBinding>>(64)

    /**
     * 获取ViewBinding的class
     *
     * @param myClass 当前类的class（getClass()）
     */
    fun <T : ViewBinding> getViewBindingClass(myClass: Class<*>?): Class<T>? {
        if (myClass == Any::class.java || myClass == null) {
            return null
        }
        cacheClass[myClass.name]?.let {
            @Suppress("UNCHECKED_CAST")
            return it as Class<T>
        }
        //遍历父类所有泛型参数
        (myClass.genericSuperclass as? ParameterizedType)?.actualTypeArguments?.forEach { type ->
            if (type is Class<*>) {
                if (ViewBinding::class.java.isAssignableFrom(type)) {
                    @Suppress("UNCHECKED_CAST")
                    val clazz = type as Class<T>
                    cacheClass[myClass.name] = clazz//缓存
                    return clazz
                }
            }
        }
        //继续循环父类查询
        return getViewBindingClass(myClass.superclass)
    }

    /**
     * 根据类获取vb实例
     * @param obj 当前带ViewBinding泛型的实例类
     */
    fun <T : ViewBinding> getViewBindingInstance(
        obj: Any,
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean = false
    ): T {
        val bindingCls: Class<ViewBinding> = getViewBindingClass(obj.javaClass)
            ?: throw IllegalArgumentException("没有找到类${obj}的ViewBinding，请检查")
        return getViewBindingInstanceByClass(bindingCls, layoutInflater, container, attachToParent)
    }

    /**
     * 根据vb class获取vb实例
     */
    fun <T : ViewBinding> getViewBindingInstanceByClass(
        clz: Class<out ViewBinding>,
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean = false
    ): T {
        try {
            val method = clz.getDeclaredMethod(
                "inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java
            )

            @Suppress("UNCHECKED_CAST")
            val vb = method.invoke(null, layoutInflater, container, attachToParent) as T
            //保存自身，方便其他框架使用
            vb.root.setTag(R.id.tag_view_binding_obj, vb)
            return vb
        } catch (e: Exception) {
            e.printStackTrace()
            throw IllegalArgumentException("无法实例化${clz}，请注意是否开启了ViewBinding.inflate混淆", e)
        }
    }
}