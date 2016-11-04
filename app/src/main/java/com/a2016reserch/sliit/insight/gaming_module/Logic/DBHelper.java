package com.a2016reserch.sliit.insight.gaming_module.Logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Sandasala on 11/3/2016.
 */
public class DBHelper {

    // Database Name
    private static final String DATABASE_NAME = "QuestionDatabase";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    private final Context mCtx;

    // Table Names
    private static final String TABLE_QUESTIONS = "questions_table";
    private static final String TABLE_QUESTIONS_EASY = "questions_table_easy";
    private static final String TABLE_QUESTIONS_MEDIUM = "questions_table_medium";
    private static final String TABLE_QUESTIONS_HARD= "questions_table_hard";
    private static final String TABLE_MARKS = "usermarks";

    // Common column names
    public static final String QUZ_ID = "id";
    public static final String QUZ_NAME = "question";
    public static final String QUZ_DIFFICULTY="difficulty";
    public static final String QUZ_ANSWER="answer";
    public static final String QUZ_SEQUENCE="sequence";
    public static final String QUZ_MARK="marks";
    public static final String QUZ_DATE="date";
    public static final String QUZ_STATUS="status";

    // Table Create Statements

    private static final String CREATE_TABLE_QUESTIONS = "CREATE TABLE "
            + TABLE_QUESTIONS + "(" + QUZ_ID
            + " INTEGER," + QUZ_NAME
            + " TEXT NOT NULL,"+  QUZ_DIFFICULTY
            + " TEXT NOT NULL," + QUZ_ANSWER
            + " TEXT NOT NULL,"+ QUZ_SEQUENCE
            + " INTEGER" + ")";

    private static final String CREATE_TABLE_QUESTIONS_EASY = "CREATE TABLE "
            + TABLE_QUESTIONS_EASY + "(" + QUZ_ID
            + " INTEGER," + QUZ_NAME
            + " TEXT NOT NULL,"+  QUZ_DIFFICULTY
            + " TEXT NOT NULL," + QUZ_ANSWER
            + " TEXT NOT NULL,"+ QUZ_SEQUENCE
            + " INTEGER" + ")";

    private static final String CREATE_TABLE_QUESTIONS_MEDIUM = "CREATE TABLE "
            + TABLE_QUESTIONS_EASY + "(" + QUZ_ID
            + " INTEGER," + QUZ_NAME
            + " TEXT NOT NULL,"+  QUZ_DIFFICULTY
            + " TEXT NOT NULL," + QUZ_ANSWER
            + " TEXT NOT NULL,"+ QUZ_SEQUENCE
            + " INTEGER" + ")";

    private static final String CREATE_TABLE_QUESTIONS_HARD = "CREATE TABLE "
            + TABLE_QUESTIONS_EASY + "(" + QUZ_ID
            + " INTEGER," + QUZ_NAME
            + " TEXT NOT NULL,"+  QUZ_DIFFICULTY
            + " TEXT NOT NULL," + QUZ_ANSWER
            + " TEXT NOT NULL,"+ QUZ_SEQUENCE
            + " INTEGER" + ")";

    private static final String CREATE_TABLE_MARKS = "CREATE TABLE " + TABLE_MARKS
            + "(" + QUZ_ID + " INTEGER,"  + QUZ_DIFFICULTY
            + " TEXT NOT NULL," +  QUZ_STATUS
            + " TEXT NOT NULL," + QUZ_MARK
            + " INTEGER" +")";



    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_QUESTIONS);
