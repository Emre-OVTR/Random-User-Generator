package emre.turhal.randomusergenerator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import emre.turhal.randomusergenerator.model.ResultsItem
import emre.turhal.randomusergenerator.retrofit.ApiService
import emre.turhal.randomusergenerator.retrofit.Retrofit
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {

    private val service = Retrofit.getRetrofitInstance().create(ApiService::class.java)

    val usersLiveData: MutableLiveData<List<ResultsItem?>> = MutableLiveData()

    fun getUsers(){
        viewModelScope.launch {
            //get only 20 users by request
            val response = service.getResults(20)
            if (response.isSuccessful){
                val apiResponse = response.body()
                val results = apiResponse?.results
                usersLiveData.postValue(results)
            }
        }
    }
}