package com.example.w7_d3_notesapp_fragments.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.w7_d3_notesapp_fragments.R
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.w7_d3_notesapp_fragments.MainActivity
import com.example.w7_d3_notesapp_fragments.database.Note
import com.example.w7_d3_notesapp_fragments.databinding.NoteRowBinding
import com.example.w7_d3_notesapp_fragments.HomeFragment
import com.example.w7_d3_notesapp_fragments.databinding.FragmentHomeBinding

class NoteAdapter(private val homeFragment: HomeFragment): RecyclerView.Adapter<NoteAdapter.ItemViewHolder>() {
    private var notes = emptyList<Note>()

    class ItemViewHolder(val binding: NoteRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ItemViewHolder {
        return ItemViewHolder(
            NoteRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteAdapter.ItemViewHolder, position: Int) {
        val item = notes[position]

        holder.binding.apply {
            noteTextView.text = item.noteText //name of the entity column
            if (position % 2 == 0) {
                holderLinearLayout.setBackgroundColor(Color.GRAY)
            }
            ibEditNote.setOnClickListener {
                with(homeFragment.sharedPreferences.edit()) {
                    putString("NoteId", item.id.toString())
                    apply()
                }
                homeFragment.findNavController().navigate(R.id.action_homeFragment_to_updateFragment)
            }
            ibDeleteNote.setOnClickListener {
                homeFragment.mainViewModel.deleteNote(item.id)
            }
        }
    }

    override fun getItemCount() = notes.size

    fun updateRecycleView(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }
}