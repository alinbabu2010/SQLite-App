package com.billu.sqliteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.billu.sqliteapp.databinding.ActivityMainBinding

/**
 * Main activity for the SQLite App
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var dbHelper : DBHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Creating instance of DBHelper
        dbHelper = DBHelper(this)

        // Invoking insert function on button click
        binding.btnInsert.setOnClickListener {
            val name = binding.name.text.toString()
            val contact = binding.contact.text.toString()
            val dob = binding.dob.text.toString()
            if(dbHelper.insertData(name,contact,dob)){
                Toast.makeText(this,"Data inserted",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,"Data not inserted",Toast.LENGTH_SHORT).show()
            }
        }

        // Invoking update function on button click
        binding.btnUpdate.setOnClickListener {
            val name = binding.name.text.toString()
            val contact = binding.contact.text.toString()
            val dob = binding.dob.text.toString()
            if(dbHelper.updateData(name,contact,dob)){
                Toast.makeText(this,"Data updated",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,"Data not updated",Toast.LENGTH_SHORT).show()
            }
        }

        // Invoking delete function on button click
        binding.btnDelete.setOnClickListener {
            val name = binding.name.text.toString()
            if(dbHelper.deleteData(name)){
                Toast.makeText(this,"Data deleted",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,"Data not deleted",Toast.LENGTH_SHORT).show()
            }
        }

        // Invoking view function on button click
        binding.btnView.setOnClickListener {
            val result = dbHelper.getData()
            if(result?.count == 0){
                Toast.makeText(this,"No entry in database",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val stringBuffer = StringBuffer()
            while (result?.moveToNext() == true){
                stringBuffer.append("Name: ${result.getString(0)} \n")
                stringBuffer.append("Contact: ${result.getString(1)} \n")
                stringBuffer.append("Dob: ${result.getString(2)} \n\n")
            }

            // Alert Dialog box to display data
            val builder = AlertDialog.Builder(this)
            builder.setCancelable(true)
            builder.setTitle("User Entries")
            builder.setMessage(stringBuffer.toString())
            builder.show()
        }
    }
}