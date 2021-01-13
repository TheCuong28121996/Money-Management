package com.money.management.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.CompoundButton
import android.widget.RelativeLayout
import com.money.management.R
import kotlinx.android.synthetic.main.item_info_view.view.*

@SuppressLint("CustomViewStyleable")
class ItemInfo @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {
    private var mListener: OnChangedListener? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.item_info_view, this, true)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.item_info_attributes, 0, 0)

            var sTemp: Int = R.styleable.item_info_attributes_text_title
            if (typedArray.hasValue(sTemp)) {
                tv_title.text = typedArray.getString(sTemp)
            }

            sTemp = R.styleable.item_info_attributes_icon_left
            if (typedArray.hasValue(sTemp)) {
                img_icon.setImageDrawable(typedArray.getDrawable(sTemp))
            }

            sTemp = R.styleable.item_info_attributes_icon_right_visibility
            if (typedArray.hasValue(sTemp)) {
                switch_theme.visibility = typedArray.getInt(sTemp, View.GONE)
                switch_theme.setOnCheckedChangeListener { buttonView: CompoundButton?, isChecked: Boolean ->
                    mListener?.onCheckedChanged(isChecked)
                }
            }

            typedArray.recycle()
        }
    }

    fun setChecked(isChecked: Boolean){
        switch_theme.isChecked = isChecked
    }

    fun setOnCheckedChangedListener(listener: OnChangedListener) {
        mListener = listener
    }

    interface OnChangedListener {
        fun onCheckedChanged(isChecked: Boolean)
    }
}