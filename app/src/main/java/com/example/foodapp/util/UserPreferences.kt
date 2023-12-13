import android.content.Context
import com.example.foodapp.data.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserPreferences(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveUsers(users: ArrayList<User>) {
        val json = gson.toJson(users)
        sharedPreferences.edit().putString("users", json).apply()
    }

    fun getUsers(): ArrayList<User> {
        val json = sharedPreferences.getString("users", null)
        return if (json != null) {
            val type = object : TypeToken<ArrayList<User>>() {}.type
            gson.fromJson(json, type)
        } else {
            arrayListOf()
        }
    }
}
