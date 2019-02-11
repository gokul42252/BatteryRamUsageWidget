package com.ct.respurceusageview

/**
 * Created by Gokul on 1/8/2018.
 * CircularProgressView
 */


import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.Style
import android.graphics.RectF
import android.graphics.Typeface
import android.support.v4.view.InputDeviceCompat
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View


class CircularLoadProgressView : View {
    private var mArcRadius: Int = 0
    private var mArcRect: RectF? = null
    private var mArcStartAngle: Int = 0
    private var mArcWidth: Int = 0
    private var mBaseArcColor: Int = 0
    private var mBaseArcPaint: Paint? = null
    private var mBaseArcSweepAngle: Int = 0
    private var mContext: Context? = null
    private var mTypeface: Typeface? = null
    private var mLabelTypeface: Typeface? = null
    private var mProgressArcColor: Int = 0
    private var mProgressArcPaint: Paint? = null
    var progressArcSweepAngle: Float = 0.toFloat()

    private var mProgressLabelStr: String? = null
    private var mProgressTimeLabelStr: String? = null
    private var mProgressTimeTextLabelStr: String? = null

    private var mProgressLabelTextSize: Int = 0
    private var mProgressValueStr: String? = null
    private var mProgressValueTextColor: Int = 0
    private var mProgressValueTextPaint: TextPaint? = null
    private var mProgressValueTextSize: Int = 0
    private var mRotationAngle: Int = 0

