package com.leeuw.roomword.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.leeuw.roomword.data.db.WordDao;
import com.leeuw.roomword.data.db.WordRoomDatabase;
import com.leeuw.roomword.model.Word;

import java.util.List;

public class WordRepository {

    private final WordDao mWordDao;
    private final LiveData<List<Word>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAlphabetizedWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> mWordDao.insert(word));
    }
}