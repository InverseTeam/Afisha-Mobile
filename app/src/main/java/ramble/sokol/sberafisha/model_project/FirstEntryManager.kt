package ramble.sokol.sberafisha.model_project

import android.content.Context

class FirstEntryManager(context: Context) {

    companion object{
        private const val PREF_FIRST_ENTRY = "PREF_FIRST_ENTRY"
        private const val FIRST_ENTRY = "FIRST_ENTRY"
        private const val PREF_FIRST_TEST = "PREF_FIRST_TEST"
        private const val FIRST_TEST = "FIRST_TEST"
    }

    private var sPref = context.getSharedPreferences(FirstEntryManager.PREF_FIRST_ENTRY, Context.MODE_PRIVATE)
    private var sPrefTest = context.getSharedPreferences(FirstEntryManager.PREF_FIRST_TEST, Context.MODE_PRIVATE)

    fun saveFirstEntry(entry: Boolean){
        val editor = sPref.edit()
        editor.putBoolean(FirstEntryManager.FIRST_ENTRY, entry)
        editor.apply()
    }

    fun getFirstEntry() : Boolean? {
        return sPref.getBoolean(FirstEntryManager.FIRST_ENTRY, false)
    }

    fun saveFirstTest(entry: Boolean){
        val editor = sPrefTest.edit()
        editor.putBoolean(FirstEntryManager.FIRST_TEST, entry)
        editor.apply()
    }

    fun getFirstTest() : Boolean? {
        return sPrefTest.getBoolean(FirstEntryManager.FIRST_TEST, false)
    }

}