package com.google.appinventor.components.runtime;


import android.content.res.AssetManager;
import android.widget.*;
import android.widget.LinearLayout;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.common.YaVersion;

import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

@DesignerComponent(version = YaVersion.NEW_LISTVIEW_COMPONENT_VERSION,
description = "<p>This is a visible component that displays a list of text elements." +
    " <br> The list can be set using the ElementsFromString property" +
    " or using the Elements block in the blocks editor. </p>",
category = ComponentCategory.USERINTERFACE,
nonVisible = false,
iconName = "images/ball.png")
@SimpleObject
public final class ListView2 extends AndroidViewComponent{

	private final ComponentContainer container;
	private final ScrollView listViewContainer;
	private final LinearLayout listViewLayout,layout;

	private final int TEXT_COLOR_DEFAULT = 0xFF000000;
	private int textColor;
	
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
		fillDataWhenEmpty(LISTVIEW_TYPE_ONE);
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

		fillDataWhenEmpty(type);
	}
	
	
	private void fillDataWhenEmpty(int type){
		String s,s1;
		listViewLayout.removeAllViews();
		if(type == LISTVIEW_TYPE_ONE){
			for(int i=0;i<12;i++){
				s = "This is Main Text" + (i+1);
				populateListView(type, s, null, i);
			}
		}
		else if(type == LISTVIEW_TYPE_TWO){
			for(int i=0;i<12;i++){
				s = "This is Main Text " + (i+1);
				s1 = "This is Sub Text " + (i+1);
				populateListView(type, s, s1, i);
			}
		}
		else if(type == LISTVIEW_TYPE_THREE){
			for(int i=0;i<12;i++) {
				s = "This is Main Text " + (i + 1);
				populateListView(type, s, null, i);
			}
		}
		else if(type == LISTVIEW_TYPE_FOUR){
			for(int i=0;i<12;i++) {
				s = "This is Main Text " + (i + 1);
				s1 = "This is Sub Text " + (i + 1);
				populateListView(type, s, s1, i);
			}
		}
	}
	
	private void populateListView(int type,String main, String sub,int position){
		if(type == LISTVIEW_TYPE_ONE){
			TextView tv = new TextView(container.$context());
			tv.setText(main);
			tv.setWidth(310);
			tv.setHeight(50);
			tv.setGravity(Gravity.CENTER_VERTICAL);
			tv.setTextColor(textColor);
			View line = new View(container.$context());
			line.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,1));
			line.setBackgroundColor(COLOR_GRAY);
			RelativeLayout listItemContainer = new RelativeLayout(container.$context());
			listItemContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,51));
			listItemContainer.addView(tv);
			listItemContainer.addView(line);
			listItemContainer.setTag("LV T1 ITEM "+(position+1));
			listViewLayout.addView(listItemContainer);
		}
		else if(type == LISTVIEW_TYPE_TWO){
			TextView tv = new TextView(container.$context());
			TextView tv2 = new TextView(container.$context());
			tv.setText(main);
			tv.setWidth(310);
			tv.setHeight(25);
			tv.setGravity(Gravity.CENTER_VERTICAL);
			tv.setTextColor(textColor);
			tv2.setText(sub);
			tv2.setWidth(310);
			tv2.setHeight(25);
			tv2.setGravity(Gravity.CENTER_VERTICAL);
			tv2.setTextColor(textColor);
			tv2.setTextSize(10);
			View line = new View(container.$context());
			line.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,1));
			line.setBackgroundColor(COLOR_GRAY);
			LinearLayout listItemContainer = new LinearLayout(container.$context());
			listItemContainer.setOrientation(LAYOUT_ORIENTATION_VERTICAL);
			listItemContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,51));
			listItemContainer.addView(tv);
			listItemContainer.addView(tv2);
			listItemContainer.addView(line);
			listItemContainer.setTag("LV T2 ITEM "+(position+1));
			listViewLayout.addView(listItemContainer);
		}
		else if(type == LISTVIEW_TYPE_THREE){
			LinearLayout listItemContainer = new LinearLayout(container.$context());
			listItemContainer.setOrientation(LAYOUT_ORIENTATION_HORIZONTAL);
			listItemContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,50));
			ImageView image = new ImageView(container.$context());
			image.setLayoutParams(new LayoutParams(50,50));
			image.setBackgroundColor(0x22000000);
			TextView mainText = new TextView(container.$context());
			mainText.setWidth(260);
			mainText.setHeight(50);
			mainText.setGravity(Gravity.CENTER_VERTICAL);
			mainText.setTextColor(textColor);
			mainText.setText(main);
			View line = new View(container.$context());
			line.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,1));
			line.setBackgroundColor(COLOR_GRAY);
			listItemContainer.addView(image);
			listItemContainer.addView(mainText);
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
			ImageView image = new ImageView(container.$context());
			image.setLayoutParams(new LayoutParams(50,50));
			image.setBackgroundColor(0x22000000);
			TextView mainText = new TextView(container.$context());
			mainText.setWidth(260);
			mainText.setHeight(25);
			mainText.setGravity(Gravity.CENTER_VERTICAL);
			mainText.setTextColor(textColor);
			mainText.setText(main);
			TextView subText = new TextView(container.$context());
			subText.setWidth(260);
			subText.setHeight(25);
			subText.setGravity(Gravity.CENTER_VERTICAL);
			subText.setTextColor(textColor);
			subText.setText(sub);
            subText.setTextSize(10);
			View line = new View(container.$context());
			line.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,1));
			line.setBackgroundColor(COLOR_GRAY);
			listItemSubContainer.addView(mainText);
			listItemSubContainer.addView(subText);
			listItemContainer.addView(image);
			listItemContainer.addView(listItemSubContainer);
			listItemContainer.addView(line);
			listViewLayout.addView(listItemContainer);
		}
	}
}
