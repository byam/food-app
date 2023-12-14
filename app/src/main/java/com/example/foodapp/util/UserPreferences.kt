import android.content.Context
import com.example.foodapp.data.Blog
import com.example.foodapp.data.MealPlan
import com.example.foodapp.data.Recipe
import com.example.foodapp.data.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserPreferences(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun clearAllData() {
        sharedPreferences.edit().clear().apply()
    }

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

    fun saveCurrentUser(user: User) {
        val json = gson.toJson(user)
        sharedPreferences.edit().putString("currentUser", json).apply()
    }

    fun getCurrentUser(): User? {
        val json = sharedPreferences.getString("currentUser", null)
        return if (json != null) {
            gson.fromJson(json, User::class.java)
        } else {
            null
        }
    }

    fun saveRecipes(recipes: ArrayList<Recipe>) {
        val json = gson.toJson(recipes)
        sharedPreferences.edit().putString("recipes", json).apply()
    }

    fun getRecipes(): ArrayList<Recipe> {
        val json = sharedPreferences.getString("recipes", null)
        return if (json != null) {
            val type = object : TypeToken<ArrayList<Recipe>>() {}.type
            gson.fromJson(json, type)
        } else {
            arrayListOf()
        }
    }

    fun saveMealPlans(mealPlans: ArrayList<MealPlan>) {
        val json = gson.toJson(mealPlans)
        sharedPreferences.edit().putString("meal_plans", json).apply()
    }

    fun getMealPlans(): ArrayList<MealPlan> {
        val json = sharedPreferences.getString("meal_plans", null)
        return if (json != null) {
            val type = object : TypeToken<ArrayList<MealPlan>>() {}.type
            gson.fromJson(json, type)
        } else {
            arrayListOf()
        }
    }

    fun saveBlogs(blogs: ArrayList<Blog>) {
        val json = gson.toJson(blogs)
        sharedPreferences.edit().putString("blogs", json).apply()
    }

    fun getBlogs(): ArrayList<Blog> {
        val json = sharedPreferences.getString("blogs", null)
        return if (json != null) {
            val type = object : TypeToken<ArrayList<Blog>>() {}.type
            gson.fromJson(json, type)
        } else {
            arrayListOf()
        }
    }

    fun getUserById(userId: Int): User? {
        val users = getUsers()
        return users.firstOrNull { it.id == userId }
    }
}
