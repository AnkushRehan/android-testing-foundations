package com.example.composepractice.feature.loginandsignup

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.composepractice.R
import com.example.composepractice.feature.loginandsignup.viewmodel.LoginAndSignUpViewModel
import com.example.composepractice.navigation.Routes



@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun LoginScreen(navController  : NavHostController) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.dark_blue)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 70.dp, start = 18.dp, end = 18.dp)
        ) {

            Icon(
                painter = painterResource(R.drawable.left_arrow),
                contentDescription = "back",
                modifier = Modifier
                    .size(18.dp)
                    .align(Alignment.CenterStart),
                tint = Color.White
            )




            Text(
                text = "Don't have an account?",
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )


            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .background(
                        color = colorResource(id = R.color.light_blue),
                        shape = RoundedCornerShape(6.dp)
                    )

            )



            {


                Headings(
                    "Sign Up", Color.White, TextStyle(fontSize = 13.sp),
                    modifier = Modifier.padding(
                        start = 13.dp,
                        top = 8.dp,
                        bottom = 8.dp,
                        end = 13.dp
                    )
                )

            }


        }


        Headings(
            "Jobsly", Color.White, TextStyle(
                fontSize = 20.sp, fontFamily =
                    FontFamily(
                        Font(R.font.poppins_bold)
                    )
            ), modifier = Modifier.padding(top = 30.dp)
        )



        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp)
        )
        {



            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, end = 20.dp)
                    .background(
                        colorResource(R.color.light_blue),
                        shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
                    )
            )
            {

            }


            BottomSheet(navController)

        }





    }

}



@Composable
private fun BottomSheet(
    navController: NavHostController,
    viewModel: LoginAndSignUpViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.uiState.collectAsStateWithLifecycle() // or collectAsStateWithLifecycle()

    // One-off events: Toast + Navigation

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is LoginaAndSignUpEffect.ShowMessage -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
                LoginaAndSignUpEffect.NavigateHome -> {
                    navController.navigate(Routes.DETAIL_SCREEN)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 12.dp)
            .background(
                colorResource(R.color.white),
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        ) {
            Headings(
                "Get started free.",
                Color.Black,
                TextStyle(
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Headings(
                "Enter your details bellow",
                Color.Black,
                TextStyle(
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 25.dp, end = 25.dp)
        ) {
            LoginInputFields(
                label = "username",
                value = state.userName,
                onValueChange = viewModel::onUserNameChange,
                isPassword = false
            )

            Spacer(modifier = Modifier.height(10.dp))

            LoginInputFields(
                label = "password",
                value = state.password,
                onValueChange = viewModel::onPasswordChange,
                visualTransformation = PasswordVisualTransformation(),
                keyboardType = KeyboardType.Password,
                isPassword = true
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 25.dp, end = 25.dp)
                .background(
                    brush = androidx.compose.ui.graphics.Brush.linearGradient(
                        colors = listOf(
                            colorResource(id = R.color.dark_blue),
                            colorResource(id = R.color.light_purple)
                        )
                    ),
                    shape = RoundedCornerShape(15.dp)
                )
                .clickable { viewModel.onSignInClicked(context) } // ✅ no params, no callback
        ) {
            Headings(
                "Login",
                Color.White,
                TextStyle(
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, bottom = 15.dp)
            )
        }

        Headings(
            "Forgot password",
            Color.Gray,
            TextStyle(
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, bottom = 15.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 25.dp, end = 25.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(modifier = Modifier.weight(1f).height(1.dp))

            Headings(
                "Or sign up with",
                Color.Gray,
                TextStyle(
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold))
                ),
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(start = 10.dp, end = 10.dp)
            )

            Divider(modifier = Modifier.weight(1f).height(1.dp))
        }

        Row(
            modifier = Modifier
                .padding(top = 30.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            LoginOptions(
                "google",
                modifier = Modifier
                    .weight(1f)
                    .background(Color.White, RoundedCornerShape(10.dp))
                    .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
            )

            LoginOptions(
                "facebook",
                modifier = Modifier
                    .weight(1f)
                    .background(Color.White, RoundedCornerShape(10.dp))
                    .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
            )
        }
    }
}


@Composable
private fun Headings(text: String, color: Color, style: TextStyle, modifier: Modifier) {

    Text(
        text = text,
        color = color,
        style = style,
        modifier = modifier
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginInputFields(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword : Boolean = false
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                colorResource(R.color.white),
                shape = RoundedCornerShape(15.dp)
            )
            .border(
                width = 1.dp,
                color = colorResource(R.color.grey),
                shape = RoundedCornerShape(15.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart)
                .padding(start = 20.dp, end = if (isPassword) 50.dp else 10.dp)
        ) {
            Text(
                text = label,
                color = Color.Black,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            )

            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = keyboardType
                ),
                visualTransformation = visualTransformation,
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color.Black
                ),
                singleLine = true,
                decorationBox = { innerTextField ->
                    TextFieldDefaults.DecorationBox(
                        value = value,
                        innerTextField = innerTextField,
                        enabled = true,
                        singleLine = true,
                        visualTransformation = visualTransformation,
                        interactionSource = remember { MutableInteractionSource() },
                        contentPadding = PaddingValues(vertical = 0.dp),
                        container = {},
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Transparent
                        )
                    )
                }
            )
        }


        if(isPassword)
        {
            Icon(
                painter = painterResource(R.drawable.show),
                contentDescription = "back",
                modifier = Modifier
                    .width(40.dp)
                    .height(20.dp)
                    .align(Alignment.CenterEnd)
                    .padding(end = 20.dp)
                , tint = Color.Black
            )
        }



    }
}
