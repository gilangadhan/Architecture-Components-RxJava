package id.co.imastudio.architecturecomponent.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import id.co.imastudio.architecturecomponent.Injection
import id.co.imastudio.architecturecomponent.R
import id.co.imastudio.architecturecomponent.adapter.UserAdapter
import id.co.imastudio.architecturecomponent.db.User
import id.co.imastudio.architecturecomponent.ui.add.AddActivity
import id.co.imastudio.architecturecomponent.ui.add.AddViewModel
import id.co.imastudio.architecturecomponent.ui.add.AddViewModelFactory

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), View.OnLongClickListener {

    lateinit var mainViewModel: MainActivityViewModel
    lateinit var mainViewModelFactory: MainActivityViewModelFactory


    lateinit var userAdapter: UserAdapter


    override fun onLongClick(view: View?): Boolean {
        var userModel = view!!.tag as User
        //DELETE
        mainViewModel.delete(userModel)
        return true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        userAdapter = UserAdapter(ArrayList<User>(), this)
        fab.setOnClickListener { view ->
            startActivity(Intent(this, AddActivity::class.java))
        }

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)


        recyclerView.adapter = userAdapter

        mainViewModelFactory = Injection.provideMainViewModelFactory(this)

        mainViewModel = ViewModelProviders.of(this, mainViewModelFactory).get(MainActivityViewModel::class.java)



        mainViewModel.deletestate.observe(this, Observer { state ->
            when (state) {
                true -> {
                    Toast.makeText(this, "Berhasin Hapus", Toast.LENGTH_SHORT).show()
                }
                false
                -> Toast.makeText(this, "Gagal Hapus", Toast.LENGTH_SHORT).show()
            }
        })


        mainViewModel.loadState.observe(this, Observer<List<User>> { t ->
            t?.let {
                userAdapter.addUser(t)
                Toast.makeText(this@MainActivity, "Berhasil Tampil", Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun onStart() {
        super.onStart()
        mainViewModel.loadState()
    }
}
