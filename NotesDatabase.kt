package com.example.notesfinal

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DBNAME="MyNotes"
val DBTABLE="Notes"
val COL_ID="ID"
val COL_TITLE="Title"
val COL_DES="Description"
val DB_VERSION=1;
var resultlist=Arraylist<Note>()

class NotesDatabase :SQLiteOpenHelper {
    var context:Context?=null
    constructor(
        context: Context?,

    ) : super(context, DBNAME, null, DB_VERSION) {
        this.context = context


    }

    override fun onCreate(sqliteDatabase: SQLiteDatabase?) {
        //CREATING TABLE
        "CREATE TABLE" + DBTABLE + "(" + COL_ID + "INTEGER PRIMARY KEY," + COL_TITLE + " TEXT," + COL_DES + "TEXT);"
       Toast.makeText(this.context, "Database is created",Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
    fun store(Content:ContentValues):Long{
        val db=this.writableDatabase

        val result=db.insert(DBTABLE,null,content)
        db.close()
        return result
    }
    fun query():ArrayList<Note> {
        val db = this.readableDatabase
        val query = "SELECT * FROM" + DBTABLE
        var cSor = db.rawQuery(query, null)
        if (cSor.moveToFirst()) {
            if (cSor.moveToFirst()) {
                do {
                    var id = cSor.getInt(cSor.getColumnIndexOrThrow(COL_ID))
                    var id = cSor.getInt(cSor.getColumnIndexOrThrow(COL_TITLE))
                    var id = cSor.getInt(cSor.getColumnIndexOrThrow(COL_DES))
                    resultlist.add(Note(id, name, des))


                } while (cSor.moveToNext())
            }
            return resultList
        }
        fun checksize(): Int {
            var counter = 0
            val db = this.readableDatabase
            val query_paramas = "SELECT * FORM" + DBTABLE
            val cSor = db!!.rawQuery(query_paramas, null)
            if (cSor.moveToFirst()) {
                do {
                    counter++

                } while (cSor.moveToNext())
            }
            return counter

        }
        fun delete(id:Int):Int{
            val db=this.writableDatabase
            val count=db!!.delete(DBTABLE,"TD=?",arrayOf(id.toString()))
            db.close()
            return count

        }
        fun update(id:Int,title:String,desc:String):Int{
            val db=this.writableDatabase
            var cv=ContentValues()
            cv.put(COL_TITLE,title)
            cv.put(COL_DES,desc)
            val count=db!!.update(DBTABLE,cv,"TD=?",arrayOf(id.toString()))
            db.close()
            return count

        }


    }