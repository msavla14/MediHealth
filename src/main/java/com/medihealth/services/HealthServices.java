package com.medihealth.services;

import com.medihealth.beans.Patient;

/**
 * The Interface HealthServices provides an interface to enroll for all the
 * medical services offered by Medi health.
 *
 * @author Mithun Savla
 */
public interface HealthServices {

	/**
	 * Enroll for diagnosis.
	 *
	 * @param patient
	 *            the patient
	 * @return the patient
	 * 
	 */
	public Patient enrolForDiagnosis(Patient patient) throws Exception;

	/**
	 * Enrol for xray.
	 *
	 * @param patient
	 *            the patient
	 * @return the patient
	 */
	public Patient enrolForXray(Patient patient) throws Exception;

	/**
	 * Enrol for blood test.
	 *
	 * @param patient
	 *            the patient
	 * @return the patient
	 */
	public Patient enrolForBloodTest(Patient patient) throws Exception;

	/**
	 * Enrol for ECG.
	 *
	 * @param patient
	 *            the patient
	 * @return the patient
	 */
	public Patient enrolForECG(Patient patient) throws Exception;

	/**
	 * Enrol for vaccines.
	 *
	 * @param patient
	 *            the patient
	 * @param noOfVaccines
	 *            the no of vaccines
	 * @return the patient
	 */
	public Patient enrolForVaccines(Patient patient, int noOfVaccines) throws Exception;
}
