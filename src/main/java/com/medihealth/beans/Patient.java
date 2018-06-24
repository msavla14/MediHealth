package com.medihealth.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class Patient stores patient info such as age, insurance, a list of the
 * medical services involved.
 * 
 * @author Mithun Savla
 */
public class Patient {

	/** The patient name. */
	private String patientName;

	/** The patient age. */
	private int patientAge;

	/** The patient insured. */
	private boolean patientInsured;

	/** The services enrolled. */
	private List<MedicalService> servicesEnrolled;

	/**
	 * returns true if a patient is created successfully with all attributes
	 * validated, false otherwise.
	 */
	private boolean patientCreatedSuccessfully;

	/**
	 * @return the patientCreatedSuccessfully
	 */
	public boolean isPatientCreatedSuccessfully() {
		return patientCreatedSuccessfully;
	}

	/**
	 * @param patientCreatedSuccessfully
	 *            the patientCreatedSuccessfully to set
	 */
	public void setPatientCreatedSuccessfully(boolean patientCreatedSuccessfully) {
		this.patientCreatedSuccessfully = patientCreatedSuccessfully;
	}

	/** The Response message of a patient object creation */
	private String responseMessage;

	/**
	 * @return the responseMessage
	 */
	public String getResponseMessage() {
		return responseMessage;
	}

	/**
	 * @param responseMessage
	 *            the responseMessage to set
	 */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	/**
	 * Default constructor that instantiates a new patient.
	 */
	public Patient() {
		this.servicesEnrolled = new ArrayList<MedicalService>();
	}

	/**
	 * Parameterised constructor which instantiates a new patient with the patient
	 * info passed as arguments.
	 *
	 * @param name
	 *            the name of the patient
	 * @param age
	 *            the age of the patient
	 * @param insurance
	 *            determines if the patient is insured or not insured
	 */
	public Patient(String name, int age, boolean insurance) // Public for constructor.
	{
		this();
		this.patientName = name;
		this.patientAge = age;
		this.patientInsured = insurance;
	}

	/**
	 * Adds a medical service.
	 *
	 * @param service
	 *            a service enrolled by a patient
	 */
	public void addService(MedicalService service) {
		servicesEnrolled.add(service);
	}

	/**
	 * Deletes a medical service.
	 *
	 * @param service
	 *            a service enrolled by a patient
	 */
	public void deleteService(MedicalService service) {
		servicesEnrolled.remove(service);
	}

	/**
	 * Gets the medical services enrolled by a patient.
	 *
	 * @return the servicesEnrolled
	 */
	public List<MedicalService> getServicesEnrolled() {
		return servicesEnrolled;
	}

	/**
	 * Sets the services enrolled by a patient.
	 *
	 * @param servicesEnrolled
	 *            the servicesEnrolled to set
	 */
	public void setServicesEnrolled(List<MedicalService> servicesEnrolled) {
		this.servicesEnrolled = servicesEnrolled;
	}

	/**
	 * Gets the patient name.
	 *
	 * @return the patientName
	 */
	public String getPatientName() {
		return patientName;
	}

	/**
	 * Sets the patient name.
	 *
	 * @param patientName
	 *            the patientName to set
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	/**
	 * Gets the patient age.
	 *
	 * @return the patientAge
	 */
	public int getPatientAge() {
		return patientAge;
	}

	/**
	 * Sets the patient age.
	 *
	 * @param patientAge
	 *            the patientAge to set
	 */
	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}

	/**
	 * Gets the patient insurance status if he is insured or not insured.
	 *
	 * @return the patientInsured
	 */
	public boolean isPatientInsured() {
		return patientInsured;
	}

	/**
	 * Sets the patient insured.
	 *
	 * @param patientInsured
	 *            the patientInsured to set
	 */
	public void setPatientInsured(boolean patientInsured) {
		this.patientInsured = patientInsured;
	}

}
