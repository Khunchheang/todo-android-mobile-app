package com.khunchheang.todo.ui.base.activity

import android.support.v7.widget.Toolbar
import android.view.MenuItem

abstract class BaseSupportToolbarActivity : BaseBasicActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            super.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    internal fun setSupportToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }
}