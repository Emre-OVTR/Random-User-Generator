package emre.turhal.randomusergenerator.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import emre.turhal.randomusergenerator.R
import emre.turhal.randomusergenerator.ViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: ViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var userAdapter: UsersListAdapter
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureToolbar()
        showUsersList()
        configureRecyclerView()
        refreshUsersList()
        launchNewRequest()
    }

    //fill recyclerview with data fetched from network
    private fun showUsersList(){
        userViewModel = ViewModel()
        userViewModel.getUsers()
        userViewModel.usersLiveData.observe(this) {
            if (it.isNotEmpty()) {
                userAdapter.submitList(it)
            } else {
                Toast.makeText(applicationContext, "Problème lors de la connexion au serveur", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun configureToolbar(){
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = (getString(R.string.app_name))
    }

    private fun configureRecyclerView(){
        recyclerView = findViewById(R.id.recyclerview)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
        userAdapter = UsersListAdapter()
        recyclerView.adapter = userAdapter
    }

    // fire a network call to refresh apiresponse
    private fun refreshUsersList(){
        userViewModel.getUsers()
        userViewModel.usersLiveData.observe(this){
            userAdapter.submitList(it)
        }
    }

    //Refresh UI with new list of users
    private fun launchNewRequest(){
        button = findViewById(R.id.refreshButton)
        button.setOnClickListener {
            refreshUsersList()
        }
    }

}