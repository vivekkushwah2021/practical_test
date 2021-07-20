package com.example.practicalapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.practicalapp.adapter.RvGridAdapter
import com.example.practicalapp.model.GridModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter:RvGridAdapter
    private var list:ArrayList<GridModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListener()
    }

    private fun initListener(){

        btn.setOnClickListener {
            var noGrid = etNumber.text.toString().toDouble()
            if (checkPerfectSquare(noGrid)){
                list.clear()
                recyclerGrid.layoutManager =GridLayoutManager(applicationContext,Math.sqrt(noGrid).toInt())
                val decorator = ItemOffsetDecoration(12)
                recyclerGrid.addItemDecoration(decorator)

                for (i in 0..noGrid.toInt()-1){
                    list.add(GridModel(false,R.color.white))
                }

                adapter = RvGridAdapter(applicationContext,list){
                    if (list.size>it){
                        list[it].color = R.color.blue
                        adapter.notifyItemChanged(it)
                        if (list.size != it+1){
                            Handler(Looper.getMainLooper()).postDelayed({
                                list[it+1].color = R.color.red
                                adapter.notifyItemChanged(it+1)
                            }, 800)
                        }else{
                            Toast.makeText(applicationContext,getString(R.string.won_game),Toast.LENGTH_SHORT).show()
                        }

                    }

                }
                recyclerGrid.adapter = adapter

                Handler(Looper.getMainLooper()).postDelayed({
                      list[0].color = R.color.red
                    adapter.notifyItemChanged(0)
                }, 800)

            }else{
                Toast.makeText(applicationContext,getString(R.string.enter_valid_number),Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun checkPerfectSquare(number:Double):Boolean{
        //Square Root of Number
        val sq= Math.sqrt(number)
        val f = Math.floor(sq)
        return sq==f
    }

}