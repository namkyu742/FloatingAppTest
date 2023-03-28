package com.example.floatingapptest

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + packageName))
            startActivityForResult(intent, REQUEST_CODE)
        } else {
            startFloatingViewService()
        }
    }

    private fun startFloatingViewService() {
        val intent = Intent(this, FloatingViewService::class.java)
        startService(intent)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Settings.canDrawOverlays(this)) {
                startFloatingViewService()
            }
        }
    }
}
/*
기본적으로 왼쪽 또는 오른쪽에 붙어있고 드래그하면 반대쪽으로 옮길 수 있다.
ㅁㄴㅇㅂㅁㅈㅇ
 */