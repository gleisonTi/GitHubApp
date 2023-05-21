package com.gleisonti.githubusers.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gleisonti.githubusers.presenter.detailuser.UserDetailView
import com.gleisonti.githubusers.presenter.listusers.UserList
import com.gleisonti.githubusers.presenter.listusers.UserListViewModel
import com.gleisonti.githubusers.ui.theme.GitHubUsersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val userListViewModel : UserListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitHubUsersTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    TitlePage(navController)
                                },
                                navigationIcon = {
                                    BackButton(navController = navController)
                                }
                            )
                        },
                    ){ contentPadding ->
                        Box(modifier = Modifier.padding(contentPadding))
                        NavHost(navController = navController, startDestination = "listUsers") {
                            composable("listUsers") { UserList(userListViewModel, navController) }
                            composable("detailUsers") { UserDetailView(userListViewModel, navController) }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun BackButton(navController: NavController) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination?.route

        if (currentDestination == "detailUsers") {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    }

    @Composable
    fun TitlePage(navController: NavController){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination?.route

        val title = when (currentDestination) {
            "listUsers" -> "Lista De Usuários"
            "detailUsers" -> "Detalhe do Usuário"
            else -> "GitHubApp"
        }
        
        Text(text = title)

    }


}

