package com.example.exam.data;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MonDao_Impl implements MonDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DestinataireItem> __insertionAdapterOfDestinataireItemAsDestinataire;

  public MonDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDestinataireItemAsDestinataire = new EntityInsertionAdapter<DestinataireItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Destinataire` (`nom`,`prenom`,`mail`,`annotation`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DestinataireItem value) {
        if (value.getNom() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getNom());
        }
        if (value.getPrenom() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPrenom());
        }
        if (value.getMail() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getMail());
        }
        if (value.getAnnotation() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getAnnotation());
        }
      }
    };
  }

  @Override
  public long insertDestinataire(final DestinataireItem destinataire) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfDestinataireItemAsDestinataire.insertAndReturnId(destinataire);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Destinataire>> getAllDestinataires() {
    final String _sql = "SELECT * FROM Destinataire";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"Destinataire"}, false, new Callable<List<Destinataire>>() {
      @Override
      public List<Destinataire> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfIdDest = CursorUtil.getColumnIndexOrThrow(_cursor, "idDest");
          final int _cursorIndexOfNom = CursorUtil.getColumnIndexOrThrow(_cursor, "nom");
          final int _cursorIndexOfPrenom = CursorUtil.getColumnIndexOrThrow(_cursor, "prenom");
          final int _cursorIndexOfMail = CursorUtil.getColumnIndexOrThrow(_cursor, "mail");
          final int _cursorIndexOfAnnotation = CursorUtil.getColumnIndexOrThrow(_cursor, "annotation");
          final List<Destinataire> _result = new ArrayList<Destinataire>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Destinataire _item;
            final long _tmpIdDest;
            _tmpIdDest = _cursor.getLong(_cursorIndexOfIdDest);
            final String _tmpNom;
            if (_cursor.isNull(_cursorIndexOfNom)) {
              _tmpNom = null;
            } else {
              _tmpNom = _cursor.getString(_cursorIndexOfNom);
            }
            final String _tmpPrenom;
            if (_cursor.isNull(_cursorIndexOfPrenom)) {
              _tmpPrenom = null;
            } else {
              _tmpPrenom = _cursor.getString(_cursorIndexOfPrenom);
            }
            final String _tmpMail;
            if (_cursor.isNull(_cursorIndexOfMail)) {
              _tmpMail = null;
            } else {
              _tmpMail = _cursor.getString(_cursorIndexOfMail);
            }
            final String _tmpAnnotation;
            if (_cursor.isNull(_cursorIndexOfAnnotation)) {
              _tmpAnnotation = null;
            } else {
              _tmpAnnotation = _cursor.getString(_cursorIndexOfAnnotation);
            }
            _item = new Destinataire(_tmpIdDest,_tmpNom,_tmpPrenom,_tmpMail,_tmpAnnotation);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<Destinataire> getDestinataireById(final long idDest) {
    final String _sql = "SELECT * FROM Destinataire WHERE idDest = ? ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, idDest);
    return __db.getInvalidationTracker().createLiveData(new String[]{"Destinataire"}, false, new Callable<Destinataire>() {
      @Override
      public Destinataire call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfIdDest = CursorUtil.getColumnIndexOrThrow(_cursor, "idDest");
          final int _cursorIndexOfNom = CursorUtil.getColumnIndexOrThrow(_cursor, "nom");
          final int _cursorIndexOfPrenom = CursorUtil.getColumnIndexOrThrow(_cursor, "prenom");
          final int _cursorIndexOfMail = CursorUtil.getColumnIndexOrThrow(_cursor, "mail");
          final int _cursorIndexOfAnnotation = CursorUtil.getColumnIndexOrThrow(_cursor, "annotation");
          final Destinataire _result;
          if(_cursor.moveToFirst()) {
            final long _tmpIdDest;
            _tmpIdDest = _cursor.getLong(_cursorIndexOfIdDest);
            final String _tmpNom;
            if (_cursor.isNull(_cursorIndexOfNom)) {
              _tmpNom = null;
            } else {
              _tmpNom = _cursor.getString(_cursorIndexOfNom);
            }
            final String _tmpPrenom;
            if (_cursor.isNull(_cursorIndexOfPrenom)) {
              _tmpPrenom = null;
            } else {
              _tmpPrenom = _cursor.getString(_cursorIndexOfPrenom);
            }
            final String _tmpMail;
            if (_cursor.isNull(_cursorIndexOfMail)) {
              _tmpMail = null;
            } else {
              _tmpMail = _cursor.getString(_cursorIndexOfMail);
            }
            final String _tmpAnnotation;
            if (_cursor.isNull(_cursorIndexOfAnnotation)) {
              _tmpAnnotation = null;
            } else {
              _tmpAnnotation = _cursor.getString(_cursorIndexOfAnnotation);
            }
            _result = new Destinataire(_tmpIdDest,_tmpNom,_tmpPrenom,_tmpMail,_tmpAnnotation);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
