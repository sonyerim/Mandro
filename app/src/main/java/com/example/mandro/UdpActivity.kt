package com.example.mandro

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mandro.databinding.ActivityUdpBinding
import com.example.mandro.databinding.ActivityVrBinding

class UdpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUdpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUdpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mWebView: WebView = binding.webViewUdp // 웹뷰 설정
        val mWebSettings: WebSettings = mWebView.settings // 웹뷰 세팅 등록
        val raspberryIp = intent.getStringExtra("raspberry_ip")

        mWebView.webViewClient = WebViewClient() // 클릭시 새창 안 뜨게
        mWebSettings.javaScriptEnabled = true // 웹페이지 자바스크립트 허용 여부
        mWebSettings.setSupportMultipleWindows(false) // 새창 띄우기 허용 여부
        mWebSettings.javaScriptCanOpenWindowsAutomatically = false // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
        mWebSettings.loadWithOverviewMode = true // 메타태그 허용 여부
        mWebSettings.useWideViewPort = true // 화면 사이즈 맞추기 허용 여부
        mWebSettings.setSupportZoom(false) // 화면 줌 허용 여부
        mWebSettings.builtInZoomControls = false // 화면 확대 축소 허용 여부
        mWebSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN // 컨텐츠 사이즈 맞추기
        mWebSettings.cacheMode = WebSettings.LOAD_NO_CACHE // 브라우저 캐시 허용 여부
        mWebSettings.domStorageEnabled = true // 로컬 저장소 허용 여부

        if (raspberryIp != null) {
            val url = "http://$raspberryIp:8888"
            mWebView.loadUrl(url) // 웹뷰에 라즈베리파이 주소 로드
        }
    }
}