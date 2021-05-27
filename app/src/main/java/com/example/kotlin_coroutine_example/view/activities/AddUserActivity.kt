package com.example.kotlin_coroutine_example.view.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_coroutine_example.MyApplication
import com.example.kotlin_coroutine_example.R
import com.example.kotlin_coroutine_example.database.User
import com.example.kotlin_coroutine_example.viewmodel.UserViewModel
import com.example.kotlin_coroutine_example.viewmodel.ViewModelFactory

class AddUserActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var saveButton: Button

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        etUsername = findViewById(R.id.et_username)
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        saveButton = findViewById(R.id.save_button)

        userViewModel =
            ViewModelProvider(this, ViewModelFactory((application as MyApplication).repository))
                .get(UserViewModel::class.java)

        saveButton.setOnClickListener {
            if (checkValidations()) {
                Toast.makeText(this, "Please provide all fields", Toast.LENGTH_SHORT).show()
            } else {
                val user = User(
                    etUsername.text.toString().trim(),
                    etEmail.text.toString().trim(),
                    etPassword.text.toString().trim()
                )
                userViewModel.insert(user)
                startActivity(Intent(this@AddUserActivity , MainActivity::class.java))
                finish()
            }
        }


    }

    private fun checkValidations(): Boolean {
        return TextUtils.isEmpty(etUsername.text.toString().trim { it <= ' ' }) ||
                TextUtils.isEmpty(etEmail.text.toString().trim { it <= ' ' }) ||
                TextUtils.isEmpty(etPassword.text.toString().trim { it <= ' ' })
    }
}