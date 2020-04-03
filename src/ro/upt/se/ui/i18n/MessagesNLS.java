package ro.upt.se.ui.i18n;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.osgi.util.NLS;

import ro.upt.se.ui.SurveyWizard;

/**
 * This class defines the internationalization messages for the application.
 * It holds all the UI messages found in the app.
 * 
 * @author razvanl
 */
public class MessagesNLS extends NLS {
	public static final String BUNDLE_NAME = "ro.upt.se.ui.i18n.Messages";
	public static final ImageDescriptor DEFAULT_IMAGE = ImageDescriptor.createFromURL(SurveyWizard.class.getResource("/resized_a7_120x80.png"));
	
	public static String APP_TITLE;
	
	public static String PAGE_DESCRIPTION;
	public static String PAGE_TITLE;
	public static String SUITED_VEHICLE;
	
	public static String NO_VEHICLE_FOUND;
	
	public static String Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8, Q9;
	
	public static String QUESTION_1;
	public static String QUESTION_1_ANSWER_1;
	public static String QUESTION_1_ANSWER_2;
	public static String QUESTION_1_ANSWER_3;
	public static String QUESTION_1_ANSWER_4;
	
	public static String QUESTION_2;
	public static String QUESTION_2_ANSWER_1;
	public static String QUESTION_2_ANSWER_2;
	public static String QUESTION_2_ANSWER_3;
	public static String QUESTION_2_ANSWER_4;
	public static String QUESTION_2_ANSWER_5;
	public static String QUESTION_2_ANSWER_6;
	public static String QUESTION_2_ANSWER_7;
	public static String QUESTION_2_ANSWER_8;
	public static String QUESTION_2_ANSWER_9;
	public static String QUESTION_2_ANSWER_10;
	
	public static String QUESTION_3;
	public static String QUESTION_3_ANSWER_1;
	public static String QUESTION_3_ANSWER_2;
	public static String QUESTION_3_ANSWER_3;
	public static String QUESTION_3_ANSWER_4;
	public static String QUESTION_3_ANSWER_5;
	public static String QUESTION_3_ANSWER_6;
	
	public static String QUESTION_4;
	public static String QUESTION_4_ANSWER_1;
	public static String QUESTION_4_ANSWER_2;
	public static String QUESTION_4_ANSWER_3;
	public static String QUESTION_4_ANSWER_4;
	public static String QUESTION_4_ANSWER_5;
	public static String QUESTION_4_ANSWER_6;
	public static String QUESTION_4_ANSWER_7;
	
	public static String QUESTION_5;
	public static String QUESTION_5_ANSWER_1;
	public static String QUESTION_5_ANSWER_2;
	public static String QUESTION_5_ANSWER_3;
	public static String QUESTION_5_ANSWER_4;
	public static String QUESTION_5_ANSWER_5;
	public static String QUESTION_5_ANSWER_6;
	
	public static String QUESTION_6;
	public static String QUESTION_6_ANSWER_1;
	public static String QUESTION_6_ANSWER_2;
	public static String QUESTION_6_ANSWER_3;
	
	public static String QUESTION_7;
	public static String QUESTION_7_ANSWER_1;
	public static String QUESTION_7_ANSWER_2;
	public static String QUESTION_7_ANSWER_3;
	public static String QUESTION_7_ANSWER_4;
	
	public static String QUESTION_8;
	public static String QUESTION_8_ANSWER_1;
	public static String QUESTION_8_ANSWER_2;
	public static String QUESTION_8_ANSWER_3;
	
	public static String QUESTION_9;
	public static String QUESTION_9_ANSWER_1;
	public static String QUESTION_9_ANSWER_2;
	public static String QUESTION_9_ANSWER_3;
	
	static {
		NLS.initializeMessages(BUNDLE_NAME, MessagesNLS.class);
	}
}