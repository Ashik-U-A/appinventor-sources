package com.google.appinventor.components.runtime;


import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.widget.*;
import android.widget.LinearLayout;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.common.YaVersion;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;
import com.google.appinventor.components.runtime.util.YailList;

import java.io.IOException;

@DesignerComponent(version = YaVersion.NEW_LISTVIEW_COMPONENT_VERSION,
description = "<p>This is a visible component that displays a list of text elements." +
    " <br> The list can be set using the ElementsFromString property" +
    " or using the Elements block in the blocks editor. </p>",
category = ComponentCategory.USERINTERFACE,
nonVisible = false,
iconName = "images/ball.png")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public final class ListView2 extends AndroidViewComponent{

	private final ComponentContainer container;
	private final ScrollView listViewContainer;
	private final LinearLayout listViewLayout,layout;

	private final int TEXT_COLOR_DEFAULT = 0xFF000000;
	private final int BACKGROUND_COLOR_DEFAULT = 0xFFFFFFFF;
	private final int SELECTION_COLOR_DEFAULT = 0xFF111111;
	private final float TEXT_SIZE_MAIN_DEFAULT = 15;
	private final float TEXT_SIZE_SUB_DEFAULT = 10;

	private String[] mainElements;
	private String[] subElements;
	private String[] pictureElements;

	private int numberOfMainElements;
	private int numberOfSubElements;
	private int numberOfPictureElements;
	private int type;

	private float mainTextSize;
	private float subTextSize;
	private int textColor;
	private int textColorSub;
	private int backgroundColor;
	private int selectionColor;

	private String selectedListItemTag;
	private int selection;

	public ListView2(ComponentContainer container) {
		super(container.$form());
		this.container = container;
		layout = new LinearLayout(container.$context());
		listViewContainer = new ScrollView(container.$context());
		listViewLayout = new LinearLayout(container.$context());
		listViewLayout.setOrientation(LAYOUT_ORIENTATION_VERTICAL);
		listViewContainer.addView(listViewLayout);
		layout.addView(listViewContainer);
		container.$add(this);
		textColor = TEXT_COLOR_DEFAULT;
		textColorSub = TEXT_COLOR_DEFAULT;
		mainTextSize = TEXT_SIZE_MAIN_DEFAULT;
		subTextSize = TEXT_SIZE_SUB_DEFAULT;
		backgroundColor = BACKGROUND_COLOR_DEFAULT;
		numberOfMainElements = 0;
		numberOfSubElements = 0;
		numberOfPictureElements = 0;
		type = LISTVIEW_TYPE_ONE;
		fillDataWhenEmpty(type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return layout;
	}
	
	@Override
	@SimpleProperty(description="Changes the Height of the ListView",
			category = PropertyCategory.APPEARANCE)
	public void Height(int height){
		if(height==LENGTH_PREFERRED){
			height=LENGTH_FILL_PARENT;
		}
		super.Height(height);
	}
	
	@Override
	@SimpleProperty(description="Changes the Width of the ListView",
			category = PropertyCategory.APPEARANCE)
	public void Width(int width){
		if(width==LENGTH_PREFERRED){
			width=LENGTH_FILL_PARENT;
		}
		super.Width(width);
	}


	@DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_LISTVIEW_TYPE,
			defaultValue = Component.LISTVIEW_TYPE_ONE+"")
	@SimpleProperty(description = "Specifies the Type of Listview. It can be a Simple List or a Complex Collection of Images and Text",
			userVisible = false)
	public void ListViewType(int type){
		this.type = type;
		Log.d("LISTVIEWTYPE()","Type = "+type);
		if(type == LISTVIEW_TYPE_ONE){
			if(numberOfMainElements == 0){
				fillDataWhenEmpty(type);
			}
			else{
				fillData(type);
			}
		}
		else if(type == LISTVIEW_TYPE_TWO){
			if(numberOfSubElements == 0 || numberOfMainElements == 0){
				fillDataWhenEmpty(type);
			}
			else{
				fillData(type);
			}
		}
		else if(type == LISTVIEW_TYPE_THREE){
			if(numberOfMainElements == 0 || numberOfPictureElements == 0){
				fillDataWhenEmpty(type);
			}
			else{
				fillData(type);
			}
		}
		else if(type == LISTVIEW_TYPE_FOUR){
			if(numberOfMainElements == 0 || numberOfSubElements == 0 || numberOfPictureElements == 0){
				fillDataWhenEmpty(type);
			}
			else fillData(type);
		}
	}
	
	
	private void fillDataWhenEmpty(int type){
		String s,s1;
		listViewLayout.removeAllViews();
		if(type == LISTVIEW_TYPE_ONE){
			for(int i=0;i<12;i++){
				s = "This is Main Text" + (i+1);
				populateListView(type, s, null, null, i);
			}
		}
		else if(type == LISTVIEW_TYPE_TWO){
			for(int i=0;i<12;i++){
				s = "This is Main Text " + (i+1);
				s1 = "This is Sub Text " + (i+1);
				populateListView(type, s, s1, null, i);
			}
		}
		else if(type == LISTVIEW_TYPE_THREE){
			for(int i=0;i<12;i++) {
				s = "This is Main Text " + (i + 1);
				populateListView(type, s, null, null, i);
			}
		}
		else if(type == LISTVIEW_TYPE_FOUR){
			for(int i=0;i<12;i++) {
				s = "This is Main Text " + (i + 1);
				s1 = "This is Sub Text " + (i + 1);
				populateListView(type, s, s1, null, i);
			}
		}
	}

	private void fillData(int type){
		listViewLayout.removeAllViews();
		if(type == LISTVIEW_TYPE_ONE){
			for(int i=0;i<numberOfMainElements;i++){
				populateListView(type, mainElements[i], null, null, i);
			}
		}
		else if(type == LISTVIEW_TYPE_TWO){
			for(int i=0;i<(numberOfMainElements<=numberOfSubElements?numberOfMainElements:numberOfSubElements);i++){
				populateListView(type, mainElements[i], subElements[i], null, i);
			}
		}
		else if(type == LISTVIEW_TYPE_THREE){
			for(int i=0;i<(numberOfMainElements<=numberOfPictureElements?numberOfMainElements:numberOfPictureElements);i++){
				populateListView(type, mainElements[i], null, pictureElements[i], i);
			}
		}
		else if(type == LISTVIEW_TYPE_FOUR){
			int temp = numberOfMainElements<=numberOfSubElements?numberOfMainElements:numberOfSubElements;
			for(int i=0;i<(numberOfPictureElements<=temp?numberOfPictureElements:temp);i++){
				populateListView(type, mainElements[i], subElements[i], pictureElements[i], i);
			}
		}
	}
	
	private void populateListView(int type,String main, String sub, String url, int position){
		if(type == LISTVIEW_TYPE_ONE){
			TextView mainText = new TextView(container.$context());
			mainText.setText(main);
			mainText.setWidth(310);
			mainText.setHeight(50);
			mainText.setGravity(Gravity.CENTER_VERTICAL);
			mainText.setTextColor(textColor);
			mainText.setTextSize(mainTextSize);
			View line = new View(container.$context());
			line.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,1));
			line.setBackgroundColor(COLOR_GRAY);
			final LinearLayout listItemContainer = new LinearLayout(container.$context());
			listItemContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,51));
			listItemContainer.addView(mainText);
			listItemContainer.setBackgroundColor(backgroundColor);
			listItemContainer.setTag("LV 1 ITEM _"+(position+1));
			listItemContainer.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					selectedListItemTag = view.getTag().toString();
					selection = Integer.parseInt(view.getTag().toString().split("_")[1]);
					view.setBackgroundColor(selectionColor);
					revertBack(view);
				}
			});
			listViewLayout.addView(listItemContainer);
			listViewLayout.addView(line);
		}
		else if(type == LISTVIEW_TYPE_TWO){
			TextView mainText = new TextView(container.$context());
			TextView subText = new TextView(container.$context());
			mainText.setText(main);
			mainText.setWidth(310);
			mainText.setHeight(25);
			mainText.setGravity(Gravity.CENTER_VERTICAL);
			mainText.setTextColor(textColor);
			mainText.setTextSize(mainTextSize);
			subText.setText(sub);
			subText.setWidth(310);
			subText.setHeight(25);
			subText.setGravity(Gravity.CENTER_VERTICAL);
			subText.setTextColor(textColorSub);
			subText.setTextSize(subTextSize);
			View line = new View(container.$context());
			line.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,1));
			line.setBackgroundColor(COLOR_GRAY);
			LinearLayout listItemContainer = new LinearLayout(container.$context());
			listItemContainer.setOrientation(LAYOUT_ORIENTATION_VERTICAL);
			listItemContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,51));
			listItemContainer.addView(mainText);
			listItemContainer.addView(subText);
			listItemContainer.setBackgroundColor(backgroundColor);
			listItemContainer.setTag("LV 2 ITEM _"+(position+1));
			listViewLayout.addView(listItemContainer);
			listViewLayout.addView(line);
		}
		else if(type == LISTVIEW_TYPE_THREE){
			LinearLayout listItemContainer = new LinearLayout(container.$context());
			listItemContainer.setOrientation(LAYOUT_ORIENTATION_HORIZONTAL);
			listItemContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,50));
			Drawable drawable;
			try{
				drawable = MediaUtil.getBitmapDrawable(container.$form(),url);
			}
			catch(IOException e){
				Log.e("IMAGE NOT LOADED","Cant find the file");
				drawable = null;
			}
			ImageView image = new ImageView(container.$context());
			image.setLayoutParams(new LayoutParams(50,50));
			if(drawable == null){
				image.setBackgroundColor(0x22000000);
			}
			else{
				ViewUtil.setImage(image,drawable);
			}
			TextView mainText = new TextView(container.$context());
			mainText.setWidth(260);
			mainText.setHeight(50);
			mainText.setGravity(Gravity.CENTER_VERTICAL);
			mainText.setTextColor(textColor);
			mainText.setTextSize(mainTextSize);
			mainText.setText(main);
			View line = new View(container.$context());
			line.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,1));
			line.setBackgroundColor(COLOR_GRAY);
			listItemContainer.addView(image);
			listItemContainer.addView(mainText);
			listItemContainer.setBackgroundColor(backgroundColor);
			listItemContainer.setTag("LV 3 ITEM _"+(position+1));
			listViewLayout.addView(listItemContainer);
			listViewLayout.addView(line);
		}
		else if(type == LISTVIEW_TYPE_FOUR){
			LinearLayout listItemContainer = new LinearLayout(container.$context());
			listItemContainer.setOrientation(LAYOUT_ORIENTATION_HORIZONTAL);
			listItemContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,50));
			LinearLayout listItemSubContainer = new LinearLayout(container.$context());
			listItemSubContainer.setOrientation(LAYOUT_ORIENTATION_VERTICAL);
			listItemSubContainer.setLayoutParams(new LayoutParams(260,50));
			Drawable drawable;
			try{
				drawable = MediaUtil.getBitmapDrawable(container.$form(),url);
			}
			catch(IOException e){
				Log.e("IMAGE NOT LOADED","Cant find the file");
				drawable = null;
			}
			ImageView image = new ImageView(container.$context());
			image.setLayoutParams(new LayoutParams(50,50));
			if(drawable == null){
				image.setBackgroundColor(0x22000000);
			}
			else{
				ViewUtil.setImage(image,drawable);
			}
			TextView mainText = new TextView(container.$context());
			mainText.setWidth(260);
			mainText.setHeight(25);
			mainText.setGravity(Gravity.CENTER_VERTICAL);
			mainText.setTextColor(textColor);
			mainText.setTextSize(mainTextSize);
			mainText.setText(main);
			TextView subText = new TextView(container.$context());
			subText.setWidth(260);
			subText.setHeight(25);
			subText.setGravity(Gravity.CENTER_VERTICAL);
			subText.setTextColor(textColorSub);
			subText.setText(sub);
            subText.setTextSize(subTextSize);
			View line = new View(container.$context());
			line.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,1));
			line.setBackgroundColor(COLOR_GRAY);
			listItemSubContainer.addView(mainText);
			listItemSubContainer.addView(subText);
			listItemContainer.addView(image);
			listItemContainer.addView(listItemSubContainer);
			listItemContainer.setBackgroundColor(backgroundColor);
			listItemContainer.setTag("LV 4 ITEM _"+(position+1));
			listViewLayout.addView(listItemContainer);
			listViewLayout.addView(line);
		}
	}

	private void revertBack(final View view){
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				view.setBackgroundColor(backgroundColor);
			}
		},1500);
		OnSelection();
	}

	@DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING,
		defaultValue = "")
	@SimpleProperty
	public void Elements_MainString(String string){
		String[] strings = string.split(",");
		mainElements = strings;
		numberOfMainElements = strings.length;
		if(numberOfMainElements == 1 && mainElements[0] == ""){
			numberOfMainElements = 0;
		}
		Log.d("ELEMENTS_MAINSTRING()","numberOfMainElements = "+numberOfMainElements);
		ListViewType(type);
	}

	/*@SimpleProperty(category = PropertyCategory.BEHAVIOR)
	public YailList Elements_MainString(){
		return YailList.makeList(mainElements);
	}*/

	@DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING,
		defaultValue = "")
	@SimpleProperty
	public void Elements_SubString(String string){
		String[] strings = string.split(",");
		subElements = strings;
		numberOfSubElements = strings.length;
		if(numberOfSubElements == 1 && subElements[0] == ""){
			numberOfSubElements = 0;
		}
		Log.d("ELEMENTS_SUBSTRING()","numberOfSubElements = "+numberOfSubElements);
		ListViewType(type);
	}

	/*@SimpleProperty(category = PropertyCategory.BEHAVIOR)
	public YailList Elements_SubString(){
		return YailList.makeList(subElements);
	}*/

	@SimpleProperty(category = PropertyCategory.BEHAVIOR)
	public int Color_MainText(){
		return textColor;
	}

	@DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR,
		defaultValue = Component.COLOR_BLACK + "")
	@SimpleProperty
	public void Color_MainText(int color){
		textColor = color;
		ListViewType(type);
	}

	@SimpleProperty(category = PropertyCategory.BEHAVIOR)
	public int Color_SubText(){ return textColorSub; }

	@DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR,
		defaultValue = Component.COLOR_BLACK + "")
	@SimpleProperty
	public void Color_SubText(int color){
		textColorSub = color;
		ListViewType(type);
	}

	@SimpleProperty(category = PropertyCategory.BEHAVIOR)
	public int Color_Background(){

		return backgroundColor;
	}

	@DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR,
		defaultValue = Component.COLOR_WHITE + "")
	@SimpleProperty
	public void Color_Background(int color){
		backgroundColor = color;
		ListViewType(type);
	}

	@SimpleProperty(category = PropertyCategory.BEHAVIOR)
	public String FontSize_MainText(){

		return (mainTextSize + "");
	}

	@DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING,
		defaultValue = "15")
	@SimpleProperty
	public void FontSize_MainText(String size){
		mainTextSize = (float) Integer.parseInt(size == "" ? mainTextSize + "" : size);
		ListViewType(type);
	}

	@SimpleProperty(category = PropertyCategory.BEHAVIOR)
	public String FontSize_SubText(){
		return (subTextSize + "");
	}

	@DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING,
		defaultValue = "10")
	@SimpleProperty
	public void FontSize_SubText(String size){
		subTextSize = (float) Integer.parseInt(size == "" ? subTextSize + "" : size);
		ListViewType(type);
	}

	@SimpleEvent
	public void OnSelection(){
		EventDispatcher.dispatchEvent(this,"OnSelection");
	}

	@DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_LISTVIEW_PICTURE_SELECTOR,
		defaultValue = "")
	@SimpleProperty(userVisible = false)
	public void Picture_Select(String value){
		numberOfPictureElements = 0;
		String[] splitter = value.split("%picNum:");
		String[] temporary = new String[splitter.length];
		for(int i=0;i<splitter.length;i++){
			String[] temp = splitter[i].split("%picName:");
			String index = temp[0];
			String fileName = temp[1];
			if(fileName != null){
				temporary[numberOfPictureElements] = fileName;
				numberOfPictureElements++;
			}
		}
		for(int i=0;i<numberOfPictureElements;i++){
			Log.v("IMAGE : ",i + ". " + temporary[i]);
		}
		Log.v("NUMBER OF PICTURE ELEMENTS: ",numberOfPictureElements + "");
		pictureElements = temporary;
		ListViewType(type);
	}
}
