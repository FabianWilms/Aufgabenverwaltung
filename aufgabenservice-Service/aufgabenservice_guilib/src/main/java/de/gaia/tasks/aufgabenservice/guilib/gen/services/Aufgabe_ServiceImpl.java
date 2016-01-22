package de.gaia.tasks.aufgabenservice.guilib.gen.services;

import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import de.gaia.tasks.aufgabenservice.api.local.Aufgabe_;
import de.gaia.tasks.aufgabenservice.api.rest.Aufgabe_RestClient;
import de.gaia.tasks.aufgabenservice.api.rest.Aufgabe_RestClientImpl;
import de.gaia.tasks.aufgabenservice.guilib.services.Aufgabe_FallbackDataGenerator;

import de.muenchen.vaadin.guilib.services.SecurityService;
import de.muenchen.vaadin.demo.i18nservice.I18nPaths;
import de.muenchen.vaadin.demo.i18nservice.buttons.SimpleAction;
import de.muenchen.vaadin.guilib.BaseUI;
import de.muenchen.vaadin.guilib.components.GenericErrorNotification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static de.muenchen.vaadin.demo.i18nservice.I18nPaths.getNotificationPath;

/*
 * This file will be overwritten on every change of the model!
 * This file was automatically generated by GAIA.
 */