    constructor(context: Context)
            : super(context) {
        this.mRotationAngle = -90
        this.mArcStartAngle = 0
        this.progressArcSweepAngle = 0.0f
        this.mBaseArcSweepAngle = 360
        this.mProgressValueStr = "0:00:00"
        this.mArcRect = RectF()
        this.mBaseArcColor = -16776961
        this.mProgressArcColor = InputDeviceCompat.SOURCE_ANY
        this.mProgressValueTextColor = -1
        this.mProgressLabelTextSize = 50
        this.mProgressValueTextSize = 125
        this.mArcWidth = 15
        this.mProgressLabelStr = "Progress"
        this.mProgressTimeLabelStr = "Put your data here"
        this.mProgressTimeTextLabelStr = "0:00:00"
        initialization(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet)
            : super(context, attrs) {
        this.mRotationAngle = -90
        this.mArcStartAngle = 0
        this.progressArcSweepAngle = 0.0f
        this.mBaseArcSweepAngle = 360
        this.mProgressValueStr = "0:00:00"
        this.mArcRect = RectF()
        this.mBaseArcColor = -16776961
        this.mProgressArcColor = InputDeviceCompat.SOURCE_ANY

        this.mProgressValueTextColor = -1
        this.mProgressLabelTextSize = 50
        this.mProgressValueTextSize = 125
        this.mArcWidth = 15
        this.mProgressLabelStr = "Progress"
        this.mProgressTimeLabelStr = "Time Elapsed"
        this.mProgressTimeTextLabelStr = "0:00:00"
        initialization(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int)
            : super(context, attrs, defStyle) {
        this.mRotationAngle = -90
        this.mArcStartAngle = 0
        this.progressArcSweepAngle = 0.0f
        this.mBaseArcSweepAngle = 360
        this.mProgressValueStr = "0:00:00"
        this.mArcRect = RectF()
        this.mBaseArcColor = -16776961
        this.mProgressArcColor = InputDeviceCompat.SOURCE_ANY

        this.mProgressValueTextColor = -1
        this.mProgressLabelTextSize = 50
        this.mProgressValueTextSize = 125
        this.mArcWidth = 15
        this.mProgressLabelStr = ""
        this.mProgressTimeLabelStr = ""
        this.mProgressTimeTextLabelStr = ""
        initialization(context, attrs, defStyle)
    }

    @SuppressLint("CustomViewStyleable")
    private fun initialization(context: Context, attrs: AttributeSet?, defStyle: Int) {
        this.mContext = context
        var typedArray: TypedArray? = null
        if (attrs != null) {
            typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircularProgress, defStyle, 0)
            this.mBaseArcColor =
                typedArray!!.getColor(R.styleable.CircularProgress_lcv_base_arc_color, this.mBaseArcColor)
            this.mProgressArcColor =
                typedArray.getColor(R.styleable.CircularProgress_lcv_progress_arc_color, this.mProgressArcColor)

            this.mProgressValueTextColor = typedArray.getColor(
                R.styleable.CircularProgress_lcv_progress_value_text_color,
                this.mProgressValueTextColor
            )
            this.mProgressLabelTextSize = typedArray.getDimension(
                R.styleable.CircularProgress_lcv_progress_label_text_size,
                this.mProgressLabelTextSize.toFloat()
            ).toInt()
            this.mProgressValueTextSize = typedArray.getDimension(
                R.styleable.CircularProgress_lcv_progress_value_text_size,
                this.mProgressValueTextSize.toFloat()
            ).toInt()
            this.mArcWidth =
                typedArray.getDimension(R.styleable.CircularProgress_lcv_arc_width, this.mArcWidth.toFloat()).toInt()
            this.mProgressLabelStr = typedArray.getString(R.styleable.CircularProgress_lcv_progress_label_str)
            val fontNameLabel = typedArray.getString(R.styleable.CircularProgress_lcv_label_fontName)
            val fontName = typedArray.getString(R.styleable.CircularProgress_lcv_fontName)
            if (fontName != null) {
                val typeface = Typeface.createFromAsset(mContext!!.assets, fontName)
                if (typeface != null) {
                    this.mTypeface = typeface
                }
            }
            if (fontNameLabel != null) {
                val typeface = Typeface.createFromAsset(mContext!!.assets, fontNameLabel)
                if (typeface != null) {
                    this.mLabelTypeface = typeface
                }
            }
        }


        this.mProgressValueTextPaint = TextPaint()
        if (mTypeface != null) {
            this.mProgressValueTextPaint!!.typeface = mTypeface
        }
        this.mProgressValueTextPaint!!.textAlign = Paint.Align.LEFT
        this.mProgressValueTextPaint!!.color = this.mProgressValueTextColor
        this.mProgressValueTextPaint!!.textSize = this.mProgressValueTextSize.toFloat()
        this.mProgressValueTextPaint!!.isAntiAlias = true


        this.mBaseArcPaint = Paint()
        this.mBaseArcPaint!!.color = this.mBaseArcColor
        this.mBaseArcPaint!!.isAntiAlias = true
        this.mBaseArcPaint!!.style = Style.FILL_AND_STROKE
        this.mBaseArcPaint!!.strokeWidth = this.mArcWidth.toFloat()
        this.mProgressArcPaint = Paint()
        this.mProgressArcPaint!!.color = this.mProgressArcColor
        this.mProgressArcPaint!!.isAntiAlias = true
        this.mProgressArcPaint!!.style = Style.STROKE
        this.mProgressArcPaint!!.strokeWidth = this.mArcWidth.toFloat()
        this.mProgressArcPaint!!.setShadowLayer(this.mArcWidth.toFloat(), 0.0f, 0.0f, this.mProgressArcColor)

        setLayerType(1, this.mProgressArcPaint)
        typedArray?.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val arcDiameter: Int
        val height = View.getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        val width = View.getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        val minOfScreenWidthHeight = Math.min(width, height)
        arcDiameter = if (minOfScreenWidthHeight == width) {
            minOfScreenWidthHeight - paddingLeft - paddingRight - this.mArcWidth
        } else {
            minOfScreenWidthHeight - paddingTop - paddingBottom - this.mArcWidth
        }
        this.mArcRadius = arcDiameter / 2
        val top = (height / 2 - arcDiameter / 2).toFloat()
        val left = (width / 2 - arcDiameter / 2).toFloat()
        this.mArcRect!!.set(
            this.mArcWidth.toFloat() + left,
            this.mArcWidth.toFloat() + top,
            arcDiameter.toFloat() + left - this.mArcWidth.toFloat(),
            arcDiameter.toFloat() + top - this.mArcWidth.toFloat()
        )
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawBaseArc(canvas)
        drawProgressArc(canvas)
        drawProgressLabelText(canvas)
    }

    private fun drawBaseArc(canvas: Canvas) {
        canvas.drawArc(
            this.mArcRect!!,
            (this.mRotationAngle + this.mArcStartAngle).toFloat(),
            this.mBaseArcSweepAngle.toFloat(),
            false,
            this.mBaseArcPaint!!
        )
    }

    private fun drawProgressArc(canvas: Canvas) {
        if (this.progressArcSweepAngle > this.mBaseArcSweepAngle.toFloat()) {
            this.progressArcSweepAngle = this.mBaseArcSweepAngle.toFloat()
        }
        canvas.drawArc(
            this.mArcRect!!,
            (this.mRotationAngle + this.mArcStartAngle).toFloat(),
            this.progressArcSweepAngle,
            false,
            this.mProgressArcPaint!!
        )
    }

    private fun drawProgressLabelText(canvas: Canvas) {
        canvas.drawText(
            this.mProgressLabelStr!!,
            this.mArcRect!!.centerX() - this.mProgressValueTextPaint!!.measureText(this.mProgressValueStr) / 4f,
            this.mArcRect!!.centerY()+ this.mProgressValueTextPaint!!.measureText(this.mProgressValueStr) /8f,
            this.mProgressValueTextPaint!!
        )
    }

    fun updateProWithProText(progressText: String,
                             progressSweepAngle: Float) {
        this.mProgressLabelStr = progressText
        this.progressArcSweepAngle = progressSweepAngle
        invalidate()
    }

    companion object {
        private var TAG_NAME: String? = null
        init {
            TAG_NAME = "CircularProgressView"
        }
    }
}