package com.google.appinventor.components.runtime;


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
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

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
			defaultValue = Component.LISTVIEW_TYPE_DEFAULT+"")
	@SimpleProperty(description = "Specifies the Type of Listview. It can be a Simple List or a Complex Collection of Images and Text",
			userVisible = false)
	public void ListViewType(int type){
			fillDataWhenEmpty(type);
	}
	
	
	private void fillDataWhenEmpty(int type){
		String s,s1;
		listViewLayout.removeAllViews();
		if(type == 1){
			for(int i=0;i<12;i++){
				s = "This is Main Text" + (i+1);
				populateListView(type,s,null,i);
			}
		}
		else if(type == 2){
			for(int i=0;i<12;i++){
				s = "This is Main Text " + (i+1);
				s1 = "This is Sub Text " + (i+1);
				populateListView(type,s,s1,i);
			}
		}
	}
	
	private void populateListView(int type,String main, String sub,int position){
		if(type == 1){
			TextView tv = new TextView(container.$context());
			tv.setText(main);
			tv.setWidth(310);
			tv.setHeight(50);
			tv.setGravity(Gravity.CENTER_VERTICAL);
			View line = new View(container.$context());
			line.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,1));
			line.setBackgroundColor(COLOR_GRAY);
			listViewLayout.addView(tv, position*2);
			listViewLayout.addView(line,position*2+1);
		}
		else if(type == 2){
			TextView tv = new TextView(container.$context());
			TextView tv2 = new TextView(container.$context());
			tv.setText(main);
			tv.setWidth(310);
			tv.setHeight(25);
			tv.setGravity(Gravity.CENTER_VERTICAL);
			tv.setTextColor(0XFF000000);
			tv2.setText(sub);
			tv2.setWidth(310);
			tv2.setHeight(25);
			tv2.setGravity(Gravity.CENTER_VERTICAL);
			tv2.setTextSize(10);
			View line = new View(container.$context());
			line.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,1));
			line.setBackgroundColor(COLOR_GRAY);
			listViewLayout.addView(tv, position*3);
			listViewLayout.addView(tv2,position*3+1);
			listViewLayout.addView(line,position*3+2);
		}
	}
}
