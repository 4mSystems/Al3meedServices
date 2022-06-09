package app.te.alameed.presentation.base.utils.images

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Environment
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.ImageButton
import android.widget.PopupWindow
import android.widget.ProgressBar
import app.te.alameed.R
import app.te.alameed.presentation.base.extensions.hide
import app.te.alameed.presentation.base.extensions.show
import app.te.alameed.presentation.base.utils.images.ImageConstants.fastblur
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.github.chrisbanes.photoview.PhotoView
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.io.RandomAccessFile
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel


class PhotoFullPopupWindow(
    ctx: Context,
    val v: View?,
    imageUrl: String?,
    bitmap: Bitmap?
) {
    //    var view: View? = null
    private var mContext: Context
    private var photoView: PhotoView
    var loading: ProgressBar
    var parent: ViewGroup
    lateinit var popupWindow: PopupWindow

    init {
        mContext = ctx

//        val viewBinding = DataBindingUtil.inflate(
//            ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
//            R.layout.popup_photo_full,
//            view as ViewGroup?,
//            false
//        )
//        val relativeContainer =
//            (mContext as Activity).findViewById<RelativeLayout>(R.id.rl_custom_layout)
//
//        val layout: View =
//            (mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
//                R.layout.popup_photo_full,
//                viewGroup
//            )
        val view =
            (ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                R.layout.popup_photo_full,
                null
            )
        popupWindow = PopupWindow(
            view, ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT, true
        )
        popupWindow.elevation = 5.0f
//        view = contentView
        val closeButton = view.findViewById<View>(R.id.ib_close) as ImageButton
        popupWindow.isOutsideTouchable = true

        // Set a click listener for the popup window close button
        closeButton.setOnClickListener { // Dismiss the popup window
            popupWindow.dismiss()
        }
        //---------Begin customising this popup--------------------
        photoView = view.findViewById<View>(R.id.image) as PhotoView
        loading = view.findViewById<View>(R.id.loading) as ProgressBar
        photoView.maximumScale = 6f
        parent = photoView.parent as ViewGroup

        if (bitmap != null) {
            loading.visibility = View.GONE
            parent.background = BitmapDrawable(
                mContext.resources,
                fastblur(Bitmap.createScaledBitmap(bitmap, 50, 50, true))
            )
            photoView.setImageBitmap(bitmap)
        } else {
            loading.isIndeterminate = true
            loading.visibility = View.VISIBLE
            photoView.appendImage(imageUrl)
        }
    }

    private fun PhotoView.appendImage(imageUrl: String?) {
        Log.e("appendImage", "appendImage: " + imageUrl)
        if (imageUrl != null && imageUrl.isNotEmpty()) {
            if (URLUtil.isValidUrl(imageUrl)) {
                val request = ImageRequest.Builder(context)
                    .data(imageUrl)
                    .crossfade(true)
                    .crossfade(400)
                    .error(R.drawable.ic_api_warning)
                    .placeholder(R.drawable.logo)
                    .target(
                        onStart = { placeholder ->
                            loading.show()
                            setImageDrawable(placeholder)
                        },
                        onSuccess = { result ->
                            loading.hide()
//                            val resultBitmap = (result as BitmapDrawable).bitmap
                            setImageDrawable(result)
//                            updateSuccessBackground(resultBitmap)

                        }
                    )
                    .listener(onError = { request: ImageRequest, throwable: Throwable ->
                        Log.e(
                            "appendImage",
                            "appendImage: " + request.toString() + "\n " + throwable.message
                        )
                        loading.hide()
                        setImageDrawable(request.error)
                    })
                    .build()

                ImageLoader(context).enqueue(request)
            } else {
                load(File(imageUrl)) {
                    crossfade(750) // 75th percentile of a second
                    build()
                }
            }

        } else {
            loading.hide()
            load(R.drawable.logo) {
                crossfade(true)
                transformations(
                    CircleCropTransformation()
                )
            }
        }
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0)
    }

//    private fun updateSuccessBackground(result: Bitmap) {
//        parent.background = BitmapDrawable(
//            mContext.resources, fastblur(
//                Bitmap.createScaledBitmap(convertToMutable(result), 50, 50, true)
//            )
//        )
//    }

}