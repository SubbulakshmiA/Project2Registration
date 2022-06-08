package com.example.project2registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrationActivity : AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    var databaseReference : DatabaseReference? =null
    var database : FirebaseDatabase? = null

    lateinit var signup_btn : Button
    lateinit var cancel_btn : Button
    lateinit var username : TextInputEditText
    lateinit var password : TextInputEditText
    lateinit var confirm_password : TextInputEditText
    lateinit var email_id : TextInputEditText
    lateinit var vm : UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_registration)

            vm =UsersViewModel(application)
            signup_btn = findViewById(R.id.signup_btn)
            cancel_btn = findViewById(R.id.cancel_btn)
            username = findViewById(R.id.username)
            password = findViewById(R.id.password)
            confirm_password = findViewById(R.id.confirm_password)
            email_id = findViewById(R.id.email_id)

            auth = FirebaseAuth.getInstance()
            database = FirebaseDatabase.getInstance()
            databaseReference = database?.reference!!.child("profile")

            register()

    }
    private fun register(){
        signup_btn.setOnClickListener {

            if(TextUtils.isEmpty( username.text.toString())){
                username.setError("Plaese Enter User Name")
                return@setOnClickListener
            }else if (TextUtils.isEmpty( password.text.toString())){
                password.setError("Plaese Enter Password")
                return@setOnClickListener
            }else if (TextUtils.isEmpty( confirm_password.text.toString())){
                confirm_password.setError("Plaese Enter Confirm Password")
                return@setOnClickListener
            }else if (TextUtils.isEmpty( email_id.text.toString())){
                email_id.setError("Plaese Enter Email-Id")
                return@setOnClickListener
            }
                println("sign up before auth")

            auth.createUserWithEmailAndPassword(email_id.text.toString(),password.text.toString())
                .addOnCompleteListener(this, OnCompleteListener{ task ->
                if(task.isSuccessful){
                    var users = Users(null,username.text.toString(),password.text.toString(),email_id.text.toString())
                    vm.insertUsers(users)
                    Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }else {
                    Toast.makeText(this, "Registration Failed", Toast.LENGTH_LONG).show()
                }
            })
        }
        cancel_btn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }

    }




}