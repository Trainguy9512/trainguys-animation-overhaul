import maya.cmds as cmds
import math
import json

WINDOW_TITLE = "Animation Overhaul Exporting"
WINDOW_WIDTH = 400

ROW_SPACING = 2
PADDING = 5

# Utility methods

def addColumnLayout():
    cmds.columnLayout(adjustableColumn=True, columnAttach=('both', PADDING))
    
def addFrameColumnLayout(label, collapsable):
    cmds.frameLayout(collapsable=collapsable, label=label)
    addColumnLayout()

def addInnerRowLayout(numberOfColumns):
    cmds.rowLayout(
        numberOfColumns=numberOfColumns,
        bgc=[0,0,0]
    )

def addDoubleRowLayout():
    cmds.rowLayout(
        numberOfColumns=2, 
        adjustableColumn2=2, 
        columnWidth2=[150, 20],
        columnAlign2=['right', 'left'], 
        columnAttach2=['right', 'left']
    )

def parentToLayout():
    cmds.setParent('..')

def addSpacer():
    cmds.columnLayout(adjustableColumn=True)
    cmds.separator(height=1, style="none")
    parentToLayout()
    
def addHeader(windowTitle):
    addColumnLayout()
    cmds.text(label='<span style=\"color:#ccc;text-decoration:none;font-size:20px;font-family:courier new;font-weight:bold;\">' + windowTitle + '</span>', height=50)
    parentToLayout()
    
def addText(label):
    return cmds.text(label=label)
    
def addButton(label, command):
    cmds.separator(height=ROW_SPACING, style="none")
    controlButton = cmds.button(label=label, command=(command))
    cmds.separator(height=ROW_SPACING, style="none")
    return controlButton
    
def addButtonNoCommand(label):
    cmds.separator(height=ROW_SPACING, style="none")
    controlButton = cmds.button(label=label)
    cmds.separator(height=ROW_SPACING, style="none")
    return controlButton
          
def addIntField():
    return cmds.intFieldGrp()
          
def addIntSlider():
    return cmds.intFieldGrp()
          
# Int Slider
def addIntSliderGroup(min, max, value):
    return cmds.intSliderGrp(field=True, minValue=min, maxValue=max, fieldMinValue=min, fieldMaxValue=max, value=value)
          
# Float Slider
def addFloatSliderGroup(min, max, value):
    return cmds.floatSliderGrp(field=True, minValue=min, maxValue=max, fieldMinValue=min, fieldMaxValue=max, value=value)
    
# Checkbox
def addCheckbox(label):
    return cmds.checkBox(label=label)
    
# Radio Button
def startRadioButtonCollection():
    return cmds.radioCollection()
    
def addRadioButton(label):
    return cmds.radioButton(label=label)
    
# Object Selection List
def searchForJoints(listIdentifier):
    selection = cmds.ls(selection=True)
    currentList = cmds.textScrollList(listIdentifier, query=True, allItems=True)
    
    
    allRigChildren = cmds.listRelatives(selection[0], children=True)
    for obj in allRigChildren:
        if 'export_joints_GRP' in obj:
            exportJoints = cmds.listRelatives(obj, type='joint', allDescendents=True)
            for jnt in exportJoints:
                if not isinstance(currentList, list) or jnt not in currentList:
                    cmds.textScrollList(listIdentifier, edit=True, append=jnt)
    
        
def removeFromObjectSelectionList(listIdentifier):
    listSelection = cmds.textScrollList(listIdentifier, query=True, selectItem=True)
    
    if listSelection != None:
        for listObject in listSelection:
            cmds.textScrollList(listIdentifier, edit=True, removeItem=listObject)
    
def addObjectSelectionList(listIdentifier, label):
    cmds.columnLayout(adjustableColumn=True)
    cmds.rowLayout(columnAttach3=['left', 'left', 'right'], numberOfColumns=3, adjustableColumn=3, columnWidth3=[10, 10, 100])
    cmds.iconTextButton(style='iconOnly', image1='search.png', width=22, height=22, command='searchForJoints("'+listIdentifier+'")')
    cmds.iconTextButton(style='iconOnly', image1='trash.png', width=22, height=22, command='removeFromObjectSelectionList("'+listIdentifier+'")')
    cmds.text(label=label)
    parentToLayout()
    
    scrollList = cmds.textScrollList(listIdentifier, allowMultiSelection=True, height=90)
    parentToLayout()
    return scrollList
                 
# File Browser

# 0     Any file, whether it exists or not.
# 1     A single existing file.
# 2     The name of a directory. Both directories and files are displayed in the dialog.
# 3     The name of a directory. Only directories are displayed in the dialog.
# 4     Then names of one or more existing files.

def browseForDirectory(identifier, mode):
    mode = int(mode)
    path = cmds.fileDialog2(fileMode=mode)
    cmds.textField(identifier, edit=True, text=path[0])

def addFileBrowser(identifier, mode, placeholderText, defaultText):
    cmds.rowLayout(numberOfColumns=2, columnAttach=[(1, 'left', 0), (2, 'right', 0)], adjustableColumn=1, height=22)
    textFieldControl = cmds.textField(identifier, placeholderText=placeholderText, text=defaultText)
    cmds.iconTextButton(style='iconOnly', image1='browseFolder.png', label='spotlight', command='browseForDirectory("'+identifier+'", '+str(mode)+')')
    cmds.setParent("..")
    return textFieldControl;
                       
