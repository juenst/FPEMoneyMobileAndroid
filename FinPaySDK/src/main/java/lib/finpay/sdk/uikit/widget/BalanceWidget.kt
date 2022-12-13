package lib.finpay.sdk.uikit.widget

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import lib.finpay.sdk.R
import lib.finpay.sdk.uikit.helper.Utils
import java.util.*

/**
 * Created by Widi Ramadhan on 11/09/22.
 */
class BalanceWidget : LinearLayout {
    private var mContext: Context

    // Background Attributes
    private var mDefaultBackgroundColor = Color.BLACK
    private var mFocusBackgroundColor = 0
    private var mDisabledBackgroundColor = Color.parseColor("#f6f7f9")
    private var mDisabledTextColor = Color.parseColor("#bec2c9")
    private var mDisabledBorderColor = Color.parseColor("#dddfe2")

    // Text Attributes
    private var mDefaultTextColor = Color.WHITE
    private var mDefaultIconColor = Color.WHITE
    private val mTextPosition = 1
    private var mDefaultTextSize = Utils.spToPx(context, 15f)
    private var mDefaultTextGravity = 0x11 // Gravity.CENTER
    private var mText: String? = null

    // Icon Attributes
    private var mIconResource: Drawable? = null
    private var mFontIconSize = Utils.spToPx(context, 15f)
    private var mFontIcon: String? = null
    private var mIconPosition = 1
    private var mIconPaddingLeft = 10
    private var mIconPaddingRight = 10
    private var mIconPaddingTop = 0
    private var mIconPaddingBottom = 0
    private var mBorderColor = Color.TRANSPARENT
    private var mBorderWidth = 0
    private var mRadius = 0
    private var mEnabled = true
    private var mTextAllCaps = false
    private var mTextTypeFace: Typeface? = null
    private var mIconTypeFace: Typeface? = null
    private val mDefaultIconFont = "fontawesome.ttf"
    private val mDefaultTextFont = "robotoregular.ttf"

    /**
     * Return Icon of the FancyButton
     *
     * @return ImageView Object
     */
    var iconImageObject: ImageView? = null
        private set

    /**
     * Return Icon Font of the FancyButton
     *
     * @return TextView Object
     */
    var iconFontObject: TextView? = null
        private set

    /**
     * Return TextView Object of the FancyButton
     *
     * @return TextView Object
     */
    var textViewObject: TextView? = null
        private set
    private var mGhost = false // Default is a solid button !
    private var mUseSystemFont = false // Default is using robotoregular.ttf
    private val mUseRippleEffect = true

    /**
     * Default constructor
     *
     * @param context : Context
     */
    constructor(context: Context) : super(context) {
        mContext = context
        mTextTypeFace = Utils.findFont(mContext, mDefaultTextFont, null)
        mIconTypeFace = Utils.findFont(mContext, mDefaultIconFont, null)
        initialize()
    }

