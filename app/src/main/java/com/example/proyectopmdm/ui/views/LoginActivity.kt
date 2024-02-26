package com.example.proyectopmdm.ui.views

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.proyectopmdm.databinding.ActivityLoginBinding
import com.example.proyectopmdm.domain.usecases.models.UserModel
import com.example.proyectopmdm.ui.viewmodels.UserViewModel
import com.example.proyectopmdm.ui.views.dialogues.RegisterDialogue
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint

class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding
    lateinit var shared : SharedPreferences
    val userViewModel: UserViewModel by viewModels()
    private var user: UserModel? = null
    private val activityContext = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadSharedPreferences()
        userViewModel.userLiveData.observe(this, {
            newUser ->
                user = newUser
        })
        if (isLoggedIn()) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            initLogin()
        }
    }

    private fun initLogin() {
        initEvent()
        binding.progressBar.visibility = View.GONE
    }

    private fun isLoggedIn() : Boolean {
        return shared.getBoolean("isLoggedIn", false)
    }

    private fun loadSharedPreferences() {
        shared = getSharedPreferences("shared_pref_file", Context.MODE_PRIVATE)
    }



    private fun initEvent() {
        binding.btnLogin.setOnClickListener { view ->

                val fieldsUser: UserModel = UserModel(getUserName(), getPass())
                lifecycleScope.launch {
                    findUser(fieldsUser)
                    if (user!!.details.equals("")) {
                        with (shared.edit()) {
                            putString("username",  user!!.name)
                            putString("email", user!!.email)
                            putString("token", user!!.token)
                            putBoolean("isLoggedIn", true)
                            apply()
                        }
                        val intent = Intent(activityContext, MainActivity::class.java)

                        startActivity(intent)
                        finish()
                    } else
                        Toast.makeText(activityContext, user!!.details, Toast.LENGTH_LONG).show()
                }


        }
        binding.btnRegister.setOnClickListener { view ->
            val dialog = RegisterDialogue(
                {
                    fieldsUser ->
                        lifecycleScope.launch {

                            val saveState = saveUser(fieldsUser)
                            Toast.makeText(activityContext, saveState, Toast.LENGTH_SHORT).show()
                        }
                })
            dialog.show(this.supportFragmentManager, "Añadir")
        }
    }

    private fun getUserName() : String {
        return binding.etUsuario.text.toString().trim()
    }

    private fun getPass() : String {
        return binding.etPassword.text.toString().trim()
    }
    suspend private fun findUser(user: UserModel) { // Para el login
        userViewModel.login(user.name, user.password)
    }

    suspend private fun saveUser(user: UserModel) : String {
        return userViewModel.saveUser(user)
    }
}