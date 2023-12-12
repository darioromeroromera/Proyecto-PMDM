package com.example.proyectopmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectopmdm.databinding.ActivityLoginBinding
import com.example.proyectopmdm.models.User
import com.example.proyectopmdm.objects_models.Users

class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding
    lateinit var usersList: MutableList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initEvent()
    }

    private fun initEvent() {
        usersList = Users.usersList
        binding.btnLogin.setOnClickListener { view ->
            val user = User(getUserName(), getPass())

            if (doesUserDataMatches(user)) {
                val intent = Intent(this, MainActivity::class.java).putExtra(
                    "user", getUserName()
                )

                startActivity(intent)
            } else
                Toast.makeText(this, "Usuario y/o contraseña incorrectos", Toast.LENGTH_LONG).show()
        }
        binding.btnRegister.setOnClickListener { view ->
            val user = User(getUserName(), getPass())

            if (doesUserExist(user))
                Toast.makeText(this, "Ese usuario ya está registrado",Toast.LENGTH_LONG).show()
            else {
                usersList.add(user)
                Toast.makeText(this, "Usuario añadido", Toast.LENGTH_LONG).show()
            }
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
}