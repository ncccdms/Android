package com.ncccdms.todolistbagian3.nav

import com.ncccdms.todolistbagian3.core.Constant.DETAIL_SCREEN
import com.ncccdms.todolistbagian3.core.Constant.FORGOT_PASSWORD_SCREEN
import com.ncccdms.todolistbagian3.core.Constant.HOME_SCREEN
import com.ncccdms.todolistbagian3.core.Constant.LIST_SCREEN
import com.ncccdms.todolistbagian3.core.Constant.MAIN_SCREEN
import com.ncccdms.todolistbagian3.core.Constant.MENU_SCREEN
import com.ncccdms.todolistbagian3.core.Constant.SIGN_IN_SCREEN
import com.ncccdms.todolistbagian3.core.Constant.SIGN_UP_SCREEN
import com.ncccdms.todolistbagian3.core.Constant.SPLASH_SCREEN
import com.ncccdms.todolistbagian3.core.Constant.VERIFY_EMAIL_SCREEN

sealed class Screen(val route:String) {
    object MenuScreen: Screen(MENU_SCREEN)
    object SplashScreen: Screen(SPLASH_SCREEN)
    object SignInScreen: Screen(SIGN_IN_SCREEN)
    object ForgotPasswordScreen: Screen(FORGOT_PASSWORD_SCREEN)
    object SignUpScreen: Screen(SIGN_UP_SCREEN)
    object VerifyEmailScreen: Screen(VERIFY_EMAIL_SCREEN)
    object HomeScreen: Screen(HOME_SCREEN)
    object MainScreen: Screen(MAIN_SCREEN)
    object ListScreen: Screen(LIST_SCREEN)
    object DetailListScreen: Screen(DETAIL_SCREEN){
        fun createRoute(listId: Int) = "detail/$listId"
    }
}