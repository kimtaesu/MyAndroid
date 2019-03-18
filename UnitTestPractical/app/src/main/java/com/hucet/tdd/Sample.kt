package com.hucet.tdd

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sample(val title: String) : Parcelable