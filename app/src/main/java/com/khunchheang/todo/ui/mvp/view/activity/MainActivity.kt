package com.khunchheang.todo.ui.mvp.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.khunchheang.todo.R
import com.khunchheang.todo.data.model.db.TaskModel
import com.khunchheang.todo.ui.adapter.TaskAdapter
import com.khunchheang.todo.ui.base.activity.BaseBasicMvpActivity
import com.khunchheang.todo.ui.listener.ItemClickListener
import com.khunchheang.todo.ui.mvp.GetTaskList
import com.khunchheang.todo.ui.mvp.ShareApp
import com.khunchheang.todo.util.AppConstants
import com.khunchheang.todo.util.TaskType
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : BaseBasicMvpActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener,
    ItemClickListener, GetTaskList.TaskListView, ShareApp.ShareAppView {

    @Inject
    lateinit var activityIntent: Intent
    @Inject
    lateinit var taskAdapter: TaskAdapter
    @Inject
    lateinit var taskListPresenter: GetTaskList.TaskListPreesnter
    @Inject
    lateinit var shareAppPresenter: ShareApp.ShareAppPresenter

    override fun onCreate(savedInstanceState: Bundle?) {

        //Set back to app theme from splash theme
        setTheme(R.style.AppTheme_NoActionBar)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //Setup drawer layout
        initDrawerLayout()

        //Init listener
        fab_add.setOnClickListener(this)

        //Init task recycler view
        setupRecyclerViewTasks()

        taskListPresenter.attach(this)
        shareAppPresenter.attach(this)
        taskListPresenter.getTaskList(TaskType.Today)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else if (!nav_view.menu.getItem(0).isChecked) {
            nav_view.menu.getItem(0).isChecked = true
            taskListPresenter.getTaskList(TaskType.Today)
            fab_add.show()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        taskListPresenter.detach()
        shareAppPresenter.detach()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AppConstants.REQUEST_CREATE_TODO_CODE && resultCode == Activity.RESULT_OK) {
            nav_view.menu.getItem(0).isChecked = true
            taskListPresenter.getTaskList(TaskType.Today)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                showSnackBar()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemClicked(view: View, pos: Int) {
        val intent = Intent(this, CreateTodoActivity::class.java)
        intent.putExtra(AppConstants.TASK_ID, taskAdapter.getItemPosition(pos).id)
        startActivityForResult(intent, AppConstants.REQUEST_CREATE_TODO_CODE)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_today -> taskListPresenter.getTaskList(TaskType.Today)

            R.id.nav_next_seven_days -> taskListPresenter.getTaskList(TaskType.NextSevenDay)

            R.id.nav_priority -> taskListPresenter.getTaskList(TaskType.Priority)

            R.id.nav_complete -> taskListPresenter.getTaskList(TaskType.Complete)

            R.id.nav_theme -> {
                showSnackBar()
            }

            R.id.nav_share -> shareAppPresenter.doShare()
        }

        fab_add.show()
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onClick(view: View?) {
        val id = view?.id
        if (id == fab_add.id) {
            activityIntent.setClass(this, CreateTodoActivity::class.java)
            startActivityForResult(activityIntent, AppConstants.REQUEST_CREATE_TODO_CODE)
        }
    }

    override fun onTaskResponse(lstOptionsSet: List<TaskModel>) {
        tv_no_task.visibility = if (lstOptionsSet.isEmpty()) View.VISIBLE else View.GONE
        taskAdapter.addItems(lstOptionsSet as ArrayList<TaskModel>)
    }

    override fun startShare(intent: Intent) {
        startActivity(intent)
    }

    private fun initDrawerLayout() {
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        nav_view.menu.getItem(0).isChecked = true
    }

    private fun setupRecyclerViewTasks() {
        recycler_task.setHasFixedSize(true)
        recycler_task.layoutManager = LinearLayoutManager(applicationContext)
        recycler_task.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))
        recycler_task.adapter = taskAdapter
        taskAdapter.itemClickListener = this

        recycler_task.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && fab_add.visibility == View.VISIBLE) {
                    fab_add.hide()
                } else if (dy < 0 && fab_add.visibility != View.VISIBLE) {
                    fab_add.show()
                }
            }
        })
    }

    private fun showSnackBar() {
        Snackbar.make(drawer_layout, getString(R.string.comming_soon), Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }
}
