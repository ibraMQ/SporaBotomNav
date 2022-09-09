package com.example.botomexample.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.botomexample.Contact
import com.example.botomexample.ContactAdapter
import com.example.botomexample.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var contactList: ArrayList<Contact>
    private lateinit var contactAdapter: ContactAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val listViewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        contactList=listViewModel.contacts
        val rvContact = binding.rvContacts
        contactAdapter=ContactAdapter()
        rvContact.adapter=contactAdapter

        contactAdapter.data = contactList

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}