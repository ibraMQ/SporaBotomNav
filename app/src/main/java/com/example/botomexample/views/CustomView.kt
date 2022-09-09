package com.example.botomexample.views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.botomexample.R
import java.util.*

class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), IObserver {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 90.0f
        typeface = Typeface.create("", Typeface.BOLD)
    }

    private var shape: String
    private var color: Int
    private var text: String
    private var txtColor: Int

    init {
        isClickable = true
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView)
        shape = typedArray.getString(R.styleable.CustomView_shape).toString()
        color = typedArray.getColor(R.styleable.CustomView_colorShape,0)
        text = typedArray.getString(R.styleable.CustomView_text).toString()
        txtColor = Color.WHITE
        typedArray.recycle()
    }

    override fun performClick(): Boolean {
        if (super.performClick()) return true

        /*
        when(txtColor){
            Color.WHITE -> txtColor=Color.BLACK
            else -> txtColor=Color.WHITE
        }
        invalidate()*/
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //draw shape
        paint.color = color
        when (shape){
            "Circle" ->
                canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(),(width / 2).toFloat(),paint)
            "Rectangle"->
                canvas.drawRect(0F,0F,width.toFloat(),height.toFloat(),paint)
            "Square"->
                canvas.drawRect(0F,0F,height.toFloat(),height.toFloat(),paint)
            else ->
                canvas.drawOval(0F,0F,width.toFloat(),height.toFloat(),paint)
        }
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(),(width / 2).toFloat(),paint)
        paint.color= txtColor
        canvas.drawText(text,(width / 2).toFloat(), (height / 2).toFloat(),paint)
    }

    fun swapColor(){
        val rnd = Random();
        color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        postInvalidate()
    }

    fun swapShape(){
        when(shape){
            "Circle" -> shape="Oval"
            "Oval" -> shape="Rectangle"
            "Rectangle" -> shape="Square"
            else -> shape="Cicrcle"
        }
        postInvalidate()
    }

    override fun check() {
        // Realizar algún cambio
        when(txtColor){
            Color.WHITE -> txtColor=Color.BLACK
            else -> txtColor=Color.WHITE
        }
        invalidate()
    }
}

interface IObserver{
    fun check()
}
