package com.example.project2registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    lateinit var login : Button
    lateinit var guest : Button
    lateinit var cancel : Button
    lateinit var sign_up : Button
    lateinit var vm : UsersViewModel
    lateinit var username : TextInputEditText
    lateinit var password : TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        vm = UsersViewModel(application)
        login = findViewById(R.id.login)
        sign_up = findViewById(R.id.sign_up)
        guest = findViewById(R.id.guest)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)

        auth = FirebaseAuth.getInstance()

        val currentuser = auth.currentUser
        if (currentuser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        login()
    }

    private fun login() {
        login.setOnClickListener {
            if (TextUtils.isEmpty(username.text.toString())) {
                username.setError("Plaese Enter User Name")
                return@setOnClickListener
            } else if (TextUtils.isEmpty(password.text.toString())) {
                password.setError("Plaese Enter Password")
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(username.text.toString(), password.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Login failed, please try again!", Toast.LENGTH_SHORT)
                    }
                }
        }

        sign_up.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))

        }
        guest.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))

        }

    }


}