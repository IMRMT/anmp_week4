package com.ubaya.advweek4160421056.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.ubaya.advweek4160421056.R
import com.ubaya.advweek4160421056.databinding.ActivityMainBinding
import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import com.ubaya.advweek4160421056.util.createNotificationChannel
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    init{
        instance = this
    }

    companion object{
        private var instance:MainActivity ?= null

        fun showNotifications(title:String, content:String, icon:Int) {
            val channelId = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"
            //"com.ay.anmp.advweek4-...."

            val builder =
                NotificationCompat.Builder(instance!!.applicationContext, channelId).apply {
                    setSmallIcon(icon)
                    setContentTitle(title)
                    setContentText(content)
                    setStyle(NotificationCompat.BigTextStyle())
                    priority = NotificationCompat.PRIORITY_DEFAULT
                    setAutoCancel(true)
                }
            val manager = NotificationManagerCompat.from(instance!!.applicationContext)
            if (ActivityCompat.checkSelfPermission(
                    instance!!.applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    instance!!,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    1
                )
                return
            }
            manager.notify(1001, builder.build())
        }
    }

    private lateinit var binding:ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       // navController = (binding.fragmentContainerView as NavHostFragment).navController
        //find by id mencari berdasarkan id seluruh res

        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT,
            false,getString(R.string.app_name),
            "App Notif Channel")
        navController = (supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
                as NavHostFragment).navController
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == 1){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.d("permission_check", "granted")
                createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT,
                    false,getString(R.string.app_name),
                    "App Notif Channel")
            }
            else{
                Log.d("permission_check", "deny")
            }
        }
    }
}