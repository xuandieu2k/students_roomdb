package vn.xdeuhug.test_roomdb.ui.main

/**
 * @Author: NGUYEN XUAN DIEU
 * @Date: 08 / 09 / 2024
 */
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vn.xdeuhug.test_roomdb.data.repository.StudentRepository
import vn.xdeuhug.test_roomdb.model.Student

class MainViewModel(private val repository: StudentRepository) : ViewModel() {

    val allStudents = repository.allStudents.asLiveData()

    fun insert(student: Student) = viewModelScope.launch {
        repository.insert(student)
    }

    fun update(student: Student) = viewModelScope.launch {
        repository.update(student)
    }

    fun delete(student: Student) = viewModelScope.launch {
        repository.delete(student)
    }
}