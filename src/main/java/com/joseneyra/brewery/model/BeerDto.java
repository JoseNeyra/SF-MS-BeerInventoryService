package com.joseneyra.brewery.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data       // Lombok Data gives us Getters, Setters, Equals, HashCodes
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto implements Serializable {

    static final long serialVersionUID = -1122213512583836341L;          // Is best practice to implement serial version IDs with Serializable implementations

    @Null
    private UUID id;

    @Null
    private Integer version;

    @Null
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)        // It's best practice to specify the format and send the json variable as a string
    private OffsetDateTime createdDate;             // UTC Date (best practice for WebApps is to use UTC Dates)

    @Null
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)        // It's best practice to specify the format and send the json variable as a string
    private OffsetDateTime lastModifiedDate;

    @NotBlank
    @Size(min = 3, max = 100)
    private String beerName;

    @NotNull
    private String beerStyle;

    @NotNull
    private String upc;

    @NotNull
    @Positive
    @JsonFormat(shape = JsonFormat.Shape.STRING)            // Sets the json variable as a string
    private BigDecimal price;

    @Positive
    private Integer quantityOnHand;
}
