package com.example.proyectopmdm.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectopmdm.data.models.MutableRepository
import com.example.proyectopmdm.ui.views.MainActivity
import com.example.proyectopmdm.databinding.FragmentRecyclerBinding
import com.example.proyectopmdm.ui.adapter.ContactoAdapter
import com.example.proyectopmdm.ui.viewmodels.RecyclerViewModel
import com.example.proyectopmdm.ui.views.dialogues.CreateDialogue
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class RecyclerFragment : Fragment() {
    lateinit var binding: FragmentRecyclerBinding
    private lateinit var mainActivity : MainActivity
    val adapter : ContactoAdapter = ContactoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = requireActivity() as MainActivity
        binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val layoutManager = LinearLayoutManager(requireActivity())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvContactos.layoutManager = layoutManager

        binding.rvContactos.adapter = adapter

        mainActivity.contactsViewModel.contactsLiveData.observe(
            requireActivity(), { contacts ->
                binding.rvContactos.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        )

        binding.fbAdd.setOnClickListener { view ->
            addContacto()
        }
    }

    private fun addContacto() {
        val dialog = CreateDialogue(
            {
                contact ->
                    mainActivity.contactsViewModel.addContact(contact)
                    binding.rvContactos.layoutManager!!.scrollToPosition(MutableRepository.contacts.lastIndex)
            })
        dialog.show(mainActivity.supportFragmentManager, "Añadir")
    }
}