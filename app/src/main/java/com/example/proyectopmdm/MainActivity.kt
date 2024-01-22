package com.example.proyectopmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.proyectopmdm.controller.RecyclerController
import com.example.proyectopmdm.dao.ContactosDao
import com.example.proyectopmdm.databinding.ActivityMainBinding
import com.example.proyectopmdm.fragments.RecyclerFragment
import com.example.proyectopmdm.models.Contacto

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController : NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var listaContactos: MutableList<Contacto>
    private lateinit var user : String
    lateinit var recyclerController: RecyclerController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarContainerView.toolbar)
        setUser()
        initList()
        initNavElements()
        initDrawer()
        initBottom()
        initEvent()
        initController()
    }

    private fun initController() {
        recyclerController = RecyclerController(this)
    }

    private fun initBottom() {
        binding.appBarContainerView.appBottomBar.bottomNavigation.setupWithNavController(navController)
    }

    private fun setUser() {
        user = intent.getStringExtra("user").toString()
    }

    override fun onSupportNavigateUp(): Boolean {
        Toast.makeText(this, "Pulsada navegación", Toast.LENGTH_SHORT)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun initDrawer() {
        val navigationView = binding.drawerNavigationView
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment,R.id.recyclerFragment, R.id.aboutUsFragment, R.id.securityFragment), binding.mainDrawer)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navigationView.setupWithNavController(navController)
        val drawerHeader = navigationView.getHeaderView(0)
        val textViewUserName = drawerHeader.findViewById<TextView>(R.id.tv_username)
        textViewUserName.text = user
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