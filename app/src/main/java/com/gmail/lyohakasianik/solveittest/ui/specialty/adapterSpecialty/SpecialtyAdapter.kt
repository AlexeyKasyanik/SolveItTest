package com.gmail.lyohakasianik.solveittest.ui.specialty.adapterSpecialty

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.lyohakasianik.solveittest.R
import com.gmail.lyohakasianik.solveittest.repository.entity.db.SpecialtyForDb

class SpecialtyAdapter(
    private var listSpecialty: List<SpecialtyForDb>,
    private val listener: OnClickListener
): RecyclerView.Adapter<SpecialtyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialtyHolder =
        SpecialtyHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_specialty,
                parent,
                false
            )
        ).apply { itemView.setOnClickListener { listener.onItemClick(listSpecialty[adapterPosition]) } }

    override fun getItemCount() = listSpecialty.size

    override fun onBindViewHolder(holder: SpecialtyHolder, position: Int) {
        holder.bind(listSpecialty[position])
    }

    fun updateListCurrency(newListCurrency: List<SpecialtyForDb>) {
        listSpecialty = newListCurrency
        notifyDataSetChanged()
    }

    interface OnClickListener {
        fun onItemClick(item: SpecialtyForDb)
    }
}