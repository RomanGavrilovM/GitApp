package ru.example.gitapp.utils

import android.view.View
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

fun View.observableClickListener(): Observable<View> {
    val publishSubject: PublishSubject<View> = PublishSubject.create()
    this.setOnClickListener { view -> publishSubject.onNext(view) }
    return publishSubject
}