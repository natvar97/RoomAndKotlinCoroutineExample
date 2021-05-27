package com.example.kotlin_coroutine_example.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_coroutine_example.MyApplication
import com.example.kotlin_coroutine_example.R
import com.example.kotlin_coroutine_example.database.User
import com.example.kotlin_coroutine_example.view.adapters.UserRecyclerAdapter
import com.example.kotlin_coroutine_example.viewmodel.UserViewModel
import com.example.kotlin_coroutine_example.viewmodel.ViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userRecyclerAdapter: UserRecyclerAdapter
    private lateinit var userViewModel: UserViewModel
    private lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userViewModel =
            ViewModelProvider(this, ViewModelFactory((application as MyApplication).repository))
                .get(UserViewModel::class.java)
        fab = findViewById(R.id.fab)

        recyclerView = findViewById(R.id.recyclerView)
        userRecyclerAdapter = UserRecyclerAdapter(this@MainActivity)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = userRecyclerAdapter

        userViewModel.getAllUsers().observe(this, Observer { list ->
            userRecyclerAdapter.getAll(list)
        })

        fab.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.fab -> {
                startActivity(Intent(this@MainActivity, AddUserActivity::class.java))
                finish()
            }
        }
    }

    fun deleteUser(user: User) {
        userViewModel.delete(user)
    }
}