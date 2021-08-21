package com.wang.franch.ui

import android.os.Bundle
import android.widget.LinearLayout
import com.just.agentweb.AgentWeb
import com.just.agentweb.DefaultWebClient
import com.wang.franch.R
import com.wang.franch.ToolbarInfo
import com.wang.franch.base.ActivityBase
import com.wang.franch.databinding.ActivityWebBinding

/**
 * Web浏览页面
 * @Author Leiyu
 * @CreateDate 2021/7/11
 */
class ActivityWeb : ActivityBase() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityWebBinding = ActivityWebBinding.inflate(layoutInflater)
        binding.toolbarInfo = toolbarInfo()?.also {
            it.centerText = intent.extras!!.getString("title", getString(R.string.app_name))
        }
        val url = concatIfPdfFile(intent.extras!!.getString("url")!!)
        setContentView(binding.root)
        val agentWeb = AgentWeb.with(this)
            .setAgentWebParent(
                binding.webContainer,
                LinearLayout.LayoutParams(-1, -1)
            )
            .useDefaultIndicator()
            .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
            .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
            .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK) //打开其他应用时，弹窗咨询用户是否前往其他应用
            .interceptUnkownUrl() //拦截找不到相关页面的Scheme
            .createAgentWeb()
            .ready()
            .go(url)
        agentWeb.webCreator.webView.settings.builtInZoomControls = true
    }

    private fun concatIfPdfFile(url: String): String {
        if (url.endsWith(".pdf")) {
            return "https://docs.google.com/gview?embedded=true&url=" + url
        }
        return url
    }
}