package com.google.appinventor.client.editor.simple.components;

import com.google.appinventor.client.Ode;
import com.google.appinventor.client.editor.simple.SimpleEditor;
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
	
	private int WIDTH;
	
	
	public MockListView2(SimpleEditor editor) {
		super(editor,TYPE,images.ball());
		listViewWidget = new VerticalPanel();
		listViewWidget.setSize(315+"px","100%");
		listViewWidget.setTitle("ListViewAdvanced");
		listViewWidget.setStylePrimaryName("ode-SimpleMockComponent");
		listViewWidget.setStyleName("listViewComponentStyle",true);
		initComponent(listViewWidget);
		MockComponentsUtil.setWidgetBackgroundColor(listViewWidget, "&HFFFFFFFF");
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
		labelMain.setWidth(WIDTH+"px");
		panelMain.add(labelMain);
		listViewWidget.add(panelMain);
	}
	
	private void listViewTypeTwo(String mainText,String subText){
		panelContainer = new VerticalPanel();
		panelContainer.setStyleName("listViewItemContainer");
		panelMain = new SimplePanel();
		panelMain.setSize(ComponentConstants.LISTVIEW_PREFERRED_WIDTH+"px",ComponentConstants.LISTVIEW_PREFERRED_HEIGHT-20+"px");
		panelMain.setStyleName("listViewItem");
		labelMain = new InlineLabel(mainText);
		panelMain.add(labelMain);
		panelSub = new SimplePanel();
		panelSub.setSize(ComponentConstants.LISTVIEW_PREFERRED_WIDTH+"px",ComponentConstants.LISTVIEW_PREFERRED_HEIGHT-20+"px");
		labelSub = new InlineLabel(subText);
		panelSub.add(labelSub);
		panelSub.setStyleName("listViewItemSmall");
		panelContainer.add(panelMain);
		panelContainer.add(panelSub);
		listViewWidget.add(panelContainer);
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
		labelMain.setStyleName("listViewItem");
		panelMain.add(labelMain);
		panelContainerTwo.add(panelMain);
		listViewWidget.add(panelContainerTwo);
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
		panelMain.add(labelMain);
		panelSub = new SimplePanel();
		panelSub.setStyleName("listViewItemSmall");
		panelSub.setSize(ComponentConstants.LISTVIEW_PREFERRED_WIDTH-40+"px",ComponentConstants.LISTVIEW_PREFERRED_HEIGHT-20+"px");
		labelSub = new InlineLabel(subText);
		panelSub.add(labelSub);
		panelContainer.add(panelMain);
		panelContainer.add(panelSub);
		panelContainerTwo.add(panelImg);
		panelContainerTwo.add(panelContainer);
		listViewWidget.add(panelContainerTwo);
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
	
	private void changeListViewTypeProperty(String value){
		if(value == "1"){
			fillDataWhenEmpty(1);
		}
		else if(value == "2"){
			fillDataWhenEmpty(2);
		}
		else if(value == "3"){
			fillDataWhenEmpty(3);
		}
		else if(value == "4"){
			fillDataWhenEmpty(4);
		}
	}
	
	@Override
	public void onPropertyChange(String propertyName,String newValue){
		if(propertyName == PROPERTY_NAME_LISTVIEW_TYPE){
			changeListViewTypeProperty(newValue);
			refreshForm();
		}
	}
}
