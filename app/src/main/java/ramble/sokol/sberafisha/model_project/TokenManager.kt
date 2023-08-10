package ramble.sokol.sberafisha.model_project

import android.content.Context

class TokenManager(context: Context) {

    companion object{
        private const val PREF_TOKEN = "PREF_TOKEN"
        private const val USER_TOKEN = "USER_TOKEN"
    }

    private var sPref = context.getSharedPreferences(PREF_TOKEN, Context.MODE_PRIVATE)

    fun saveToken(token: String){
        val editor = sPref.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun getToken() : String? {
        return sPref.getString(USER_TOKEN, null)
    }

}