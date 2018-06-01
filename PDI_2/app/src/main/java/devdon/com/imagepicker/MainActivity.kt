package devdon.com.pdi_1

import android.app.Activity
import android.content.*
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import android.view.DragEvent
import android.view.View.OnClickListener
import android.view.View.OnLongClickListener
import android.widget.AdapterView
import android.widget.TextView
import devdon.com.pdi_1.R.id.*
import android.content.ClipData
import android.R.attr.label
import android.content.Context.CLIPBOARD_SERVICE
import android.view.View.DragShadowBuilder
import android.content.ClipDescription
import android.widget.Toast
import android.widget.LinearLayout
import android.view.ViewGroup
import android.view.View.OnDragListener
import java.util.Random

//import javax.swing.text.html.HTML.getTag

    // This is the method that the system calls when it dispatches a drag event to the
    // listener.
    var imageId = IntArray(10)


class MainActivity : AppCompatActivity() {

        private val ACTION_CAMERA_REQUEST_CODE = 100
        private val ACTION_ALBUM_REQUEST_CODE = 200


        override fun onCreate(savedInstanceState: Bundle?) {
            /*
            imageId[0] = R.drawable.bg_don
            imageId[1] = R.drawable.a11
            imageId[2] = R.drawable.a12
            imageId[3] = R.drawable.a13
            imageId[4] = R.drawable.a14
            imageId[5] = R.drawable.a15
            imageId[6] = R.drawable.a16
            imageId[7] = R.drawable.a17
            imageId[8] = R.drawable.a18
            imageId[9] = R.drawable.a19
            */

            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            cameraAppButton.setOnClickListener(cameraAppButtonHandler)
            albumAppButton.setOnClickListener(albumAppButtonHandler)

            imageView6.scaleType = ImageView.ScaleType.CENTER_CROP

            imageView1.setOnLongClickListener(MyClickListener())


        }

