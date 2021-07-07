
package com.example.potteroapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

  private val characterList = mutableListOf<CharacterModel>()

  fun setCharacterList(characterList: List<CharacterModel>) {
    this.characterList.clear()
    this.characterList.addAll(characterList)
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.row_character_viewholder,
        parent, false)
    return CharacterViewHolder(view)
  }

  override fun getItemCount() = characterList.size

  @SuppressLint("DefaultLocale")
  override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
    val character = characterList[position]
    with(holder) {
      characterNameTextView.text = String.format(characterNameTextView.context.getString(
        R.string.name_placeholder), character.name)
      if (character.house.isNullOrEmpty())
        characterRoleTextView.text = String.format(characterRoleTextView.context.getString(R.string
          .role_placeholder), "Unknown")
      else
        characterRoleTextView.text = String.format(characterRoleTextView.context.getString(R.string
          .role_placeholder), character.house.capitalize())
      if (character.ancestry.isNullOrEmpty())
        orderOfThePhoenixTextView.text = String.format(orderOfThePhoenixTextView.context.getString(R.string
          .orderOfThePhoenix_placeholder), "Unknown")
      else
        orderOfThePhoenixTextView.text = String.format(orderOfThePhoenixTextView.context.getString(R.string
          .orderOfThePhoenix_placeholder), character.ancestry.capitalize())
    }
  }

  inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val characterNameTextView: TextView = itemView.findViewById(R.id.character_name_textview)
    val characterRoleTextView: TextView = itemView.findViewById(R.id.character_role_textview)
    val orderOfThePhoenixTextView: TextView = itemView.findViewById(R.id.orderOfThePhoenix_textview)
  }
}