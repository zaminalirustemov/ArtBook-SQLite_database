package com.example.artbook


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artbook.databinding.RecyclerViewItemBinding

class ArtAdapter(val artList : ArrayList<Arts>) : RecyclerView.Adapter<ArtAdapter.ArtHolder>() {
    class ArtHolder(val binding:RecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtHolder {
        val binding=RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArtHolder(binding)
    }

    override fun getItemCount(): Int = artList.size

    override fun onBindViewHolder(holder: ArtHolder, position: Int) {
        holder.binding.textViewArtItem.text=artList.get(position).name
        holder.itemView.setOnClickListener{
            val intent=Intent(holder.itemView.context,ArtActivity::class.java)
            intent.putExtra("info","old")
            intent.putExtra("id",artList[position].id)
            holder.itemView.context.startActivity(intent)
        }
    }

}