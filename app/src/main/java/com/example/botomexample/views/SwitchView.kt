package com.example.botomexample.views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import com.example.botomexample.R

class SwitchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), IObservable {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 90.0f
        typeface = Typeface.create("", Typeface.BOLD)
    }
    private var value:Boolean
    private val listObservers = ArrayList<IObserver>()

    init {
        isClickable=true
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.SwitchView)
        value = typedArray.getBoolean(R.styleable.SwitchView_startValue,false)
        typedArray.recycle()
    }

    override fun performClick(): Boolean {
        if (super.performClick()) return true

        value = !value
        notifyAl()

        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //draw shape
        if(value){
            paint.color=Color.GREEN
            canvas.drawRect((width/2).toFloat(),0F,width.toFloat(),height.toFloat(),paint)
            paint.color=Color.GRAY
            canvas.drawRect(0f,0F,(width/2).toFloat(),height.toFloat(),paint)
        }else{
            paint.color=Color.GRAY
            canvas.drawRect((width/2).toFloat(),0F,width.toFloat(),height.toFloat(),paint)
            paint.color=Color.RED
            canvas.drawRect(0f,0F,(width/2).toFloat(),height.toFloat(),paint)
        }
    }

    override fun addObserver(observer: IObserver) {
        listObservers.add(observer)
    }

    override fun removeObserver(observer: IObserver) {
        listObservers.remove(observer)
    }

    override fun notifyAl(){
        for(observer in listObservers){
            observer.check()
        }
    }
}

interface IObservable{

    fun addObserver(observer: IObserver)
    fun removeObserver(observer: IObserver)
    fun notifyAl()

}