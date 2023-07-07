package com.itis.taskmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment

class ContainerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        var jsonHandler = HandlerJSON()
//        jsonHandler.test()

        var controller =
            (supportFragmentManager.findFragmentById(R.id.container)
                    as NavHostFragment).navController

    }
}