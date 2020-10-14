package com.gmail.lyohakasianik.solveittest.ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gmail.lyohakasianik.solveittest.R
import com.gmail.lyohakasianik.solveittest.ui.listPerson.ListPersonFragment
import com.gmail.lyohakasianik.solveittest.ui.specialty.ListSpecialtyFragment
import com.gmail.lyohakasianik.solveittest.utils.PERSON_ID
import com.gmail.lyohakasianik.solveittest.utils.SPECIALTY_ID

@ExperimentalStdlibApi
class StartActivity : AppCompatActivity(), ListSpecialtyFragment.Listener,
    ListPersonFragment.ListenerStartPersonFragment {

    private var whichLandscape: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity)
        whichLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(
                R.id.containerForFragment,
                ListSpecialtyFragment()
            )
            transaction.commit()
        }
    }

    override fun startListPersonFragment(specialtyId: Long) {
        replaceFragment(ListPersonFragment(), SPECIALTY_ID, specialtyId)
    }

    private fun replaceFragment(fragment: Fragment, idKey: String, idElement: Long) {
        val bundle = Bundle()
        bundle.putString(idKey, idElement.toString())
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(
            selectionOrientation(whichLandscape),
            fragment
        ).addToBackStack(null).commit()
    }

    private fun selectionOrientation(whichLandscape: Boolean) = if (whichLandscape) {
        R.id.frameLayoutContainerTwo
    } else R.id.containerForFragment

    override fun startPersonFragment(personId: Long) {
        replaceFragment(PersonFragment(), PERSON_ID, personId)
    }
}