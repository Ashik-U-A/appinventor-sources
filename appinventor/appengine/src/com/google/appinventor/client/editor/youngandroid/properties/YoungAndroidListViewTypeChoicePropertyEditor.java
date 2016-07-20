package com.google.appinventor.client.editor.youngandroid.properties;

import com.google.appinventor.client.widgets.properties.ChoicePropertyEditor;
import static com.google.appinventor.client.Ode.MESSAGES;

public class YoungAndroidListViewTypeChoicePropertyEditor extends ChoicePropertyEditor{

	public static final Choice[] type = new Choice[] {
		new Choice(MESSAGES.defaultListViewType(),"0"),
		new Choice(MESSAGES.listViewTypeOne(),"1"),
		new Choice(MESSAGES.listViewTypeTwo(),"2"),
		new Choice(MESSAGES.listViewTypeThree(),"3"),
		new Choice(MESSAGES.listViewTypeFour(),"4")
	};
	public YoungAndroidListViewTypeChoicePropertyEditor() {
		super(type);
		// TODO Auto-generated constructor stub
	}

}
