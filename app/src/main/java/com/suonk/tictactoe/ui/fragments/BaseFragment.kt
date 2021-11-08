package com.suonk.tictactoe.ui.fragments

import androidx.fragment.app.Fragment
import com.suonk.tictactoe.ui.activity.MainActivity

abstract class BaseFragment : Fragment() {

    protected val navController by lazy {
        (activity as MainActivity).navController
    }

    protected val contextActivity by lazy {
        activity as MainActivity
    }
}