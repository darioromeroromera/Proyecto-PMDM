package com.example.proyectopmdm.ui.views

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.proyectopmdm.databinding.ActivityLoginBinding
import com.example.proyectopmdm.data.models.User
import com.example.proyectopmdm.data.datasource.Users
import com.example.proyectopmdm.ui.views.dialogues.RegisterDialogue

class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding
    lateinit var usersList: MutableList<User>
    lateinit var shared : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUserList()
        loadSharedPreferences()
        if (isLoggedIn()) {
            val user = User(shared.getString("username", "defValue")!!, "", "")

            if (doesUserExist(user)) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else
                initLogin()
        } else {
            initLogin()
        }
    }

    private fun initLogin() {
        initEvent()
        binding.progressBar.visibility = View.GONE
    }


    private fun initUserList() {
        usersList = Users.usersList
    }

    private fun isLoggedIn() : Boolean {
        return shared.getBoolean("isLoggedIn", false)
    }

    private fun loadSharedPreferences() {
        shared = getSharedPreferences("shared_pref_file", Context.MODE_PRIVATE)
    }



    private fun initEvent() {
        binding.btnLogin.setOnClickListener { view ->
            var user = User(getUserName(), getPass(), "")

            if (doesUserDataMatches(user)) {
                user = findUser(user)!!
                with (shared.edit()) {
                    putString("username",  user.name)
                    putString("email", user.email)
                    putBoolean("isLoggedIn", true)
                    apply()
                }
                val intent = Intent(this, MainActivity::class.java)

                startActivity(intent)
                finish()
            } else
                Toast.makeText(this, "Usuario y/o contraseña incorrectos", Toast.LENGTH_LONG).show()
        }
        binding.btnRegister.setOnClickListener { view ->
            val dialog = RegisterDialogue(
                {
                    user ->
                    if (doesUserExist(user))
                        Toast.makeText(this, "Ese usuario ya está registrado",Toast.LENGTH_LONG).show()
                    else {
                        usersList.add(user)
                        Toast.makeText(this, "Usuario añadido", Toast.LENGTH_LONG).show()
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

    private fun doesUserExist(user: User) : Boolean {
        var exists = false
        for (u in usersList) {
            if (u.name.equals(user.name))
                exists = true
        }
        return exists
    }

    private fun doesUserDataMatches(user: User) : Boolean {
        var matches = false
        for (u in usersList) {
            if (u.name.equals(user.name) && u.password.equals(user.password))
                matches = true
        }
        return matches
    }

    private fun findUser(user: User) : User? {
        var foundUser: User? = null
        for (user in usersList) {
            if (user.name.equals(user.name))
                foundUser = user
        }
        return foundUser
    }
}