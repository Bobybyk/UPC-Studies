package com.example.exam.data;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00032\u0006\u0010\u0007\u001a\u00020\bH\'J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\'\u00a8\u0006\f"}, d2 = {"Lcom/example/exam/data/MonDao;", "", "getAllDestinataires", "Landroidx/lifecycle/LiveData;", "", "Lcom/example/exam/data/Destinataire;", "getDestinataireById", "idDest", "", "insertDestinataire", "destinataire", "Lcom/example/exam/data/DestinataireItem;", "app_debug"})
public abstract interface MonDao {
    
    @androidx.room.Insert(entity = com.example.exam.data.Destinataire.class)
    public abstract long insertDestinataire(@org.jetbrains.annotations.NotNull()
    com.example.exam.data.DestinataireItem destinataire);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM Destinataire")
    public abstract androidx.lifecycle.LiveData<java.util.List<com.example.exam.data.Destinataire>> getAllDestinataires();
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM Destinataire WHERE idDest = :idDest ")
    public abstract androidx.lifecycle.LiveData<com.example.exam.data.Destinataire> getDestinataireById(long idDest);
}