    // 通過 intent 使用 Camera
    private fun takeImageFromCameraWithIntent() {
        println("take image from camera")
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, ACTION_CAMERA_REQUEST_CODE)
    }

    // 通過 intent 使用 album
    private fun takeImageFromAlbumWithIntent() {
        println("take image from album")

        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, ACTION_ALBUM_REQUEST_CODE)
    }
   // private val cameraAppButtonHandler = View.OnClickListener { view ->
    //    takeImageFromCameraWithIntent()
   // }
    private val cameraAppButtonHandler = OnClickListener {
       val random = Random()
       val imgIndex : IntArray= IntArray(11)
       var flag:Boolean
       var i:Int
       i=0
       flag = true
       while (flag)
       {
           val rid:Int = random.nextInt(12345)%10
           //Log.d("Evry Random: ",rid.toString())
           //imgIndex[i]=rid
           if (!imgIndex.contains(rid)){
               imgIndex[i]=rid
               //Log.d("Choice Random: ",i.toString()+"="+imgIndex[i].toString())
               i++
           }
           if (i>=9) {
               flag = false
           }
       }

       imageView1.setImageResource(imageId[imgIndex[0]])
       imageView2.setImageResource(imageId[imgIndex[1]])
       imageView3.setImageResource(imageId[imgIndex[2]])
       imageView4.setImageResource(imageId[imgIndex[3]])
       imageView5.setImageResource(imageId[imgIndex[4]])
       imageView6.setImageResource(imageId[imgIndex[5]])
       imageView7.setImageResource(imageId[imgIndex[6]])
       imageView8.setImageResource(imageId[imgIndex[7]])
       imageView9.setImageResource(imageId[imgIndex[8]])
       /*
       imageView3.setImageResource(imageId[0])
       imageView5.setImageResource(imageId[2])
       imageView1.setImageResource(imageId[3])
       imageView4.setImageResource(imageId[4])
       imageView7.setImageResource(imageId[5])
       imageView9.setImageResource(imageId[6])
       imageView8.setImageResource(imageId[7])
       imageView2.setImageResource(imageId[8])
       imageView6.setImageResource(imageId[9])
       */
   }

        //copy from java project (T.S)
    private inner class MyClickListener : OnLongClickListener {

            // called when the item is long-clicked
            override fun onLongClick(view: View): Boolean {
                // TODO Auto-generated method stub
                // create it from the object's tag
                //val item = ClipData.Item(view.tag as CharSequence)
                var current =view //留下被拖曳的原始資料 ????
                val item = ClipData.Item(view.resources.toString() as CharSequence) //改成resources也一樣將imageView1與resource image一起拖曳
                //val item = ClipData.Item(view.id as CharSequence) //原先用id 會將imageView1與resource image一起拖曳
                Log.d("LongClick", view.resources.toString())
                val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
                val data = ClipData(view.resources.toString(), mimeTypes, item)
                //取ClipData與上面ClipData.item連動
                val shadowBuilder = View.DragShadowBuilder(view)
                view.startDrag(data, //data to be dragged
                        shadowBuilder, //drag shadow
                        view, //local data about the drag and drop operation
                        0   //no needed flags
                )
                view.visibility = View.INVISIBLE
                return true
            }
/*
         fun onDrag(View v, DragEvent dragEvent):Boolean  {
            var current :View = v
            var result:Boolean = true
            var action:Int = dragEvent.getAction()

            when (action) {
               DragEvent.ACTION_DRAG_STARTED->
               {
               Log.d("Drop", "started")
               }

               DragEvent.ACTION_DRAG_ENTERED->
                {
               Log.d("Drop", "entered")
                }

                DragEvent.ACTION_DRAG_EXITED->
                 {
                    Log.d("Drop", "exit")
                 }

                DragEvent.ACTION_DROP->
                {
                  Log.d("Drop", "Drop")
                }
                 DragEvent.ACTION_DRAG_ENDED->
                 {
                  Log.d("Drop", "End")
                 }

                DragEvent.ACTION_DRAG_LOCATION->
                {
                    Log.d("Drop", "Drag location")
                }
                else->
                {
                  result = false
                }
            }
             return result;
        }
*/
        }

    //private val albumAppButtonHandler = View.OnClickListener { view ->
    //    takeImageFromAlbumWithIntent()
    //}
    private val albumAppButtonHandler = OnClickListener {
        val random = Random()
        val imgIndex : IntArray= IntArray(11)
        var flag:Boolean
        var i:Int
       // i=0
        //flag = true
        i = Random().nextInt(1234)%3
        //i=1
        when (i)
        {
            0->{
                imageId[0] = R.drawable.bg_don
                imageId[1] = R.drawable.a11
                imageId[2] = R.drawable.a12
                imageId[3] = R.drawable.a13
                imageId[4] = R.drawable.a14
                imageId[5] = R.drawable.a15
                imageId[6] = R.drawable.a16
                imageId[7] = R.drawable.a17
                imageId[8] = R.drawable.a18
                imageId[9] = R.drawable.a19
            }
            1->{
                imageId[0] = R.drawable.bg_don
                imageId[1] = R.drawable.b11
                imageId[2] = R.drawable.b12
                imageId[3] = R.drawable.b13
                imageId[4] = R.drawable.b14
                imageId[5] = R.drawable.b15
                imageId[6] = R.drawable.b16
                imageId[7] = R.drawable.b17
                imageId[8] = R.drawable.b18
                imageId[9] = R.drawable.b19
            }
            2->{
                imageId[0] = R.drawable.bg_don
                imageId[1] = R.drawable.c11
                imageId[2] = R.drawable.c12
                imageId[3] = R.drawable.c13
                imageId[4] = R.drawable.c14
                imageId[5] = R.drawable.c15
                imageId[6] = R.drawable.c16
                imageId[7] = R.drawable.c17
                imageId[8] = R.drawable.c18
                imageId[9] = R.drawable.c19
            }
        }
        imageView1.setImageResource(imageId[1])
        imageView2.setImageResource(imageId[2])
        imageView3.setImageResource(imageId[3])
        imageView4.setImageResource(imageId[4])
        imageView5.setImageResource(imageId[5])
        imageView6.setImageResource(imageId[6])
        imageView7.setImageResource(imageId[7])
        imageView8.setImageResource(imageId[8])
        imageView9.setImageResource(imageId[9])
    }

    private fun displayImage(bitmap: Bitmap) {
        imageView1.setImageBitmap(bitmap)
        imageView2.setImageBitmap(bitmap)
        imageView3.setImageBitmap(bitmap)
        imageView4.setImageBitmap(bitmap)
        imageView5.setImageBitmap(bitmap)
        imageView6.setImageBitmap(bitmap)
        imageView7.setImageBitmap(bitmap)
        imageView8.setImageBitmap(bitmap)
        imageView9.setImageBitmap(bitmap)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        println("收到 result code $requestCode")

        when(requestCode) {
            ACTION_CAMERA_REQUEST_CODE -> {
                if(resultCode == Activity.RESULT_OK && data != null){
                    displayImage(data.extras.get("data") as Bitmap)
                }
            }

            ACTION_ALBUM_REQUEST_CODE -> {
                if(resultCode == Activity.RESULT_OK && data != null){
                    val resolver = this.contentResolver
                    val bitmap = MediaStore.Images.Media.getBitmap(resolver, data.data)
                    displayImage(bitmap)

                }
            }
            else -> {
                println("no handler onActivityReenter")
            }
        }


    }



}