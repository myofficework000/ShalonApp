package com.example.shaloonapp.view.screens

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.shaloonapp.view.MainActivity
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegisterScreenKtTest {

    @get:Rule
    val composeRule = createComposeRule()




    private lateinit var edFirstname: SemanticsNodeInteraction
    private lateinit var edLastName: SemanticsNodeInteraction
    private lateinit var edPassword: SemanticsNodeInteraction
    private lateinit var edEmail: SemanticsNodeInteraction
    private lateinit var textRegisterUser: SemanticsNodeInteraction
    private lateinit var btnRegister: SemanticsNodeInteraction

    @Before
    fun setUp() {

        composeRule.setContent { RegisterScreen(navController = rememberNavController()) }
        with(composeRule){


            edFirstname = onNodeWithTag("firstName")
            edLastName = onNodeWithTag("lastName")
            edPassword = onNodeWithTag("password")
            edEmail = onNodeWithTag("email")
            textRegisterUser = onNodeWithTag("TextRegister")
            btnRegister = onNodeWithText("SignUp")
        }
    }


    @Test
    fun verifyAllViewsExists(){


        edFirstname.assertExists()
        edLastName.assertExists()
        edPassword.assertExists()
        edEmail.assertExists()
        textRegisterUser.assertExists()
        btnRegister.assertExists()

    }


    @Test
    fun whenInputIsEmptyThenCounterDisplayInvalidEntry(){
        btnRegister.performClick()
        textRegisterUser.assertTextEquals("Invalid credentials")
    }



    @Test
    fun whenInputsAreGoodThenSaySuccessfulRegistration() {

        edFirstname.performTextInput("pavani")
        edLastName.performTextInput("velma")
        edPassword.performTextInput("12345678")
        edEmail.performTextInput("pavani@gmail.com")
        btnRegister.performClick()
        textRegisterUser.assertTextEquals("Successful")
    }

    @Test
    fun WhenEmailIsEmptyThenSayInvalid() {

        edFirstname.performTextInput("pavani")
        edLastName.performTextInput("velma")
        edPassword.performTextInput("12345678")
        edEmail.performTextInput("")
        btnRegister.performClick()
        textRegisterUser.assertTextEquals("Invalid credentials")
    }

    @Test
    fun WhenPasswordIsEmptyThenSayInvalid() {

        edFirstname.performTextInput("pavani")
        edLastName.performTextInput("velma")
        edPassword.performTextInput("")
        edEmail.performTextInput("pavani@gmail.com")
        btnRegister.performClick()
        textRegisterUser.assertTextEquals("Invalid credentials")
    }



    @Test
    fun WhenPasswordIsLessThanFiveThenSayInvalid() {

        edFirstname.performTextInput("pavani")
        edLastName.performTextInput("velma")
        edPassword.performTextInput("bn")
        edEmail.performTextInput("pavani@gmail.com")
        btnRegister.performClick()
        textRegisterUser.assertTextEquals("Invalid credentials")
    }
}