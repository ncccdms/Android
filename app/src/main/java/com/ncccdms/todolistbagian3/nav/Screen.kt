package com.ncccdms.todolistbagian3.nav

import com.ncccdms.todolistbagian3.core.Constant.FORGOT_PASSWORD_SCREEN
import com.ncccdms.todolistbagian3.core.Constant.MENU_SCREEN
import com.ncccdms.todolistbagian3.core.Constant.PROFILE_SCREEN
import com.ncccdms.todolistbagian3.core.Constant.SIGN_IN_SCREEN
import com.ncccdms.todolistbagian3.core.Constant.SIGN_UP_SCREEN
import com.ncccdms.todolistbagian3.core.Constant.VERIFY_EMAIL_SCREEN

sealed class Screen(val route:String) {
    object MenuScreen: Screen(MENU_SCREEN)
    object SignInScreen: Screen(SIGN_IN_SCREEN)
    object ForgotPasswordScreen: Screen(FORGOT_PASSWORD_SCREEN)
    object SignUpScreen: Screen(SIGN_UP_SCREEN)
    object VerifyEmailScreen: Screen(VERIFY_EMAIL_SCREEN)
    object ProfileScreen: Screen(PROFILE_SCREEN)
}