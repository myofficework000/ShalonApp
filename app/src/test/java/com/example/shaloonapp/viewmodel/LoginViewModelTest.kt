package com.example.shaloonapp.viewmodel

import com.example.shaloonapp.model.ResultState
import com.example.shaloonapp.model.dto.User
import com.example.shaloonapp.model.repository.IRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {


    @Mock
    lateinit var repository: IRepository

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var user1Flow : Flow<ResultState<User>>
    val user1 =User(1,"pavani","velma","pavani@gmail.com","password")

    val successState =ResultState.Success(user1)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        loginViewModel = LoginViewModel(repository)
        user1Flow = flow {
            emit(successState)
        }

    }

    @After
    fun tearDown() {

    }

    @Test
    fun `Given Email and Password When getUser method call Then Return User`()=
        runTest {
            val email ="pavani@gmail.com"
            val password ="password"

            `when`( repository.getUser(email,password)).thenReturn(user1Flow)

            loginViewModel.getUser(email,password)
            advanceUntilIdle()// Yields to perform the registrations

            assertThat(loginViewModel.user.value , instanceOf(ResultState.Success::class.java))
            assertEquals(loginViewModel.user.value,successState)


            verify(repository).getUser(email,password)
        }
}