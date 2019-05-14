package com.example.phonebook;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MyDAO {
    @Query("SELECT * FROM User ")
    List<User> ShowAllUsers();

    @Query("SELECT * FROM User WHERE ID == :userid") // Find an user with the entered id
    User ShowUserWithId(int userid);

    @Insert
     public void AddUser(User user);

    @Delete
    public void DeleteUser(User user);

    @Update
    public void UpdateUser(User user);

}
