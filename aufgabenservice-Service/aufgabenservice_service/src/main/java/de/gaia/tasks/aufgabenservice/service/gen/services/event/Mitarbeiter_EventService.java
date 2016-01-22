package de.gaia.tasks.aufgabenservice.service.gen.services.event;


import de.gaia.tasks.aufgabenservice.service.gen.domain.Mitarbeiter_;

/*
 * This file will be overwritten on every change of the model!
 * This file was automatically generated by GAIA.
 */
/**
 * Provides methods to implement logic before and after Events.
 */
public interface Mitarbeiter_EventService {
	void onAfterCreate(Mitarbeiter_ entity);
	void onBeforeCreate(Mitarbeiter_ entity);
	void onBeforeSave(Mitarbeiter_ entity);
	void onAfterSave(Mitarbeiter_ entity);
	void onBeforeLinkSave(Mitarbeiter_ parent, Object linked);
	void onAfterLinkSave(Mitarbeiter_ parent, Object linked);
	void onBeforeLinkDelete(Mitarbeiter_ parent, Object linked);
	void onBeforeDelete(Mitarbeiter_ entity);
	void onAfterDelete(Mitarbeiter_ entity);
	void onAfterLinkDelete(Mitarbeiter_ parent, Object linked);
}