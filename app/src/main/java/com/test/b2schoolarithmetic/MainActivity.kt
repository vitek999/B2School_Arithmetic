package com.test.b2schoolarithmetic

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)

        val destinationsWithHideKeyboard = listOf(R.id.loginFragment, R.id.registrationFragment)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottom_navigation.visibility = if (destination.id in destinationsWithHideKeyboard) View.GONE else View.VISIBLE
        }
    }
}
