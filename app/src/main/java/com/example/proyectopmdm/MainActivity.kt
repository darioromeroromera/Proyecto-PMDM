package com.example.proyectopmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.proyectopmdm.controller.RecyclerController
import com.example.proyectopmdm.dao.ContactosDao
import com.example.proyectopmdm.databinding.ActivityMainBinding
import com.example.proyectopmdm.fragments.RecyclerFragment
import com.example.proyectopmdm.models.Contacto

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController : NavController
    lateinit var listaContactos: MutableList<Contacto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initNavElements()
        initList()
        initEvent()
    }

    private fun initList() {
        listaContactos = ContactosDao.dao.getContactsData().toMutableList()
    }

    private fun initNavElements() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.tbitem_home -> {
                navController.navigate(R.id.homeFragment)
                true
            }
            R.id.tbitem_contacts -> {
                navController.navigate(R.id.recyclerFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initEvent() {
        val user = intent.getStringExtra("user")
        binding.tvSaludo.text = binding.tvSaludo.text.toString() + user
    }
}