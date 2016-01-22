package de.gaia.tasks.aufgabenservice.service.services.businessactions;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import de.gaia.tasks.aufgabenservice.service.gen.services.businessactions.MitarbeiterInformieren_BusinessActionService;

/**
 * Provides a service to execute business-actions.
 * If used as generated by GAIA this service will be autowired and called by BusinessActionController.
 */
@Service
@PreAuthorize("hasAuthority('AufgabenService_BUSINESSACTION_MitarbeiterInformieren')")
public class MitarbeiterInformieren_BusinessActionServiceImpl implements MitarbeiterInformieren_BusinessActionService {
	// If you need access to the database you can autowire a Repository.
	// Repositories are generated into the package: .gen.rest
	//
	// @Autowired
	// <EntityName>Repository repo;

	/**
	 * This BusinessAction's purpose is: null
	 * TODO: Implement
	 */
	@Override
	public void mitarbeiterInformieren(){
		
		throw new UnsupportedOperationException("The BusinessAction mitarbeiterinformieren is not yet implemented!");
	}
	
}