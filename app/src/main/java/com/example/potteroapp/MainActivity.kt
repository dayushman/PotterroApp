package com.example.potteroapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.potteroapp.databinding.ActivityMainBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Main Screen
 */
class MainActivity : AppCompatActivity() {

  private lateinit var characterAdapter: CharacterAdapter
  lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    // Switch to AppTheme for displaying the activity
    setTheme(R.style.AppTheme)
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    // dummy code

    // Your code
    characterAdapter = CharacterAdapter()
    binding.characterRecyclerview.adapter = characterAdapter

    val potterApi = Retrofit.Builder()
        .baseUrl("http://127.0.0.1:8080")
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .client(OkHttpProvider.getOkHttpClient())
        .build()
        .create(PotterApi::class.java)

    potterApi.getCharacters().enqueue(object : Callback<List<CharacterModel>> {

      override fun onFailure(call: Call<List<CharacterModel>>, t: Throwable) {
        showErrorState()



      }

      override fun onResponse(call: Call<List<CharacterModel>>,
                              response: Response<List<CharacterModel>>) {
        if (response.isSuccessful && response.body() != null) {
          val characterList = response.body()!!
          if (characterList.isEmpty()) {
            showEmptyDataState()
          } else {
            showCharacterList(characterList)
          }
        } else {
          showErrorState()
        }
      }
    })
  }

  private fun showEmptyDataState() {
    binding.characterRecyclerview.visibility = View.GONE
    binding.progressBar.visibility = View.GONE
    binding.textview.visibility = View.VISIBLE
    binding.textview.text = getString(R.string.there_seems_to_be_no_data)
  }

  private fun showCharacterList(characterList: List<CharacterModel>) {
    binding.characterRecyclerview.visibility = View.VISIBLE
    binding.progressBar.visibility = View.GONE
    binding.textview.visibility = View.GONE
    characterAdapter.setCharacterList(characterList)
  }

  private fun showErrorState() {
    binding.characterRecyclerview.visibility = View.GONE
    binding.progressBar.visibility = View.GONE
    binding.textview.visibility = View.VISIBLE
    binding.textview.text = getString(R.string.something_went_wrong)
  }

}
