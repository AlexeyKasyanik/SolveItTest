package com.gmail.lyohakasianik.solveittest.ui.listPerson.adapterListPersons

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gmail.lyohakasianik.solveittest.repository.entity.db.PersonForDb
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_person.*

class PersonHolder(view: View) : RecyclerView.ViewHolder(view), LayoutContainer {
    override val containerView: View?
        get() = itemView

    fun bind(person: PersonForDb) {
        namePersonTextView.text = person.firstName.toString()
        lastNamePersonTextView.text = person.lastName
        agePersonTextView.text = person.birthday.toString()
    }
}