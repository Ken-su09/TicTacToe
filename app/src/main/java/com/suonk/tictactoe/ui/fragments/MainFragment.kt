package com.suonk.tictactoe.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.res.ResourcesCompat
import com.suonk.tictactoe.R
import com.suonk.tictactoe.databinding.FragmentMainBinding

class MainFragment : BaseFragment() {

    private var binding: FragmentMainBinding? = null
    private var mutableListOfCases = mutableListOf<RelativeLayout>()
    private var currentTurn = 1

    private var playerOneScore = 0
    private var playerTwoScore = 0

    private var case1_1 = false
    private var case1_2 = false
    private var case1_3 = false
    private var case2_1 = false
    private var case2_2 = false
    private var case2_3 = false
    private var case3_1 = false
    private var case3_2 = false
    private var case3_3 = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayPlayersScore()
        countdownTimer()
        playerClick()

        binding?.resetGame?.setOnClickListener {
            resetAll()
        }
    }

    private fun boardInitialize(r: RelativeLayout) {
        mutableListOfCases.add(r)
    }

    private fun displayPlayersScore() {
        binding?.apply {
            player1ScoreValue.text = playerOneScore.toString()
            player2ScoreValue.text = playerTwoScore.toString()
        }
    }

    private fun playerClick() {
        binding?.apply {
            oneOne.setOnClickListener {
                if (!checkIfTheCaseIsAlreadyPlayed(oneOne)) {
                    case1_1 = true
                    playerHasPlayed(oneOne, oneOneIcon)
                }
            }
            oneTwo.setOnClickListener {
                if (!checkIfTheCaseIsAlreadyPlayed(oneTwo)) {
                    case1_2 = true
                    playerHasPlayed(oneTwo, oneTwoIcon)
                }
            }
            oneThree.setOnClickListener {
                if (!checkIfTheCaseIsAlreadyPlayed(oneThree)) {
                    case1_3 = true
                    playerHasPlayed(oneThree, oneThreeIcon)
                }
            }
            twoOne.setOnClickListener {
                if (!checkIfTheCaseIsAlreadyPlayed(twoOne)) {
                    case2_1 = true
                    playerHasPlayed(twoOne, twoOneIcon)
                }
            }
            twoTwo.setOnClickListener {
                if (!checkIfTheCaseIsAlreadyPlayed(twoTwo)) {
                    case2_2 = true
                    playerHasPlayed(twoTwo, twoTwoIcon)
                }
            }
            twoThree.setOnClickListener {
                if (!checkIfTheCaseIsAlreadyPlayed(twoThree)) {
                    case2_3 = true
                    playerHasPlayed(twoThree, twoThreeIcon)
                }
            }
            threeOne.setOnClickListener {
                if (!checkIfTheCaseIsAlreadyPlayed(threeOne)) {
                    case3_1 = true
                    playerHasPlayed(threeOne, threeOneIcon)
                }
            }
            threeTwo.setOnClickListener {
                if (!checkIfTheCaseIsAlreadyPlayed(threeTwo)) {
                    case3_2 = true
                    playerHasPlayed(threeTwo, threeTwoIcon)
                }
            }
            threeThree.setOnClickListener {
                if (!checkIfTheCaseIsAlreadyPlayed(threeThree)) {
                    case3_3 = true
                    playerHasPlayed(threeThree, threeThreeIcon)
                }
            }
        }
    }

    private fun playerHasPlayed(r: RelativeLayout, imageView: AppCompatImageView) {
        boardInitialize(r)
        currentTurn++
        playerTurnSymbol()
        if (whichPlayerIsTurn()) {
            imageView.setImageDrawable(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_circle,
                    null
                )
            )
        } else {
            imageView.setImageDrawable(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_x,
                    null
                )
            )
        }

        if (checkIfVictory()) {
            currentTurn -= 1
            if (whichPlayerIsTurn()) {
                playerOneScore++
            } else {
                playerTwoScore++
            }
            currentTurn = 1
            displayPlayersScore()
            end()
        }
    }

    private fun playerTurnSymbol() {
        if (whichPlayerIsTurn()) {
            binding?.player1Turn?.visibility = View.INVISIBLE
            binding?.player2Turn?.visibility = View.VISIBLE
        } else {
            binding?.player1Turn?.visibility = View.VISIBLE
            binding?.player2Turn?.visibility = View.INVISIBLE
        }
    }

    private fun whichPlayerIsTurn(): Boolean {
        return currentTurn % 2 == 0
    }

    //region =========================================== Check If ===========================================

    private fun checkIfTheCaseIsAlreadyPlayed(r: RelativeLayout): Boolean {
        return mutableListOfCases.contains(r)
    }

    private fun checkIfVictory(): Boolean {
        //Horizontal Victory
        if (case1_1 && case2_1 && case3_1) {
            return true
        }
        if (case1_2 && case2_2 && case3_2) {
            return true
        }
        if (case1_1 && case2_1 && case3_1) {
            return true
        }

        //Vertical Victory
        if (case1_3 && case1_2 && case1_1) {
            return true
        }
        if (case2_3 && case2_2 && case2_1) {
            return true
        }
        if (case3_3 && case3_2 && case3_1) {
            return true
        }

        //Diagonal Victory
        if (case3_3 && case2_2 && case1_1) {
            return true
        }
        if (case1_3 && case2_2 && case3_1) {
            return true
        }

        return false
    }

    //endregion

    private fun resetAll() {
        case1_1 = false
        case1_2 = false
        case1_3 = false
        case2_1 = false
        case2_2 = false
        case2_3 = false
        case3_1 = false
        case3_2 = false
        case3_3 = false

        mutableListOfCases.clear()

        playerOneScore = 0
        playerTwoScore = 0
        countdownTimer()
    }

    private fun end(){
        mutableListOfCases.clear()

//        binding?.apply {
//            oneOneIcon.setImageDrawable(Color.TRANSPARENT)
//            twoOneIcon.setDrawa
//            threeOneIcon.setDrawa
//            oneTwoIcon.setDrawa
//            oneThreeIcon.setDrawa
//            threeThreeIcon.setDrawa
//            twoTwoIcon.setDrawa
//        }
    }

    private fun countdownTimer() {
        val timer = object : CountDownTimer(180000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val m = (millisUntilFinished / 1000) / 60
                val s = (millisUntilFinished / 1000) % 60
                binding?.countdown?.text = String.format("%02d:%02d", m, s)
            }

            override fun onFinish() {
                when {
                    playerOneScore > playerTwoScore -> {
                        Toast.makeText(
                            contextActivity,
                            "Game over, the winner is Player 1, $playerOneScore to $playerTwoScore",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    playerOneScore < playerTwoScore -> {
                        Toast.makeText(
                            contextActivity,
                            "Game over, the winner is Player 2 $playerTwoScore to $playerOneScore",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    else -> {
                        Toast.makeText(
                            contextActivity,
                            "Game over, it is a DRAW !",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
        timer.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}