package com.example.proyectopmdm.ui.views.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectopmdm.data.models.mem.MutableRepository
import com.example.proyectopmdm.ui.views.MainActivity
import com.example.proyectopmdm.databinding.FragmentRecyclerBinding
import com.example.proyectopmdm.ui.adapter.ContactoAdapter
import com.example.proyectopmdm.ui.views.dialogues.CreateDialogue

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

        mainActivity.contactsViewModel.errorLiveData.observe(
            requireActivity(), { details ->
                if (!details.equals("")) {
                    Toast.makeText(mainActivity as Context, details, Toast.LENGTH_SHORT).show()
                }
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
                    //mainActivity.contactsViewModel.addContact(contact)
                    binding.rvContactos.layoutManager!!.scrollToPosition(MutableRepository.contacts.lastIndex)
            })
        dialog.show(mainActivity.supportFragmentManager, "Añadir")
    }
}