//            db.execSQL(CREATE_TABLE_QUESTIONS_EASY);
//            db.execSQL(CREATE_TABLE_QUESTIONS_MEDIUM);
//            db.execSQL(CREATE_TABLE_QUESTIONS_HARD);
            db.execSQL(CREATE_TABLE_MARKS);
        }

        public void onUpgrade(SQLiteDatabase db, int
                oldVersion, int newVersion) {
            // on upgrade drop older tables
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS_EASY );
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS_MEDIUM);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS_HARD );
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_MARKS);
//            if (newVersion > oldVersion) {
//                db.execSQL("ALTER TABLE questions_table ADD COLUMN sequence INTEGER DEFAULT 0");
//            }

            // create new tables
            onCreate(db);
        }
    }

    public void Reset() {
        mDbHelper.onUpgrade(this.mDb, 1, 1);
    }

    public DBHelper(Context ctx) {
        mCtx = ctx;
        mDbHelper = new DatabaseHelper(mCtx);
    }

    public DBHelper open() throws SQLException {
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public void insertMarks(Marks details){

        ContentValues values = new ContentValues();
        //Current date and time
        values.put(QUZ_ID,details.getQuizId());
        values.put(QUZ_DIFFICULTY,details.getDifficulty());
        values.put(QUZ_STATUS,details.getStatus());
        values.put(QUZ_MARK, details.getMarks());
        Log.d("Test","Values are "+values.toString());

        // values.put("date", DateFormat.getDateTimeInstance().format(new Date()));
        mDb.insert(TABLE_MARKS, null, values);

    }


    public void insertQuestions(Questions quz) {

        ContentValues cv = new ContentValues();

        cv.put(QUZ_ID, quz.getQuizId());
        cv.put(QUZ_NAME, quz.getQuestion());
        cv.put(QUZ_DIFFICULTY, quz.getDifficulty());
        cv.put(QUZ_ANSWER, quz.getAnswers());
        cv.put(QUZ_SEQUENCE,quz.getSequence());

        mDb.insert(TABLE_QUESTIONS, null, cv);
    }

    public Cursor getAllStatus() {


        Cursor res = null;

        ///   res= mDb.rawQuery("select count(status) from usermarks where status=? and difficulty=?", new String[]{"correct",difficulty});

        res = mDb.rawQuery("SELECT status FROM usermarks",null );



        return res;
    }

    public String getTableAsString(SQLiteDatabase db, String tableName) {

        String tableString = String.format("Table %s:\n", tableName);
        Cursor allRows  = db.rawQuery("SELECT * FROM " + tableName, null);
        if (allRows.moveToFirst() ){
            String[] columnNames = allRows.getColumnNames();
            do {
                for (String name: columnNames) {
                    tableString += String.format("%s: %s\n", name,
                            allRows.getString(allRows.getColumnIndex(name)));
                }
                tableString += "\n";

            } while (allRows.moveToNext());
        }

        return tableString;
    }

    public Cursor readData() {
        String[] allColumns = new String[] { QUZ_ID,
                QUZ_STATUS};
        Cursor c = mDb.query(TABLE_MARKS, allColumns, null,
                null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }



    public int getAllMarks(String status,String difficulty) {


        Cursor res = null;

        ///   res= mDb.rawQuery("select count(status) from usermarks where status=? and difficulty=?", new String[]{"correct",difficulty});

        res = mDb.rawQuery("SELECT COUNT(status) FROM usermarks WHERE status =? and difficulty =? group by status",new String[]{status,difficulty} );

        int count=res.getCount();

        return count;
    }

    public Cursor getAllQuestions(String difficulty,int qid) {

        Cursor res = null;

        res =  mDb.rawQuery( "select question from questions_table where id=? and difficulty=?" , new String[]{String.valueOf(qid),difficulty} );

        return res;

    }

    public Cursor getQuestionIds(String difficulty) {

        Cursor cursor =null;

        cursor=mDb.rawQuery("select id FROM " + TABLE_QUESTIONS + " where difficulty = ? ", new String[]{difficulty} );

        return cursor;
    }

    public String[] getAnswers(int id, String difficulty)
    {

        Cursor res = null;

        res =  mDb.rawQuery( "select distinct answer from questions_table where id=? and difficulty=?" , new String[]{String.valueOf(id),difficulty} );

        id++;

        res.moveToFirst();

        ArrayList<String> answers = new ArrayList<String>();

        while(!res.isAfterLast()) {
            answers.add(res.getString(res.getColumnIndex("answer")));
            res.moveToNext();
        }

        String [] status=answers.toArray(new String[answers.size()]);

        return status;

    }

    public Cursor retrieveMaxSeqNo(String difficulty)
    {

        Cursor res = null;

        res = mDb.rawQuery("select t1.id,t1.sequence,t1.difficulty from questions_table t1 inner join ( select id, max(sequence) max_sequence,difficulty from questions_table group by id,difficulty) t2 on t1.id = t2.id and t1.sequence = t2.max_sequence and t1.difficulty=t2.difficulty where t1.difficulty = ? ", new String[]{difficulty});

        return res;


    }

    public void clearTable()
    {
        try {
            mDb.delete(TABLE_QUESTIONS, null, null);
        }
        catch(Exception e)
        {
            System.out.println("npk sanda nishali:"+ e.getMessage());
        }
    }

}
