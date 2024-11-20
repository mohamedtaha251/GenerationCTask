import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Car(
    @Json(name = "model") val model: Int,
    @Json(name = "plate_number") val plateNumber: String,
    @Json(name = "brand") val brand: String,
    @Json(name = "unit_price") val unitPrice: Double,
    @Json(name = "currency") val currency: String,
    @Json(name = "color") val color: String
)
