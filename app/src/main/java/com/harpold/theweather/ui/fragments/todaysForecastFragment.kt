package com.harpold.theweather.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.harpold.theweather.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1Today = "param1"
private const val ARG_PARAM2Today = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [todaysForecastFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class todaysForecastFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1Today: String? = null
    private var param2Today: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1Today = it.getString(ARG_PARAM1Today)
            param2Today = it.getString(ARG_PARAM2Today)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todays_forecast, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment todaysForecastFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            todaysForecastFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}