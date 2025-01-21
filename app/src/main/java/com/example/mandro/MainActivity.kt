package com.example.mandro

import android.content.Intent
import android.health.connect.datatypes.units.Length
import android.os.Bundle
import android.view.Menu
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mandro.databinding.ActivityMainBinding
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import android.util.Log
import android.widget.SeekBar
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var requestQueue: RequestQueue
    private var eyeDistance: String = "17"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        // SeekBar 값 변경 리스너 설정
        binding.eyeDistance.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // SeekBar의 현재 progress 값을 eyeDistance로 업데이트
                eyeDistance = progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // SeekBar를 조작하기 시작했을 때 동작
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // SeekBar 조작을 멈췄을 때 동작
            }
        })

        requestQueue = Volley.newRequestQueue(this)

        val etIpAddress = binding.etIpAddress

        binding.btnSet.setOnClickListener {
            sendValuesToRaspberryPi(eyeDistance, etIpAddress.text.toString())
            Toast.makeText(this, "값이 변경되었습니다.", Toast.LENGTH_SHORT).show()

        }

        // 버튼 클릭 시 IP 주소 넘겨주고, VRActivity로 이동
        binding.btnNormal.setOnClickListener {
            val intent = Intent(this, VRActivity::class.java)
            intent.putExtra("raspberry_ip", etIpAddress.text.toString())
            intent.putExtra("stream_path", "/normal")
            startActivity(intent)
        }

        binding.btnDistored.setOnClickListener {
            val intent = Intent(this, VRActivity::class.java)
            intent.putExtra("raspberry_ip", etIpAddress.text.toString())
            intent.putExtra("stream_path", "/distorted")
            startActivity(intent)
        }

    }

    private fun sendValuesToRaspberryPi(eyeDistance: String, ipAddress: String) {
        val url = "http://${ipAddress}:8000/update"
        val stringRequest = object : StringRequest(Request.Method.POST, url,
            { response -> Log.d("Response", response) },
            { error -> Log.e("Error", error.toString()) }) {
            override fun getParams(): MutableMap<String, String> {
                return mutableMapOf(
                    "left" to eyeDistance,
                    "right" to eyeDistance
                )
            }
        }
        requestQueue.add(stringRequest)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
}