package com.example.w7_d3_notesapp_fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class UpdateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        val sharedPreferences = requireActivity().getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        val mainViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        val updateET = view.findViewById<EditText>(R.id.updateNoteEditText)
        view.findViewById<Button>(R.id.updateButton).setOnClickListener{
            val noteId = sharedPreferences.getString("NoteId", "").toString()
            mainViewModel.editNote(noteId!!.toInt(),updateET.text.toString())
            updateET.text.clear()
            updateET.clearFocus()
            with(sharedPreferences.edit()) {
                putString("NoteId", updateET.text.toString())
                apply()
            }
            Navigation.findNavController(view).navigate(R.id.action_updateFragment_to_homeFragment)
        }
        return view
    }
}