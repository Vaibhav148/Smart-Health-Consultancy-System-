package com.example.myapplication;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.dom.DOMLocator;

public class database extends SQLiteOpenHelper {

    private static final String TABLE_PAT = "PATIENT";
    private static final String TABLE_DOC = "DOCTOR";
    private static final String TABLE_ADM = "ADMIN";
    private static final String TABLE_APPOINT = "APPOINT";
    private static final String TABLE_MED = "MEDICAL";
    private static final String TABLE_FEED = "FEEDBACK";



    //PATIENT
    private static final String PID = "PID";
    private static final String PNAME = "PNAME";
    private static final String PPNO = "PPNO";
    private static final String PADDR = "PADDR";
    private static final String PEMAIL = "PEMAIL";

    //DOCTOR
    private static final String DID = "DID";
    private static final String DNAME = "DNAME";
    private static final String DPNO = "DPNO";
    private static final String DADDR = "DADDR";
    private static final String DEMAIL = "DEMAIL";
    private static final String DSPEC = "DSPEC";
    private static final String DQUAL = "DQUAL";
    private static final String DBIO = "DBIO";
   // private static final String DIMAGE = "DIMAGE";

    //ADMIN
    private static final String AID = "AID";
    private static final String ANAME = "ANAME";
    private static final String AEMAIL = "AEMAIL";


    //APPOINTMENT
    private static final String APPID = "APPID";
    private static final String PPID = "PPID";
    private static final String DDID = "DDID";
    private static final String DATE = "DATE";

    //MED RECORD
    private static final String MID = "MID";
    private static final String MPID = "MPID";
    private static final String MDID = "MDID";
    private static final String SYMP = "SYMP";
    private static final String DIS = "DIS";
   private static final String PRSC = "PRSC";
   //feedback
   private static final String FID = "FID";
    private static final String INFO = "INFO";











    // Table columns


    // Database Information
    static final String DB_NAME = "data.db";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query

    public database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
           String CREATE_TABLE_PAT= "create table " + TABLE_PAT + "(" + PID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PNAME + " TEXT NOT NULL, " + PPNO + " TEXT NOT NULL, "+PADDR+" TEXT NOT NULL, "+PEMAIL+" TEXT NOT NULL)";
        String CREATE_TABLE_DOC= "create table " + TABLE_DOC + "(" + DID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DNAME + " TEXT NOT NULL, " + DPNO + " TEXT NOT NULL, "+DADDR+" TEXT NOT NULL, "+DEMAIL+" TEXT NOT NULL, "+DSPEC+" TEXT NOT NULL,"+DQUAL+" TEXT NOT NULL ,"+ DBIO +" TEXT NOT NULL )";
        String CREATE_TABLE_APPOINT= "create table " + TABLE_APPOINT + "(" + APPID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PPID + " INTEGER NOT NULL , " + DDID + " INTEGER NOT NULL, "+DATE+" DATETIME NOT NULL,FOREIGN KEY "+"("+PPID+")"+" REFERENCES "+TABLE_PAT+"("+PID +"),FOREIGN KEY "+"("+DDID+")"+" REFERENCES "+TABLE_DOC+"("+DID +") )";
        String CREATE_TABLE_MED= "create table " + TABLE_MED + "(" + MID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + MPID + " INTEGER NOT NULL , " + MDID  + " INTEGER NOT NULL, "+ DIS  + " TEXT NOT NULL ,"+ SYMP  + " TEXT NOT NULL,"+PRSC+" TEXT NOT NULL , FOREIGN KEY "+"("+MPID+")"+" REFERENCES "+TABLE_PAT+"("+PID +"),FOREIGN KEY "+"("+MDID+")"+" REFERENCES "+TABLE_DOC+"("+ DID +"))";

