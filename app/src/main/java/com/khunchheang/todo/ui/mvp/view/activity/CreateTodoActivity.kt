package com.khunchheang.todo.ui.mvp.view.activity

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.khunchheang.todo.R
import com.khunchheang.todo.data.model.db.TaskModel
import com.khunchheang.todo.data.model.other.OptionSetModel
import com.khunchheang.todo.ui.adapter.OptionSetAdapter
import com.khunchheang.todo.ui.base.activity.BaseSupportToolbarMvpActivity
import com.khunchheang.todo.ui.listener.ItemClickListener
import com.khunchheang.todo.ui.mvp.CreateTodo
import com.khunchheang.todo.ui.mvp.DeleteTask
import com.khunchheang.todo.ui.mvp.GetTask
import com.khunchheang.todo.ui.mvp.OptionsSet
import com.khunchheang.todo.util.AppConstants
import com.khunchheang.todo.util.Common
import kotlinx.android.synthetic.main.activity_create_todo.*
import kotlinx.android.synthetic.main.content_create_todo.*
import kotlinx.android.synthetic.main.layout_calendar_view.view.*
import java.util.*
import javax.inject.Inject


class CreateTodoActivity : BaseSupportToolbarMvpActivity(), View.OnClickListener, OptionsSet.OptionSetView,
    CreateTodo.CreateTodoView, ItemClickListener, GetTask.GetTaskView, DeleteTask.DeleteTaskView {

    @Inject
    lateinit var optionsSetAdapter: OptionSetAdapter
    @Inject
    lateinit var optionSetPresenter: OptionsSet.OptionSetPresenter
    @Inject
    lateinit var createTodoPresenter: CreateTodo.CreateTodoPresenter
    @Inject
    lateinit var getTaskPresenter: GetTask.GetTaskPresenter
    @Inject
    lateinit var deleteTaskPresenter: DeleteTask.DeleteTaskPresenter
    @Inject
    lateinit var currentDate: Date

    private lateinit var selectedDueDate: Date
    private var taskId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_todo)
        setSupportToolbar(toolbar)

        optionSetPresenter.attach(this)
        createTodoPresenter.attach(this)
        getTaskPresenter.attach(this)
        deleteTaskPresenter.attach(this)

        optionSetPresenter.getOptionsSet()

        //Set default due date
        selectedDueDate = currentDate

        //Set options set recycler view
        setupRecyclerOptionsSet()

        //Init listener
        fab_done.setOnClickListener(this)

        taskId = intent.getLongExtra(AppConstants.TASK_ID, 0L)
        if (taskId > 0) getTaskPresenter.getTaskById(taskId)
    }

    override fun onTaskResponse(taskModel: TaskModel) {
        edt_task.setText(taskModel.task)
        optionsSetAdapter.getItemPosition(0).subTitle = Common.getDisplayMonthAndDay(taskModel.dueDate!!)
        optionsSetAdapter.getItemPosition(1).isPriority = taskModel.isPriority!!
        optionsSetAdapter.notifyDataSetChanged()
    }

    override fun onDeleteTaskSuccess(msg: String) {
        showToast(msg)
        setResult(Activity.RESULT_OK)
        super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (taskId != 0L) menuInflater.inflate(R.menu.menu_create_todo, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when {
            item.itemId == R.id.action_delete -> {
                deleteTaskPresenter.deleteTask(taskId)
                true
            }
            item.itemId == R.id.action_mark_as_complete -> {
                createTask(true)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(view: View?) {
        if (fab_done.id == view?.id) createTask(false)
    }

    override fun onItemClicked(view: View, pos: Int) {
        hideKeyboard()
        if (pos == 0) showCalendarBottomSheet()
    }

    override fun onDestroy() {
        optionSetPresenter.detach()
        createTodoPresenter.detach()
        getTaskPresenter.detach()
        deleteTaskPresenter.detach()
        super.onDestroy()
    }

    override fun onOptionsSetResponse(lstOptionsSet: List<OptionSetModel>) {
        optionsSetAdapter.addItems(lstOptionsSet as ArrayList<OptionSetModel>)
    }

    override fun onCreateTodoSuccess(todoModel: TaskModel) {
        hideKeyboard()
        if (todoModel.id == null) {
            showToast(R.string.cannot_create_task)
            return
        }
        setResult(Activity.RESULT_OK)
        onBackPressed()
    }

    private fun setupRecyclerOptionsSet() {
        recycler_option_set.setHasFixedSize(true)
        recycler_option_set.layoutManager = LinearLayoutManager(applicationContext)
        recycler_option_set.adapter = optionsSetAdapter
        optionsSetAdapter.itemClickListener = this
    }

    private fun showCalendarBottomSheet() {
        val sheetView = layoutInflater.inflate(R.layout.layout_calendar_view, null, false)
        val bottomSheetDialog = BottomSheetDialog(this@CreateTodoActivity)
        bottomSheetDialog.setContentView(sheetView)
        bottomSheetDialog.show()
        sheetView.btn_cancel.setOnClickListener {
            selectedDueDate = currentDate
            bottomSheetDialog.dismiss()
        }

        sheetView.btn_ok.setOnClickListener {
            bottomSheetDialog.dismiss()
            setDueDateSubtitle()
        }

        sheetView.calendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDueDate = GregorianCalendar(year, month, dayOfMonth, 0, 0).time
        }
    }

    private fun setDueDateSubtitle() {
        optionsSetAdapter.getItemPosition(0).subTitle = Common.getDisplayMonthAndDay(selectedDueDate)
        optionsSetAdapter.notifyItemChanged(0)
    }

    private fun createTask(isComplete: Boolean) {
        val task = edt_task.text.toString()
        val isPriority = optionsSetAdapter.getItemPosition(1).isPriority
        createTodoPresenter.createTodo(taskId, task, selectedDueDate, isPriority, isComplete)
    }
}
