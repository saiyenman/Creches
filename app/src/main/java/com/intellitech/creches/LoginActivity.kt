package com.intellitech.creches

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.intellitech.creches.models.KidAccount
import com.intellitech.creches.services.DataService
import com.intellitech.creches.utils.PARENT_PHONE_EXTRA
import com.intellitech.creches.utils.PARENT_PHONE_PREF
import com.intellitech.creches.utils.SHARED_PREF_NAME
import com.kusu.loadingbutton.LoadingButton
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var loginBtn: LoadingButton
    private lateinit var loginPhoneEditText: EditText
    private lateinit var loginPasswordEditText: EditText
    lateinit var sharedPrefs : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()
        mAuth.setLanguageCode("fr")
        loginBtn = findViewById(R.id.login_login_btn)
        loginPhoneEditText = findViewById(R.id.login_phone)
        loginPasswordEditText = findViewById(R.id.login_password)

        sharedPrefs = getSharedPreferences(SHARED_PREF_NAME, 0)
    }

    override fun onStart() {
        super.onStart()
        // Just setting the toolbar title
        loginBtn.setOnClickListener {
            performLogin()
        }
    }

    private fun performLogin() {
        val phone = loginPhoneEditText.text.toString()
        val password = loginPasswordEditText.text.toString()

        if (phone.length < 10) {
            loginPhoneEditText.error= "Veuillez inserer un numéro correcte"
            return
        }
        if (phone.isEmpty()) {
            loginPhoneEditText.error = "Veuillez insérer votre numéro de téléphone"
            return
        }
        if (password.isEmpty()) {
            loginPasswordEditText.error = "Veuillez insérer votre mot de passe"
            return
        }
        loginBtn.showLoading()
        FirebaseAuth.getInstance().signInWithEmailAndPassword("$phone@gmail.com", password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    loginBtn.hideLoading()
                    sharedPrefs.edit().putString(PARENT_PHONE_PREF, phone).apply()
                    sharedPrefs.edit().putString("kidId","amine").apply()
                    startMainActivity(phone)
                }
            }
            .addOnFailureListener {
                loginBtn.hideLoading()
                FancyToast.makeText(this, "La connexion a échoué, veuillez vérifier vos informations", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
            }
    }

    private fun startMainActivity(phone: String) {
        val mainIntent = Intent(this, MainActivity::class.java)
        mainIntent.putExtra(PARENT_PHONE_EXTRA, phone)
        startActivity(mainIntent)
        finish()
    }
}
