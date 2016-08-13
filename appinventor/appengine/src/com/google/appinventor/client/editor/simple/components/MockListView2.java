package com.google.appinventor.client.editor.simple.components;

import com.google.appinventor.client.Ode;
import com.google.appinventor.client.editor.simple.SimpleEditor;
import com.google.appinventor.client.output.OdeLog;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;



public class MockListView2 extends MockVisibleComponent{

	public static String TYPE = "ListView2";
	private final VerticalPanel listViewWidget;
	private VerticalPanel panelContainer;
	private HorizontalPanel panelContainerTwo;
	private SimplePanel panelMain;
	private SimplePanel panelSub;
	private SimplePanel panelImg;
	private InlineLabel labelMain;
	private Label labelSub;
	private Image image;

	private int type;
	private int WIDTH;
	private String[] mainElements;
	private String[] subElements;
	private String[] pictureElements;
	private int numberOfMainElements;
	private int numberOfSubElements;
	private int numberOfPictureElements;

	private String MAIN_TEXT_SIZE_DEFAULT = "15";
	private String SUB_TEXT_SIZE_DEFAULT = "10";

	private String mainTextColor;
	private String subTextColor;
	private String backgroundColor;
	private String mainTextSize;
	private String subTextSize;
	
	
	public MockListView2(SimpleEditor editor) {
		super(editor,TYPE,images.ball());
		listViewWidget = new VerticalPanel();
		listViewWidget.setSize(315+"px","100%");
		listViewWidget.setTitle("ListViewAdvanced");
		listViewWidget.setStylePrimaryName("ode-SimpleMockComponent");
		listViewWidget.setStyleName("listViewComponentStyle",true);
		initComponent(listViewWidget);
		MockComponentsUtil.setWidgetBackgroundColor(listViewWidget, "&HFFFFFFFF");
		numberOfMainElements = 0;
		numberOfSubElements = 0;
		numberOfPictureElements = 0;
		type = 1;
		mainTextColor = "&HFF000000";
		subTextColor = "&HFF000000";
		backgroundColor = "&HFFFFFFFF";
		mainTextSize = MAIN_TEXT_SIZE_DEFAULT;
		subTextSize = SUB_TEXT_SIZE_DEFAULT;
		fillDataWhenEmpty(1);
		setAttributes();
		// TODO Auto-generated constructor stub
	}
	
	private void listViewTypeOne(String mainText){
		panelMain = new SimplePanel();
		panelMain.setSize(310+"px", 40+"px");//Change
		panelMain.setStyleName("listViewItemContainer");
		labelMain = new InlineLabel();
		labelMain.setText(mainText);
		MockComponentsUtil.setWidgetTextColor(labelMain,mainTextColor);
		MockComponentsUtil.setWidgetFontSize(labelMain,mainTextSize);
		labelMain.setWidth(WIDTH+"px");
		panelMain.add(labelMain);
		listViewWidget.add(panelMain);
		MockComponentsUtil.setWidgetBackgroundColor(listViewWidget,backgroundColor);
	}
	
	private void listViewTypeTwo(String mainText,String subText){
		panelContainer = new VerticalPanel();
		panelContainer.setStyleName("listViewItemContainer");
		panelMain = new SimplePanel();
		panelMain.setSize(ComponentConstants.LISTVIEW_PREFERRED_WIDTH+"px",ComponentConstants.LISTVIEW_PREFERRED_HEIGHT-20+"px");
		panelMain.setStyleName("listViewItem");
		labelMain = new InlineLabel(mainText);
		MockComponentsUtil.setWidgetTextColor(labelMain,mainTextColor);
		MockComponentsUtil.setWidgetFontSize(labelMain,mainTextSize);
		panelMain.add(labelMain);
		panelSub = new SimplePanel();
		panelSub.setSize(ComponentConstants.LISTVIEW_PREFERRED_WIDTH+"px",ComponentConstants.LISTVIEW_PREFERRED_HEIGHT-20+"px");
		labelSub = new InlineLabel(subText);
		MockComponentsUtil.setWidgetTextColor(labelSub,subTextColor);
		MockComponentsUtil.setWidgetFontSize(labelSub,subTextSize);
		panelSub.add(labelSub);
		panelSub.setStyleName("listViewItemSmall");
		panelContainer.add(panelMain);
		panelContainer.add(panelSub);
		listViewWidget.add(panelContainer);
		MockComponentsUtil.setWidgetBackgroundColor(listViewWidget,backgroundColor);
	}
	
