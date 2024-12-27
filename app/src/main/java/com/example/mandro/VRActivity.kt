package com.example.mandro

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.mandro.databinding.ActivityVrBinding
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

class VRActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVrBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVrBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mWebView: WebView = binding.webView // 웹뷰 설정
        val mWebSettings: WebSettings = mWebView.settings // 웹뷰 세팅 등록

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

        mWebView.loadUrl("http://192.168.0.175:8000/index.html") // 웹뷰에 표시할 라즈베리파이 주소, 웹뷰 시작
    }
}