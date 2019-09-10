package com.edsusantoo.bismillah.academy.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.edsusantoo.bismillah.academy.R


class MyButton(context: Context?) : AppCompatButton(context) {

    private var enabledBackground: Drawable? = null
    private var disabledBackground: Drawable? = null
    private var textColor: Int? = 0


    constructor(context: Context?, attributeSet: AttributeSet) : this(context)
    constructor(context: Context?, attributeSet: AttributeSet, deffStyleAttr: Int) : this(context, attributeSet)

    init {
        val resource = resources
        enabledBackground = resource.getDrawable(R.drawable.bg_button)
        disabledBackground = resource.getDrawable(R.drawable.bg_button_disable)
        textColor = ContextCompat.getColor(getContext(), android.R.color.background_light)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        background = if (isEnabled) enabledBackground else disabledBackground
        setTextColor(textColor!!)
        textSize = 12.0F
        gravity = Gravity.CENTER
    }

}