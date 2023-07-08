package com.itis.taskmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.itis.taskmanager.databinding.ActivityContainerBinding

class ContainerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContainerBinding
    private lateinit var db: DataBaseHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        binding = ActivityContainerBinding.inflate(layoutInflater)
        db = DataBaseHandler(this)
        db.readData()

        var controller =
            (supportFragmentManager.findFragmentById(R.id.container)
                    as NavHostFragment).navController

    }

    override fun onDestroy() {
        super.onDestroy()
        TaskRepository.list.forEach { task -> db.insertData(task) }
    }
}