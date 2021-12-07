package com.example.notesfinal
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
class AddNotes : AppCompatActivity() {
    var id=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)
        title="add Notes"
        try{
            var bundle:Bundle?=intent.extras
            id=bundle!!.getInt("ID",0)
            if(id!=0){
                etTitle.setText(bundle.getString("name"))
                etDes.setText(bumdle.getString("DES"))
            }
        }catch(e:Exception){
            e.printStackTrace()
        }
    }
    fun btnAdd(: View){
        var values= ContentValues()
        if(etTitle.text.length>0)
        {
            values.put("Title",etTitle.text.toString())
            values.put("Description",etDes.text.toString)

        }
        else{
            etTitle.setError("You Forget to add text ")
            ettitle.requestFocus()
            return
        }
        if(id==0)
        {
            val ID=database.store(values)
            if(ID>0){
                Toast.makeText(this,"note is added",Toast.LENGTH_SHORT).show()
                finish()
            }
            else
            {
                Toast.makeText(this,"cannot add note",Toast.LENGTH_SHORT).show()


            }
            else{
                val ID=database.update(id,etTitle.text.toString(),etDes.text.toString())
            if(ID>0){
                Toast.makeText(this,"note is updated",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"cannot add note",Toast.l)
            }
        }

        }



    }
}