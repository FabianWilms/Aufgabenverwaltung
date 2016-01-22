package de.gaia.tasks.aufgabenservice.service.services.event;

import org.springframework.stereotype.Service;

import de.gaia.tasks.aufgabenservice.service.gen.domain.Aufgabe_;
import de.gaia.tasks.aufgabenservice.service.gen.services.event.Aufgabe_EventService;

/*
 * This file will NOT be overwritten by GAIA.
 * This file was automatically generated by GAIA.
 */
/**
 * Provides methods to implement logic before and after Events.
 * If used as generated by GAIA this service will be autowired and called by Aufgabe_EventListener.
 */
@Service
public class Aufgabe_EventServiceImpl implements Aufgabe_EventService{
	// If you need access to the database you can autowire a Repository.
	// Repositories are generated into the package: .gen.rest
	//
	// @Autowired
	// <EntityName>Repository repo;
	
	@Override
	public void onAfterCreate(Aufgabe_ entity) {
		// Add your logic here.
	}
	@Override
	public void onBeforeCreate(Aufgabe_ entity) {
		// Add your logic here.
	}
	@Override
	public void onBeforeSave(Aufgabe_ entity) {
		// Add your logic here.
	}
	@Override
	public void onAfterSave(Aufgabe_ entity) {
		// Add your logic here.
	}
	@Override
	public void onBeforeLinkSave(Aufgabe_ parent, Object linked) {
		// Add your logic here.
	}
	@Override
	public void onAfterLinkSave(Aufgabe_ parent, Object linked) {
		// Add your logic here.
	}
	@Override
	public void onBeforeLinkDelete(Aufgabe_ parent, Object linked) {
		// Add your logic here.
	}
	@Override
	public void onBeforeDelete(Aufgabe_ entity) {
		// Add your logic here.
	}
	@Override
	public void onAfterDelete(Aufgabe_ entity) {
		// Add your logic here.
	}
	@Override
	public void onAfterLinkDelete(Aufgabe_ parent, Object linked) {
		// Add your logic here.
	}
}