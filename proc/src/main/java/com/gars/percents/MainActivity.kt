package com.gars.percents

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.MenuItem
import androidx.fragment.app.commit
import com.gars.percents.common.koin
import com.gars.percents.home.HomeFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            (findViewById<Toolbar>(R.id.toolbar))?.apply {
                setSupportActionBar(this)
            }

            koin.declare(supportFragmentManager, override = true)

            supportFragmentManager.commit {
                replace(R.id.container, HomeFragment.newInstance(), "root")
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> { onBackPressed(); true }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
