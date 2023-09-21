package net.javaguides.organizationservice.service;

import net.javaguides.organizationservice.dto.OrganizationDto;

/**
 * @author vsushko
 */
public interface OrganizationService {

    OrganizationDto saveOrganization(OrganizationDto organizationDto);

    OrganizationDto getOrganizationByCode(String organizationCode);
}
