package com.example.compocart.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.compocart.data.local.converter.StringListConverter

@Entity(tableName = "products")
data class Product(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String, // Ürün açıklaması
    val price: Double,
    val discountPercentage: Double?, // İndirim yüzdesi
    val rating: Double?, // Ürün puanı
    val stock: Int, // Stok bilgisi
    val category: String,
    @TypeConverters(StringListConverter::class)
    val tags: List<String> = emptyList(), // Renk veya özellikler
    val brand: String?, // Marka bilgisi
    val sku: String?, // Stok kodu
    val weight: Int?, // Ağırlık bilgisi
    val warrantyInformation: String?, // Garanti bilgisi
    val shippingInformation: String?, // Kargo bilgisi
    val availabilityStatus: String?, // Stok durumu
    val returnPolicy: String?, // İade politikası
    val images: List<String> = emptyList(), // Ürün görselleri
    val thumbnail: String?, // Küçük resim


    // Favorilere ekleme durumu
    val isFavorite: Boolean = false // Varsayılan olarak false
)
