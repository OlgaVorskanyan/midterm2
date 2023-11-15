package com.example.midterm

import User
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

class UserComposables {
    companion object {
        @Composable
        fun UserList(users: List<User>) {
            LazyColumn {
                items(users) { user ->
                    UserListItem(user = user)
                }
            }
        }

        @Composable
        fun UserListItem(user: User) {
            Text(text = "Name: ${user.name}")
            Text(text = "Username: ${user.username}")
            Text(text = "Email: ${user.email}")
            Text(text = "Phone: ${user.phone}")
            Text(text = "Website: ${user.website}")
            Text(text = "Address: ${user.address.street}, ${user.address.suite}, ${user.address.city}, ${user.address.zipcode}")
            Text(text = "Company: ${user.company.name}, ${user.company.catchPhrase}, ${user.company.bs}")
        }
    }
}
