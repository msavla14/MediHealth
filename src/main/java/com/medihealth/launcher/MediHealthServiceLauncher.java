package com.medihealth.launcher;

import java.text.DecimalFormat;

import com.medihealth.beans.MedicalService;
import com.medihealth.beans.Patient;
import com.medihealth.commons.CommonErrorMessages;
import com.medihealth.services.HealthServices;
import com.medihealth.servicesimpl.HealthServicesImpl;

/**
 * The Class MediHealthServiceLauncher is the main class where the application
 * begins.
 */
public class MediHealthServiceLauncher {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) throws Exception {

		// A sample patient p1 created
		Patient p1 = new Patient();
		p1.setPatientName("Mithun Savla");

		/**
		 * Valid age is 1-125 years, change the age value out of this range to print an
		 * error message
		 */
		p1.setPatientAge(75); // Eligible for Super senior discount
		p1.setPatientInsured(true); // Not eligible for insurance discount on blood test
		p1 = validateAge(p1); // validates the age entered is a valid value
		if (p1.isPatientCreatedSuccessfully()) {
			HealthServices healthService = new HealthServicesImpl();
			healthService.enrolForBloodTest(p1); // patient p1 enrolled for Blood test
			healthService.enrolForDiagnosis(p1); // patient p1 enrolled for Diagnosis
			healthService.enrolForECG(p1); // patient p1 enrolled for ECG
			healthService.enrolForXray(p1); // patient p1 enrolled for Xray
			healthService.enrolForVaccines(p1, 1); // patient p1 enrolled for 1 Vaccine
			printBill(p1); // Print a bill for patient p1
		} else {
			System.out.println(p1.getResponseMessage());
		}
		// A sample patient p2 created
		Patient p2 = new Patient();
		p2.setPatientName("Jason Stratham");
		p2.setPatientAge(50);
		p2.setPatientInsured(true); // Eligible for insurance discount on blood test

		p2 = validateAge(p2); // validates the age entered is a valid value
		if (p2.isPatientCreatedSuccessfully()) {
			HealthServices healthService2 = new HealthServicesImpl();
			healthService2.enrolForBloodTest(p2);// patient p2 enrolled for Blood test
			healthService2.enrolForDiagnosis(p2);// patient p2 enrolled for Diagnosis
			healthService2.enrolForECG(p2); // patient p2 enrolled for ECG
			healthService2.enrolForXray(p2);// patient p2 enrolled for Xray
			healthService2.enrolForVaccines(p2, 4);// patient p2 enrolled for 4 Vaccines
			printBill(p2); // Print a bill for patient p2
		} else {
			System.out.println(p1.getResponseMessage());
		}
	}

	/**
	 * Prints the bill with all the services enrolled by a patient. the bill prints
	 * default price of each service and the discounted price and then sums the
	 * total amount payable by a patient.
	 *
	 * @param patient
	 *            the patient
	 */
	public static void printBill(Patient patient) {
		/*
		 * A formatter to format to 2 decimal place. e.g 23.57686 will be formatted to
		 * 23.57
		 */
		DecimalFormat df2 = new DecimalFormat(".##");

		/* Total sum of the default price of all the services */
		Double sumOfDefaultPrice = patient.getServicesEnrolled().stream().mapToDouble(MedicalService::getDefaultPrice)
				.sum();
		/* Total sum of the discounted price of all the services. */
		Double sumOfDiscountedPrice = patient.getServicesEnrolled().stream()
				.mapToDouble(MedicalService::getDiscountedPrice).sum();
		System.out.println("--------------------*****------------------");
		System.out.println("Patient Name: " + patient.getPatientName());
		System.out.println("Patient Age: " + patient.getPatientAge());
		if (patient.isPatientInsured()) {
			System.out.println("Patient Insured: Yes");
		} else {
			System.out.println("Patient Insured: No");
		}
		System.out.println("---------");
		System.out.println("Service	Price	Price After Discount");

		for (MedicalService medicalService : patient.getServicesEnrolled()) {
			System.out.println(medicalService.getServiceName() + "	£" + df2.format(medicalService.getDefaultPrice())
					+ " £" + df2.format(medicalService.getDiscountedPrice()));

		}
		System.out.println("Total" + "	£" + df2.format(sumOfDefaultPrice) + "	£" + df2.format(sumOfDiscountedPrice));

	}

	/**
	 * Validates if age entered is a valid number and sets the result in to
	 * responseMessage of the patient object.
	 * 
	 * @param patient
	 * @return patient
	 */
	public static Patient validateAge(Patient patient) {
		if (patient.getPatientAge() <= 0 || patient.getPatientAge() > 125) {
			patient.setPatientCreatedSuccessfully(false);
			patient.setResponseMessage(CommonErrorMessages.INVALID_AGE);
		} else {
			patient.setPatientCreatedSuccessfully(true);
		}
		return patient;
	}
}
