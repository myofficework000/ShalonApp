package com.example.shaloonapp.model.dto

enum class PaymentMethod{
    BANK, CASH
}
data class Appointment(
    val appointmentId: Int,
    val userId: Int,
    val barberId: Int,
    val services: List<Service>,
    val appointmentDate: String,
    val appointmentTime: String,
    val serviceCharge: Double,
    val status: String,
    val paymentMode: PaymentMethod
)
