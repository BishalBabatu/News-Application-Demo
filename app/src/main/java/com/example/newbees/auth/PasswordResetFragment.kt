package com.example.newbees.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.mubarak.newscastmb.R
import com.mubarak.newscastmb.databinding.FragmentPasswordResetBinding

class PasswordResetFragment : Fragment() {

    private var _binding: FragmentPasswordResetBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPasswordResetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        binding.btnResetPassword.setOnClickListener {
            val email = binding.etResetEmail.text.toString().trim()

            if (email.isNotEmpty()) {
                resetPassword(email)
            } else {
                Toast.makeText(context, "Please enter your email address", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun resetPassword(email: String) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(context, "Reset link sent to your email", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_passwordResetFragment_to_loginFragment)
            } else {
                Toast.makeText(context, "Failed to send reset email: ${task.exception?.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
