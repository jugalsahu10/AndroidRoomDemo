package com.android.roomappdemo.fragements.add

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.roomappdemo.R
import com.android.roomappdemo.data.User
import com.android.roomappdemo.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.btnAdd.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val firstName = etFirstName.text.toString()
        val lastName = etLastName.text.toString()
        val age = etAge.text

        if (checkInput(firstName, lastName, age)) {
            mUserViewModel.addUser(User(0, firstName, lastName, Integer.parseInt(age.toString())))
            Toast.makeText(requireContext(), "Successfully Added!", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragement)
        } else {
            Toast.makeText(requireContext(), "Please fill out all details!", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun checkInput(firstName: String, lastName: String, age: Editable): Boolean {
        return !(firstName.isEmpty() && lastName.isEmpty() && age.isEmpty())
    }
}