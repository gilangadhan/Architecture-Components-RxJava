package id.co.imastudio.architecturecomponent.ui.add

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import id.co.imastudio.architecturecomponent.Injection
import id.co.imastudio.architecturecomponent.R
import id.co.imastudio.architecturecomponent.db.User
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.content_add.*

class AddActivity : AppCompatActivity() {

    lateinit var mAddViewModelFactory: AddViewModelFactory

    lateinit var mAddViewModel: AddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setSupportActionBar(toolbar)

        mAddViewModelFactory = Injection.provideViewModelFactory(this)

        mAddViewModel = ViewModelProviders.of(this, mAddViewModelFactory).get(AddViewModel::class.java)

        fab.setOnClickListener {
            val user = User()
            user.nama_user = edtNama.text.toString()
            user.gambar_user = ""
            user.nomer_user = edtNomer.text.toString()
            mAddViewModel.addState(user)
        }

        mAddViewModel.addState.observe(this, Observer { state ->
            when (state) {
                true ->
                    success()

                false
                -> Toast.makeText(this, "Gagal Input", Toast.LENGTH_SHORT).show()
            }
        })


    }

    private fun success() {
        finish()
        Toast.makeText(this, "Sukses Input", Toast.LENGTH_SHORT).show()
    }

}