@SpringComponent @UIScope
public class Aufgabe_ServiceImpl implements Aufgabe_Service, Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(Aufgabe_ServiceImpl.class);
    public static final String TIMEOUT_I18N = "timeout";
	public static final int TIMEOUT_VAL = 5;
    
    private Aufgabe_RestClient client;
    private RestTemplate template;
    private SecurityService securityService;
    private final ExecutorService executor;

    @Autowired
    public Aufgabe_ServiceImpl(SecurityService securityService, @Value("${AUFGABENSERVICE.microservice.basePath}") String basePath) {
        this.securityService=securityService;
        
        final URI baseUri = URI.create(basePath);
		this.client = new Aufgabe_RestClientImpl(getTemplate(),baseUri);
		
        executor = Executors.newCachedThreadPool();
    }

	/**
	 * creates one Aufgabe_
	 * @param aufgabe the one who shall be created
	 * @return aufgabe
	 */
    @Override
    public Aufgabe_ create(Aufgabe_ aufgabe) {
    	Aufgabe_ returnAufgabe;
        Future<Aufgabe_> result = executor.submit(() -> client.create(aufgabe));
        try {
            returnAufgabe = result.get(TIMEOUT_VAL, TimeUnit.SECONDS);
        } catch (HttpClientErrorException e) {
            returnAufgabe = Aufgabe_FallbackDataGenerator.createAufgabeFallback();
            LOG.error(e.getMessage());
            showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.create);
        } catch (TimeoutException e) {
            returnAufgabe = Aufgabe_FallbackDataGenerator.createAufgabeFallback();
            LOG.error(e.getMessage());
            showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.create, TIMEOUT_I18N);
        } catch (Exception e) {
            returnAufgabe = Aufgabe_FallbackDataGenerator.createAufgabeFallback();
            LOG.error(e.getMessage());
            showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.create);
        } finally {
            result.cancel(true);
        }
        return returnAufgabe;
    }

	/**
	 * updates one Aufgabe_
	 * @param aufgabe the one who shall be updated
	 * @return aufgabe
	 */
    @Override
    public Aufgabe_ update(Aufgabe_ aufgabe) {
    	Aufgabe_ returnAufgabe;
        Future<Aufgabe_> result = executor.submit(() -> client.update(aufgabe));
        try {
            returnAufgabe = result.get(TIMEOUT_VAL, TimeUnit.SECONDS);
        } catch (HttpClientErrorException e) {
            returnAufgabe = Aufgabe_FallbackDataGenerator.createAufgabeFallback();
            LOG.error(e.getMessage());
            showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.update);
        } catch (TimeoutException e) {
            returnAufgabe = Aufgabe_FallbackDataGenerator.createAufgabeFallback();
            LOG.error(e.getMessage());
            showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.update, TIMEOUT_I18N);
        } catch (Exception e) {
            returnAufgabe = Aufgabe_FallbackDataGenerator.createAufgabeFallback();
            LOG.error(e.getMessage());
            showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.update);
        } finally {
            result.cancel(true);
        }
        return returnAufgabe;
    }

	/**
	 * Method to delete a certain link
	 * @param link the link
	 * @return successful
	 */
    @Override
    public boolean delete(Link link) {
    	Future<?> result = executor.submit(() -> client.delete(link));
        try {
            result.get(TIMEOUT_VAL, TimeUnit.SECONDS);
            return true;
        } catch (HttpClientErrorException e) {
            LOG.error(e.getMessage());
            HttpStatus statusCode = e.getStatusCode();
            if (statusCode.equals(HttpStatus.CONFLICT) || statusCode.equals(HttpStatus.NOT_FOUND))
                showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.delete, statusCode.toString());
            else
                showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.delete);
            return false;
        } catch (TimeoutException e) {
            LOG.error(e.getMessage());
            showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.delete, TIMEOUT_I18N);
            return false;
        } catch (Exception e) {
            LOG.error(e.getMessage());
            showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.delete);
            return false;
        } finally {
            result.cancel(true);
        }
    }

	/**
	 * Returns all Aufgabe_
	 * @return aufgabes the results
	 */
    @Override
    public List<Aufgabe_> findAll() {
        List<Aufgabe_> aufgabes;
        Future<List<Aufgabe_>> result = Executors.newCachedThreadPool().submit((Callable<List<Aufgabe_>>) client::findAll);
        try {
            aufgabes = result.get(TIMEOUT_VAL, TimeUnit.SECONDS);
            return aufgabes;
        } catch (HttpClientErrorException e) {
            aufgabes = Aufgabe_FallbackDataGenerator.createAufgabesFallback();
            LOG.error(e.getMessage());
            showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.read);
        } catch (TimeoutException e) {
            aufgabes = Aufgabe_FallbackDataGenerator.createAufgabesFallback();
            LOG.error(e.getMessage());
            showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.read, TIMEOUT_I18N);
        } catch (Exception e) {
            aufgabes = Aufgabe_FallbackDataGenerator.createAufgabesFallback();
            LOG.error(e.getMessage());
            showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.read);
        } finally {
            result.cancel(true);
        }
        return aufgabes;
    }

	/**
	 * Returns all Aufgabe_ of a specified relation
	 * @param relation the relation
	 * @return aufgabes the results
	 */
    @Override
    public List<Aufgabe_> findAll(Link relation) {
        List<Aufgabe_> aufgabes;
        Future<List<Aufgabe_>> result = executor.submit(() -> client.findAll(relation));
        try {
            aufgabes = result.get(TIMEOUT_VAL, TimeUnit.SECONDS);
        } catch (HttpClientErrorException e) {
            aufgabes = Aufgabe_FallbackDataGenerator.createAufgabesFallback();
            LOG.error(e.getMessage());
            showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.read);
        } catch (TimeoutException e) {
            aufgabes = Aufgabe_FallbackDataGenerator.createAufgabesFallback();
            LOG.error(e.getMessage());
            showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.read, TIMEOUT_I18N);
        } catch (Exception e) {
            aufgabes = Aufgabe_FallbackDataGenerator.createAufgabesFallback();
            LOG.error(e.getMessage());
            showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.read);
        } finally {
            result.cancel(true);
        }
        return aufgabes;
    }

	/**
	 * Returns an Optional of Aufgabe_ to one Link
	 * @param link the link
	 * @return the found Aufgabe_ 
	 */
    @Override
    public Optional<Aufgabe_> findOne(Link link) {
        Optional<Aufgabe_> aufgabe;
        Future<Optional<Aufgabe_>> result = executor.submit(() -> client.findOne(link));
        try {
            aufgabe = result.get(TIMEOUT_VAL, TimeUnit.SECONDS);
        } catch (HttpClientErrorException e) {
            aufgabe = Aufgabe_FallbackDataGenerator.createOptionalAufgabeFallback();
            LOG.error(e.getMessage());
            showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.read);
        } catch (TimeoutException e) {
            aufgabe = Aufgabe_FallbackDataGenerator.createOptionalAufgabeFallback();
            LOG.error(e.getMessage());
            showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.read, TIMEOUT_I18N);
        } catch (Exception e) {
            aufgabe = Aufgabe_FallbackDataGenerator.createOptionalAufgabeFallback();
			if(link.getRel()==null){
            	LOG.error(e.getMessage());
            	showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.read);
            }
        } finally {
            result.cancel(true);
        }
        return aufgabe;
    }

	/**
	 * Query to given string
	 * @param query
	 * @return aufgabes the results
	 */
    @Override
    public List<Aufgabe_> queryAufgabe(String query) {
        List<Aufgabe_> aufgabes;
        Future<List<Aufgabe_>> result = executor.submit(() -> client.findFullTextFuzzy(query));
        try {
            aufgabes = result.get(TIMEOUT_VAL, TimeUnit.SECONDS);
        } catch (HttpClientErrorException e) {
            aufgabes = Aufgabe_FallbackDataGenerator.createAufgabesFallback();
            LOG.error(e.getMessage());
            showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.read);
        } catch (TimeoutException e){
	          aufgabes = Aufgabe_FallbackDataGenerator.createAufgabesFallback();
	          LOG.error(e.getMessage());
	        showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.read, TIMEOUT_I18N);
        } catch (Exception e) {
            aufgabes = Aufgabe_FallbackDataGenerator.createAufgabesFallback();
            LOG.error(e.getMessage());
            showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.read);
		} finally {
			result.cancel(true);
		}
        return aufgabes;
    }

	/**
     * Sets relations to entities
     * @param link the endpoint of the relation
     * @param links collection of links that are set to be related
     * @return successful
     */
    @Override
    public boolean setRelations(Link link, List<Link> links) {
        Future<?> result = executor.submit(() -> client.setRelations(link, links));
        try {
            result.get(TIMEOUT_VAL, TimeUnit.SECONDS);
            return true;
        } catch (HttpClientErrorException e) {
            LOG.error(e.getMessage());
            showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.association);
            return false;
        } catch (TimeoutException e) {
            LOG.error(e.getMessage());
            showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.association, TIMEOUT_I18N);
            return false;
        } catch (Exception e) {
            LOG.error(e.getMessage());
            showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.association);
            return false;
        } finally {
            result.cancel(true);
        }
    }

	/**
     * Sets a relation to an entity
     * @param link the endpoint of the relation
     * @param relation the link that is set to be related
     * @return successful
     */
    @Override
    public boolean setRelation(Link link, Link relation) {
        Future<?> result = executor.submit(() -> client.setRelation(link, relation));
        try {
            result.get(TIMEOUT_VAL, TimeUnit.SECONDS);
            return true;
        } catch (HttpClientErrorException e) {
            LOG.error(e.getMessage());
            showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.association);
            return false;
        } catch (TimeoutException e) {
            LOG.error(e.getMessage());
            showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.association, TIMEOUT_I18N);
            return false;
        } catch (Exception e) {
            LOG.error(e.getMessage());
            showErrorNotification(I18nPaths.NotificationType.error, SimpleAction.association);
            return false;
        } finally {
            result.cancel(true);
        }
    }

    /**
     * Gets the resttemplate from the security if not present
     * @return resttemplate of this session
     */
    public RestTemplate getTemplate() {
        if (template != null) {
            return template;
        }
        return securityService.getRestTemplate().orElse(null);
    }

	/**
     * Shows an error notification
     * @param type the type of the notification
     * @param action the type of action performed
     */
    private void showErrorNotification(I18nPaths.NotificationType type, SimpleAction action) {
        GenericErrorNotification succes = new GenericErrorNotification(
                BaseUI.getCurrentI18nResolver().resolveRelative(Aufgabe_.class, getNotificationPath(type, action, I18nPaths.Type.label)),
                BaseUI.getCurrentI18nResolver().resolveRelative(Aufgabe_.class, getNotificationPath(type, action, I18nPaths.Type.text)));
        succes.show(Page.getCurrent());
    }

	/**
     * Shows an error notification specified by a status code
     * @param type the type of the notification
     * @param action the type of action performed
     * @param statusCode the status code
     */
    private void showErrorNotification(I18nPaths.NotificationType type, SimpleAction action, String statusCode) {
        GenericErrorNotification succes = new GenericErrorNotification(
                BaseUI.getCurrentI18nResolver().resolveRelative(Aufgabe_.class, getNotificationPath(type, action, I18nPaths.Type.label, statusCode)),
                BaseUI.getCurrentI18nResolver().resolveRelative(Aufgabe_.class, getNotificationPath(type, action, I18nPaths.Type.text, statusCode)));
        succes.show(Page.getCurrent());
    }
}
