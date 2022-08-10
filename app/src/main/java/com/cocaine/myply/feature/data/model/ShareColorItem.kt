package com.cocaine.myply.feature.data.model

import com.cocaine.myply.R

/**
 * 프레임 뷰와 폰트 색상 정보를 담고 있는 클래스
 * */
enum class ShareColorItem(val viewColor: Int, val fontColor: Int) {
    Frame1(R.color.white, R.color.black),
    Frame2(R.color.primary_green_basic, R.color.white),
    Frame3(R.color.primary_green_light, R.color.white),
    Frame4(R.color.secondary_red, R.color.white),
    Frame5(R.color.secondary_brown, R.color.white),
    Frame6(R.color.secondary_butter, R.color.black),
    Frame7(R.color.secondary_blue, R.color.black)
}