package emre.turhal.randomusergenerator.ui

import android.os.Bundle
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
    }

    companion object{
        const val USER = "USER"
    }

    private fun displayUser(){
        val imageView = binding.userPhoto
        val name = binding.name
        val dob = binding.dob
        user = intent.getSerializableExtra(USER) as ResultsItem
        Glide.with(this).load(user.picture?.large)
            .apply(RequestOptions().circleCrop()).into(imageView)

       name.text = "${user.name?.first} ${user.name?.last}"


    }
}