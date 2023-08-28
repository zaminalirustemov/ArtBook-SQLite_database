package com.example.artbook

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artbook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var artList: ArrayList<Arts>
    private lateinit var artAdapter: ArtAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        artList= ArrayList<Arts>()

        artAdapter= ArtAdapter(artList)
        binding.recyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
        binding.recyclerView.adapter=artAdapter

        try{
            val database=this.openOrCreateDatabase("Arts", Context.MODE_PRIVATE,null)

            val cursor=database.rawQuery("SELECT * FROM arts",null)
            val artNameIx=cursor.getColumnIndex("artName")
            val idIx=cursor.getColumnIndex("id")

            while(cursor.moveToNext()){
                val name=cursor.getString(artNameIx)
                val id=cursor.getInt(idIx)
                val art=Arts(name,id)
                artList.add(art)
            }

            artAdapter.notifyDataSetChanged()

            cursor.close()

        }catch (e:Exception){
            e.printStackTrace()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.art_options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.addArtItem) {

            val intent = Intent(this@MainActivity, ArtActivity::class.java)
            intent.putExtra("info","new")
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}