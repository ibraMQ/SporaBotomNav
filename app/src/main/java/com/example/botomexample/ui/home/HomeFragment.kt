package com.example.botomexample.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.botomexample.databinding.FragmentHomeBinding
import com.example.botomexample.views.CustomView
import com.example.botomexample.views.SwitchView

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var switchView: SwitchView
    private lateinit var customView: CustomView
    private lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        switchView=binding.svFrag
        customView=binding.cvFrag
        button=binding.btnChange

        switchView.addObserver(customView)
        val onClickBtnChange = View.OnClickListener{
            customView.swapColor()
            customView.swapShape()
        }
        button.setOnClickListener(onClickBtnChange)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}