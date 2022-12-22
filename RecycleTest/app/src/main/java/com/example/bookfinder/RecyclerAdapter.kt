package com.example.bookfinder

import android.content.Context
import android.content.Intent
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bookfinder.databinding.ItemListBinding

class RecyclerAdapter(val context: Context/*, val members:ArrayList<Twice>*/): RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {
    /*inner class MyViewHolder(view:View): RecyclerView.ViewHolder(view){
        val imageMember: ImageView
        val txtName: TextView
        val txtNick: TextView
        val imageFlag: ImageView

        init{
            imageMember = view.findViewById(R.id.img_member)
            txtName = view.findViewById(R.id.tv_name)
            txtNick = view.~~~
        }
        view.setOnClickListener {
            ~~~
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                val member = members[position]
                Toast.makeText~~

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val itemView = layoutInflater.inflate(R.layout.item_list, parent, false)
        val holder = ViewHolder(itemView)
        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val number = members[position]
        holder.imageMember.setImageResources(member.imageId)
        holder.txtName.text = member.name
        holder.txtNick.text = ~~
    }
    override fun getItemCount(): Int {
        return members.size
    }*/
    var dataList = mutableListOf<Twice>()

    inner class MyViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(twice: Twice){
            binding.imgMember.setImageResource(twice.imageId)
            binding.tvName.text = twice.name
            binding.tvNick.text = twice.nick
            binding.imgFlag.setImageResource(twice.flagId)
            binding.itemList.setOnClickListener {
                //Toast.makeText(context, "이름 ${twice.name}", Toast.LENGTH_SHORT).show()
                Intent(context, DetailActivity::class.java).apply {
                    putExtra("image", twice.imageId)
                    putExtra("name", twice.name)
                    putExtra("nick", twice.nick)
                }.run { context.startActivity(this) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //val number = members[position]
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}