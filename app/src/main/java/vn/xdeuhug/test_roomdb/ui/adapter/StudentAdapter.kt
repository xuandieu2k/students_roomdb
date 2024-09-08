package vn.xdeuhug.test_roomdb.ui.adapter

/**
 * @Author: NGUYEN XUAN DIEU
 * @Date: 08 / 09 / 2024
 */
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vn.xdeuhug.test_roomdb.databinding.ItemStudentBinding
import vn.xdeuhug.test_roomdb.model.Student

class StudentAdapter() : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    private var students = listOf<Student>()

    inner class StudentViewHolder(private val binding: ItemStudentBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(student: Student) {
            binding.textViewName.text = student.name
            binding.textViewAge.text = "Age: ${student.age}"
//            binding.textViewGender.text = "Gender: ${student.gender}"
            binding.textViewAddress.text = "Address: ${student.address}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = ItemStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun getItemCount(): Int = students.size

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<Student>) {
        students = list
        notifyDataSetChanged()
    }
}