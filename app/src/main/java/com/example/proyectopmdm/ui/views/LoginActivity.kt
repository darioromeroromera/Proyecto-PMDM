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
            if (getUserName().trim().equals("") || getPass().trim().equals(""))
                Toast.makeText(this, "Los campos deben estar rellenos", Toast.LENGTH_LONG).show()
            else {
                val fieldsUser: UserModel = UserModel(getUserName(), getPass(), "")
                lifecycleScope.launch {
                    findUser(fieldsUser)
                    if (user != null) {
                        with (shared.edit()) {
                            putString("username",  user!!.name)
                            putString("email", user!!.email)
                            putBoolean("isLoggedIn", true)
                            apply()
                        }
                        val intent = Intent(activityContext, MainActivity::class.java)

                        startActivity(intent)
                        finish()
                    } else
                        Toast.makeText(activityContext, "Usuario y/o contraseña incorrectos", Toast.LENGTH_LONG).show()
                }


            }

        }
        binding.btnRegister.setOnClickListener { view ->
            val dialog = RegisterDialogue(
                {
                    fieldsUser ->
                        lifecycleScope.launch {
                            findUserByName(fieldsUser)
                            if (user != null)
                                Toast.makeText(activityContext, "Ese usuario ya está registrado",Toast.LENGTH_LONG).show()
                            else {
                                saveUser(fieldsUser)
                                Toast.makeText(activityContext, "Usuario añadido", Toast.LENGTH_LONG).show()
                            }
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

    suspend private fun findUserByName(user: UserModel) { // Para el registro
        userViewModel.getUserByName(user!!.name)
    }

    suspend private fun findUser(user: UserModel) { // Para el login
        userViewModel.getUser(user.name, user.password)
    }

    suspend private fun saveUser(user: UserModel) {
        userViewModel.saveUser(user)
    }
}