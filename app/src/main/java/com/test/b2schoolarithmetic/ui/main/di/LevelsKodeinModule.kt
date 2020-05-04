package com.test.b2schoolarithmetic.ui.main.di

import androidx.recyclerview.widget.LinearLayoutManager
import com.test.b2schoolarithmetic.ui.main.adapter.viewholder.LevelItemViewHolderFactory
import com.test.b2schoolarithmetic.ui.main.adapter.viewholder.ListItemViewHolderFactory
import com.test.b2schoolarithmetic.ui.main.adapter.viewholder.ThemeViewHolderFactory
import com.test.b2schoolarithmetic.ui.main.vo.ListItem
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.factory
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import java.lang.IllegalStateException

val levelsKodeinModule = Kodein.Module("LevelsKodeinModule"){
    bind<LinearLayoutManager>() with singleton { LinearLayoutManager(instance()) }
    bind<ListItemViewHolderFactory>() with factory { type: Int ->
        when(type) {
            ListItem.TYPE_HEADER -> ThemeViewHolderFactory()
            ListItem.TYPE_EVENT -> LevelItemViewHolderFactory()
            else -> throw IllegalStateException("wrong type: $type of list view")
        }
    }
}