    /**
     * Default constructor called from Layouts
     *
     * @param context : Context
     * @param attrs   : Attributes Array
     */
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        mContext = context
        val attrsArray = context.obtainStyledAttributes(attrs, R.styleable.FinpayMoneyAttrs, 0, 0)
        initAttributesArray(attrsArray)
        attrsArray.recycle()
        initialize()
    }

    /**
     * Initialize Button dependencies
     * - Initialize Button Container : The LinearLayout
     * - Initialize Button TextView
     * - Initialize Button Icon
     * - Initialize Button Font Icon
     */
    private fun initialize() {
        initializeButtonContainer()
        textViewObject = setupTextView()
        iconImageObject = setupIconView()
        iconFontObject = setupFontIconView()
        var iconIndex: Int
        var textIndex: Int
        var view1: View
        var view2: View
        removeAllViews()
        setupBackground()
        val views = ArrayList<View>()
        if (mIconPosition == POSITION_LEFT || mIconPosition == POSITION_TOP) {
            if (iconImageObject != null) {
                views.add(iconImageObject!!)
            }
            if (iconFontObject != null) {
                views.add(iconFontObject!!)
            }
            if (textViewObject != null) {
                views.add(textViewObject!!)
            }
        } else {
            if (textViewObject != null) {
                views.add(textViewObject!!)
            }
            if (iconImageObject != null) {
                views.add(iconImageObject!!)
            }
            if (iconFontObject != null) {
                views.add(iconFontObject!!)
            }
        }
        for (view in views) {
            this.addView(view)
        }
    }

    /**
     * Setup Text View
     *
     * @return : TextView
     */
    private fun setupTextView(): TextView {
        if (mText == null) {
            mText = "Fancy Button"
        }
        val textView = TextView(mContext)
        textView.text = mText
        textView.gravity = mDefaultTextGravity
        textView.setTextColor(if (mEnabled) mDefaultTextColor else mDisabledTextColor)
        textView.textSize =
            Utils.pxToSp(context, mDefaultTextSize.toFloat()).toFloat()
        textView.layoutParams =
            LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        if (!isInEditMode && !mUseSystemFont) {
            textView.typeface = mTextTypeFace //we can pass null in first arg
        }
        return textView
    }

    /**
     * Setup Font Icon View
     *
     * @return : TextView
     */
    private fun setupFontIconView(): TextView? {
        if (mFontIcon != null) {
            val fontIconView = TextView(mContext)
            fontIconView.setTextColor(if (mEnabled) mDefaultIconColor else mDisabledTextColor)
            val iconTextViewParams =
                LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            iconTextViewParams.rightMargin = mIconPaddingRight
            iconTextViewParams.leftMargin = mIconPaddingLeft
            iconTextViewParams.topMargin = mIconPaddingTop
            iconTextViewParams.bottomMargin = mIconPaddingBottom
            if (textViewObject != null) {
                if (mIconPosition == POSITION_TOP || mIconPosition == POSITION_BOTTOM) {
                    iconTextViewParams.gravity = Gravity.CENTER
                    fontIconView.gravity = Gravity.CENTER
                } else {
                    fontIconView.gravity = Gravity.CENTER_VERTICAL
                    iconTextViewParams.gravity = Gravity.CENTER_VERTICAL
                }
            } else {
                iconTextViewParams.gravity = Gravity.CENTER
                fontIconView.gravity = Gravity.CENTER_VERTICAL
            }
            fontIconView.layoutParams = iconTextViewParams
            if (!isInEditMode) {
                fontIconView.textSize =
                    Utils.pxToSp(context, mFontIconSize.toFloat()).toFloat()
                fontIconView.text = mFontIcon
                fontIconView.typeface = mIconTypeFace
            } else {
                fontIconView.textSize =
                    Utils.pxToSp(context, mFontIconSize.toFloat()).toFloat()
                fontIconView.text = "O"
            }
            return fontIconView
        }
        return null
    }

    /**
     * Text Icon resource view
     *
     * @return : ImageView
     */
    private fun setupIconView(): ImageView? {
        if (mIconResource != null) {
            val iconView = ImageView(mContext)
            iconView.setImageDrawable(mIconResource)
            iconView.setPadding(
                mIconPaddingLeft,
                mIconPaddingTop,
                mIconPaddingRight,
                mIconPaddingBottom
            )
            val iconViewParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            if (textViewObject != null) {
                if (mIconPosition == POSITION_TOP || mIconPosition == POSITION_BOTTOM) iconViewParams.gravity =
                    Gravity.CENTER else iconViewParams.gravity = Gravity.CENTER_VERTICAL
                iconViewParams.rightMargin = 10
                iconViewParams.leftMargin = 10
            } else {
                iconViewParams.gravity = Gravity.CENTER_VERTICAL
            }
            iconView.layoutParams = iconViewParams
            return iconView
        }
        return null
    }

    /**
     * Initialize Attributes arrays
     *
     * @param attrsArray : Attributes array
     */
    private fun initAttributesArray(attrsArray: TypedArray) {
        mDefaultBackgroundColor = attrsArray.getColor(
            R.styleable.FinpayMoneyAttrs_fm_defaultColor,
            mDefaultBackgroundColor
        )
        mFocusBackgroundColor =
            attrsArray.getColor(R.styleable.FinpayMoneyAttrs_fm_focusColor, mFocusBackgroundColor)
        mDisabledBackgroundColor = attrsArray.getColor(
            R.styleable.FinpayMoneyAttrs_fm_disabledColor,
            mDisabledBackgroundColor
        )
        mEnabled = attrsArray.getBoolean(R.styleable.FinpayMoneyAttrs_android_enabled, true)
        mDisabledTextColor = attrsArray.getColor(
            R.styleable.FinpayMoneyAttrs_fm_disabledTextColor,
            mDisabledTextColor
        )
        mDisabledBorderColor = attrsArray.getColor(
            R.styleable.FinpayMoneyAttrs_fm_disabledBorderColor,
            mDisabledBorderColor
        )
        mDefaultTextColor =
            attrsArray.getColor(R.styleable.FinpayMoneyAttrs_fm_textColor, mDefaultTextColor)
        // if default color is set then the icon's color is the same (the default for icon's color)
        mDefaultIconColor =
            attrsArray.getColor(R.styleable.FinpayMoneyAttrs_fm_iconColor, mDefaultTextColor)
        mDefaultTextSize = attrsArray.getDimension(
            R.styleable.FinpayMoneyAttrs_fm_textSize,
            mDefaultTextSize.toFloat()
        ).toInt()
        mDefaultTextSize = attrsArray.getDimension(
            R.styleable.FinpayMoneyAttrs_fm_textSize,
            mDefaultTextSize.toFloat()
        ).toInt()
        mDefaultTextGravity =
            attrsArray.getInt(R.styleable.FinpayMoneyAttrs_fm_textGravity, mDefaultTextGravity)
        mBorderColor =
            attrsArray.getColor(R.styleable.FinpayMoneyAttrs_fm_borderColor, mBorderColor)
        mBorderWidth = attrsArray.getDimension(
            R.styleable.FinpayMoneyAttrs_fm_borderWidth,
            mBorderWidth.toFloat()
        ).toInt()
        mRadius = attrsArray.getDimension(R.styleable.FinpayMoneyAttrs_fm_radius, mRadius.toFloat())
            .toInt()
        mFontIconSize = attrsArray.getDimension(
            R.styleable.FinpayMoneyAttrs_fm_fontIconSize,
            mFontIconSize.toFloat()
        ).toInt()
        mIconPaddingLeft = attrsArray.getDimension(
            R.styleable.FinpayMoneyAttrs_fm_iconPaddingLeft,
            mIconPaddingLeft.toFloat()
        ).toInt()
        mIconPaddingRight = attrsArray.getDimension(
            R.styleable.FinpayMoneyAttrs_fm_iconPaddingRight,
            mIconPaddingRight.toFloat()
        ).toInt()
        mIconPaddingTop = attrsArray.getDimension(
            R.styleable.FinpayMoneyAttrs_fm_iconPaddingTop,
            mIconPaddingTop.toFloat()
        ).toInt()
        mIconPaddingBottom = attrsArray.getDimension(
            R.styleable.FinpayMoneyAttrs_fm_iconPaddingBottom,
            mIconPaddingBottom.toFloat()
        ).toInt()
        mTextAllCaps = attrsArray.getBoolean(R.styleable.FinpayMoneyAttrs_fm_textAllCaps, false)
        mTextAllCaps =
            attrsArray.getBoolean(R.styleable.FinpayMoneyAttrs_android_textAllCaps, false)
        mGhost = attrsArray.getBoolean(R.styleable.FinpayMoneyAttrs_fm_ghost, mGhost)
        mUseSystemFont =
            attrsArray.getBoolean(R.styleable.FinpayMoneyAttrs_fm_useSystemFont, mUseSystemFont)
        var text = attrsArray.getString(R.styleable.FinpayMoneyAttrs_fm_text)
        if (text == null) { //no fb_text attribute
            text = attrsArray.getString(R.styleable.FinpayMoneyAttrs_android_text)
        }
        mIconPosition =
            attrsArray.getInt(R.styleable.FinpayMoneyAttrs_fm_iconPosition, mIconPosition)
        val fontIcon = attrsArray.getString(R.styleable.FinpayMoneyAttrs_fm_fontIconResource)
        val iconFontFamily = attrsArray.getString(R.styleable.FinpayMoneyAttrs_fm_iconFont)
        val textFontFamily = attrsArray.getString(R.styleable.FinpayMoneyAttrs_fm_textFont)
        val icon: Drawable? = null
        mIconResource = try {
            attrsArray.getDrawable(R.styleable.FinpayMoneyAttrs_fm_iconResource)
        } catch (e: Exception) {
            null
        }
        if (fontIcon != null) mFontIcon = fontIcon
        if (text != null) mText = if (mTextAllCaps) text.uppercase(Locale.getDefault()) else text
        if (!isInEditMode) {
            mIconTypeFace = if (iconFontFamily != null) {
                Utils.findFont(mContext, iconFontFamily, mDefaultIconFont)
            } else {
                Utils.findFont(mContext, mDefaultIconFont, null)
            }
            mTextTypeFace = if (textFontFamily != null) {
                Utils.findFont(mContext, textFontFamily, mDefaultTextFont)
            } else {
                Utils.findFont(mContext, mDefaultTextFont, null)
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun getRippleDrawable(
        defaultDrawable: Drawable,
        focusDrawable: Drawable,
        disabledDrawable: Drawable
    ): Drawable {
        return if (!mEnabled) {
            disabledDrawable
        } else {
            RippleDrawable(
                ColorStateList.valueOf(mFocusBackgroundColor),
                defaultDrawable,
                focusDrawable
            )
        }
    }

    @SuppressLint("NewApi")
    private fun setupBackground() {


        // Default Drawable
        val defaultDrawable = GradientDrawable()
        defaultDrawable.cornerRadius = mRadius.toFloat()
        if (mGhost) {
            defaultDrawable.setColor(resources.getColor(android.R.color.transparent)) // Hollow Background
        } else {
            defaultDrawable.setColor(mDefaultBackgroundColor)
        }

        //Focus Drawable
        val focusDrawable = GradientDrawable()
        focusDrawable.cornerRadius = mRadius.toFloat()
        focusDrawable.setColor(mFocusBackgroundColor)

        // Disabled Drawable
        val disabledDrawable = GradientDrawable()
        disabledDrawable.cornerRadius = mRadius.toFloat()
        disabledDrawable.setColor(mDisabledBackgroundColor)
        disabledDrawable.setStroke(mBorderWidth, mDisabledBorderColor)

        // Handle Border
        if (mBorderColor != 0) {
            defaultDrawable.setStroke(mBorderWidth, mBorderColor)
        }

        // Handle disabled border color
        if (!mEnabled) {
            defaultDrawable.setStroke(mBorderWidth, mDisabledBorderColor)
            if (mGhost) {
                disabledDrawable.setColor(resources.getColor(android.R.color.transparent))
            }
        }
        if (mUseRippleEffect && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.background = getRippleDrawable(defaultDrawable, focusDrawable, disabledDrawable)
        } else {
            val states = StateListDrawable()

            // Focus/Pressed Drawable
            val drawable2 = GradientDrawable()
            drawable2.cornerRadius = mRadius.toFloat()
            if (mGhost) {
                drawable2.setColor(resources.getColor(android.R.color.transparent)) // No focus color
            } else {
                drawable2.setColor(mFocusBackgroundColor)
            }

            // Handle Button Border
            if (mBorderColor != 0) {
                if (mGhost) {
                    drawable2.setStroke(
                        mBorderWidth,
                        mFocusBackgroundColor
                    ) // Border is the main part of button now
                } else {
                    drawable2.setStroke(mBorderWidth, mBorderColor)
                }
            }
            if (!mEnabled) {
                if (mGhost) {
                    drawable2.setStroke(mBorderWidth, mDisabledBorderColor)
                } else {
                    drawable2.setStroke(mBorderWidth, mDisabledBorderColor)
                }
            }
            if (mFocusBackgroundColor != 0) {
                states.addState(intArrayOf(android.R.attr.state_pressed), drawable2)
                states.addState(intArrayOf(android.R.attr.state_focused), drawable2)
                states.addState(intArrayOf(-android.R.attr.state_enabled), disabledDrawable)
            }
            states.addState(intArrayOf(), defaultDrawable)
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                setBackgroundDrawable(states)
            } else {
                this.background = states
            }
        }
    }

    /**
     * Initialize button container
     */
    private fun initializeButtonContainer() {
        if (mIconPosition == POSITION_TOP || mIconPosition == POSITION_BOTTOM) {
            this.orientation = VERTICAL
        } else {
            this.orientation = HORIZONTAL
        }
        if (this.layoutParams == null) {
            val containerParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            this.layoutParams = containerParams
        }
        this.gravity = Gravity.CENTER
        this.isClickable = true
        this.isFocusable = true
        if (mIconResource == null && mFontIcon == null && paddingLeft == 0 && paddingRight == 0 && paddingTop == 0 && paddingBottom == 0) {
            setPadding(20, 20, 20, 20)
        }
    }

    /**
     * Set the capitalization of text
     *
     * @param textAllCaps : is text to be capitalized
     */
    fun setTextAllCaps(textAllCaps: Boolean) {
        mTextAllCaps = textAllCaps
        setText(mText)
    }

    /**
     * Set the color of text
     *
     * @param color : Color
     * use Color.parse('#code')
     */
    fun setTextColor(color: Int) {
        mDefaultTextColor = color
        if (textViewObject == null) initialize() else textViewObject!!.setTextColor(color)
    }

    /**
     * Setting the icon's color independent of the text color
     *
     * @param color : Color
     */
    fun setIconColor(color: Int) {
        if (iconFontObject != null) {
            iconFontObject!!.setTextColor(color)
        }
    }

    /**
     * Set Background color of the button
     *
     * @param color : use Color.parse('#code')
     */
    override fun setBackgroundColor(color: Int) {
        mDefaultBackgroundColor = color
        if (iconImageObject != null || iconFontObject != null || textViewObject != null) {
            setupBackground()
        }
    }

    fun getmDefaultIconColor(): Int {
        return mDefaultIconColor
    }

    fun setIconColorFilter(colorFilter: Int) {
        iconImageObject!!.setColorFilter(colorFilter, PorterDuff.Mode.SRC_ATOP)
    }

    /**
     * Set Focus color of the button
     *
     * @param color : use Color.parse('#code')
     */
    fun setFocusBackgroundColor(color: Int) {
        mFocusBackgroundColor = color
        if (iconImageObject != null || iconFontObject != null || textViewObject != null) setupBackground()
    }

    /**
     * Set Disabled state color of the button
     *
     * @param color : use Color.parse('#code')
     */
    fun setDisableBackgroundColor(color: Int) {
        mDisabledBackgroundColor = color
        if (iconImageObject != null || iconFontObject != null || textViewObject != null) setupBackground()
    }

    /**
     * Set Disabled state color of the button text
     *
     * @param color : use Color.parse('#code')
     */
    fun setDisableTextColor(color: Int) {
        mDisabledTextColor = color
        if (textViewObject == null) initialize() else if (!mEnabled) textViewObject!!.setTextColor(
            color
        )
    }

    /**
     * Set Disabled state color of the button border
     *
     * @param color : use Color.parse('#code')
     */
    fun setDisableBorderColor(color: Int) {
        mDisabledBorderColor = color
        if (iconImageObject != null || iconFontObject != null || textViewObject != null) {
            setupBackground()
        }
    }

    /**
     * Set the size of Text in sp
     *
     * @param textSize : Text Size
     */
    fun setTextSize(textSize: Int) {
        mDefaultTextSize = Utils.spToPx(context, textSize.toFloat())
        if (textViewObject != null) textViewObject!!.textSize = textSize.toFloat()
    }

    /**
     * Set the gravity of Text
     *
     * @param gravity : Text Gravity
     */
    fun setTextGravity(gravity: Int) {
        mDefaultTextGravity = gravity
        if (textViewObject != null) {
            textViewObject!!.gravity = gravity
        }
    }

    /**
     * Set Padding for mIconView and mFontIconSize
     *
     * @param paddingLeft   : Padding Left
     * @param paddingTop    : Padding Top
     * @param paddingRight  : Padding Right
     * @param paddingBottom : Padding Bottom
     */
    fun setIconPadding(paddingLeft: Int, paddingTop: Int, paddingRight: Int, paddingBottom: Int) {
        mIconPaddingLeft = paddingLeft
        mIconPaddingTop = paddingTop
        mIconPaddingRight = paddingRight
        mIconPaddingBottom = paddingBottom
        if (iconImageObject != null) {
            iconImageObject!!.setPadding(
                mIconPaddingLeft,
                mIconPaddingTop, mIconPaddingRight, mIconPaddingBottom
            )
        }
        if (iconFontObject != null) {
            iconFontObject!!.setPadding(
                mIconPaddingLeft,
                mIconPaddingTop, mIconPaddingRight, mIconPaddingBottom
            )
        }
    }

    /**
     * Set an icon from resources to the button
     *
     * @param drawable : Int resource
     */
    fun setIconResource(drawable: Int) {
        mIconResource = mContext.resources.getDrawable(drawable)
        if (iconImageObject == null || iconFontObject != null) {
            iconFontObject = null
            initialize()
        } else iconImageObject!!.setImageDrawable(mIconResource)
    }

    /**
     * Set a drawable to the button
     *
     * @param drawable : Drawable resource
     */
    fun setIconResource(drawable: Drawable?) {
        mIconResource = drawable
        if (iconImageObject == null || iconFontObject != null) {
            iconFontObject = null
            initialize()
        } else iconImageObject!!.setImageDrawable(mIconResource)
    }

    /**
     * Set a font icon to the button (eg FFontAwesome or Entypo...)
     *
     * @param icon : Icon value eg : \uf082
     */
    fun setIconResource(icon: String?) {
        mFontIcon = icon
        if (iconFontObject == null) {
            iconImageObject = null
            initialize()
        } else iconFontObject!!.text = icon
    }

    /**
     * Set Icon size of the button (for only font icons) in sp
     *
     * @param iconSize : Icon Size
     */
    fun setFontIconSize(iconSize: Int) {
        mFontIconSize = Utils.spToPx(context, iconSize.toFloat())
        if (iconFontObject != null) iconFontObject!!.textSize = iconSize.toFloat()
    }

    /**
     * Set Icon Position
     * Use the global variables (FancyButton.POSITION_LEFT, FancyButton.POSITION_RIGHT, FancyButton.POSITION_TOP, FancyButton.POSITION_BOTTOM)
     *
     * @param position : Position
     */
    fun setIconPosition(position: Int) {
        mIconPosition = if (position > 0 && position < 5) position else POSITION_LEFT
        initialize()
    }

    /**
     * Set color of the button border
     *
     * @param color : Color
     * use Color.parse('#code')
     */
    fun setBorderColor(color: Int) {
        mBorderColor = color
        if (iconImageObject != null || iconFontObject != null || textViewObject != null) {
            setupBackground()
        }
    }

    /**
     * Set Width of the button
     *
     * @param width : Width
     */
    fun setBorderWidth(width: Int) {
        mBorderWidth = width
        if (iconImageObject != null || iconFontObject != null || textViewObject != null) {
            setupBackground()
        }
    }

    /**
     * Set Border Radius of the button
     *
     * @param radius : Radius
     */
    fun setRadius(radius: Int) {
        mRadius = radius
        if (iconImageObject != null || iconFontObject != null || textViewObject != null) {
            setupBackground()
        }
    }

    /**
     * Set custom font for button Text
     *
     * @param fontName : Font Name
     * Place your text fonts in assets
     */
    fun setCustomTextFont(fontName: String?) {
        mTextTypeFace = Utils.findFont(mContext, fontName, mDefaultTextFont)
        if (textViewObject == null) initialize() else textViewObject!!.typeface = mTextTypeFace
    }

    /**
     * Set Custom font for button icon
     *
     * @param fontName : Font Name
     * Place your icon fonts in assets
     */
    fun setCustomIconFont(fontName: String?) {
        mIconTypeFace = Utils.findFont(mContext, fontName, mDefaultIconFont)
        if (iconFontObject == null) initialize() else iconFontObject!!.typeface = mIconTypeFace
    }

    /**
     * Override setEnabled and rebuild the fancybutton view
     * To redraw the button according to the state : enabled or disabled
     *
     * @param value
     */
    override fun setEnabled(value: Boolean) {
        super.setEnabled(value)
        mEnabled = value
        initialize()
    }

    /**
     * Setting the button to have hollow or solid shape
     *
     * @param ghost
     */
    fun setGhost(ghost: Boolean) {
        mGhost = ghost
        if (iconImageObject != null || iconFontObject != null || textViewObject != null) {
            setupBackground()
        }
    }

    /**
     * If enabled, the button title will ignore its custom font and use the default system font
     *
     * @param status : true || false
     */
    fun setUsingSystemFont(status: Boolean) {
        mUseSystemFont = status
    }

    /**
     * Return Text of the button
     *
     * @return Text
     */
    val text: CharSequence
        get() = if (textViewObject != null) textViewObject!!.text else ""

    /**
     * Set Text of the button
     *
     * @param text : Text
     */
    fun setText(text: String?) {
        var text = text
        text = if (mTextAllCaps) text!!.uppercase(Locale.getDefault()) else text
        mText = text
        if (textViewObject == null) initialize() else textViewObject!!.text = text
    }

    companion object {
        val TAG = MainButtonWidget::class.java.simpleName
        const val POSITION_LEFT = 1
        const val POSITION_RIGHT = 2
        const val POSITION_TOP = 3
        const val POSITION_BOTTOM = 4
    }
}