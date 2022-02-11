package com.imfondof.wanandroid.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.CompoundButton
import android.widget.TabHost.OnTabChangeListener
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTabHost
import com.imfondof.wanandroid.R
import com.imfondof.wanandroid.more.util.ListUtil
import com.imfondof.wanandroid.more.view.MyRadioButton
import com.imfondof.wanandroid.ui.mine.MyFrg
import java.util.*

class MainActivity : FragmentActivity(), CompoundButton.OnCheckedChangeListener, OnTabChangeListener {
    private var tabButtons: ArrayList<MyRadioButton>? = null
    private var mTabHost: FragmentTabHost? = null
    private lateinit var radioButton1: MyRadioButton
    private lateinit var radioButton2: MyRadioButton
    private lateinit var radioButton3: MyRadioButton
    private lateinit var radioButton4: MyRadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private enum class TABS { 首页, 导航, 日程, 消息, 我 }

    private val mTargetView = arrayOf<Class<*>>(
        MyFrg::class.java,
        MyFrg::class.java,
        MyFrg::class.java,
        MyFrg::class.java,
        MyFrg::class.java
    )

    override fun onTabChanged(tabId: String) {
        val tabPosition: Int = TABS.valueOf(tabId).ordinal
        if (tabPosition > ListUtil.getSize(tabButtons) - 1) return
        tabButtons?.get(tabPosition)?.isChecked = true
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        mTabHost = findViewById(android.R.id.tabhost)

        radioButton1=findViewById(R.id.gone)

        tabButtons = ArrayList()
        tabButtons?.add(radioButton1)
        tabButtons?.add(radioButton2)
        tabButtons?.add(radioButton3)
        tabButtons?.add(radioButton4)

        val tabCount: Int = ListUtil.getSize(tabButtons)
        for (i in 0 until tabCount) {
            val rb_tab = tabButtons?.get(i)
            val label = TABS.values()[i].name
            rb_tab?.text = label
            mTabHost?.addTab(mTabHost!!.newTabSpec(label).setIndicator(label), mTargetView[i], null)
        }
    }
}