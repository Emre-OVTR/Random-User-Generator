package emre.turhal.randomusergenerator.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import emre.turhal.randomusergenerator.databinding.ActivityUserDetailsBinding
import emre.turhal.randomusergenerator.model.ResultsItem

class UserDetailsActivity : AppCompatActivity() {


    private lateinit var binding : ActivityUserDetailsBinding
    private lateinit var user:ResultsItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        displayUser()
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "User Details"

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    companion object{
        const val USER = "USER"
    }

    private fun displayUser(){
        val imageView = binding.userPhoto
        user = intent.getSerializableExtra(USER) as ResultsItem
        Glide.with(this).load(user.picture?.large)
            .apply(RequestOptions().circleCrop()).into(imageView)

        binding.name.text = "${user.name?.first} ${user.name?.last}"
        binding.dob.text = user.dob?.age.toString()
        binding.userAddress.text = user.location?.street?.name
        binding.userCity.text = user.location?.city
        binding.userCountry.text = user.location?.country
        binding.userEmail.text = user.email
        binding.userPhone.text = user.phone


    }
}