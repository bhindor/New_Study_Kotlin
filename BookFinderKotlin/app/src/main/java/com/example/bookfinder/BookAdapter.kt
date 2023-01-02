package com.example.bookfinder

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class BookAdapter(context: Context) : RecyclerView.Adapter<BookAdapter.MyViewHolder>() {
    val context: Context
    val books: ArrayList<Book>

    init {
        this.context = context
        this.books = ArrayList()
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageThumbnail: ImageView
        val txtTitle: TextView
        val txtAuthors: TextView
        val txtPublisher: TextView
        val txtPrice: TextView

        init {
            imageThumbnail = view.findViewById(R.id.img_main)
            txtTitle = view.findViewById(R.id.tv_title)
            txtAuthors = view.findViewById(R.id.tv_author)
            txtPublisher = view.findViewById(R.id.tv_publisher)
            txtPrice = view.findViewById(R.id.tv_price)

            view.setOnClickListener {
                val pos = adapterPosition
                if(pos != RecyclerView.NO_POSITION) {
                    val book = item(pos)
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra("url", book.url).run { context.startActivity(this) }

                }
            }
        }
    }

    fun add(book: Book) {
        books.add(book)
    }

    fun clear() {
        books.clear()
    }

    fun item(pos: Int): Book {
       return books.get(pos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.book_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val book = books[position]
        Glide.with(holder.itemView.context).load(book.thumbnail).into(holder.imageThumbnail)
        holder.txtTitle.text = book.title
        holder.txtAuthors.text = book.authors
        holder.txtPublisher.text = book.publisher
        holder.txtPrice.text = book.price.toString()

    }

    override fun getItemCount(): Int {
        return books.size
    }
}