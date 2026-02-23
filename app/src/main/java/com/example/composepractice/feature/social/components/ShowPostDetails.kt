package com.example.composepractice.feature.social.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.composepractice.R
import com.example.composepractice.domain.model.socialInformation.SocialPostInformationRootItem


@Composable
fun ShowPostDetails(
    postInformationRootItem: SocialPostInformationRootItem,
    isSelected: Boolean,
    onItemClick: () -> Unit ,


) {
    val backgroundColor = if (isSelected) Color.LightGray else Color.White

    Card(
        elevation = 8.dp,
        backgroundColor = backgroundColor,
        modifier = Modifier
            .padding(5.dp)
            .clickable { onItemClick() }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.user),
                contentDescription = "user",
                modifier = Modifier.padding(10.dp).size(50.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text("id ${postInformationRootItem.id}")
                Spacer(modifier = Modifier.height(10.dp))
                Text("title ${postInformationRootItem.title}")
            }
        }
    }
}