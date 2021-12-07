package com.example.notesfinal
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
class MainActivity : AppCompatActivity() {
    var createNote:FloatingActionButton?=null
    var listNotes=ArrayList<Note>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNote=findViewById(R.id.button_add_note)
        createNote?.setOnClickListener{

            val intent= Intent(this,AddNotes::class.java)
            startActivity(intent)
        }
    }
    fun LoadData()
    {
        var database=NotesDatabase(this)
        listNotes.clear()
        val cursor=database.query()
        listNotes=cursor
        var myNoteAdapter=MYNotesAdapter(this,listNotes)
        lvNotes.adapter=myNotesAdapter
    }
    override fun onResume(){
        super.onResume()
        LoadData()
        var database=NotesDatabase(this)
        if(database.checksize()>0){
            noNotes.visiblity=View.GONE
            lvNotes.visiblity=View.VISIBLE

        }else{
            noNotes.visiblity=View.VISIBLE
            noNotes.visiblity=View.GONE
        }
    }
    inner class MYNotesAdapter: BaseAdapter() {
        private var listNotes: ArrayList<Note>

        constructor(context: Context, listNotes:ArrayList<Note>):super()
        {
            this.listNotes=listNotes
            this.context=context
        }
        override fun getCount(): Int {
            return listNotes.size
        }
        override fun getItem(p0: Int): Any {

            return listnotes[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var myview=layoutInflater.inflate(R.layout.row_custom_listitem,null)
            var myNote=listNotes[p0]
            myview.tvTitle.text=myNote.noteName
            myview.tvDes.text=myNote.noteDes
            myview,ivDelete.setOnClickListener{
                var database=NotesDatabase(context)
                database.delete(myNote.noteId!!)
                LoadData()
                if(database.checkSize()>0){
                    noNotes.visiblity=View.GONE
                    lvNotes.visiblity=View.VISIBLE

                }else{
                    noNotes.visiblity=View.VISIBLE
                    noNotes.visiblity=View.GONE
                }

            }
            myview.ivEdit.setOnCliclListener{
                GoToUpdate(myNote)

            }
            return myview
        }

    }
    fun GoToUpdate(note:Note){
      var intent=Intent(this,AddNotes::class.java)
      intent.putExtra("ID",note.noteId)
        intent.putExtra("NAME",note.noteName)
        intent.putExtra("DES",note.noteDes)
        startActivity(intent)

    }
}