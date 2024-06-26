package com.egadwys.notesApp.ui.note.list

import androidx.recyclerview.widget.DiffUtil
import com.egadwys.notesApp.data.note.models.Note

class NoteDiffUtil(
    private val oldList: List<Note>,
    private val newList: List<Note>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].title == newList[newItemPosition].title
                && oldList[oldItemPosition].content == newList[newItemPosition].content
                && oldList[oldItemPosition].date == newList[newItemPosition].date
                && oldList[oldItemPosition].priority == newList[newItemPosition].priority
    }
}