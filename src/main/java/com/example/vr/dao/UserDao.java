package com.example.vr.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import com.example.vr.bean.User;

import java.util.List;

/**
 * Created by Administrator on 2018/6/12.
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM user ")
    List<User> getAllUsers();

    @Query("SELECT * FROM user WHERE id=:id")
    User getUser(int id);

    @Insert
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User... users);
}
