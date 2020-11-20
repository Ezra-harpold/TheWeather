package com.harpold.theweather.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.harpold.theweather.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1Tomorrow = "param1"
private const val ARG_PARAM2Tomorrow = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [tomorrowForecastFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class tomorrowForecastFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1Tomorrow: String? = null
    private var param2Tomorrow: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1Tomorrow = it.getString(ARG_PARAM1Tomorrow)
            param2Tomorrow = it.getString(ARG_PARAM2Tomorrow)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tomorrow_forecast, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1Tomorrow Parameter 1.
         * @param param2Tomorrow Parameter 2.
         * @return A new instance of fragment tomorrowForecastFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            tomorrowForecastFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}