package com.example.greatgamelibrary.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.greatgamelibrary.data.GameItem
import com.example.greatgamelibrary.R

class GameItemAdapter(val gameItemList: ArrayList<GameItem>): RecyclerView.Adapter<GameItemAdapter.GameItemViewHolder>() {

    private lateinit var listener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        this.listener=listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.game_item,parent,false)
        return GameItemViewHolder(itemView,listener)
    }

    override fun getItemCount(): Int {
        return gameItemList.size
    }

    override fun onBindViewHolder(holder: GameItemViewHolder, position: Int) {
        val currentItem = gameItemList[position]
        holder.gameImage.setImageBitmap(currentItem.gameImage)
        holder.gameTitle.text = currentItem.gameTitle
    }

    class GameItemViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView){
        val gameImage: ImageView = itemView.findViewById(R.id.gameImage)
        val gameTitle: TextView = itemView.findViewById(R.id.gameTitle)
        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }
}