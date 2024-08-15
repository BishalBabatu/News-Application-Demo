package com.example.newbees.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mubarak.newscastmb.R

class RegisterFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val nameEditText = view.findViewById<TextInputEditText>(R.id.et_register_name)
        val emailEditText = view.findViewById<TextInputEditText>(R.id.et_register_email)
        val passwordEditText = view.findViewById<TextInputEditText>(R.id.et_register_password)
        val registerButton = view.findViewById<Button>(R.id.btn_new_user_register)
        val signInTextView = view.findViewById<TextView>(R.id.tv_existing_user_sign_in)

        registerButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                createUser(name, email, password)
            } else {
                Toast.makeText(context, "Name, email and password cannot be empty", Toast.LENGTH_LONG).show()
            }
        }

        signInTextView.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        return view
    }

    private fun createUser(name: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                user?.let {
                    val userId = user.uid
                    val creationDate = java.util.Date()

                    val userDetails = hashMapOf(
                        "userId" to userId,
                        "name" to name,
                        "email" to email,
                        "creationDate" to creationDate
                    )

                    firestore.collection("users").document(userId).set(userDetails)
                        .addOnSuccessListener {
                            Toast.makeText(context, "Registration successful and user details saved!", Toast.LENGTH_LONG).show()
                            findNavController().navigate(R.id.action_registerFragment_to_trendingNewsFragment)
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(context, "Failed to save user details: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                }
            } else {
                Toast.makeText(context, "Registration failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
