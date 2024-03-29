package com.example.w7_d3_notesapp_fragments

import android.content.Context
import android.content.SharedPreferences
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.w7_d3_notesapp_fragments.adapter.NoteAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    private lateinit var rvNotes: RecyclerView
    private lateinit var rvAdapter: NoteAdapter
    private lateinit var editText: EditText
    private lateinit var submitBtn: Button

    lateinit var mainViewModel: MyViewModel
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        sharedPreferences = requireActivity().getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        mainViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        mainViewModel.getNotes().observe(viewLifecycleOwner, {
                notes -> rvAdapter.updateRecycleView(notes)
        })

        view.findViewById<Button>(R.id.saveButton).setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_updateFragment)
        }

        editText = view.findViewById<EditText>(R.id.messageEditText)
        submitBtn = view.findViewById<Button>(R.id.saveButton)
        submitBtn.setOnClickListener {
            mainViewModel.addNote(editText.text.toString())
            editText.text.clear()
            editText.clearFocus()
        }

        rvNotes = view.findViewById(R.id.recyclerView)
        rvAdapter = NoteAdapter(this)
        rvNotes.adapter = rvAdapter
        rvNotes.layoutManager = LinearLayoutManager(requireContext())

        mainViewModel.getNotes()
        return view
    }
}