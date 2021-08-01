package com.gen28.fetchrewards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gen28.fetchrewards.ui.main.RewardFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, RewardFragment.newInstance())
                    .commitNow()
        }
    }
}