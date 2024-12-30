package com.example.mandro

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mandro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val etIpAddress = binding.etIpAddress

        // 버튼 클릭 시 IP 주소 넘겨주고, VRActivity로 이동
        binding.btnGoToVRActivity.setOnClickListener {
            val intent = Intent(this, VRActivity::class.java)
            intent.putExtra("raspberry_ip", etIpAddress.text.toString())
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
}