	private void listViewTypeThree(String mainText,String url){
		if(url == null){
			url = Ode.getImageBundle().image().getURL();
		}
		panelContainerTwo = new HorizontalPanel();
		panelContainerTwo.setStyleName("listViewItemContainer");
		panelImg = new SimplePanel();
		panelImg.setStyleName("listViewItemImage");
		panelImg.setSize(40+"px", 40+"px");
		image = new Image(url);
		image.setHeight(40+"px");
		image.setWidth(40+"px");
		panelImg.add(image);
		panelContainerTwo.add(panelImg);
		panelMain = new SimplePanel();
		panelMain.setHeight(ComponentConstants.LISTVIEW_PREFERRED_HEIGHT+"px");
		panelMain.setWidth(ComponentConstants.LISTVIEW_PREFERRED_WIDTH-40+"px");
		panelMain.setStyleName("listViewItem");
		labelMain = new InlineLabel(mainText);
		MockComponentsUtil.setWidgetTextColor(labelMain,mainTextColor);
		MockComponentsUtil.setWidgetFontSize(labelMain,mainTextSize);
		labelMain.setStyleName("listViewItem");
		panelMain.add(labelMain);
		panelContainerTwo.add(panelMain);
		listViewWidget.add(panelContainerTwo);
		MockComponentsUtil.setWidgetBackgroundColor(listViewWidget,backgroundColor);
	}
	
	private void listViewTypeFour(String mainText, String subText, String url){
		if(url == null){
			url = Ode.getImageBundle().image().getURL();
		}
		panelContainerTwo = new HorizontalPanel();
		panelContainerTwo.setStyleName("listViewItemContainer");
		panelImg = new SimplePanel();
		panelImg.setStyleName("listViewItemImage");
		panelImg.setSize(40+"px",40+"px");
		image = new Image(url);
		image.setSize(40+"px", 40+"px");
		panelImg.add(image);
		panelContainer = new VerticalPanel();
		panelMain = new SimplePanel();
		panelMain.setSize(ComponentConstants.LISTVIEW_PREFERRED_WIDTH-40+"px", ComponentConstants.LISTVIEW_PREFERRED_HEIGHT-20+"px");
		panelMain.setStyleName("listViewItem");
		labelMain = new InlineLabel(mainText);
		MockComponentsUtil.setWidgetTextColor(labelMain,mainTextColor);
		MockComponentsUtil.setWidgetFontSize(labelMain,mainTextSize);
		panelMain.add(labelMain);
		panelSub = new SimplePanel();
		panelSub.setStyleName("listViewItemSmall");
		panelSub.setSize(ComponentConstants.LISTVIEW_PREFERRED_WIDTH-40+"px",ComponentConstants.LISTVIEW_PREFERRED_HEIGHT-20+"px");
		labelSub = new InlineLabel(subText);
		MockComponentsUtil.setWidgetTextColor(labelSub,subTextColor);
		MockComponentsUtil.setWidgetFontSize(labelSub,subTextSize);
		panelSub.add(labelSub);
		panelContainer.add(panelMain);
		panelContainer.add(panelSub);
		panelContainerTwo.add(panelImg);
		panelContainerTwo.add(panelContainer);
		listViewWidget.add(panelContainerTwo);
		MockComponentsUtil.setWidgetBackgroundColor(listViewWidget,backgroundColor);
	}
	
	private void setAttributes(){
		WIDTH = 310;	//To be Internationalized
	}
	
	private void fillDataWhenEmpty(int type){
		listViewWidget.clear();
		int height =0, i=0;
		if(type == 1){
			while(height<450){
				listViewTypeOne("This is Main Text "+(i+1));
				i++;
				height = height + 40;
			}
		}
		else if(type == 2){
			while(height<450){
				listViewTypeTwo("This is Main Text "+(i+1),"This is Sub Text "+(i+1));
				i++;
				height = height + 40;
			}
		}
		else if(type == 3){
			while(height<450){
				listViewTypeThree("This is Main Text "+(i+1),null);
				i++;
				height = height + 40;
			}
		}
		else if(type == 4){
			while(height<450){
				listViewTypeFour("This is Main Text "+(i+1),"This is Sub Text "+(i+1),null);
				i++;
				height = height + 40;
			}
		}
	}

	private void fillData(int type){
		listViewWidget.clear();
		if(type == 1){
			for(int i=0;i<numberOfMainElements;i++){
				listViewTypeOne(mainElements[i]);
			}
		}
		else if(type == 2){
			for(int i=0;i<(numberOfMainElements<=numberOfSubElements?numberOfMainElements:numberOfSubElements);i++){
				listViewTypeTwo(mainElements[i],subElements[i]);
			}
		}
		else if(type == 3){
			for(int i=0;i<(numberOfMainElements<=numberOfPictureElements?numberOfMainElements:numberOfPictureElements);i++){
				listViewTypeThree(mainElements[i],pictureElements[i]);
			}
		}
		else if(type == 4){
			int temporary = numberOfMainElements <= numberOfSubElements ? numberOfMainElements : numberOfSubElements;
			for(int i=0;i<(numberOfPictureElements <= temporary ? numberOfPictureElements : temporary);i++){
				listViewTypeFour(mainElements[i],subElements[i],pictureElements[i]);
			}
		}
	}

