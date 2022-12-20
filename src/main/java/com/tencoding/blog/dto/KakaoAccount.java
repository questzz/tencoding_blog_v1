
package com.tencoding.blog.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "profile_nickname_needs_agreement",
    "profile",
    "has_email",
    "email_needs_agreement",
    "is_email_valid",
    "is_email_verified",
    "email"
})

public class KakaoAccount {

    @JsonProperty("profile_nickname_needs_agreement")
    public Boolean profileNicknameNeedsAgreement;
    @JsonProperty("profile")
    public Profile profile;
    @JsonProperty("has_email")
    public Boolean hasEmail;
    @JsonProperty("email_needs_agreement")
    public Boolean emailNeedsAgreement;
    @JsonProperty("is_email_valid")
    public Boolean isEmailValid;
    @JsonProperty("is_email_verified")
    public Boolean isEmailVerified;
    @JsonProperty("email")
    public String email;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
