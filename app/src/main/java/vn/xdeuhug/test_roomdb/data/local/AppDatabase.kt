package vn.xdeuhug.test_roomdb.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import vn.xdeuhug.test_roomdb.data.local.dao.StudentDao
import vn.xdeuhug.test_roomdb.model.Student

/**
 * @Author: NGUYEN XUAN DIEU
 * @Date: 08 / 09 / 2024
 */
@Database(entities = [Student::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "students_db"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                instance
            }
        }

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE students ADD COLUMN gender TEXT DEFAULT 'Unknown'")
            }
        }

//        val MIGRATION_2_3 = object : Migration(2, 3) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("CREATE TABLE students_new (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT, age INTEGER NOT NULL, gender TEXT)")
//                database.execSQL("INSERT INTO students_new (id, name, age, gender) SELECT id, name, age, gender FROM students")
//                database.execSQL("DROP TABLE students")
//                database.execSQL("ALTER TABLE students_new RENAME TO students")
//            }
//        }
    }
}