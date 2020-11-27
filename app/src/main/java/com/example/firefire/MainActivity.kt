package com.example.firefire

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val notasRef = FirebaseDatabase.getInstance().getReference("notas")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        save_button.setOnClickListener {

            saveMarkFromForm()

        }


        notasRef.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(databaseError: DatabaseError) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildChanged(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                val notas = dataSnapshot.getValue(Notas::class.java)
                if (notas != null) writeNotas(notas)
            }
        })
    }

    private fun saveMarkFromForm() {
        val notas = Notas(
            name_editText.text.toString(),
            subject_editText.text.toString(),
            mark_editText.text.toString().toDouble()
        )
        notasRef.push().setValue(notas)
    }

    private fun writeNotas(notas: Notas) {
        val text = list_textView.text.toString() + notas.toString() + "\n"
        list_textView.text = text
    }
}

