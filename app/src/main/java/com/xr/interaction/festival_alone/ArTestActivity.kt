package com.xr.interaction.festival_alone

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.math.Quaternion
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.xr.interaction.festival_alone.databinding.ActivityArTestBinding


class ArTestActivity : AppCompatActivity(), View.OnClickListener {


//    lateinit var arrayView: Array<View>

    //lateinit var image0Renderable: ModelRenderable
    /*
    lateinit var image1Renderable: ModelRenderable
    lateinit var image2Renderable: ModelRenderable
    lateinit var image3Renderable: ModelRenderable
    lateinit var image4Renderable: ModelRenderable
    lateinit var image5Renderable: ModelRenderable
    lateinit var image6Renderable: ModelRenderable
    lateinit var image7Renderable: ModelRenderable
*/

    internal var seleted = 1
    lateinit var arFragment: ArFragment

    override fun onClick(view: View?) {
        if(view !!.id == R.id.image0) {
            seleted = 1
            mySetBackGround(view!!.id)
        }
        /*
        else if (view !!.id == R.id.image1) {
            seleted = 2
            mySetBackGround(view!!.id)
        }
        else if (view !!.id == R.id.image2) {
            seleted = 3
            mySetBackGround(view!!.id)
        }
        else if (view !!.id == R.id.image3) {
            seleted = 4
            mySetBackGround(view!!.id)
        }
        else if (view !!.id == R.id.image4) {
            seleted = 5
            mySetBackGround(view!!.id)
        }
        else if (view !!.id == R.id.image5) {
            seleted = 6
            mySetBackGround(view!!.id)
        }
        else if (view !!.id == R.id.image6) {
            seleted = 7
            mySetBackGround(view!!.id)
        }

         */
        else  {
            seleted = 8
            mySetBackGround(view!!.id)
        }

    }


    lateinit var image0Renderable: ModelRenderable

    private lateinit var binding: ActivityArTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar_test)

       // setupArray()
        setupClickListener()
        setUpModel()



        arFragment = supportFragmentManager.findFragmentById(R.id.scene_from_fragment) as ArFragment
        arFragment.setOnTapArPlaneListener{hitResult, plane, motionEvent ->
            val anchor = hitResult.createAnchor()
            val anchorNode = AnchorNode(anchor)
            anchorNode.setParent(arFragment.arSceneView.scene)

            val node = Node()
            node.setParent(anchorNode)
            node.worldRotation = Quaternion()


            val bear = TransformableNode(arFragment.transformationSystem)
            bear.setParent(node)
            bear.renderable = image0Renderable
            bear.select()

            createModel(anchorNode, seleted)
        }

    }

    private fun createModel(anchorNode: AnchorNode, selected: Int) {
        if(selected == 1) {
            val bear = TransformableNode(arFragment.transformationSystem)
            bear.setParent(anchorNode)
            bear.renderable = image0Renderable
            bear.select()
        }


    }

    private fun mySetBackGround(id : Int) {
        /*
        for(i in arrayView.indices) {
            if(arrayView[i].id == id)
                arrayView[i].setBackgroundColor(Color.parseColor("#80333639"))
            else
                arrayView[i].setBackgroundColor(Color.TRANSPARENT)
        }

         */
    }


    private fun setupArray() {
        /*
        arrayView = arrayOf(
            binding.image0,
            binding.image1,
            binding.image2,
            binding.image3,
            binding.image4,
            binding.image5,
            binding.image6,
            binding.image7
        )

         */
    }

    private fun setupClickListener() {
        /*
        for (i in arrayView.indices) {
            arrayView[i].setOnClickListener(this)
        }

         */
    }

    private fun setUpModel() {


        ModelRenderable.builder()
            .setSource(this, R.drawable.asset1)
            .build()
            .thenAccept { modelRenderable -> image0Renderable = modelRenderable
                val image0Renderable : ImageView = binding.image0
                image0Renderable.setOnClickListener {
                    Log.d("몰라용", "모르겠어용")
                }
            }
            .exceptionally { throwalbe ->
                Toast.makeText(this@ArTestActivity, "can't load", Toast.LENGTH_SHORT).show()
                null
            }

        /*
        ModelRenderable.builder().setSource(this, R.drawable.asset11)
            .build()
            .thenAccept { modelRenderable -> image1Renderable = modelRenderable }
            .exceptionally { throwalbe ->
                Toast.makeText(this@MainActivity, "can't load", Toast.LENGTH_SHORT).show()
                null
            }

        ModelRenderable.builder().setSource(this, R.drawable.asset12)
            .build()
            .thenAccept { modelRenderable -> image2Renderable = modelRenderable }
            .exceptionally { throwalbe ->
                Toast.makeText(this@MainActivity, "can't load", Toast.LENGTH_SHORT).show()
                null
            }

        ModelRenderable.builder().setSource(this, R.drawable.asset13)
            .build()
            .thenAccept { modelRenderable -> image3Renderable = modelRenderable }
            .exceptionally { throwalbe ->
                Toast.makeText(this@MainActivity, "can't load", Toast.LENGTH_SHORT).show()
                null
            }
        ModelRenderable.builder().setSource(this, R.drawable.asset14)
            .build()
            .thenAccept { modelRenderable -> image4Renderable = modelRenderable }
            .exceptionally { throwalbe ->
                Toast.makeText(this@MainActivity, "can't load", Toast.LENGTH_SHORT).show()
                null
            }
        ModelRenderable.builder().setSource(this, R.drawable.asset15)
            .build()
            .thenAccept { modelRenderable -> image5Renderable = modelRenderable }
            .exceptionally { throwalbe ->
                Toast.makeText(this@MainActivity, "can't load", Toast.LENGTH_SHORT).show()
                null
            }
        ModelRenderable.builder().setSource(this, R.drawable.asset16)
            .build()
            .thenAccept { modelRenderable -> image6Renderable = modelRenderable }
            .exceptionally { throwalbe ->
                Toast.makeText(this@MainActivity, "can't load", Toast.LENGTH_SHORT).show()
                null
            }
        ModelRenderable.builder().setSource(this, R.drawable.asset17)
            .build()
            .thenAccept { modelRenderable -> image7Renderable = modelRenderable }
            .exceptionally { throwalbe ->
                Toast.makeText(this@MainActivity, "can't load", Toast.LENGTH_SHORT).show()
                null
            }

         */
    }


}