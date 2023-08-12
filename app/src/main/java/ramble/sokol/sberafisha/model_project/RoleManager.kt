package ramble.sokol.sberafisha.model_project

import android.content.Context
import androidx.compose.ui.semantics.Role

class RoleManager(
    context: Context
) {

    companion object{
        private const val PREF_ROLE = "PREF_ROLE"
        private const val ROLE = "ROLE"
    }

    private var sPref = context.getSharedPreferences(RoleManager.PREF_ROLE, Context.MODE_PRIVATE)

    fun saveRole(role: Int){
        val editor = sPref.edit()
        editor.putInt(RoleManager.ROLE, role)
        editor.apply()
    }

    fun getRole() : Int? {
        return sPref.getInt(RoleManager.ROLE, 0)
    }

}