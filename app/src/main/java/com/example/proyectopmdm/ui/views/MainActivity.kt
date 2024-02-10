package com.example.proyectopmdm.ui.views

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.proyectopmdm.R
import com.example.proyectopmdm.controller.RecyclerController
import com.example.proyectopmdm.data.datasource.Repository
import com.example.proyectopmdm.data.models.ContactsDao
import com.example.proyectopmdm.databinding.ActivityMainBinding
import com.example.proyectopmdm.data.models.Contacto
import com.example.proyectopmdm.data.models.MutableRepository
import com.example.proyectopmdm.ui.viewmodels.RecyclerViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    lateinit var navController : NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var user : String
    private lateinit var email : String
    lateinit var recyclerController: RecyclerController
    lateinit var shared: SharedPreferences
    val contactsViewModel : RecyclerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSharedPreferences()
        setSupportActionBar(binding.appBarContainerView.toolbar)
        setUserData()
        initList()
        initNavElements()
        initDrawer()
        initEvent()
        //initController()
    }

    private fun getSharedPreferences() {
        shared = getSharedPreferences("shared_pref_file", Context.MODE_PRIVATE)
    }

    private fun initController() {
        recyclerController = RecyclerController(this)
    }


    private fun setUserData() {
        user = shared.getString("username", "defValue")!!
        email = shared.getString("email", "defValue")!!
    }

    override fun onSupportNavigateUp(): Boolean {
        Toast.makeText(this, "Pulsada navegación", Toast.LENGTH_SHORT)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun initDrawer() {
        val navigationView = binding.drawerNavigationView
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.recyclerFragment,
                R.id.aboutUsFragment,
                R.id.securityFragment
            ), binding.mainDrawer)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navigationView.setupWithNavController(navController)
        val drawerHeader = navigationView.getHeaderView(0)
        val textViewUserName = drawerHeader.findViewById<TextView>(R.id.tv_username)
        val textViewEmail = drawerHeader.findViewById<TextView>(R.id.tv_email)
        textViewUserName.text = user
        textViewEmail.text = email
        navigationView.menu.findItem(R.id.logout).setOnMenuItemClickListener {item ->
            with(shared.edit()) {
                clear()
                apply()
            }
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            true
        }
    }

    private fun initList() {
        MutableRepository.contacts = Repository.listaContactos.toMutableList()
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
            R.id.tbitem_settings -> {
                navController.navigate(R.id.settingsFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initEvent() {
        binding.appBarContainerView.tvSaludo.text = binding.appBarContainerView.tvSaludo.text.toString() + user
    }

}