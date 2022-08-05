package com.appsgit.mylauncher.extensions

import android.annotation.SuppressLint
import android.gesture.Gesture
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.appsgit.mylauncher.ui.main.MainActivity
import kotlin.math.abs

@SuppressLint("ClickableViewAccessibility")
fun MainActivity.enableGesture() {

    var gestureListener = object : GestureDetector.OnGestureListener {
        val minSwipeDistance = 300
        val threshold = 100
        val velocityThreshold = 10
        var isSwipingUp = false
        var percentage = 1.0

        override fun onDown(e: MotionEvent?): Boolean {
            if (view1.alpha <= 0f && isRecyclerviewScrollable()) {
                if (isSwipingUp && isRecyclerViewInTop()) {
                    return false
                } else {
                    isSwipingUp = false
                    return isRecyclerViewInTop()
                }
            }
            return true
        }

        override fun onShowPress(p0: MotionEvent?) {
        }

        override fun onSingleTapUp(p0: MotionEvent?): Boolean = true
        override fun onScroll(e1: MotionEvent?,
                              e2: MotionEvent?,
                              velocityX: Float,
                              velocityY: Float): Boolean {

            val xDiff = (e2?.x ?: 0f) - (e1?.x ?: 0f)
            val yDiff = (e2?.y ?: 0f) - (e1?.y ?: 0f)
            if (abs(xDiff) > abs(yDiff)) {
                return true
            } else {
                if (abs(yDiff) > threshold && abs(velocityY) > velocityThreshold) {
                    var alpha = 0f
                    if (yDiff > threshold) {
                        isSwipingUp = false
                        if (view1.alpha >= 1f) return true
                        percentage = ((abs(yDiff)/minSwipeDistance)).toDouble()
                        if (percentage > 1) percentage = 1.0

                        alpha = percentage.toFloat()

                        if (view1.alpha != alpha) {
                            view1.alpha = alpha
                        }
                    } else {
                        isSwipingUp = true
                        if (view1.alpha <= 0f) return true
                        percentage = ((abs(yDiff)/minSwipeDistance)).toDouble()
                        if (percentage < 0) percentage = 0.0

                        alpha = (1 - percentage).toFloat()
                        if (view1.alpha != alpha) {
                            view1.alpha = alpha
                        }
                    }
                }
            }
            return true
        }

        override fun onLongPress(p0: MotionEvent?) {}

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean = false
    }

    var mDetector = GestureDetector(this, gestureListener)
    var touchListener = View.OnTouchListener { _, p1 -> mDetector.onTouchEvent(p1) }
    view1.setOnTouchListener(touchListener)
}

internal fun MainActivity.isRecyclerviewScrollable() =
    this.recyclerView.computeHorizontalScrollRange() > recyclerView.width ||
            recyclerView.computeVerticalScrollRange() > recyclerView.height

internal fun MainActivity.isRecyclerViewInTop() : Boolean {
    var pos = (recyclerView.layoutManager as? GridLayoutManager)
        ?.findFirstCompletelyVisibleItemPosition() ?: -1
    return pos  < 4
}