package hu.bme.mit.theta.restapi.model.enums

import com.fasterxml.jackson.annotation.JsonProperty

enum class Priority(val value: String) {

    @JsonProperty("BEST_EFFORT") BESTEFFORT("BEST_EFFORT"),

    @JsonProperty("LOW") LOW("LOW"),

    @JsonProperty("MEDIUM") MEDIUM("MEDIUM"),

    @JsonProperty("HIGH") HIGH("HIGH");

}