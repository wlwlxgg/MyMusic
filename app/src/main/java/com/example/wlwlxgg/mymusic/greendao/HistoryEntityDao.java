package com.example.wlwlxgg.mymusic.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.wlwlxgg.mymusic.entity.HistoryEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "HISTORY_ENTITY".
*/
public class HistoryEntityDao extends AbstractDao<HistoryEntity, String> {

    public static final String TABLENAME = "HISTORY_ENTITY";

    /**
     * Properties of entity HistoryEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property SongId = new Property(0, String.class, "songId", true, "SONG_ID");
        public final static Property Time = new Property(1, String.class, "time", false, "TIME");
        public final static Property Title = new Property(2, String.class, "title", false, "TITLE");
        public final static Property Author = new Property(3, String.class, "author", false, "AUTHOR");
        public final static Property Album = new Property(4, String.class, "album", false, "ALBUM");
        public final static Property LrcLink = new Property(5, String.class, "lrcLink", false, "LRC_LINK");
        public final static Property FileLink = new Property(6, String.class, "fileLink", false, "FILE_LINK");
        public final static Property ImageSmall = new Property(7, String.class, "imageSmall", false, "IMAGE_SMALL");
        public final static Property ImageMid = new Property(8, String.class, "imageMid", false, "IMAGE_MID");
        public final static Property ImageBig = new Property(9, String.class, "imageBig", false, "IMAGE_BIG");
    }


    public HistoryEntityDao(DaoConfig config) {
        super(config);
    }
    
    public HistoryEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"HISTORY_ENTITY\" (" + //
                "\"SONG_ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: songId
                "\"TIME\" TEXT," + // 1: time
                "\"TITLE\" TEXT," + // 2: title
                "\"AUTHOR\" TEXT," + // 3: author
                "\"ALBUM\" TEXT," + // 4: album
                "\"LRC_LINK\" TEXT," + // 5: lrcLink
                "\"FILE_LINK\" TEXT," + // 6: fileLink
                "\"IMAGE_SMALL\" TEXT," + // 7: imageSmall
                "\"IMAGE_MID\" TEXT," + // 8: imageMid
                "\"IMAGE_BIG\" TEXT);"); // 9: imageBig
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"HISTORY_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, HistoryEntity entity) {
        stmt.clearBindings();
 
        String songId = entity.getSongId();
        if (songId != null) {
            stmt.bindString(1, songId);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(2, time);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(4, author);
        }
 
        String album = entity.getAlbum();
        if (album != null) {
            stmt.bindString(5, album);
        }
 
        String lrcLink = entity.getLrcLink();
        if (lrcLink != null) {
            stmt.bindString(6, lrcLink);
        }
 
        String fileLink = entity.getFileLink();
        if (fileLink != null) {
            stmt.bindString(7, fileLink);
        }
 
        String imageSmall = entity.getImageSmall();
        if (imageSmall != null) {
            stmt.bindString(8, imageSmall);
        }
 
        String imageMid = entity.getImageMid();
        if (imageMid != null) {
            stmt.bindString(9, imageMid);
        }
 
        String imageBig = entity.getImageBig();
        if (imageBig != null) {
            stmt.bindString(10, imageBig);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, HistoryEntity entity) {
        stmt.clearBindings();
 
        String songId = entity.getSongId();
        if (songId != null) {
            stmt.bindString(1, songId);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(2, time);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(4, author);
        }
 
        String album = entity.getAlbum();
        if (album != null) {
            stmt.bindString(5, album);
        }
 
        String lrcLink = entity.getLrcLink();
        if (lrcLink != null) {
            stmt.bindString(6, lrcLink);
        }
 
        String fileLink = entity.getFileLink();
        if (fileLink != null) {
            stmt.bindString(7, fileLink);
        }
 
        String imageSmall = entity.getImageSmall();
        if (imageSmall != null) {
            stmt.bindString(8, imageSmall);
        }
 
        String imageMid = entity.getImageMid();
        if (imageMid != null) {
            stmt.bindString(9, imageMid);
        }
 
        String imageBig = entity.getImageBig();
        if (imageBig != null) {
            stmt.bindString(10, imageBig);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public HistoryEntity readEntity(Cursor cursor, int offset) {
        HistoryEntity entity = new HistoryEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // songId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // time
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // title
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // author
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // album
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // lrcLink
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // fileLink
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // imageSmall
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // imageMid
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // imageBig
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, HistoryEntity entity, int offset) {
        entity.setSongId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setTime(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTitle(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAuthor(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setAlbum(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setLrcLink(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setFileLink(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setImageSmall(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setImageMid(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setImageBig(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    @Override
    protected final String updateKeyAfterInsert(HistoryEntity entity, long rowId) {
        return entity.getSongId();
    }
    
    @Override
    public String getKey(HistoryEntity entity) {
        if(entity != null) {
            return entity.getSongId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(HistoryEntity entity) {
        return entity.getSongId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}