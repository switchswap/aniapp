package moe.swap.aniapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import moe.swap.aniapp.R
import moe.swap.aniapp.databinding.FragmentNotificationsBinding
import moe.swap.aniapp.ui.viewmodels.LibraryViewModel

class LibraryFragment : Fragment() {

    private lateinit var libraryViewModel: LibraryViewModel
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        libraryViewModel =
                ViewModelProvider(this).get(LibraryViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        libraryViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        setHasOptionsMenu(true)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val menuItem = menu.findItem(R.id.action_search)

        val searchView: SearchView = menuItem.actionView as SearchView
        searchView.queryHint = "Search for anime!"

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus();
                return false;
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
    }

}