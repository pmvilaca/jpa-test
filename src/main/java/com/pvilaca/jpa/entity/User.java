package com.pvilaca.jpa.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@NamedEntityGraphs({@NamedEntityGraph(name = "withAllDetails", attributeNodes = {@NamedAttributeNode("userDetails"),
                                                                                        @NamedAttributeNode("contactDetails")}),
                           @NamedEntityGraph(name = "withUserDetails", attributeNodes = {@NamedAttributeNode("userDetails")})
})
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @JsonManagedReference
    @OneToOne(mappedBy = "user")
    private UserDetails userDetails;

    @OneToOne
    private ContactDetails contactDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }
}
