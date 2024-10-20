package com.example.todoapp.data.mapper

import com.example.todoapp.data.local.entity.TodoEntity
import com.example.todoapp.domain.model.Todo

fun TodoEntity.toDomainModel(): Todo {
    return Todo(
        id = id,
        title = title,
        description = description
    )
}

fun Todo.toEntityModel(): TodoEntity {
    return TodoEntity(
        id = id,
        title = title,
        description = description
    )
}