	private void setMainElements(String string){
		String[] strings = string.split(",");
		mainElements = strings;
		numberOfMainElements = strings.length;
		if(numberOfMainElements == 1 && mainElements[0] == ""){
			numberOfMainElements = 0;
		}
		changeListViewTypeProperty(type + "");
	}

	private void setSubElements(String string){
		String[] strings = string.split(",");
		subElements = strings;
		numberOfSubElements = strings.length;
		if(numberOfSubElements == 1 && subElements[0] == ""){
			numberOfSubElements = 0;
		}
		changeListViewTypeProperty(type + "");
	}
	
	private void changeListViewTypeProperty(String value){
		if(value == "1"){
			type = 1;
			if(numberOfMainElements == 0){
				fillDataWhenEmpty(type);
			}
			else{
				fillData(type);
			}
		}
		else if(value == "2"){
			type = 2;
			if(numberOfSubElements == 0 || numberOfMainElements == 0){
				fillDataWhenEmpty(type);
			}
			else{
				fillData(type);
			}
		}
		else if(value == "3"){
			type = 3;
			if(numberOfMainElements == 0 || numberOfPictureElements == 0){
				fillDataWhenEmpty(type);
			}
			else {
				fillData(type);
			}
		}
		else if(value == "4"){
			type = 4;
			if(numberOfMainElements == 0 || numberOfSubElements == 0 || numberOfPictureElements == 0){
				fillDataWhenEmpty(type);
			}
			else {
				fillData(type);
			}
		}
	}

	private void setMainTextColor(String color){
		mainTextColor = color;
		changeListViewTypeProperty(type + "");
	}

	private void setSubTextColor(String color){
		subTextColor = color;
		changeListViewTypeProperty(type + "");
	}

	private void setBackgroundColor(String color){
		backgroundColor = color;
		changeListViewTypeProperty(type + "");
	}

	private void setMainTextFontSize(String size){
		mainTextSize = size == "" ? MAIN_TEXT_SIZE_DEFAULT : size;
		changeListViewTypeProperty(type + "");
	}

	private void setSubTextFontSize(String size){
		subTextSize = size == "" ? SUB_TEXT_SIZE_DEFAULT : size;
		changeListViewTypeProperty(type + "");
	}

	private void setPicture(String value){
		numberOfPictureElements = 0;
		String[] fileNames = value.split("%picNum:");
		for(String x:fileNames){
			OdeLog.log("X :: "+x);
		}
		String[] temporary = new String[fileNames.length];
		for(int i=0;i<fileNames.length;i++){
			String[] temp = fileNames[i].split("%picName:");
			String index = temp[0];
			String fileName = temp[1];
			if(fileName != null){
				temporary[i] = convertImagePropertyValueToUrl(fileName);
				numberOfPictureElements++;
			}
		}
		pictureElements = temporary;
		changeListViewTypeProperty(type + "");
	}
	
	@Override
	public void onPropertyChange(String propertyName,String newValue){
		super.onPropertyChange(propertyName,newValue);
		if(propertyName == PROPERTY_NAME_LISTVIEW_TYPE){
			changeListViewTypeProperty(newValue);
			refreshForm();
		}
		else if(propertyName == PROPERTY_NAME_LISTVIEW_ELEMENTS_MAIN){
			setMainElements(newValue);
			refreshForm();
		}
		else if(propertyName == PROPERTY_NAME_LISTVIEW_ELEMENTS_SUB){
			setSubElements(newValue);
			refreshForm();
		}
		else if(propertyName == PROPERTY_NAME_LISTVIEW_TEXT_COLOR_MAIN){
			setMainTextColor(newValue);
			refreshForm();
		}
		else if(propertyName == PROPERTY_NAME_LISTVIEW_TEXT_COLOR_SUB){
			setSubTextColor(newValue);
			refreshForm();
		}
		else if(propertyName == PROPERTY_NAME_LISTVIEW_BACKGROUND_COLOR){
			setBackgroundColor(newValue);
			refreshForm();
		}
		else if(propertyName == PROPERTY_NAME_LISTVIEW_TEXT_SIZE_MAIN){
			setMainTextFontSize(newValue);
			refreshForm();
		}
		else if(propertyName == PROPERTY_NAME_LISTVIEW_TEXT_SIZE_SUB){
			setSubTextFontSize(newValue);
			refreshForm();
		}
		else if(propertyName == PROPERTY_NAME_LISTVIEW_PICTURE_SELECTOR){
			setPicture(newValue);
		}
	}
}
