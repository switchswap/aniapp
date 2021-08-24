package moe.swap.aniapp.ui.downloads

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DownloadsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is downloads Fragment"
    }
    val text: LiveData<String> = _text
}