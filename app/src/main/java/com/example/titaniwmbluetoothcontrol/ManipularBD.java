package com.example.titaniwmbluetoothcontrol;


import android.content.Context;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Update;

import java.util.List;

@Database(entities = {ManipularBD.Person.class}, version = 1, exportSchema = false)
public abstract  class ManipularBD extends RoomDatabase {

    private static ManipularBD INSTANCE;

    public abstract PersonDao personDao();

    public static ManipularBD getAppDatabase(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ManipularBD.class, "ImpressoraDB")
                    //.fallbackToDestructiveMigration()
                    //.allowMainThreadQueries()
                    .build();
        }

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }



    @Dao
    public interface PersonDao {

        @Insert
        long insert(Person person);

        @Insert
        void insertAll(List<Person> personList);

        @Update
        int update(Person person);

        @Delete
        void delete(Person person);

        @Query("SELECT * FROM person")
        List<Person> getAll();

        @Query("SELECT * FROM person WHERE id = :idperson")
        Person getById(int idperson);

    }






    @Entity
     public static class Person {

        @PrimaryKey(autoGenerate = true)
        private long id;
        @ColumnInfo(name = "first_name")
        private String firstName;
        @ColumnInfo(name = "last_name")
        private String lastName;
        private int age;
        private String email;

        public Person() {}

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

}

