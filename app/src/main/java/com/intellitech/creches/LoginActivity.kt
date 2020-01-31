package com.intellitech.creches

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_login.*
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    var codeSent = ""
    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            if (e is FirebaseAuthInvalidCredentialsException) {
                FancyToast.makeText(this@LoginActivity, "Les données saisies sont invalides !", FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show()
            } else if (e is FirebaseTooManyRequestsException) {
                FancyToast.makeText(this@LoginActivity, "Veuillez patienter avant de refaire l'opération", FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show()
            }
        }

        override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
            codeSent = verificationId
            FancyToast.makeText(this@LoginActivity, "Code secret envoyé !", FancyToast.LENGTH_LONG, FancyToast.INFO, false).show()
            login_verification_code.isEnabled = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()
        mAuth.setLanguageCode("fr")
    }

    override fun onStart() {
        super.onStart()
        // Just setting the toolbar title
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(resources.getColor(R.color.whiteColor))
        supportActionBar?.title = "Espace parent"
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
           startMainActivity()
        }
        login_request_code.setOnClickListener {
            sendVerificationCode()
        }
        login_login_btn.setOnClickListener {
            verifiyLoginCode()
        }
    }

    private fun sendVerificationCode() {
        val phoneNumber= login_phone.text.toString()
        if (phoneNumber.isEmpty() || phoneNumber.length < 10) {
            login_phone.error = "Veuillez saisir un numéro valide"
            login_phone.requestFocus()
            return
        } // We'll add more verification later

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            "+213" + login_phone.text.toString().removePrefix("0"),
            20,
            TimeUnit.SECONDS,
            this,
            callbacks
        )
        Log.d("tratra", "+213" + login_phone.text.toString())
    }

    private fun verifiyLoginCode() {
        val enteredCode = login_verification_code.text.toString()
        if (enteredCode.isEmpty() || enteredCode.length < 6) {
            login_verification_code.error = "Veuillez saisir un code valide"
            login_verification_code.requestFocus()
        } else {
            val credential = PhoneAuthProvider.getCredential(codeSent, enteredCode)
            signInWithPhoneAuthCredential(credential)
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    FancyToast.makeText(this@LoginActivity, "Authentification avec succès", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show()
                    startMainActivity()
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        FancyToast.makeText(this@LoginActivity, "Les données saisies sont invalides !", FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show()
                    }
                }
            }
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}
