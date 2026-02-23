package com.example.composepractice.feature.social

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composepractice.domain.model.socialInformation.SocialPostInformationRootItem


@Composable
fun ShowPostDetail(navController: NavHostController)
{

    // Retrieve the data
    val data = navController.previousBackStackEntry?.savedStateHandle
        ?.get<SocialPostInformationRootItem>("selected_post")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        if (data != null) {
            Text(
                text = data.body,
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.Red,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .padding(top = 30.dp, bottom = 30.dp)
                    .fillMaxWidth()
            )
        } else {

            Text(
                text = "No detail found",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }

}