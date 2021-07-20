package com.example.practicalapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicalapp.adapter.RvUserAdapter
import com.example.practicalapp.model.UserModel
import kotlinx.android.synthetic.main.activity_pagination.*

class PaginationActivity : AppCompatActivity() {

    private lateinit var pageViewModel: PageViewModel
    private lateinit var adapter:RvUserAdapter
    private var page:Int = 1
    private lateinit var mLayoutManager:LinearLayoutManager
    private var list:ArrayList<UserModel.Data> = ArrayList()
    private var loading:Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagination)
        pageViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(PageViewModel::class.java)
        init()
    }

    private fun init(){
    mLayoutManager = LinearLayoutManager(applicationContext)
    recyclerUser.layoutManager = mLayoutManager
        adapter = RvUserAdapter(applicationContext,list)
        recyclerUser.adapter = adapter

        getUser()
        recyclerUser.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                    val visibleItemCount = mLayoutManager.getChildCount();
                    val totalItemCount = mLayoutManager.getItemCount();
                    val pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();
                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false
                            page += 1
                            getUser()
                        }
                    }
            }
        })
    }

    private fun getUser(){
        progress_layout.visibility = View.VISIBLE
        pageViewModel.getUser(page)
        pageViewModel.userData.observe(this,{
            progress_layout.visibility = View.GONE
            loading = true
            if (it !=null){
                if (it.data.isNotEmpty()){
                    list.addAll(it.data)
                    adapter.notifyDataSetChanged()
                }else{
                    loading = false
                }
            }
        })
    }

}