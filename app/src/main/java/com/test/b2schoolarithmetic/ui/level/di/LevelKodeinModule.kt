package com.test.b2schoolarithmetic.ui.level.di

import androidx.recyclerview.widget.GridLayoutManager
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val levelKodeinModule = Kodein.Module("LevelKodeinModule") {
    bind<GridLayoutManager>() with singleton { GridLayoutManager(instance(), 2) }
}