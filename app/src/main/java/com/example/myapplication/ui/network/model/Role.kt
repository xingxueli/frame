package com.example.myapplication.ui.network.model

enum class Role(
    var id: Int,
    var value: String
) {
    UN_CHOOSE(0, "unChoose"), RECRUITER(1, "recruiter"), CANDIDATE(2, "candidate");

    companion object {
        fun getValueById(id: Int): String {
            for (role in values()) {
                if (role.id == id) {
                    return role.value
                }
            }
            return ""
        }

        fun getRoleById(id: Int): Role {
            for (role in values()) {
                if (role.id == id) {
                    return role
                }
            }
            throw IllegalArgumentException("No such id : $id for Role")
        }

        fun getRoleByValue(value: String): Role {
            for (role in values()) {
                if (role.value == value) {
                    return role
                }
            }
            throw IllegalArgumentException("No such value : $value for Role")
        }
    }
}