def deleteIfOpen():  
    if cmds.window('windowObjectAOE', exists=True):
        cmds.deleteUI('windowObjectAOE')
        
def getCloseCommand():
    return('cmds.deleteUI(\"' + 'windowObjectAOE' + '\", window=True)')

def createWindow():
    cmds.window(
        'windowObjectAOE', 
        title=WINDOW_TITLE, 
        width=WINDOW_WIDTH,
        height=50,
        maximizeButton=False
    )
    
    addSpacer()
    addHeader('Animation Overhaul Exporting')
    addSpacer()
    
    addFrameColumnLayout('Settings', False)
    
    addDoubleRowLayout()
    addText('Frame Start')
    controlFrameStart = addIntField()
    parentToLayout()
    
    addDoubleRowLayout()
    addText('Frame End')
    controlFrameEnd = addIntField()
    parentToLayout()
    
    addDoubleRowLayout()
    addText('Entity Key')
    controlEntityKey = cmds.textField(text = 'player')
    parentToLayout()
    
    controlExportJoints = addObjectSelectionList('jointSelector', 'Joints to Export')
    
    addDoubleRowLayout()
    addText('Timeline Group Directory')
    controlTimelineGroupDirectory = addFileBrowser('animationFileBrowser', 2, '', 'C:/Users/train/OneDrive/Desktop/Minecraft Modding/trainguys-animation-overhaul-1.19.x/src/main/resources/assets/animation_overhaul/timelinegroups')
    parentToLayout()
    
    addButton('Export', 'executeScript("'+controlFrameStart+'", "'+controlFrameEnd+'", "'+controlEntityKey+'", "'+controlExportJoints+'", "'+controlTimelineGroupDirectory+'")')
    addButton('Close', getCloseCommand())
    
    parentToLayout()
    parentToLayout()
    parentToLayout()
    addSpacer()
    
    cmds.showWindow('windowObjectAOE')
    
def executeScript(controlFrameStart, controlFrameEnd, controlEntityKey, controlExportJoints, controlTimelineGroupDirectory):
    frameStart = cmds.intFieldGrp(controlFrameStart, query=True, value=True)[0]
    frameEnd = cmds.intFieldGrp(controlFrameEnd, query=True, value=True)[0]
    entityKey = cmds.textField(controlEntityKey, query=True, text=True)
    exportJoints = cmds.textScrollList(controlExportJoints, query=True, allItems=True)
    directory = cmds.textField(controlTimelineGroupDirectory, query=True, text=True)
    
    if isinstance(exportJoints, list):
        if frameEnd > frameStart:
            exportAnimation(frameStart, frameEnd, entityKey, exportJoints, directory)
    
def exportAnimation(frameStart, frameEnd, entityKey, exportJoints, directory):
    
    fileName = cmds.file(query=True, sceneName=True, shortName=True)
    splittedName = fileName.split('_')
    finalName = '_'.join(splittedName[1:len(splittedName)])[0:-3]
    animationKey = finalName
   
    
    attributes = ['translateX', 'translateY', 'translateZ', 'rotateX', 'rotateY', 'rotateZ']
    attributesJava = ['x', 'y', 'z', 'xRot', 'yRot', 'zRot']
    
    masterDict = {"format_version": '0.2', "frame_length": frameEnd - frameStart}
    partsList = []
    
    for joint in exportJoints:
        partName = joint.split(':')[1].split('_')[0]
        #partName = joint.split('_')[0]
        partDict = {"name": partName}
        keyframesDict = {}
        for i in range((frameEnd * 1) + 1):
            keyframeDict = {}
            currentTime = i / 1
            cmds.currentTime(currentTime)
            
            keyframeDict['translate'] = [0,0,0]
            keyframeDict['rotate'] = [0,0,0]
                
            for j in range(len(attributes)):
                attributeDict = {}
                value = cmds.getAttr(joint + '.' + attributes[j])
                if(j >= 3):
                    value = math.radians(value)
                    value = round(value, 5)
                else:
                    value = round(value, 3)
                if j == 1 or j == 2 or j == 4 or j == 5:
                    value = -value
                
                
                if(j >= 3):
                    #rotate
                    keyframeDict['rotate'][j-3] = value
                else:
                    #translate
                    keyframeDict['translate'][j] = value
                    
            keyframesDict[i] = keyframeDict
        partDict["keyframes"] = keyframesDict
        partsList.append(partDict)
    masterDict["parts"] = partsList
    masterDictJSON = json.dumps(masterDict)

    directory += '/' + entityKey

    if not os.path.exists(directory):
        os.makedirs(directory)
        
    directory += '/' + animationKey + '.json'
    
    with open(directory, 'w') as outfile:
        json.dump(masterDict, outfile)
    
    print("Saved animation " + animationKey + " for entity " + entityKey + " with " + str(len(exportJoints)) + " parts")
    
deleteIfOpen()
createWindow()