package com.android.lyricsapp.modules.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.lyricsapp.R
import com.android.lyricsapp.modules.createAccount.CreateAccountActivity
import com.android.lyricsapp.modules.forgotPassword.ForgotActivity
import com.android.lyricsapp.modules.home.view.NewsActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private val TAG = "LoginActivity"

    private var email: String? = null
    private var password: String? = null

    private var createTextView: TextView? = null
    private var forgotTextView: TextView? = null
    private var loginButton: Button? = null
    private var passwordTextView: EditText? = null
    private var emailTextView: EditText? = null

    private var mAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setUpFields()
    }

    private fun setUpFields() {
        createTextView = findViewById<View>(R.id.new_account_login_text) as TextView
        forgotTextView = findViewById<View>(R.id.forgot_login_text) as TextView
        emailTextView = findViewById<View>(R.id.name_edit_text) as EditText
        passwordTextView = findViewById<View>(R.id.last_name_edit_text) as EditText
        loginButton = findViewById<Button>(R.id.activity_login_button_confirm_id)

        mAuth = FirebaseAuth.getInstance()

        forgotTextView!!.setOnClickListener {
            startActivity(
                Intent(
                    this@LoginActivity,
                    ForgotActivity::class.java
                )
            )
        }

        createTextView!!.setOnClickListener {
            startActivity(
                Intent(
                    this@LoginActivity,
                    CreateAccountActivity::class.java
                )
            )
        }

        loginButton!!.setOnClickListener { loginUser() }
    }

    private fun loginUser() {
        email = emailTextView?.text.toString()
        password = passwordTextView?.text.toString()

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            Log.d(TAG, "logging in user.")

            mAuth!!.signInWithEmailAndPassword(email!!, password!!)
                .addOnCompleteListener(this){task ->
                    if(task.isSuccessful){
                        Log.d(TAG, "signInWithEmail:success")
                        updateUI()
                    } else {
                        Log.e(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(this@LoginActivity, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }

                }
        } else {
            Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI() {
        val intent = Intent(this@LoginActivity, NewsActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}