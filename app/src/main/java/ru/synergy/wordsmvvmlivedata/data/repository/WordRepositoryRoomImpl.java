package ru.synergy.wordsmvvmlivedata.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import ru.synergy.wordsmvvmlivedata.data.room.Word;
import ru.synergy.wordsmvvmlivedata.data.room.WordDao;
import ru.synergy.wordsmvvmlivedata.data.room.WordRoomDatabase;
import ru.synergy.wordsmvvmlivedata.domain.WordRepository;

public class WordRepositoryRoomImpl implements WordRepository {
    private WordDao mWordDao;
    private  LiveData<List<Word>> mAllWords;

    public WordRepositoryRoomImpl(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.getWordDao();
        mAllWords = mWordDao.getAlphabetizedWords();

    }

    @Override
    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    @Override
    public void insert(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() ->{
            mWordDao.insert(word);
        });

    }

    @Override
    public void deleteAll() {
        WordRoomDatabase.databaseWriteExecutor.execute(() ->{
            mWordDao.deleteAll();
        });
    }
}

