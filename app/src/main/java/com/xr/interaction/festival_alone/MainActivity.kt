package com.xr.interaction.festival_alone


import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.core.Anchor
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import java.util.function.Consumer
import java.util.function.Function


class MainActivity : AppCompatActivity() {
    private var arFragment: ArFragment? = null
    private var andyRenderable: ModelRenderable? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // AR 사용 가능 여부 체크
        if (!checkIsSupportedDeviceOrFinish(this)) {
            return
        }

        setContentView(R.layout.activity_main)
        arFragment = supportFragmentManager.findFragmentById(R.id.ux_fragment) as ArFragment?
        ModelRenderable.builder()
            .setSource(this, Uri.parse("asset1.png"))
            .build()
            .thenAccept(Consumer { renderable: ModelRenderable? ->
                andyRenderable = renderable
            })
            .exceptionally(
                Function<Throwable, Void?> { throwable: Throwable? ->
                    val toast =
                        Toast.makeText(this, "andy renderable을 불러올 수 없습니다", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                    null
                })

        // 화면을 탭해서 3D 객체 생성
        arFragment!!.setOnTapArPlaneListener { hitResult: HitResult, plane: Plane?, motionEvent: MotionEvent? ->
            if (andyRenderable == null) {
                return@setOnTapArPlaneListener
            }
            val anchor: Anchor = hitResult.createAnchor()
            val anchorNode = AnchorNode(anchor)
            anchorNode.setParent(arFragment!!.arSceneView.scene)
            val andy =
                TransformableNode(arFragment!!.transformationSystem)
            andy.setParent(anchorNode)
            andy.renderable = andyRenderable
            andy.select()
        }
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
        private const val MIN_OPENGL_VERSION = 3.0
        fun checkIsSupportedDeviceOrFinish(activity: Activity): Boolean {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                Log.e(TAG, "Sceneform은 누가 버전 이상에서만 사용할 수 있습니다")
                activity.finish()
                return false
            }
            val openGlVersionString =
                (activity.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager)
                    .deviceConfigurationInfo
                    .glEsVersion
            if (openGlVersionString.toDouble() < MIN_OPENGL_VERSION) {
                Log.e(TAG, "Sceneform은 OpenGL ES 3.0 이상 버전을 요구합니다")
                activity.finish()
                return false
            }
            return true
        }
    }
}