        String CREATE_TABLE_ADM= "create table " + TABLE_ADM + "(" + AID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ANAME + " TEXT NOT NULL, " + AEMAIL + " TEXT NOT NULL)";
        String CREATE_TABLE_FEED= "create table " + TABLE_FEED + "(" + FID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + INFO +     " TEXT NOT NULL)";

//db.execSQL(CREATE_TABLE_MED);
        db.execSQL(CREATE_TABLE_DOC);
        db.execSQL(CREATE_TABLE_APPOINT);
        db.execSQL(CREATE_TABLE_MED);
        db.execSQL(CREATE_TABLE_ADM);

        db.execSQL(CREATE_TABLE_PAT);
        db.execSQL(CREATE_TABLE_FEED);
        Log.i("table","created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOC);
        onCreate(db);
    }
    void addFeed(feedback feed) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(INFO, feed.getInfo()); // Contact Name

        //values.put(PEMAIL, pat.getPatEmail());// Contact Phone

        // Inserting Row
        db.insert(TABLE_FEED, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    void addPat(patient pat) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PNAME, pat.getPatName()); // Contact Name
        values.put(PEMAIL, pat.getPatEmail());
        values.put(PPNO, pat.getPatPpno());
        values.put(PADDR, pat.getPatPaddr());
        //values.put(PEMAIL, pat.getPatEmail());// Contact Phone

        // Inserting Row
        db.insert(TABLE_PAT, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
    public List<String> getFeed() {
        List<String> patList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_FEED;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        String m;
        //patient pat;

        if (cursor != null) {

            //return "lul";

            if (cursor.moveToFirst()) {
                do {

                    // Adding contact to list
                    patList.add(cursor.getString(1));
                } while (cursor.moveToNext());
            }

        }


        return patList;
    }

    void addadm(admin pat) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ANAME, pat.getAnme()); // Contact Name
        values.put(AEMAIL, pat.getAemail());

        //values.put(PEMAIL, pat.getPatEmail());// Contact Phone

        // Inserting Row
        db.insert(TABLE_ADM, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
    void addAppoint(int pat,int doc) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(PPID, pat);
        values.put(DDID,doc);
        values.put(DATE, "");
        //values.put(PEMAIL, pat.getPatEmail());// Contact Phone

        // Inserting Row
        db.insert(TABLE_APPOINT, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
    void addDate(int pid,String doc) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(DATE,doc);
        String wherecl=APPID+"=?";
        String wherearg[]={String.valueOf(pid)};
        db.update(TABLE_APPOINT,values,wherecl,wherearg);
        db.close();
        //values.put(PEMAIL, pat.getPatEmail());// Contact Phone

        // Inserting Row

    }
    void updateMed(int mid,String dis,String pres) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(DIS,dis);
        values.put(PRSC,pres);
        String wherecl=MID+"=?";
        String wherearg[]={String.valueOf(mid)};
        db.update(TABLE_MED,values,wherecl,wherearg);
        db.close();
        //values.put(PEMAIL, pat.getPatEmail());// Contact Phone

        // Inserting Row

    }
    void updateDoc(int did,doctor doc) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(DNAME,doc.getDocName());
        values.put(DEMAIL,doc.getDocEmail());
        values.put(DPNO,doc.getDocdpno());
        values.put(DADDR,doc.getDocAddr());
        values.put(DSPEC,doc.getDocSpec());
        values.put(DQUAL,doc.getDocQual());
        values.put(DBIO,doc.getBio());




        String wherecl=DID+"=?";
        String wherearg[]={String.valueOf(did)};
        db.update(TABLE_DOC,values,wherecl,wherearg);
        db.close();
        //values.put(PEMAIL, pat.getPatEmail());// Contact Phone

