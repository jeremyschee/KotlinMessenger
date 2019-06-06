package android.example.kotlinmessenger

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        login_button_login.setOnClickListener {
            val email = email_edittext_login.text.toString()
            val password = password_edittext_login.text.toString()

            Log.d("LoginActivity","Logged in with $email")
            if (email.isEmpty()||password.isEmpty()) {
                Toast.makeText(this,"Please enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener
                    Log.d("LoginActivity","Successfully login user ${it.result.user.uid}")
                }
                .addOnFailureListener {
                    Log.d("LoginActivity","Failed to login user: ${it.message}")
                }
        }
        back_to_register_textview.setOnClickListener {
            finish()
        }
    }
}