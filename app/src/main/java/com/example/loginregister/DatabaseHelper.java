package com.example.loginregister;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;
import java.util.Locale;
import java.util.Date;
import java.util.List;


import java.text.SimpleDateFormat;

public class DatabaseHelper extends SQLiteOpenHelper {

    List<Questions> questionsList =new ArrayList<>();
    // Logcat tag
    private static final String LOG = "DatabaseHelper";
    // Database Name
    public static final String DATABASE_NAME = "MyDBName.db";
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_USER = "users";
    private static final String TABLE_QUESTIONS = "questions";
    private static final String TABLE_TESTS_SETTINGS = "test_settings";
    private static final String TABLE_CREATE_TESTS = "create_test";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";


    // USERS Table - column names
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_NAME = "name";
    private static final String KEY_SURNAME = "surname";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_TEL = "tel";

    // QUESTIONS Table - column names
    private static final String KEY_CORRECT_TEXT = "correctText";
    private static final String KEY_QUESTIONS = "question";
    private static final String KEY_CHOICE_A = "choiceA";
    private static final String KEY_CHOICE_B = "choiceB";
    private static final String KEY_CHOICE_C = "choiceC";
    private static final String KEY_CHOICE_D = "choiceD";
    private static final String KEY_DIFFUCULTY = "difficulty";


    //EXAM SETTINGS Table - column names
    private static final String KEY_TEST_TIME = "test_time";
    private static final String KEY_QUESTION_POINT = "question_point";
    private static final String KEY_TEST_DIFFUCULTY = "test_difficulty";

    //CREATING  EXAM  Table - column names
    private static final String KEY_TEST_DIFFUCULTY_SELECTION = "test_difficulty_selection";
    private static final String KEY_QUESTION_ID = "question_id";


    // Table Create Statements
    //USERS table create statement
    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER
            + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_USERNAME + " TEXT,"
            + KEY_PASSWORD + " TEXT,"
            + KEY_NAME + " TEXT,"
            + KEY_SURNAME + " TEXT,"
            + KEY_EMAIL + " TEXT,"
            + KEY_TEL + " TEXT," +
            KEY_CREATED_AT + " DATETIME" + ")";

    // Questions table create statement
    private static final String CREATE_TABLE_QUESTIONS = "CREATE TABLE " + TABLE_QUESTIONS
            + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_CORRECT_TEXT + " TEXT,"
            + KEY_QUESTIONS + " TEXT,"
            + KEY_CHOICE_A + " TEXT,"
            + KEY_CHOICE_B + " TEXT,"
            + KEY_CHOICE_C + " TEXT,"
            + KEY_CHOICE_D + " TEXT,"
            + KEY_DIFFUCULTY + " INTEGER,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    //Creating exam settings table create statement
    private static final String CREATE_TABLE_EXAM_SETTINGS = "CREATE TABLE " + TABLE_TESTS_SETTINGS
            + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_TEST_TIME + " INTEGER,"
            + KEY_QUESTION_POINT + " TEXT,"
            + KEY_TEST_DIFFUCULTY + " TEXT,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    //Creating exam table create statement
    private static final String CREATE_TABLE_CREATE_EXAM = "CREATE TABLE " + TABLE_CREATE_TESTS
            + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_TEST_DIFFUCULTY_SELECTION + " INTEGER,"
            + KEY_QUESTION_ID + " INTEGER,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_QUESTIONS);
        db.execSQL(CREATE_TABLE_EXAM_SETTINGS);
        db.execSQL(CREATE_TABLE_CREATE_EXAM);
     /*   db.execSQL("CREATE TABLE user(ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT,name TEXT," +
                "surname TEXT, email TEXT, tel TEXT)");

       db.execSQL("CREATE TABLE questions(ID INTEGER PRIMARY KEY AUTOINCREMENT, correctText TEXT, " +
                "question TEXT,choiceA TEXT,choiceB TEXT,choiceC TEXT,choiceD TEXT,difficulty TEXT)");

      */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_USER );
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_QUESTIONS );
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_TESTS_SETTINGS );
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_CREATE_TESTS );
        // create new tables
        onCreate(db);

       /* db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS questions");
        */
    }

    public boolean Insert(String username, String password, String name, String surname, String email, String tel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("name", name);
        contentValues.put("surname", surname);
        contentValues.put("email", email);
        contentValues.put("tel", tel);

        Log.d("sql app", "adding data "+ username +" to "+DATABASE_NAME);

        //long result = sqLiteDatabase.insert("user", null, contentValues);
        long result = sqLiteDatabase.insert("users", null, contentValues);
        return result != -1;
    }


    public boolean questionAdd(String correctText, String question,String choiceA,
                               String choiceB,String choiceC,String choiceD,String difficulty) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("correctText",correctText);
        cv.put("question",question);
        cv.put("choiceA",choiceA);
        cv.put("choiceB",choiceB);
        cv.put("choiceC",choiceC);
        cv.put("choiceD",choiceD);
        cv.put("difficulty",difficulty);


        // Log.d("sql app", "adding data "+quizModel.toString()+" to "+DATABASE_NAME);
        long result = sqLiteDatabase.insert("questions", null, cv);
        return result != -1;
    }

    public List<Questions> getAllQuestions() {

        String[] columns =
                {DatabaseHelper.KEY_ID,
                        DatabaseHelper.KEY_CORRECT_TEXT,
                        DatabaseHelper.KEY_DIFFUCULTY,
                        DatabaseHelper.KEY_CHOICE_A,
                        DatabaseHelper.KEY_CHOICE_B,
                        DatabaseHelper.KEY_CHOICE_C,
                        DatabaseHelper.KEY_CHOICE_D,
                        DatabaseHelper.KEY_QUESTIONS};
        SQLiteDatabase db = this.getWritableDatabase();

        try (Cursor cursor = db.query(DatabaseHelper.TABLE_QUESTIONS, columns, null, null, null, null,null)) {
            while (cursor.moveToNext()) {
                int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_ID);
                int id = cursor.getInt(index1);
                int index2 = cursor.getColumnIndex(DatabaseHelper.KEY_QUESTIONS);
                String question = cursor.getString(index2);
                int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_CORRECT_TEXT);
                String correctText = cursor.getString(index3);
                int index4 = cursor.getColumnIndex(DatabaseHelper.KEY_CHOICE_A);
                String choiceA = cursor.getString(index4);
                int index5 = cursor.getColumnIndex(DatabaseHelper.KEY_CHOICE_B);
                String choiceB = cursor.getString(index5);
                int index6 = cursor.getColumnIndex(DatabaseHelper.KEY_CHOICE_C);
                String choiceC = cursor.getString(index6);
                int index7 = cursor.getColumnIndex(DatabaseHelper.KEY_CHOICE_D);
                String choiceD = cursor.getString(index7);
                int index8 = cursor.getColumnIndex(DatabaseHelper.KEY_DIFFUCULTY);
                String difficulty = cursor.getString(index8);
                Questions questions = new Questions(id, correctText, question, choiceA, choiceB,
                        choiceC, choiceD, difficulty);
                questionsList.add(questions);
            }
        }
        return questionsList;
    }



    private String getDateTime()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }


    public Boolean CheckLogin(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username = ? and password = ?",
                new String[]{username,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean CheckUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}