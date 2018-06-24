package com.medihealth.services;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medihealth.beans.MedicalService;
import com.medihealth.beans.Patient;
import com.medihealth.servicesimpl.HealthServicesImpl;

/**
 * The Class HealthServiceTest to test all the methods defined in HealthServices
 * Interface & its implementing class.
 *
 * @author Mithun Savla
 */
class HealthServiceTest {

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

	/** A test patient */
	private Patient p1;

	/** A test patient */
	private Patient p2;

	/** A test patient */
	private Patient p3;

	/** A test patient */
	private Patient p4;

	/** A test patient */
	private Patient p5;

	/**
	 * Sets the patients with different age and insurance values to be used to test
	 * discounts.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@BeforeEach
	public void setUpPatients() throws Exception {
		p1 = new Patient("Tom Cruise", 40, false); // No Discount
		p2 = new Patient("Steven King", 4, true); // Child Discount - 40%
		p3 = new Patient("Ketut Ling", 68, false); // Senior Discount - 60%
		p4 = new Patient("Amitabh Bachhan", 75, false); // Super Senior Discount - 90%
		p5 = new Patient("Angelina Jolie", 45, true); // Insurance Discount on Blood Test
	}

	/**
	 * Test enrol for diagnosis.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testEnrolForDiagnosis() throws Exception {
		HealthServices healthService = new HealthServicesImpl();
		p1 = healthService.enrolForDiagnosis(p1);
		MedicalService medicalService = p1.getServicesEnrolled().get(0);
		Double defaultPrice = medicalService.getDefaultPrice();
		Double discountedPrice = medicalService.getDiscountedPrice();
		assertTrue(medicalService.getServiceName().equals(DIAGNOSIS));
		assertTrue(defaultPrice.equals(DEFAULT_PRICE_DIAGNOSIS));
		assertTrue(discountedPrice.equals(DEFAULT_PRICE_DIAGNOSIS)); // no discount applied
	}

	/**
	 * Test enrol for xray.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testEnrolForXray() throws Exception {
		HealthServices healthService = new HealthServicesImpl();
		p2 = healthService.enrolForXray(p2);// 40 % child discount applied but no discount for Insurance as it is only
											// for Blood Test
		MedicalService medicalService = p2.getServicesEnrolled().get(0);
		Double defaultPrice = medicalService.getDefaultPrice();
		Double discountedPrice = medicalService.getDiscountedPrice();
		assertTrue(medicalService.getServiceName().equals(XRAY));
		assertTrue(defaultPrice.equals(DEFAULT_PRICE_XRAY));
		assertTrue(discountedPrice.equals(DEFAULT_PRICE_XRAY - (DEFAULT_PRICE_XRAY * CHILD_DISCOUNT_40)));
	}

	/**
	 * Test enrol for blood test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testEnrolForBloodTest() throws Exception {
		HealthServices healthService = new HealthServicesImpl();
		p5 = healthService.enrolForBloodTest(p5);
		MedicalService medicalService = p5.getServicesEnrolled().get(0);
		Double defaultPrice = medicalService.getDefaultPrice();
		Double discountedPrice = medicalService.getDiscountedPrice(); // Insurance 15% discount for blood test
		assertTrue(medicalService.getServiceName().equals(BLOOD_TEST));
		assertTrue(defaultPrice.equals(DEFAULT_PRICE_BLOOD_TEST));
		assertTrue(discountedPrice
				.equals(DEFAULT_PRICE_BLOOD_TEST - (DEFAULT_PRICE_BLOOD_TEST * MEDIHEALTH_INSURANCE_DISCOUNT_15)));
	}

	/**
	 * Test enrol for ECG.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testEnrolForECG() throws Exception {
		HealthServices healthService = new HealthServicesImpl();
		p3 = healthService.enrolForECG(p3); // Senior Discount - 60%
		MedicalService medicalService = p3.getServicesEnrolled().get(0);
		Double defaultPrice = medicalService.getDefaultPrice();
		Double discountedPrice = medicalService.getDiscountedPrice();
		assertTrue(medicalService.getServiceName().equals(ECG));
		assertTrue(defaultPrice.equals(DEFAULT_PRICE_ECG));
		assertTrue(discountedPrice.equals(DEFAULT_PRICE_ECG - (DEFAULT_PRICE_ECG * SENIOR_DISCOUNT_60)));
	}

	/**
	 * Test enrol for vaccines.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testEnrolForVaccines() throws Exception {
		HealthServices healthService = new HealthServicesImpl();
		p4 = healthService.enrolForVaccines(p4, 4); // Super Senior Discount - 90% on 4 vaccines and service cost
		MedicalService medicalService = p4.getServicesEnrolled().get(0);
		Double defaultPrice = medicalService.getDefaultPrice();
		Double discountedPrice = medicalService.getDiscountedPrice();
		Double totalAmount = (DEFAULT_SERVICE_PRICE_VACCINE + (4 * DEFAULT_PRICE_VACCINE));
		assertTrue(medicalService.getServiceName().equals(VACCINE));
		assertTrue(defaultPrice.equals(totalAmount));
		assertTrue(discountedPrice.equals(totalAmount - (totalAmount * SUPER_SENIOR_DISCOUNT_90)));
	}

}
