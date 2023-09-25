package com.example.shaloonapp.view.screens

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginScreenKtTest {

    @get:Rule
    val composeRule = createComposeRule()

   /* @Mock
    lateinit var repository: IRepository

    lateinit var mockLoginViewModel: LoginViewModel = mock(LoginViewModel::class.java)
*/



    lateinit var counterDisplayResultText : SemanticsNodeInteraction
    lateinit var counterInputEmail : SemanticsNodeInteraction
    lateinit var counterInputPassword : SemanticsNodeInteraction
    lateinit var counterButtonSignIn : SemanticsNodeInteraction
    lateinit var counterButtonSignUp : SemanticsNodeInteraction
    lateinit var counterHomeScreen : SemanticsNodeInteraction

    val validEmail = "test@gmail.com"
    val validPassword = "123"
    @Before
    fun setUp() {
      //  mockLoginViewModel = LoginViewModel(repository)
        ///////////////////////////////////////////////////////////////////////
        // Specify behavior for the mock
       /* coEvery { mockLoginViewModel.getUser(any(), any()) } coAnswers {
            // Simulate behavior when getUser is called
            val email = arg<String>(0)
            val password = arg<String>(1)

            // Define your desired behavior here, e.g., returning a specific ResultState
            if (email == "test@gmail.com" && password == "123") {
                ResultState.Success(User(*//* user data here *//*))
            } else {
                ResultState.Error(" Error")
            }
        }*/
        ///////////////////////////////////////////////////////////////////////
        composeRule.setContent {
            LoginScreen(navController = rememberNavController())
        }
        with(composeRule){
            counterDisplayResultText = onNodeWithTag("Error Message")
            counterInputEmail = onNodeWithTag("Input Email")
            counterInputPassword = onNodeWithTag("Input Password")
            counterButtonSignIn = onNodeWithTag("SignIn Button")
            counterButtonSignUp = onNodeWithTag("SignUp Button")
            counterHomeScreen = onNodeWithTag("Home Screen")
        }
    }

    @After
    fun tearDown() {
    }

    @Test
    fun verifyAllViewExistsExceptError(){
        counterInputEmail.assertExists()
        counterInputPassword.assertExists()
        counterButtonSignIn.assertExists()
        counterButtonSignUp.assertExists()
    }

   /* @Test
    fun Given_Valid_Email_And_Password_When_Click_SignIn_Then_Success(){
        counterInputEmail.performTextInput(validEmail)
        counterInputPassword.performTextInput(validPassword)
        counterButtonSignIn.performClick()


        counterHomeScreen.assertExists()
    }*/
}