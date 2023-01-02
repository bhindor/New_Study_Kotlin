package com.example.bookfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

class MainActivity : AppCompatActivity() {
    val END_POINT = "Https://dapi.kakao.com/v3/search/book?query=%s&page=%d"
    val API_KEY = "979976d0d53efeba6a80aebc267af9e8"
    var page = 1
    var adapter: BookAdapter? = null
    val btn_1: Button by lazy {
        findViewById(R.id.btn_1)
    }
    val btn_2: Button by lazy {
        findViewById(R.id.btn_2)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = BookAdapter(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler)
        recyclerView.adapter = adapter
        val manager = LinearLayoutManager(this)
        recyclerView.layoutManager = manager
        val deco = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(deco)
        val searchView: SearchView = findViewById(R.id.search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val task = BookThreadTask()
                task.execute(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })


        btn_1.isEnabled = false
        btn_2.setOnClickListener {
            page++
            getBook(searchView.query.toString())
        }
        btn_1.setOnClickListener {
            page--
            getBook(searchView.query.toString())
        }
    }

    private fun getBook(query: String?) {
        btn_1.isEnabled = page > 1
        val task = BookThreadTask()
        task.execute(query)
    }

    fun search(query: String?): String? {
        var result = ""
        var str: String?
        try {
            val strURL = String.format(END_POINT, query, page)
            Log.d("testasd", strURL)
            val url = URL(strURL)
            val conn = url.openConnection() as HttpURLConnection
            conn.setRequestProperty("Authorization", "KakaoAK ${API_KEY}")
            conn.connect()

            if (conn.responseCode == HttpURLConnection.HTTP_OK) {
                val tmp = InputStreamReader(conn.inputStream, StandardCharsets.UTF_8)
                val reader = BufferedReader(tmp)
                val buffer = StringBuffer()
                while (reader.readLine().also { str = it } != null) {
                    buffer.append(str)
                }
                result = buffer.toString()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
    }

    fun parsing(json: String) {
        val root = JSONObject(json)
        val meta = root.getJSONObject("meta")
        val isEnd = meta.getBoolean("is_end")
        btn_2.isEnabled = !isEnd
        val documents = root.getJSONArray("documents")
        adapter?.clear()
        for (i in 0 until documents.length()) {
            val doc = documents.getJSONObject(i)
            val title = doc.getString("title")
            val publisher = doc.getString("publisher")
            val authors = doc.getJSONArray("authors").join(",")
            val price = doc.getInt("price")
            val thumbnail = doc.getString("thumbnail")
            val url = doc.getString("url")
            val book = Book(title, publisher, authors, price, thumbnail, url)
            adapter?.add(book)
        }
        adapter?.notifyDataSetChanged()
    }

    inner class BookThreadTask : ThreadTask<String?, String?>() {
        override fun onPreExecute() {

        }

        override fun doInBackground(arg: String?): String? {
            return search(arg)
        }

        override fun onPostExecute(result: String?) {
            parsing(result!!)
        }

    }
}