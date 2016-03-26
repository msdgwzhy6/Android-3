package com.ikok.notepad.Util;

import android.os.AsyncTask;

import com.ikok.notepad.DBUtil.NoteDB;

/**
 * Created by Anonymous on 2016/3/25.
 */
public class DeleteAsyncTask extends AsyncTask<Integer,Void,Void> {

    private NoteDB noteDB;

    public DeleteAsyncTask(NoteDB noteDB) {
        this.noteDB = noteDB;
    }

    @Override
    protected Void doInBackground(Integer... params) {
        noteDB.deleteById(params[0]);
        return null;
    }

}
