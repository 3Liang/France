package com.wang.franch.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wang.franch.R
import com.wang.franch.base.ActivityBase
import com.wang.franch.databinding.ActivityMainBinding

/**
 * 首页
 *
 * @Author Leiyu
 * @CreateDate 2021/2/6
 */
class ActivityMain : ActivityBase(), BottomNavigationView.OnNavigationItemSelectedListener {

    lateinit var mBinding: ActivityMainBinding
    private val fragments: Array<Fragment> = arrayOf(FragmentIndex(), FragmentMe())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        mBinding.bottomNavigationView.setOnNavigationItemSelectedListener(this)
        mBinding.bottomNavigationView.selectedItemId = R.id.index
        setContentView(mBinding.root)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.index) {
            switchFragment(0)
        } else {
            switchFragment(1)
        }
        return true
    }

    /**
     * 切换fragment当底部选中切换时
     *
     * @param position 新选中的位置
     */
    private fun switchFragment(position: Int) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        for (i in fragments.indices) { //遍历隐藏前一个非当前位置的fragment
            if (fragments.get(i).isVisible() && i != position) {
                fragmentTransaction.hide(fragments.get(i))
            }
        }
        if (!fragments.get(position).isAdded()) { //fragment未添加
            fragmentTransaction.add(mBinding.contentContainer.getId(), fragments.get(position))
        } else if (fragments.get(position).isHidden()) { //fragment已添加，但被隐藏
            fragmentTransaction.show(fragments.get(position))
        }
        fragmentTransaction.commitAllowingStateLoss()
    }
}