package com.cyrus822.whatsdog.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;
import lombok.Data;

@Entity
@Data
@Table(name = "restaurant")
public class Restaurants implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rId;

    @Column(name = "name", nullable = false)
    @Size(max = 20, message = "Name cannot be longer than 20 characters")
    private String rName;

    @Column(name = "phone", nullable = true)
    private String rPhone;

    @Column(name = "address", nullable = false)
    @Size(max = 255, min = 5, message = "Address must between 5 and 20 characters")
    private String rAddr;

    @Column(name = "website", nullable = true)
    @URL(message = "Please provide a valid URL")
    private String rWeb;
}
