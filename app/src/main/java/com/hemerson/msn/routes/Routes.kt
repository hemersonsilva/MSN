package com.hemerson.msn.routes

sealed class Routes(val routeName: String) {
    object Login : Routes("login")
    object Register : Routes("register")
    object Messages : Routes("messages")
    object Contacts : Routes("contacts")
    object Chat : Routes("chat")
}
