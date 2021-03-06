

package com.eliza.comps.nav.rally

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.eliza.comps.nav.rally.RallyScreen.Accounts
import com.eliza.comps.nav.rally.RallyScreen.Bills
import com.eliza.comps.nav.rally.RallyScreen.Overview
import com.eliza.comps.nav.rally.data.UserData
import com.eliza.comps.nav.rally.ui.accounts.AccountsBody
import com.eliza.comps.nav.rally.ui.accounts.SingleAccountBody
import com.eliza.comps.nav.rally.ui.bills.BillsBody
import com.eliza.comps.nav.rally.ui.components.RallyTabRow
import com.eliza.comps.nav.rally.ui.overview.OverviewBody
import com.eliza.comps.nav.rally.ui.theme.RallyTheme

/**
 * This Activity recreates part of the Rally Material Study from
 * https://material.io/design/material-studies/rally.html
 */
class RallyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RallyApp()
        }
    }
}

@Composable
fun RallyApp() {
    RallyTheme {
        val allScreens = RallyScreen.values().toList()
        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()
        val currentScreen = RallyScreen.fromRoute(backstackEntry.value?.destination?.route)

        Scaffold(
            topBar = {
                RallyTabRow(
                    allScreens = allScreens,
                    onTabSelected = { screen ->
                        navController.navigate(screen.name)
                    },
                    currentScreen = currentScreen
                )
            }
        ) { innerPadding ->
            RallyNavHost(navController, modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun RallyNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Overview.name,
        modifier = modifier
    ) {
        composable(Overview.name) {
            OverviewBody(
                onClickSeeAllAccounts = { navController.navigate(Accounts.name) },
                onClickSeeAllBills = { navController.navigate(Bills.name) },
                onAccountClick = { name ->
                    navigateToSingleAccount(navController, name)
                },
            )
        }
        composable(Accounts.name) {
            AccountsBody(accounts = UserData.accounts) { name ->
                navigateToSingleAccount(navController = navController, accountName = name)
            }
        }
        composable(Bills.name) {
            BillsBody(bills = UserData.bills)
        }
        val accountsName = Accounts.name
        composable(
            route = "$accountsName/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                }
            ),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "rally://$accountsName/{name}"
                }
            ),
        ) { entry ->
            val accountName = entry.arguments?.getString("name")
            val account = UserData.getAccount(accountName)
            SingleAccountBody(account = account)
        }
    }
}

private fun navigateToSingleAccount(navController: NavHostController, accountName: String) {
    navController.navigate("${Accounts.name}/$accountName")
}
