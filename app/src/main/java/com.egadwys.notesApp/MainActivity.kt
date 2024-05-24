package com.egadwys.notesApp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.egadwys.notesApp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var builder = NotificationCompat.Builder(this, "123")
            .setSmallIcon(android.R.drawable.stat_notify_chat)
            .setContentTitle("Testing notifikasi")
            .setContentText("testestestestestestestestestes")
            .setOngoing(false)
            .setStyle(
                NotificationCompat.BigTextStyle()
                .bigText("testestestestestestestestestestestestestestestestestestestestestestestestestestestestestestestestestestestestestestestes"))
            .setPriority(NotificationCompat.PRIORITY_MAX)

        with(NotificationManagerCompat.from(this)) {
            if (ActivityCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            notify(123, builder.build())
        }

        setupBottomNav()
    }

    private fun setupBottomNav() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(binding.fragmentContainer.id) as NavHostFragment
        val navController = navHostFragment.navController

        binding.apply {
            bottomNav.setupWithNavController(navController)

            navController.addOnDestinationChangedListener { _, destination, _ ->
                bottomNav.visibility = when (destination.id) {
                    R.id.noteListFragment -> View.VISIBLE
                    R.id.todoListFragment -> View.VISIBLE
                    else -> View.GONE
                }
            }
        }
    }
}