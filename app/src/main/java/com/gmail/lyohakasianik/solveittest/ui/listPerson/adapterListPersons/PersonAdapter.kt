package com.gmail.lyohakasianik.solveittest.ui.listPerson.adapterListPersons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.lyohakasianik.solveittest.R
import com.gmail.lyohakasianik.solveittest.repository.entity.db.PersonForDb

class PersonAdapter(
    private var listPerson: List<PersonForDb>,
    private val listener: OnClickListener
) : RecyclerView.Adapter<PersonHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder =
        PersonHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_person,
                parent,
                false
            )
        ).apply { itemView.setOnClickListener { listener.onItemClick(listPerson[adapterPosition]) } }

    override fun getItemCount() = listPerson.size

    override fun onBindViewHolder(holder: PersonHolder, position: Int) {
        holder.bind(listPerson[position])
    }

    fun updateListPersons(newListPerson: List<PersonForDb>) {
        listPerson = newListPerson
        notifyDataSetChanged()
    }

    interface OnClickListener {
        fun onItemClick(item: PersonForDb)
    }
}