package com.origamitown.dotcloud.models.comment

import android.os.Build
import com.origamitown.dotcloud.models.user.UserDetails
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

data class Comment(
    val comment: String,
    val userDetails: UserDetails,
    val commentId: Int,
) {
    val commentDate get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalDateTime.now()
    } else {
        Date()
    }
}
