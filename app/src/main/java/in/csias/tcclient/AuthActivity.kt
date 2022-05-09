package `in`.csias.tcclient

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main. activity_auth.*
import java.util.*

class AuthActivity : AppCompatActivity() {

    private lateinit var phoneNo: String
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        login.setOnClickListener{
            phoneNo = loginTxt.text.toString()
            sharedPreferences.edit().putBoolean(MainFragment.KEY_LOGIN, true).apply()
            sharedPreferences.edit().putString(MainFragment.KEY_DEVICE, phoneNo).apply()
            login()
        }
    }

    private fun login(){
        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
        finish()
    }

}