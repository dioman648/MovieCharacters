package com.example.moviecharacters.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecharacters.R
import com.example.moviecharacters.ui.CharactersViewModel
import com.example.moviecharacters.ui.adapters.CharactersListAdapter
import kotlinx.android.synthetic.main.fragment_characters_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharactersListFragment : Fragment(R.layout.fragment_characters_list) {
    lateinit var viewModel: CharactersViewModel
    lateinit var charactersAdapter: CharactersListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        viewModel = ViewModelProvider(this).get(CharactersViewModel::class.java)
        viewModel.loadCharacters()
        lifecycleScope.launch {
            viewModel.loadCharacters().collectLatest {
                charactersAdapter.submitData(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        charactersAdapter.setOnItemClickListener { character ->

            val action =
                CharactersListFragmentDirections.actionCharactersListToProfileFragment(character.id!!)
            findNavController().navigate(action)
        }
    }

    private fun setupAdapter() {
        val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        charactersAdapter = CharactersListAdapter()
        rvCharacters.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = charactersAdapter
            addItemDecoration(decoration)
        }
    }
}