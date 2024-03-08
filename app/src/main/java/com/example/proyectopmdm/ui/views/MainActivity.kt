package com.example.proyectopmdm.ui.views

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.proyectopmdm.R
import com.example.proyectopmdm.data.datasource.Repository
import com.example.proyectopmdm.databinding.ActivityMainBinding
import com.example.proyectopmdm.data.models.mem.MutableRepository
import com.example.proyectopmdm.data.models.network.MutableContactRepository
import com.example.proyectopmdm.ui.viewmodels.RecyclerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    lateinit var navController : NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var user : String
    private lateinit var email : String
    private lateinit var token : String
    private lateinit var imagen : String
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
    }

    private fun getSharedPreferences() {
        shared = getSharedPreferences("shared_pref_file", Context.MODE_PRIVATE)
    }


    private fun setUserData() {
        user = shared.getString("username", "defValue")!!
        email = shared.getString("email", "defValue")!!
        token = shared.getString("token", "defValue")!!
        imagen = shared.getString("imagen", "defValue")!!
        if (imagen.equals("unavailable"))
                imagen = "https://img.freepik.com/free-vector/illustration-businessman_53876-5856.jpg"
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
        val imageViewUser = drawerHeader.findViewById<ImageView>(R.id.iv_user)
        textViewUserName.text = user
        textViewEmail.text = email
        Glide.with(this).load(imagen).into(imageViewUser)
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
        contactsViewModel.listContacts(token)
        //contactsViewModel.listContacts("token que no funciona para que falle")

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