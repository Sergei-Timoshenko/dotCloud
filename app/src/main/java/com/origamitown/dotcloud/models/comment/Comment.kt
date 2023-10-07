package com.origamitown.dotcloud.models.comment

import android.os.Build
import android.os.Parcelable
import com.origamitown.dotcloud.models.user.UserDetails
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

@Parcelize
data class Comment(
    val comment: String,
    val userDetails: UserDetails,
    val commentId: Int,
) : Parcelable {
    val commentDate get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalDateTime.now()
    } else {
        Date()
    }
}