        // Inserting Row

    }
    void updatePat(int did,patient doc) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(PNAME,doc.getPatName());
        values.put(PEMAIL,doc.getPatEmail());
        values.put(PPNO,doc.getPatPpno());
        values.put(PADDR,doc.getPatPaddr());





        String wherecl=PID+"=?";
        String wherearg[]={String.valueOf(did)};
        db.update(TABLE_PAT,values,wherecl,wherearg);
        db.close();
        //values.put(PEMAIL, pat.getPatEmail());// Contact Phone

        // Inserting Row

    }
    void addMed(medical_rec m) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(MPID, m.getMpid());
        values.put(MDID,m.getMdid());
        values.put(SYMP,m.getSym());
        values.put(DIS,m.getDis());
        values.put(PRSC,m.getPres());

        //values.put(PEMAIL, pat.getPatEmail());// Contact Phone

        // Inserting Row
        db.insert(TABLE_MED, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
   medical_rec getmed(int pid) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MED, new String[] { MID,
                        MPID, MDID,DIS,SYMP,PRSC }, MID + "=?",
                new String[] { String.valueOf(pid) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        medical_rec pat = new medical_rec(Integer.valueOf(cursor.getString(0)),
                Integer.valueOf(cursor.getString(1)), Integer.valueOf(cursor.getString(2)),(cursor.getString(3)),cursor.getString(4),cursor.getString(5));
        // return contact
        return pat;
    }


    void addDoc(doctor doc)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DNAME, doc.getDocName()); // Contact Name
        values.put(DEMAIL, doc.getDocEmail());
        values.put(DPNO, doc.getDocdpno());
        values.put(DADDR, doc.getDocAddr());
        values.put(DSPEC, doc.getDocSpec());
        values.put(DQUAL, doc.getDocQual());
        values.put(DBIO, doc.getBio());
        //values.put(PEMAIL, pat.getPatEmail());// Contact Phone

        // Inserting Row
        db.insert(TABLE_DOC, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection

    }


    patient getPatient(String pid) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PAT, new String[] { PID,
                        PNAME, PEMAIL,PPNO,PADDR }, PID + "=?",
                new String[] { String.valueOf(pid) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        patient pat = new patient((cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),(cursor.getString(3)),cursor.getString(4));
        // return contact
        return pat;
    }

    patient getPatEmail(String pid) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PAT, new String[] { PID,
                        PNAME, PEMAIL,PPNO,PADDR }, PEMAIL + "=?",
                new String[] { String.valueOf(pid) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        patient pat = new patient((cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),(cursor.getString(3)),cursor.getString(4));
        // return contact
        return pat;
    }
    doctor getDocByemail(String pid) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DOC, new String[] { DID,
                        DNAME, DEMAIL,DPNO,DADDR,DSPEC,DQUAL,DBIO}, DEMAIL + "=?",
                new String[] { String.valueOf(pid) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        doctor doc = new doctor((cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),(cursor.getString(3)),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7));
        // return contact
        return doc;
    }
    int getPatId(String pid) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PAT, new String[] { PID,
                        PNAME, PEMAIL,PPNO,PADDR }, PEMAIL + "=?",
                new String[] { String.valueOf(pid) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        patient pat = new patient((cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),(cursor.getString(3)),cursor.getString(4));
        // return contact
        return Integer.valueOf(pat.getPatPuid());
    }
    patient getPatByid(String pid) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PAT, new String[] { PID,
                        PNAME, PEMAIL,PPNO,PADDR }, PID + "=?",
                new String[] { String.valueOf(pid) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        patient pat = new patient((cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),(cursor.getString(3)),cursor.getString(4));
        // return contact
        return pat;
    }
    appointment getAppointment( int pid) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_APPOINT, new String[] { APPID,
                        PPID, DDID,DATE }, PPID + "=?",
                new String[] { String.valueOf(pid) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        appointment pat = new appointment(Integer.valueOf(cursor.getString(0)),Integer.valueOf(cursor.getString(1)),Integer.valueOf(cursor.getString(2)),cursor.getString(3));
        // return contact
        return pat;


    }

    doctor getDoctor(String did) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DOC, new String[] { DID,
                        DNAME, DEMAIL,DPNO,DADDR,DSPEC,DQUAL,DBIO},  DID + "=?",
                new String[] { String.valueOf(did) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

            doctor doc = new doctor((cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),(cursor.getString(3)),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7));
        // return contact
        return doc;
    }
    doctor getDocAddr(String addr) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DOC, new String[] { DID,
                        DNAME, DEMAIL,DPNO,DADDR,DSPEC,DQUAL,DBIO},  DADDR + "=?",
                new String[] { String.valueOf(addr) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        doctor doc = new doctor((cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),(cursor.getString(3)),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7));
        // return contact
        return doc;
    }
    doctor getDocId(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DOC, new String[] { DID,
                        DNAME, DEMAIL,DPNO,DADDR,DSPEC,DQUAL,DBIO},  DID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        doctor doc = new doctor((cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),(cursor.getString(3)),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7));
        // return contact
        return doc;
    }


    // code to get all contacts in a list view
    public List<patient> getAllPatients() {
        List<patient> patList = new ArrayList<patient>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_PAT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        String m;
        patient pat;

            if (cursor != null) {

                //return "lul";

                    if (cursor.moveToFirst()) {
                        do {
                            pat = new patient();
                            pat.setPatPuid(cursor.getString(0));
                            pat.setPatName(cursor.getString(1));
                            pat.setPatEmail(cursor.getString(2));
                            pat.setPatPpno(cursor.getString(3));
                            pat.setPatPaddr(cursor.getString(4));
                            // Adding contact to list
                            patList.add(pat);
                        } while (cursor.moveToNext());
                    }

            }


        return patList;
    }
                    //String pname = cursor.getString(1);
                    //String pidd = cursor.getString(0);
                    //String pemail = cursor.getString(2);
                    //int pnoindex=cursor.getColumnIndex(PPNO);
                    //int pno = cursor.getInt(pnoindex);
                    //String paddr = cursor.getString(4);
                    //patient p = new patient(pidd, pname, pemail, pno, paddr);
                    //return pno;
                   // db.close();
                    //return cursor.getString(0);

                  /*  pat= new patient();
                    // pat.setPatPuid(cursor.getString(0));
                    pat.setPatName(cursor.getString(1));
                    pat.setPatEmail(cursor.getString(2));
                    pat.setPatPpno(Integer.parseInt(cursor.getString(3)));
                    pat.setPatPaddr(cursor.getString(4));
                    // Adding contact to list
                    patList.add(pat);
*/





            // looping through all rows and adding to list
        /*
        */
            //return patList ;





        public List<doctor> getDoc(String dsp) {
            List<doctor> docList = new ArrayList<doctor>();
            // Select All Query
            //String selectQuery = "SELECT  * FROM "+TABLE_DOC+" WHERE "+ DSPEC +" = "+dsp;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.query(TABLE_DOC, new String[] { DID,
                            DNAME, DEMAIL,DPNO,DADDR,DSPEC,DQUAL,DBIO },  DSPEC + "=?",
                    new String[] { String.valueOf(dsp) }, null, null, null, null);


            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    doctor doc = new doctor();
                    doc.setDocDuid(cursor.getString(0));
                    doc.setDocDname(cursor.getString(1));
                    doc.setDocEmail(cursor.getString(2));
                    doc.setDocDpno(cursor.getString(3));
                    doc.setDocAddr(cursor.getString(4));
                    doc.setDocSpec(cursor.getString(5));
                    doc.setDocQual(cursor.getString(6));
                    doc.setBio(cursor.getString(7));
                    // Adding contact to list
                    docList.add(doc);
                } while (cursor.moveToNext());
            }


            // return contact list
        return docList;
    }
    public List<doctor> getAllDoc() {
        List<doctor> docList = new ArrayList<doctor>();
        // Select All Query
        //String selectQuery = "SELECT  * FROM "+TABLE_DOC+" WHERE "+ DSPEC +" = "+dsp;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_DOC, new String[] { DID,
                        DNAME, DEMAIL,DPNO,DADDR,DSPEC,DQUAL,DBIO },   null,
                null, null, null, null, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                doctor doc = new doctor();
                doc.setDocDuid(cursor.getString(0));
                doc.setDocDname(cursor.getString(1));
                doc.setDocEmail(cursor.getString(2));
                doc.setDocDpno(cursor.getString(3));
                doc.setDocAddr(cursor.getString(4));
                doc.setDocSpec(cursor.getString(5));
                doc.setDocQual(cursor.getString(6));
                doc.setBio(cursor.getString(7));
                // Adding contact to list
                docList.add(doc);
            } while (cursor.moveToNext());
        }


        // return contact list
        return docList;
    }


    public List<appointment> getPatAppoint(String dsp) {
        List<appointment> docList = new ArrayList<>();
        // Select All Query
        //String selectQuery = "SELECT  * FROM "+TABLE_DOC+" WHERE "+ DSPEC +" = "+dsp;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_APPOINT, new String[] { APPID,
                        PPID, DDID,DATE  },  PPID + "=?",
                new String[] { String.valueOf(dsp) }, null, null, null, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
          appointment app=new appointment();

          app.setPpid(Integer.valueOf(cursor.getString(1)));
          app.setDdid(Integer.valueOf(cursor.getString(2)));
          app.setDate(cursor.getString(3));
          app.setAppid(Integer.valueOf(cursor.getString(0)));

                docList.add(app);


            } while (cursor.moveToNext());
        }


        // return contact list
        return docList;
    }
    public List<appointment> getDocAppoint(String dsp) {
        List<appointment> docList = new ArrayList<>();
        // Select All Query
        //String selectQuery = "SELECT  * FROM "+TABLE_DOC+" WHERE "+ DSPEC +" = "+dsp;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_APPOINT, new String[] { APPID,
                        PPID, DDID,DATE  },  DDID + "=?",
                new String[] { String.valueOf(dsp) }, null, null, null, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                appointment app=new appointment();

                app.setPpid(Integer.valueOf(cursor.getString(1)));
                app.setDdid(Integer.valueOf(cursor.getString(2)));
                app.setDate(cursor.getString(3));
                app.setAppid(Integer.valueOf(cursor.getString(0)));

                docList.add(app);


            } while (cursor.moveToNext());
        }


        // return contact list
        return docList;
    }
    public List<medical_rec> getPatMed(String dsp) {
        List<medical_rec> docList = new ArrayList<>();
        // Select All Query
        //String selectQuery = "SELECT  * FROM "+TABLE_DOC+" WHERE "+ DSPEC +" = "+dsp;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_MED, new String[] { MID,
                        MPID, MDID,DIS,SYMP,PRSC  },  MPID + "=?",
                new String[] { String.valueOf(dsp) }, null, null, null, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                medical_rec app=new medical_rec();

                app.setMpid(Integer.valueOf(cursor.getString(1)));
                app.setMdid(Integer.valueOf(cursor.getString(2)));
                app.setDis(cursor.getString(3));
                app.setSym(cursor.getString(4));
                app.setPres(cursor.getString(5));

                docList.add(app);


            } while (cursor.moveToNext());
        }


        // return contact list
        return docList;
    }


    // code to update the single contact
   public int updatePatient(patient pat) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PNAME, pat.getPatName()); // Contact Name
        values.put(PEMAIL, pat.getPatEmail());
        values.put(PPNO, pat.getPatPpno());
        values.put(PADDR, pat.getPatPaddr());

        // updating row
        return db.update(TABLE_PAT, values, PID + "=?",
                new String[] { String.valueOf(pat.getPatPuid()) });
    }

    // Deleting single contact
    public void deletePatient( String pid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PAT, PID + " = ?",
                new String[] { String.valueOf(pid) });
        db.close();
    }


    public int getPatientCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PAT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
