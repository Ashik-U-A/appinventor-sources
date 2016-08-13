package com.google.appinventor.client.editor.youngandroid.properties;


import com.google.appinventor.client.Ode;
import com.google.appinventor.client.editor.youngandroid.YaFormEditor;
import com.google.appinventor.client.explorer.project.Project;
import com.google.appinventor.client.explorer.project.ProjectChangeListener;
import com.google.appinventor.client.output.OdeLog;
import com.google.appinventor.client.widgets.properties.AdditionalChoicePropertyEditor;
import com.google.appinventor.client.wizards.FileUploadWizard;
import com.google.appinventor.shared.rpc.project.FileNode;
import com.google.appinventor.shared.rpc.project.FolderNode;
import com.google.appinventor.shared.rpc.project.ProjectNode;
import com.google.appinventor.shared.rpc.project.youngandroid.YoungAndroidAssetsFolder;
import com.google.appinventor.shared.rpc.project.youngandroid.YoungAndroidProjectNode;
import com.google.appinventor.shared.storage.StorageUtil;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Created by ashik on 12/8/16.
 */
public class YoungAndroidListViewPictureSelectorPropertyEditor extends AdditionalChoicePropertyEditor
        implements ProjectChangeListener{

    private final YoungAndroidAssetsFolder assetsFolder;
    private String fileAggregate = "";
    private boolean hasValue = false;

    public YoungAndroidListViewPictureSelectorPropertyEditor(final YaFormEditor editor){
        Project project = Ode.getInstance().getProjectManager().getProject(editor.getProjectId());
        assetsFolder = ((YoungAndroidProjectNode) project.getRootNode()).getAssetsFolder();
        project.addProjectChangeListener(this);

        VerticalPanel pictureContainer = new VerticalPanel();
        HorizontalPanel pictureSubContainer = null;
        for(int i=0;i<12;i++){
            final Image image = new Image();
            image.setSize("40px","40px");
            image.setStylePrimaryName("ode-SimpleMockComponent");
            image.setUrl(Ode.getImageBundle().image().getURL());
            final int finalI = i;

            image.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent clickEvent) {
                    FileUploadWizard.FileUploadedCallback callback = new FileUploadWizard.FileUploadedCallback() {
                        @Override
                        public void onFileUploaded(FolderNode folderNode, FileNode fileNode) {
                            image.setUrl(StorageUtil.getFileUrl(fileNode.getProjectId(),fileNode.getFileId()));
                            OdeLog.log("FILE NAME: "+fileNode.getFullName());
                            boolean replaceFlag = false;

                            if(fileAggregate == ""){
                                fileAggregate = fileAggregate + finalI + "%picName:" + fileNode.getName() + "%picNum:";
                            }

                            String[] splitter = fileAggregate.split("%picNum:");
                            int[] picNumber = new int[splitter.length];
                            String[] picName = new String[splitter.length];
                            for(int i=0;i<splitter.length;i++){
                                String[] temp = splitter[i].split("%picName:");
                                picNumber[i] = Integer.parseInt(temp[0]);
                                picName[i] = temp[1];
                            }
                            for(int i=0;i<splitter.length;i++){
                                if(picNumber[i] == finalI){
                                    picName[i] = fileNode.getName();
                                    replaceFlag = true;
                                    break;
                                }
                            }
                            if(replaceFlag == false){
                                fileAggregate = fileAggregate + finalI + "%picName:" + fileNode.getName() + "%picNum:";
                            }
                            else{
                                fileAggregate = "";
                                for(int i=0;i<splitter.length;i++){
                                    fileAggregate = fileAggregate + picNumber[i] + "%picName:" + picName[i] + "%picNum:";
                                }
                            }
                            hasValue = true;
                            closeAdditionalChoiceDialog(true);
                        }
                    };
                    FileUploadWizard uploader = new FileUploadWizard(assetsFolder, callback);
                    uploader.show();
                }
            });
            if(i % 3 == 0){
                pictureSubContainer= new HorizontalPanel();
                pictureContainer.add(pictureSubContainer);
                pictureSubContainer.add(image);
            }
            else{
                pictureSubContainer.add(image);
            }
        }
        pictureContainer.setWidth("100%");

        DeferredCommand.addCommand(new Command() {
            @Override
            public void execute() {
                if (editor.isLoadComplete()) {
                    finishInitialization();
                } else {
                    // Editor still hasn't finished loading.
                    DeferredCommand.addCommand(this);
                }
            }
        });

        initAdditionalChoicePanel(pictureContainer);
    }

    public void finishInitialization(){
        String value = property.getValue();
        if(value == "None" || hasValue == false){
            property.setValue("");
        }
    }

    @Override
    protected boolean okAction() {
        if(hasValue == true)
        property.setValue(fileAggregate);
        else property.setValue("");
        return true;
    }


    @Override
    public void onProjectLoaded(Project project) {

    }

    @Override
    public void onProjectNodeAdded(Project project, ProjectNode node) {

    }

    @Override
    public void onProjectNodeRemoved(Project project, ProjectNode node) {

    }
}
