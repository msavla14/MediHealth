package com.medihealth.servicesimpl;

import com.medihealth.beans.MedicalService;
import com.medihealth.beans.Patient;
import com.medihealth.services.HealthServices;

/**
 * Implementing class for HealthServices.java
 * 
 * @author Mithun Savla
 */
public class HealthServicesImpl implements HealthServices {

	/** The Constant DIAGNOSIS. */
	private static final String DIAGNOSIS = "Diagnosis";

	/** The Constant XRAY. */
	private static final String XRAY = "X-Ray";

	/** The Constant BLOOD_TEST. */
	private static final String BLOOD_TEST = "Blood Test";

	/** The Constant ECG. */
	private static final String ECG = "ECG";

	/** The Constant VACCINE. */
	private static final String VACCINE = "Vaccine";

	/** The Constant DEFAULT_PRICE_DIAGNOSIS. */
	private static final double DEFAULT_PRICE_DIAGNOSIS = 60;

	/** The Constant DEFAULT_PRICE_XRAY. */
	private static final double DEFAULT_PRICE_XRAY = 150;

	/** The Constant DEFAULT_PRICE_BLOOD_TEST. */
	private static final double DEFAULT_PRICE_BLOOD_TEST = 78;

	/** The Constant DEFAULT_PRICE_ECG. */
	private static final double DEFAULT_PRICE_ECG = 200.40;

	/** The Constant DEFAULT_PRICE_VACCINE. */
	private static final double DEFAULT_PRICE_VACCINE = 15;

	/** The Constant DEFAULT_SERVICE_PRICE_VACCINE. */
	private static final double DEFAULT_SERVICE_PRICE_VACCINE = 27.50;

	/** The Constant CHILD_DISCOUNT_40. */
	private static final double CHILD_DISCOUNT_40 = (0.4);

	/** The Constant SENIOR_DISCOUNT_60. */
	private static final double SENIOR_DISCOUNT_60 = (0.6);

	/** The Constant SUPER_SENIOR_DISCOUNT_90. */
	private static final double SUPER_SENIOR_DISCOUNT_90 = (0.9);

	/** The Constant MEDIHEALTH_INSURANCE_DISCOUNT_15. */
	private static final double MEDIHEALTH_INSURANCE_DISCOUNT_15 = (0.15);

	/*
	 * Enrols a patient for Diagnosis service and calculates the default price and
	 * the discounted price based on the age of the patient.
	 * 
	 * @see com.medihealth.services.HealthServices#enrolForDiagnosis(com.medihealth.
	 * beans.Patient)
	 */
	@Override
	public Patient enrolForDiagnosis(Patient patient) {
		MedicalService mservice = setServiceAttributes(DIAGNOSIS, DEFAULT_PRICE_DIAGNOSIS);
		checkDiscountApplicable(patient, mservice);
		patient.addService(mservice);
		return patient;
	}

	/*
	 * Enrols a patient for Xray service and calculates the default price and the
	 * discounted price based on the age of the patient.
	 * 
	 * @see
	 * com.medihealth.services.HealthServices#enrolForXray(com.medihealth.beans.
	 * Patient)
	 */
	@Override
	public Patient enrolForXray(Patient patient) {
		MedicalService mservice = setServiceAttributes(XRAY, DEFAULT_PRICE_XRAY);
		checkDiscountApplicable(patient, mservice);
		patient.addService(mservice);
		return patient;
	}

	/*
	 * Enrols a patient for Blood Test service and calculates the default price and
	 * the discounted price based on insurance and age of the patient.
	 * 
	 * @see com.medihealth.services.HealthServices#enrolForBloodTest(com.medihealth.
	 * beans.Patient)
	 */
	@Override
	public Patient enrolForBloodTest(Patient patient) {
		MedicalService mservice = setServiceAttributes(BLOOD_TEST, DEFAULT_PRICE_BLOOD_TEST);
		checkDiscountApplicable(patient, mservice);
		patient.addService(mservice);
		return patient;
	}

