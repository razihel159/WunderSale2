package com.stevenlopez.block2.p1.wundersale.fragments.shopping

import ProfileInfoFragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.stevenlopez.block2.p1.wundersale.Activity.LoginSignupActivity
import com.stevenlopez.block2.p1.wundersale.R

class ProfileFragment: Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val logoutButton = view.findViewById<Button>(R.id.LogOut)
        val profileButton = view.findViewById<Button>(R.id.myProfile)

        logoutButton.setOnClickListener {
            Toast.makeText(requireContext(), "Logged out", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), LoginSignupActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        profileButton.setOnClickListener{
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(
                R.id.shoppingHostFragment,
                ProfileInfoFragment()
            )
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}