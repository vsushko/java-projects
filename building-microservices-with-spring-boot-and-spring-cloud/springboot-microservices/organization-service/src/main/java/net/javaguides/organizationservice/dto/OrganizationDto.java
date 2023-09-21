package net.javaguides.organizationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author vsushko
 */
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {

    private Long id;

    private String organizationName;

    private String organizationDescription;

    private String organizationCode;

    private LocalDateTime createdDate;
}