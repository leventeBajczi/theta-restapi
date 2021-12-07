package hu.bme.mit.theta.restapi.model.enums

import com.fasterxml.jackson.annotation.JsonProperty

enum class Permissions(val value: String) {

    @JsonProperty("LIST_TASKS") LISTTASKS("LIST_TASKS"),

    @JsonProperty("LIST_USERS") LISTUSERS("LIST_USERS"),

    @JsonProperty("SUBMIT_TASKS") SUBMITTASKS("SUBMIT_TASKS"),

    @JsonProperty("MANAGE_USERS") MANAGEUSERS("MANAGE_USERS"),

    @JsonProperty("MANAGE_EXECUTABLES") MANAGEEXECUTABLES("MANAGE_EXECUTABLES");

}