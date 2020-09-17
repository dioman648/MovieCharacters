package com.example.moviecharacters.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.moviecharacters.R
import com.example.moviecharacters.ui.CharactersViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import java.lang.StringBuilder


class ProfileFragment : Fragment(R.layout.fragment_profile){

    private val viewModel by lazy {
        ViewModelProvider(this).get(CharactersViewModel::class.java)
    }

    val actionBar by lazy {
        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(toolbar)
        activity.supportActionBar
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI()

        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowTitleEnabled(true)
        actionBar?.title = ""
    }

    fun updateUI(){
        val id = navArgs<ProfileFragmentArgs>().value.id
        viewModel.updateProfile(id ?: 1)
        viewModel.profile.observe(viewLifecycleOwner, Observer { profile ->
            Glide.with(this).load(profile.image).into(profile_iv_image)
            actionBar?.title = profile.name
            profile_tv_status.text = profile.status
            profile_tv_species.text = profile.species
            if (profile.type.isEmpty()){
                profile_tv_type.text = "Default"
            }else{
                profile_tv_type.text = textTransfer(profile.type)
            }
            profile_tv_gender.text = profile.gender
            val episodesKeys = StringBuilder()
            profile.episode.forEach { episode ->
                episodesKeys.append(episode.removePrefix("https://rickandmortyapi.com/api/episode/")+ ",")
            }
            episodesKeys.toString()
            viewModel.loadEpisodes(episodesKeys.toString())
            viewModel.episodes.observe(viewLifecycleOwner, Observer {episodes ->
               episodes.forEach { episode ->
                   profile_episodes.append("${episode.episode} - ${episode.name} \n\n")
               }
            })
        })
    }

    private fun textTransfer(str:String):String{
        if (str.length>15){
            return str.replace(" ","\n")
        }
        return str
    }


}