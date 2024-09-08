package vn.xdeuhug.test_roomdb.data.repository

import kotlinx.coroutines.flow.Flow
import vn.xdeuhug.test_roomdb.data.local.dao.StudentDao
import vn.xdeuhug.test_roomdb.model.Student

/**
 * @Author: NGUYEN XUAN DIEU
 * @Date: 08 / 09 / 2024
 */
class StudentRepository(private val studentDao: StudentDao) {

    val allStudents: Flow<List<Student>> = studentDao.getAllStudents()

    suspend fun insert(student: Student) {
        studentDao.insert(student)
    }

    suspend fun update(student: Student) {
        studentDao.update(student)
    }

    suspend fun delete(student: Student) {
        studentDao.delete(student)
    }
}