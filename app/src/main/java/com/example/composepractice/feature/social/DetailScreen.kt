package com.example.composepractice.feature.social

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composepractice.R
import com.example.composepractice.domain.model.socialInformation.SocialPostInformationRootItem
import com.example.composepractice.feature.social.components.ShowPostDetails
import com.example.composepractice.feature.social.viewmodel.SocialViewModel
import com.example.composepractice.navigation.Routes

import com.example.composeproject.data.networkSection.responseUtil.Status


@Preview(showSystemUi = true)
@Composable
fun DetailScreen(navController: NavHostController) {



    val socialViewModel: SocialViewModel = hiltViewModel()


    var selectedTab by remember { androidx.compose.runtime.mutableIntStateOf(0) }


    Scaffold(
        bottomBar = {

            BottomNavigation(backgroundColor = Color.White , elevation = 8.dp) {


                BottomNavigationItem(selected = selectedTab==0 , onClick = {selectedTab = 0 },
                    label = {Text("home")} , icon = {
                        Icon(painter = painterResource(R.drawable.home) , contentDescription = "", Modifier.size(24.dp))})


                BottomNavigationItem(selected = selectedTab==1 , onClick = {selectedTab = 1},
                    label = {Text("albums")} , icon = {
                        Icon(painter = painterResource(R.drawable.image_gallery) , contentDescription = "" , modifier =
                            Modifier.size(24.dp))})

            }

        }

    ) { paddingValues ->

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
        ) {
            when (selectedTab) {
                0 -> HomeScreen(socialViewModel = socialViewModel, navController = navController)
                1 -> ShowLocalDatabase(viewModel = socialViewModel, navController = navController)
            }
        }
    }



    // type miss match errors

//    val socialViewModel: SocialViewModel = hiltViewModel()  // normal flow without bottom
//
//
//    Column(modifier = Modifier.fillMaxSize()) {
//        Text(
//            "API Data (Real-time)",
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxWidth(),
//            style = TextStyle(textAlign = TextAlign.Center, fontSize = 18.sp, fontWeight = FontWeight.Bold)
//        )
//
//        val posts by socialViewModel.response.collectAsState()
//
//        LazyColumn(modifier = Modifier.weight(1f)) {
//            items(posts ?: emptyList()) { data ->
//                ShowPostDetails(data , navController)
//            }
//        }
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//
//
//
//
//    }
//
//
//       ShowLocalDatabase(socialViewModel)

}



@Composable
private fun HomeScreen(socialViewModel : SocialViewModel , navController : NavHostController) {


    var selectedItemId by remember { androidx.compose.runtime.mutableIntStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            "API Data (Real-time)",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 20.dp),
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )

        val resource by socialViewModel.response.collectAsStateWithLifecycle()

        when (resource!!.status) {
            Status.LOADING -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            Status.ERROR -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "An error occurred", color = Color.Red)
                }
            }
            Status.SUCCESS -> {
                val posts = resource?.data
                LazyColumn {
                    items(posts ?: emptyList()) { post ->
                        ShowPostDetails(
                            postInformationRootItem = post,
                            isSelected = post.id == selectedItemId,
                            onItemClick = {

                                selectedItemId = post.id

                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

    }
}


@Composable
private fun ShowLocalDatabase(viewModel: SocialViewModel, navController: NavHostController) {
    val localPosts by viewModel.localPosts.collectAsState()


    var selectedItemId by remember { androidx.compose.runtime.mutableIntStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            "Local Database (Saved Items: ${localPosts.size})",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            style = TextStyle(textAlign = TextAlign.Center, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Blue)
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {

            items(localPosts) { post ->
                ShowPostDetails(
                    postInformationRootItem = post,
                    isSelected = post.id == selectedItemId,
                    onItemClick = {

                        selectedItemId = post.id

                    }
                )
            }
        }



    }
}


