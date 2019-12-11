package ch.app.domain.master

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MasterModel(

    val category: String,

    val url: String
) : Parcelable