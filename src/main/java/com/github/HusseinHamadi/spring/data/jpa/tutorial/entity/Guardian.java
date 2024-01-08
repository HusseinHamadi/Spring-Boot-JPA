package com.github.HusseinHamadi.spring.data.jpa.tutorial.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//cannot be an entity because we don't a different table for it
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@AttributeOverrides(
        {
                @AttributeOverride(
                        name = "name",
                        column = @Column(name = "guardian_name")
                ),
                @AttributeOverride(
                        name = "email",
                        column = @Column(name = "guardian_email")
                ),
                @AttributeOverride(
                        name = "mobile",
                        column = @Column(name = "guardian_mobile")
                )
        }
)
public class Guardian {


    private String name;
    private String email;
    private String mobile;
}
