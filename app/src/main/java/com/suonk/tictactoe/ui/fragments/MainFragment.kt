package com.suonk.tictactoe.ui.fragments

import android.os.Bundle
import android.os.CountDownTimer
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

    private var playerOneCase1_1 = false
    private var playerOneCase1_2 = false
    private var playerOneCase1_3 = false
    private var playerOneCase2_1 = false
    private var playerOneCase2_2 = false
    private var playerOneCase2_3 = false
    private var playerOneCase3_1 = false
    private var playerOneCase3_2 = false
    private var playerOneCase3_3 = false

    private var playerTwoCase1_1 = false
    private var playerTwoCase1_2 = false
    private var playerTwoCase1_3 = false
    private var playerTwoCase2_1 = false
    private var playerTwoCase2_2 = false
    private var playerTwoCase2_3 = false
    private var playerTwoCase3_1 = false
    private var playerTwoCase3_2 = false
    private var playerTwoCase3_3 = false


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
                    playerHasPlayed(oneOne, oneOneIcon)
                }
            }
            oneTwo.setOnClickListener {
                if (!checkIfTheCaseIsAlreadyPlayed(oneTwo)) {
                    playerHasPlayed(oneTwo, oneTwoIcon)
                }
            }
            oneThree.setOnClickListener {
                if (!checkIfTheCaseIsAlreadyPlayed(oneThree)) {
                    playerHasPlayed(oneThree, oneThreeIcon)
                }
            }
            twoOne.setOnClickListener {
                if (!checkIfTheCaseIsAlreadyPlayed(twoOne)) {
                    playerHasPlayed(twoOne, twoOneIcon)
                }
            }
            twoTwo.setOnClickListener {
                if (!checkIfTheCaseIsAlreadyPlayed(twoTwo)) {
                    playerHasPlayed(twoTwo, twoTwoIcon)
                }
            }
            twoThree.setOnClickListener {
                if (!checkIfTheCaseIsAlreadyPlayed(twoThree)) {
                    playerHasPlayed(twoThree, twoThreeIcon)
                }
            }
            threeOne.setOnClickListener {
                if (!checkIfTheCaseIsAlreadyPlayed(threeOne)) {
                    playerHasPlayed(threeOne, threeOneIcon)
                }
            }
            threeTwo.setOnClickListener {
                if (!checkIfTheCaseIsAlreadyPlayed(threeTwo)) {
                    playerHasPlayed(threeTwo, threeTwoIcon)
                }
            }
            threeThree.setOnClickListener {
                if (!checkIfTheCaseIsAlreadyPlayed(threeThree)) {
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
            binding?.apply {
                when (r) {
                    oneOne -> {
                        playerOneCase1_1 = true
                    }
                    oneTwo -> {
                        playerOneCase1_2 = true
                    }
                    oneThree -> {
                        playerOneCase1_3 = true
                    }
                    twoOne -> {
                        playerOneCase2_1 = true
                    }
                    twoTwo -> {
                        playerOneCase2_2 = true
                    }
                    twoThree -> {
                        playerOneCase2_3 = true
                    }
                    threeOne -> {
                        playerOneCase3_1 = true
                    }
                    threeTwo -> {
                        playerOneCase3_2 = true
                    }
                    threeThree -> {
                        playerOneCase3_3 = true
                    }
                }
            }
        } else {
            imageView.setImageDrawable(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_x,
                    null
                )
            )
            binding?.apply {
                when (r) {
                    oneOne -> {
                        playerTwoCase1_1 = true
                    }
                    oneTwo -> {
                        playerTwoCase1_2 = true
                    }
                    oneThree -> {
                        playerTwoCase1_3 = true
                    }
                    twoOne -> {
                        playerTwoCase2_1 = true
                    }
                    twoTwo -> {
                        playerTwoCase2_2 = true
                    }
                    twoThree -> {
                        playerTwoCase2_3 = true
                    }
                    threeOne -> {
                        playerTwoCase3_1 = true
                    }
                    threeTwo -> {
                        playerTwoCase3_2 = true
                    }
                    threeThree -> {
                        playerTwoCase3_3 = true
                    }
                }
            }
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

        if(checkIfDraw()){
            Toast.makeText(
                contextActivity,
                "Draw",
                Toast.LENGTH_SHORT
            ).show()
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
        if (playerOneCase1_1 && playerOneCase2_1 && playerOneCase3_1) {
            return true
        }
        if (playerTwoCase1_1 && playerTwoCase2_1 && playerTwoCase3_1) {
            return true
        }
        if (playerOneCase1_2 && playerOneCase2_2 && playerOneCase3_2) {
            return true
        }
        if (playerTwoCase1_2 && playerTwoCase2_2 && playerTwoCase3_2) {
            return true
        }
        if (playerOneCase1_1 && playerOneCase2_1 && playerOneCase3_1) {
            return true
        }
        if (playerTwoCase1_1 && playerTwoCase2_1 && playerTwoCase3_1) {
            return true
        }

        //Vertical Victory
        if (playerOneCase1_3 && playerOneCase1_2 && playerOneCase1_1) {
            return true
        }
        if (playerTwoCase1_3 && playerTwoCase1_2 && playerTwoCase1_1) {
            return true
        }
        if (playerOneCase2_3 && playerOneCase2_2 && playerOneCase2_1) {
            return true
        }
        if (playerTwoCase2_3 && playerTwoCase2_2 && playerTwoCase2_1) {
            return true
        }
        if (playerOneCase3_3 && playerOneCase3_2 && playerOneCase3_1) {
            return true
        }
        if (playerTwoCase3_3 && playerTwoCase3_2 && playerTwoCase3_1) {
            return true
        }

        //Diagonal Victory
        if (playerOneCase3_3 && playerOneCase2_2 && playerOneCase1_1) {
            return true
        }
        if (playerOneCase1_3 && playerOneCase2_2 && playerOneCase3_1) {
            return true
        }
        if (playerTwoCase3_3 && playerTwoCase2_2 && playerTwoCase1_1) {
            return true
        }
        if (playerTwoCase1_3 && playerTwoCase2_2 && playerTwoCase3_1) {
            return true
        }

        return false
    }

    private fun checkIfDraw(): Boolean {
        if (!checkIfVictory()) {
            if (mutableListOfCases.size == 9) {
                return true
            }
        }
        return false
    }

    //endregion

    private fun resetAll() {

        mutableListOfCases.clear()

        playerOneScore = 0
        playerTwoScore = 0
        countdownTimer()
        end()
    }

    private fun end() {
        mutableListOfCases.clear()

        playerOneCase1_1 = false
        playerOneCase1_2 = false
        playerOneCase1_3 = false
        playerOneCase2_1 = false
        playerOneCase2_2 = false
        playerOneCase2_3 = false
        playerOneCase3_1 = false
        playerOneCase3_2 = false
        playerOneCase3_3 = false
        playerTwoCase1_1 = false
        playerTwoCase1_2 = false
        playerTwoCase1_3 = false
        playerTwoCase2_1 = false
        playerTwoCase2_2 = false
        playerTwoCase2_3 = false
        playerTwoCase3_1 = false
        playerTwoCase3_2 = false
        playerTwoCase3_3 = false

        binding?.apply {
            oneOneIcon.setImageResource(0)
            twoOneIcon.setImageResource(0)
            threeOneIcon.setImageResource(0)
            threeTwoIcon.setImageResource(0)
            oneTwoIcon.setImageResource(0)
            oneThreeIcon.setImageResource(0)
            threeThreeIcon.setImageResource(0)
            twoTwoIcon.setImageResource(0)
            twoThreeIcon.setImageResource(0)
        }
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
                resetAll()
            }
        }
        timer.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}