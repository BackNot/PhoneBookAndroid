package com.example.phonebook;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class DbContext extends RoomDatabase
{
    public abstract MyDAO MyConreteDAO();
}
