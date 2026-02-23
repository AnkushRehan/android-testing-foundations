package com.example.composepractice.domain.model.socialInformation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class SocialPostInformationRootItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
) : Parcelable