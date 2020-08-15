package com.android.lyricsapp.modules.createAccount

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.lyricsapp.R
import com.android.lyricsapp.model.News
import com.android.lyricsapp.modules.home.view.NewsActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CreateAccountActivity : AppCompatActivity() {

    private var firstNameEditText: EditText? = null
    private var lastNameEditText: EditText? = null
    private var emailEditText: EditText? = null
    private var passwordEditText: EditText? = null
    private var buttonCreate: Button? = null

    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    private val TAG = "CreateAccountActivity"

    private var firstName: String? = null
    private var lastName: String? = null
    private var email: String? = null
    private var password: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        initialise()
    }

    private fun initialise() {
        firstNameEditText = findViewById<View>(R.id.name_edit_text) as EditText
        lastNameEditText = findViewById<View>(R.id.last_name_edit_text) as EditText
        emailEditText = findViewById<View>(R.id.email_create_edit_text) as EditText
        passwordEditText = findViewById<View>(R.id.password_create_edit_text) as EditText
        buttonCreate = findViewById<View>(R.id.activity_review_button_confirm_id) as Button

        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("Users")

        buttonCreate!!.setOnClickListener { createNewAccount() }
    }

    private fun createNewAccount() {



        firstName = firstNameEditText?.text.toString()
        lastName = lastNameEditText?.text.toString()
        email = emailEditText?.text.toString()
        password = passwordEditText?.text.toString()

        if (!TextUtils.isEmpty(firstName) &&
            !TextUtils.isEmpty(lastName) &&
            !TextUtils.isEmpty(email) &&
            !TextUtils.isEmpty(password)
        ){
            mAuth = FirebaseAuth.getInstance()

            mAuth!!
                .createUserWithEmailAndPassword(email!!, password!!)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val userId = mAuth!!.currentUser!!.uid
                        //Verify Email
                        verifyEmail();
                        //update user profile information
                        val currentUserDb = mDatabaseReference!!.child(userId)
                        currentUserDb.child("firstName").setValue(firstName)
                        currentUserDb.child("lastName").setValue(lastName)
                        updateUserInfoAndUI()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(this@CreateAccountActivity, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
    } else {
        Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
    }
    }

    private fun verifyEmail() {
        val mUser = mAuth!!.currentUser;
        mUser!!.sendEmailVerification()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@CreateAccountActivity,
                        "Verification email sent to " + mUser.getEmail(),
                        Toast.LENGTH_SHORT).show()
                } else {
                    Log.e(TAG, "sendEmailVerification", task.exception)
                    Toast.makeText(this@CreateAccountActivity,
                        "Failed to send verification email.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun updateUserInfoAndUI() {
        val intent = Intent(this@CreateAccountActivity, NewsActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}