	/*
	 * Enrols a patient for ECG service and calculates the default price and the
	 * discounted price based on the age of the patient.
	 * 
	 * @see com.medihealth.services.HealthServices#enrolForECG(com.medihealth.beans.
	 * Patient)
	 */
	@Override
	public Patient enrolForECG(Patient patient) {
		MedicalService mservice = setServiceAttributes(ECG, DEFAULT_PRICE_ECG);
		checkDiscountApplicable(patient, mservice);
		patient.addService(mservice);
		return patient;
	}

	/*
	 * Enrols a patient for Vaccine service and calculates the default price and the
	 * discounted price based on the age of the patient.
	 * 
	 * @see
	 * com.medihealth.services.HealthServices#enrolForVaccines(com.medihealth.beans
	 * .Patient, int)
	 */
	@Override
	public Patient enrolForVaccines(Patient patient, int noOfVaccines) {
		double allVaccinesPrice = (DEFAULT_SERVICE_PRICE_VACCINE + (noOfVaccines * DEFAULT_PRICE_VACCINE));
		MedicalService mservice = setServiceAttributes(VACCINE, allVaccinesPrice);
		checkDiscountApplicable(patient, mservice);
		patient.addService(mservice);
		return patient;
	}

	/**
	 * Sets the service attributes.
	 *
	 * @param name
	 *            the name
	 * @param price
	 *            the price
	 * @return the medical service
	 */
	public MedicalService setServiceAttributes(String name, double price) {
		MedicalService mservice = new MedicalService();
		mservice.setServiceName(name);
		mservice.setDefaultPrice(price);
		return mservice;
	}

	/**
	 * Check discount applicable based on age and insurance if a patient is insured.
	 *
	 * @param patient
	 *            the patient
	 * @param mservice
	 *            the mservice
	 */
	void checkDiscountApplicable(Patient patient, MedicalService mservice) {
		applyDiscountByAge(patient, mservice);
		applyDiscountByInsurance(patient, mservice);
	}

	/**
	 * Apply discount by age. - Children under 5 receive 40% discount - Senior
	 * citizens (between 65 and 70) receive a 60% discount - Senior citizens (over
	 * 70) receive a 90% discount
	 * 
	 * @param patient
	 *            the patient
	 * @param mservice
	 *            the mservice
	 */
	void applyDiscountByAge(Patient patient, MedicalService mservice) {
		int age = patient.getPatientAge();

		if (age < 5) {
			// Children under 5 receive 40% discount
			mservice.setDiscountedPrice(mservice.getDefaultPrice() - (mservice.getDefaultPrice() * CHILD_DISCOUNT_40));
		} else if (age >= 65 && age <= 70) {
			// Senior citizens (between 65 and 70) receive a 60% discount
			mservice.setDiscountedPrice(mservice.getDefaultPrice() - (mservice.getDefaultPrice() * SENIOR_DISCOUNT_60));
		} else if (age > 70) {
			// Senior citizens (over 70) receive a 90% discount
			mservice.setDiscountedPrice(
					mservice.getDefaultPrice() - (mservice.getDefaultPrice() * SUPER_SENIOR_DISCOUNT_90));
		} else {
			// No Discount
			mservice.setDiscountedPrice(mservice.getDefaultPrice());
		}
	}

	/**
	 * Apply discount by insurance for Blood Test.
	 *
	 * @param patient
	 *            the patient
	 * @param mservice
	 *            the mservice
	 */
	void applyDiscountByInsurance(Patient patient, MedicalService mservice) {
		if (mservice.getServiceName().equals(BLOOD_TEST) && patient.isPatientInsured()) {
			mservice.setDiscountedPrice(
					mservice.getDiscountedPrice() - (mservice.getDiscountedPrice() * MEDIHEALTH_INSURANCE_DISCOUNT_15));
		}

	}

}
