package vn.xdeuhug.test_roomdb.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import vn.xdeuhug.test_roomdb.R
import vn.xdeuhug.test_roomdb.data.local.AppDatabase
import vn.xdeuhug.test_roomdb.data.repository.StudentRepository
import vn.xdeuhug.test_roomdb.databinding.ActivityMainBinding
import vn.xdeuhug.test_roomdb.model.Student
import vn.xdeuhug.test_roomdb.ui.adapter.StudentAdapter

/**
 * @Author: NGUYEN XUAN DIEU
 * @Date: 08 / 09 / 2024
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = StudentAdapter()
        viewModel = MainViewModel(StudentRepository(
            AppDatabase.getDatabase(this).studentDao()
        ))
        binding.rvStudent.layoutManager = LinearLayoutManager(this)
        binding.rvStudent.adapter = adapter

        viewModel.allStudents.observe(this) { students ->
            adapter.submitList(students)
        }

        binding.btnAdd.setOnClickListener {
            showAddStudentDialog()
        }
    }

    @SuppressLint("MissingInflatedId")
    private fun showAddStudentDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_student, null)
        val nameEditText = dialogView.findViewById<EditText>(R.id.editTextName)
        val ageEditText = dialogView.findViewById<EditText>(R.id.editTextAge)
//        val addressEditText = dialogView.findViewById<EditText>(R.id.editTextAddress)
        val genderEditText = dialogView.findViewById<EditText>(R.id.editTextGender)

        AlertDialog.Builder(this)
            .setTitle("Add Student")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val name = nameEditText.text.toString()
                val age = ageEditText.text.toString().toIntOrNull() ?: 0
//                val address = addressEditText.text.toString()
                val gender = genderEditText.text.toString()

                if (name.isNotEmpty()) {
                    val student = Student(name = name, age = age, gender = gender)
                    viewModel.insert(student)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}