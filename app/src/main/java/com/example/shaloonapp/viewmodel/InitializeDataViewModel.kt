package com.example.shaloonapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shaloonapp.model.ResultState
import com.example.shaloonapp.model.dto.Appointment
import com.example.shaloonapp.model.dto.AppointmentWithServiceCrossRef
import com.example.shaloonapp.model.dto.Barber
import com.example.shaloonapp.model.dto.Service
import com.example.shaloonapp.model.dto.User
import com.example.shaloonapp.model.repository.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InitializeDataViewModel @Inject constructor(
    private val iRepository: IRepository
)
    : ViewModel() {

    fun initDB(){
        insertMultipleService()
    }
    private fun insertMultipleService(){
        viewModelScope.launch {
            try {
                // Insert services, barbers, and users concurrently
                val serviceInsertDeferred = async { iRepository.insertMultipleService(getListOfService()) }
                val barberInsertDeferred = async { iRepository.insertMultipleBarber(getListOfBarbers()) }
                val userInsertDeferred = async { iRepository.insertMultipleUser(getListOfUser()) }

                // Wait for all insert operations to complete
                val serviceInsertResult = serviceInsertDeferred.await()
                val barberInsertResult = barberInsertDeferred.await()
                val userInsertResult = userInsertDeferred.await()

                // Check the results if needed

                // Insert appointments and cross-references after previous inserts
                val appointmentInsertDeferred = async {
                    iRepository.insertMultipleAppointment(getListOfAppointment()) }
                val crossRefInsertDeferred = async {
                    iRepository.insertMultipleAppointmentWithServiceCrossRef(
                        getListOfAppointmentServiceCrossRef())
                }

                // Wait for the appointment and cross-ref insert operations to complete
                val appointmentInsertResult = appointmentInsertDeferred.await()
                val crossRefInsertResult = crossRefInsertDeferred.await()
                getAptWithService()
                //////////
            }catch (e: Exception){
                Log.i("InitializeDataViewModel", e.printStackTrace().toString())
            }
        }
    }
    fun getAptWithService(){
        viewModelScope.launch {
            iRepository.getAllAppointmentWithListOfService().collectLatest { resultState ->
                when(resultState){
                    is ResultState.Success ->
                        resultState.body?.let {
                           Log.i("InitializeDataViewModel",it.toString())
                        }
                    is ResultState.Error ->{
                        Log.i("InitializeDataViewModel",resultState.errorMessage)
                    }
                    else ->{

                    }
                }

            }
        }
    }
    fun getListOfService()= listOf(
        Service(1, "Haircut", 25, "High-quality haircut service", 30,
            "img/service/haircut.png"),
        Service(2, "Manicure", 15, "Nail care and styling", 45,
            "img/service/manicure_main.jpg"),
        Service(3, "Pedicure", 20, "Relaxing pedicure treatment", 60,
            "img/service/pedicure.png"),
        Service(4, "Facial", 30, "Revitalize your skin", 45,
            "img/service/facial.jpg"),
        Service(5, "Massage", 40, "Massage therapy", 60,
            "img/service/massage.jpg"),
        Service(6, "Waxing", 35, "Effective hair removal", 30,
            "img/service/waxing.jpg"),
        Service(7, "Body Scrub", 50, "Exfoliating body scrub", 45,
            "img/service/body_scrub.jpg"),
        Service(8, "Hair Color", 50, "Hair coloring services", 120,
            "img/service/hair_color.jpeg"),
        Service(9, "Eyebrow Threading", 12, "Precise eyebrow shaping", 15, "sdaf"),
        Service(10, "Makeup", 40, "Professional makeup application", 60, ""),
        Service(11, "Nail Extensions", 30, "Nail extensions and design", 60, ""),
        Service(12, "Hot Stone Massage", 50, "Hot stone therapy", 75, ""),
        Service(13, "Mud Mask", 30, "Detoxifying facial mud mask", 45, ""),
        Service(14, "Deep Tissue Massage", 45, "Deep tissue muscle massage", 60, ""),
        Service(15, "Brazilian Wax", 40, "Brazilian waxing service", 30, ""),
        Service(16, "Aromatherapy", 35, "Aromatherapy relaxation", 60, ""),
        Service(17, "Threading", 10, "Facial hair threading", 15, ""),
        Service(18, "Tanning", 30, "UV tanning service", 20, ""),
        Service(19, "Body Wrap", 45, "Body wrap and hydration", 60, ""),
        Service(20, "Acrylic Nails", 25, "Acrylic nail application", 60, ""),
        Service(21, "Hair Extensions", 60, "Hair extensions and styling", 90, ""),
        Service(22, "French Manicure", 20, "Classic French manicure", 45, ""),
        Service(23, "Eyelash Extensions", 50, "Eyelash extension service", 90, ""),
        Service(24, "Spa Package", 80, "Full spa package", 120, ""),
        Service(25, "Bikini Wax", 25, "Bikini waxing service", 30, ""),
        Service(26, "Balayage", 60, "Balayage hair coloring", 120, ""),
        Service(27, "Teeth Whitening", 40, "Teeth whitening treatment", 45, ""),
        Service(28, "Mud Bath", 45, "Relaxing mud bath", 60, ""),
        Service(29, "Eyebrow Tinting", 15, "Eyebrow tinting service", 30, ""),
        Service(30, "Hot Oil Massage", 45, "Hot oil massage therapy", 60, ""),
        Service(31, "Laser Hair Removal", 70, "Laser hair removal treatment", 60, ""),
        Service(32, "Foot Reflexology", 30, "Foot reflexology massage", 45, ""),
        Service(33, "Permanent Makeup", 75, "Permanent makeup application", 90, ""),
        Service(34, "Aqua Facial", 55, "HydraFacial treatment", 60, ""),
        Service(35, "Full Body Scrub", 60, "Full-body exfoliating scrub", 75, ""),
        Service(36, "Chemical Peel", 55, "Chemical peel for skin rejuvenation", 45, ""),
        Service(37, "Couples Massage", 70, "Couples massage therapy", 90, ""),
        Service(38, "Eyelash Tinting", 20, "Eyelash tinting service", 30, ""),
        Service(39, "Deep Cleansing Facial", 45, "Deep cleansing facial treatment", 60, ""),
        Service(40, "Lip Wax", 10, "Lip waxing service", 15, ""),
        Service(41, "Scalp Massage", 25, "Scalp massage therapy", 30, ""),
        Service(42, "Microdermabrasion", 50, "Microdermabrasion for skin resurfacing", 45, ""),
        Service(43, "Eyebrow Microblading", 90, "Eyebrow microblading service", 120, ""),
        Service(44, "Lash Lift", 35, "Eyelash lift and curl", 45, ""),
        Service(45, "Hair Straightening", 60, "Hair straightening treatment", 90, ""),
        Service(46, "Henna Tattoo", 20, "Henna tattoo application", 30, ""),
        Service(47, "Ear Piercing", 15, "Ear piercing service", 15, ""),
        Service(48, "Foot Detox", 30, "Ionic foot detox", 30, ""),
        Service(49, "Anti-Aging Facial", 50, "Anti-aging facial treatment", 60, ""),
        Service(50, "Spa Manicure", 25, "Luxury spa manicure", 45, ""),
        Service(51, "Classic Haircut", 20, "A traditional haircut for a classic look", 30, ""),
        Service(52, "Buzz Cut", 15, "Very short and low-maintenance haircut", 20, ""),
        Service(53, "Layered Haircut", 25, "Haircut with layers for volume and texture", 45, ""),
        Service(54, "Crew Cut", 18, "Short haircut with faded sides", 25, ""),
        Service(55, "Pixie Cut", 30, "Short and stylish haircut", 35, ""),
        Service(56, "Bob Haircut", 25, "Chin-length bob haircut", 40, ""),
        Service(57, "Fringe Haircut", 20, "Haircut with front bangs", 30, ""),
        Service(58, "Undercut", 22, "Haircut with shaved sides and longer top", 35, ""),
        Service(59, "Mullet", 25, "Distinctive haircut with short front and long back", 45, ""),
        Service(60, "Pompadour", 28, "Classic haircut with high volume on top", 40, ""),
        Service(61, "Fade Haircut", 20, "Gradual fade from short to long", 30, ""),
        Service(62, "Quiff Haircut", 28, "Stylish and voluminous top haircut", 40, ""),
        Service(63, "Spiky Haircut", 22, "Short and spiky hair styling", 30, ""),
        Service(64, "Emo Haircut", 30, "Alternative and expressive haircut", 45, ""),
        Service(65, "Cornrows", 35, "Tight braided hairstyle", 60, ""),
        Service(66, "Dreadlocks", 40, "Twisted and knotted hair strands", 60, ""),
        Service(67, "Bowl Cut", 18, "Simple and rounded haircut", 30, ""),
        Service(68, "Afro Haircut", 35, "Natural and voluminous hairstyle", 50, ""),
        Service(69, "Mohawk Haircut", 30, "Distinctive hairstyle with shaved sides", 45, ""),
        Service(70, "Shag Haircut", 28, "Layered and messy haircut", 40, ""),
        Service(71, "Curly Haircut", 25, "Haircut for curly hair types", 45, ""),
        Service(72, "Taper Haircut", 20, "Haircut with gradual tapering", 30, ""),
        Service(73, "Bald Fade", 15, "Fade haircut with a bald finish", 20, ""),
        Service(74, "Braided Bun", 35, "Elegant braided bun hairstyle", 60, ""),
        Service(75, "High and Tight", 20, "High fade with short top", 25, ""),
        Service(76, "Beard Trim", 10, "Grooming and shaping of facial hair", 15, ""),
        Service(77, "Faux Hawk", 25, "Faux mohawk-inspired haircut", 30, ""),
        Service(78, "Side Part Haircut", 22, "Classic side-parted hairstyle", 35, ""),
        Service(79, "Curtain Bangs", 18, "Long bangs parted down the middle", 30, ""),
        Service(80, "Comb Over", 22, "Slicked-back and combed hairstyle", 35, ""),
        Service(81, "Crimped Hair", 25, "Textured and wavy crimped hair", 45, ""),
        Service(82, "Feathered Haircut", 28, "Feathery and layered hairstyle", 40, ""),
        Service(83, "Layered Bob", 25, "Bob haircut with added layers", 45, ""),
        Service(84, "Bantu Knots", 30, "Tight coiled knots hairstyle", 60, ""),
        Service(85, "Side Shave", 20, "Shaved side with long top", 30, ""),
        Service(86, "Surfer Haircut", 22, "Beachy and carefree hairstyle", 35, ""),
        Service(87, "Wavy Haircut", 25, "Haircut for wavy and textured hair", 45, ""),
        Service(88, "Braided Ponytail", 35, "Ponytail with braided elements", 60, ""),
        Service(89, "Beehive Hairdo", 30, "Retro and voluminous updo", 45, ""),
        Service(90, "Top Knot", 20, "High and top-knotted hairstyle", 30, ""),
        Service(91, "Chignon", 25, "Elegant and low bun hairstyle", 40, ""),
        Service(92, "Victory Rolls", 30, "Vintage victory roll hairstyle", 45, ""),
        Service(93, "Twisted Updo", 28, "Twisted and stylish updo", 40, ""),
        Service(94, "Slicked-Back Hair", 22, "Smooth and combed-back hairstyle", 30, ""),
        Service(95, "Half-Up Half-Down", 25, "Half-up and half-down hairstyle", 45, ""),
        Service(96, "Crown Braid", 35, "Braided crown hairstyle", 60, ""),
        Service(97, "Spaghetti Hair", 18, "Long and straight hairstyle", 30, ""),
        Service(98, "Wet Look Hair", 22, "Slick and wet-look hairstyle", 30, ""),
        Service(99, "Braided Top Knot", 28, "Top-knotted hairstyle with braids", 45, ""),
        Service(100, "Short Undercut", 15, "Short haircut with undercut style", 25, "")

    )

    fun getListOfBarbers()= listOf(
        Barber(1, "John", "Doe", "5 years", 4.0, "img/baber/man_1.png"),
        Barber(2, "Jane", "Smith", "8 years", 4.5, "img/baber/woman_1.png"),
        Barber(3, "Michael", "Johnson", "6 years", 4.6, "img/baber/man_2.png"),
        Barber(4, "Emily", "Brown", "7 years", 4.7, "img/baber/woman_2.png"),
        Barber(5, "William", "Davis", "9 years", 3.9, "img/baber/man_1.png"),
        Barber(6, "Olivia", "Miller", "4 years", 3.5, "img/baber/woman_3.png"),
        Barber(7, "James", "Wilson", "7 years", 3.0, "img/baber/man_3.png"),
        Barber(8, "Sophia", "Moore", "5 years", 2.4, "img/baber/woman_4.png"),
        Barber(9, "Benjamin", "Taylor", "6 years", 4.0, "img/baber/man_4.png"),
        Barber(10, "Ava", "Anderson", "8 years", 4.0, "img/baber/woman_1.png"),
        Barber(11, "Liam", "Thomas", "4 years", 4.9, "img/baber/man_2.png"),
        Barber(12, "Emma", "Jackson", "6 years", 5.0, ""),
        Barber(13, "Daniel", "White", "7 years", 4.3, ""),
        Barber(14, "Mia", "Harris", "5 years", 3.5, ""),
        Barber(15, "Alexander", "Martin", "8 years", 3.6, ""),
        Barber(16, "Ella", "Thompson", "6 years", 3.7, ""),
        Barber(17, "Henry", "Garcia", "5 years", 3.8, ""),
        Barber(18, "Scarlett", "Martinez", "4 years", 3.9, ""),
        Barber(19, "Sebastian", "Robinson", "7 years", 4.0, ""),
        Barber(20, "Lily", "Clark", "9 years", 4.1, ""),
        Barber(21, "Matthew", "Rodriguez", "6 years", 4.2, ""),
        Barber(22, "Grace", "Lewis", "5 years", 4.3, ""),
        Barber(23, "Jackson", "Walker", "8 years", 4.4, ""),
        Barber(24, "Chloe", "Perez", "4 years", 4.5, ""),
        Barber(25, "Ethan", "Hill", "7 years", 4.6, ""),
        Barber(26, "Aria", "Scott", "6 years", 4.7, ""),
        Barber(27, "David", "Turner", "5 years", 4.8, ""),
        Barber(28, "Avery", "Phillips", "8 years", 4.9, ""),
        Barber(29, "Jameson", "Campbell", "4 years", 2.0, ""),
        Barber(30, "Sofia", "Parker", "7 years", 2.9,"")
    )

    fun getListOfUser()= listOf(
        User(0,"duc","nguyen","",""),
        User(1,"hu","shiyan","",""),
        User(2,"pavani","velma","pavani@gmail.com","password"),
        User(3,"yuan","yao","",""),
        User(4,"amar","sapcanin","",""),
        User(5,"john","doe","",""),
        User(6,"jane","street","",""),
        User(7,"peter","copper","",""),
        User(8,"mike","doe","",""),
        User(9,"tom","mas","",""),
        User(10,"tony","street","",""),

    )

    fun getListOfAppointment() = listOf(
        Appointment( 2, 1, "2023-09-12",
            "11:00 AM", 25.0, "Confirmed", "BANK"),
        Appointment( 1, 3, "2023-09-13",
            "12:00 PM", 30.0, "Canceled", "CASH"),
        Appointment( 3, 3, "2023-09-14",
            "1:00 PM", 35.0, "Confirmed", "BANK"),
        Appointment( 2, 3, "2023-09-12",
            "11:00 AM", 25.0, "Confirmed", "BANK"),
        Appointment( 1, 4, "2023-09-13",
            "12:00 PM", 30.0, "Confirmed", "CASH"),
        Appointment( 3, 3, "2023-09-14",
            "1:00 PM", 35.0, "Confirmed", "BANK"),
        Appointment( 2, 2, "2023-09-12",
            "11:00 AM", 25.0, "Confirmed", "BANK"),
        Appointment( 1, 5, "2023-09-13",
            "12:00 PM", 30.0, "Confirmed", "CASH"),
        Appointment(3, 3, "2023-09-14",
            "1:00 PM", 35.0, "Confirmed", "BANK"),
    )


    fun getListOfAppointmentServiceCrossRef() = listOf(
        AppointmentWithServiceCrossRef(1,1),
        AppointmentWithServiceCrossRef(1,2),
        AppointmentWithServiceCrossRef(1,3),
        AppointmentWithServiceCrossRef(1,4),

        AppointmentWithServiceCrossRef(2,2),
        AppointmentWithServiceCrossRef(2,3),
        AppointmentWithServiceCrossRef(2,4),

        AppointmentWithServiceCrossRef(3,5),
        AppointmentWithServiceCrossRef(3,6),
        AppointmentWithServiceCrossRef(3,7),
        AppointmentWithServiceCrossRef(3,4),

        AppointmentWithServiceCrossRef(4,1),
        AppointmentWithServiceCrossRef(4,2),
        AppointmentWithServiceCrossRef(4,3),
        AppointmentWithServiceCrossRef(4,4),

        AppointmentWithServiceCrossRef(5,2),
        AppointmentWithServiceCrossRef(5,3),
        AppointmentWithServiceCrossRef(5,4),

        AppointmentWithServiceCrossRef(6,5),
        AppointmentWithServiceCrossRef(6,6),
        AppointmentWithServiceCrossRef(6,7),
        AppointmentWithServiceCrossRef(6,4),
    )


}