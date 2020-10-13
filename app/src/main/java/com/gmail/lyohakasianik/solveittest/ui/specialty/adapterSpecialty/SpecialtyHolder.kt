package com.gmail.lyohakasianik.solveittest.ui.specialty.adapterSpecialty

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gmail.lyohakasianik.solveittest.repository.entity.db.SpecialtyForDb
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_specialty.*

class SpecialtyHolder(view: View) : RecyclerView.ViewHolder(view), LayoutContainer {
    override val containerView: View?
        get() = itemView

    fun bind(specialty: SpecialtyForDb) {
        specialtyNameTextView.text = specialty.specialtyName
    }
}