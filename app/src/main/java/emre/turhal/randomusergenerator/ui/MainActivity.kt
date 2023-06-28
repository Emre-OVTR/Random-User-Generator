package emre.turhal.randomusergenerator.ui

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import emre.turhal.randomusergenerator.R
import emre.turhal.randomusergenerator.ViewModel
import emre.turhal.randomusergenerator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: ViewModel
    private lateinit var userAdapter: UsersListAdapter
    private lateinit var binding : ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        configureToolbarAndRecyclerView()
        showUsersList()
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

    private fun configureToolbarAndRecyclerView(){
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = (getString(R.string.app_name))
        val recyclerView = binding.recyclerview
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
        binding.refreshButton.setOnClickListener {
            refreshUsersList()
        }
    }

}