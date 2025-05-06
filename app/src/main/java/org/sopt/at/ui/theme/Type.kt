package org.sopt.at.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import org.sopt.at.R

object PretendardFont {
    val Bold = FontFamily(Font(R.font.pretendard_bold))
    val SemiBold = FontFamily(Font(R.font.pretendard_semibold))
    val Regular = FontFamily(Font(R.font.pretendard_regular))
}

sealed interface TypographyTokens {
    val title1: Title1
    val body1: Body1
}

@Immutable
data class Title1(
    val b24: TextStyle,
    val m24: TextStyle,
    val r24: TextStyle
)

@Immutable
data class Body1(
    val b16: TextStyle,
    val m16: TextStyle,
    val r16: TextStyle
)


class DefaultTypographyTokens : TypographyTokens {
    override val title1 = Title1(
        b24 = TextStyle(
            fontFamily = PretendardFont.Bold,
            fontSize = 24.sp,
            lineHeight = 32.sp
        ),
        m24 = TextStyle(
            fontFamily = PretendardFont.SemiBold,
            fontSize = 24.sp,
            lineHeight = 32.sp
        ),
        r24 = TextStyle(
            fontFamily = PretendardFont.Regular,
            fontSize = 24.sp,
            lineHeight = 32.sp
        )
    )

    override val body1 = Body1(
        b16 = TextStyle(
            fontFamily = PretendardFont.Bold,
            fontSize = 16.sp,
            lineHeight = 24.sp
        ),
        m16 = TextStyle(
            fontFamily = PretendardFont.SemiBold,
            fontSize = 16.sp,
            lineHeight = 24.sp
        ),
        r16 = TextStyle(
            fontFamily = PretendardFont.Regular,
            fontSize = 16.sp,
            lineHeight = 24.sp
        )
    )
}

val LocalTivingTypographyProvider = staticCompositionLocalOf<TypographyTokens> {
    DefaultTypographyTokens()
}