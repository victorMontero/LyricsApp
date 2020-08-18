package com.android.lyricsapp.modules.forgotPassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.lyricsapp.R
import com.android.lyricsapp.modules.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class ForgotActivity : AppCompatActivity() {

    private val TAG = "ForgotActivity"

    private var emailEditText: EditText? = null
    private var buttonForgot: Button? = null

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)

        initialise()
    }

    private fun initialise() {
        emailEditText = findViewById(R.id.email_forgot_edit_text) as EditText
        buttonForgot = findViewById(R.id.recover_button_confirm_id) as Button

        mAuth = FirebaseAuth.getInstance()

        buttonForgot!!.setOnClickListener { sendResetPasswordEmail() }
    }

    private fun sendResetPasswordEmail() {
        val email = emailEditText?.text.toString()

        if(!TextUtils.isEmpty(email)){
            mAuth!!
                .sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        val message = "email sent."
                        Log.d(TAG, message)
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        updateUI()
                    } else {
                        Log.w(TAG, task.exception!!.message)
                        Toast.makeText(this, "no user found with this email", Toast.LENGTH_SHORT).show()
                    }
                }
        } else{
            Toast.makeText(this, "enter your email", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI() {
        val intent = Intent(this@ForgotActivity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}