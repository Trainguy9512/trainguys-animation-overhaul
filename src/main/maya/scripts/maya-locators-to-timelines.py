import maya.cmds as cmds
import math
import json

selection = cmds.ls(selection=True)

startKeyframe = 0
endKeyframe = 100
keysPerFrame = 1

attributes = ['translateX', 'translateY', 'translateZ', 'rotateX', 'rotateY', 'rotateZ']
attributesJava = ['x', 'y', 'z', 'xRot', 'yRot', 'zRot']

animationName = 'test'


# put("leftLeg", ChannelTimeline.floatChannelTimeline().addKeyframe(TransformChannel.x, 0, 2F));

#hashMapName = animationName + 'AnimationTimelines'
#print('public static final HashMap<String, ChannelTimeline<Float>> ' + hashMapName + ' = new HashMap<String, ChannelTimeline<Float>>(){{')

masterDict = {"frame_length": endKeyframe - startKeyframe}
partsList = []

for locator in selection:
    partName = locator.split('_')[0]
    partDict = {"name": partName}
    #baseString = '    put("' + partName + '", ChannelTimeline.floatChannelTimeline()'
    keyframesDict = {}
    for i in range((endKeyframe * keysPerFrame) + 1):
        keyframeDict = {}
        currentTime = i / keysPerFrame
        cmds.currentTime(currentTime)
        for j in range(len(attributes)):
            attributeDict = {}
            value = cmds.getAttr(locator + '.' + attributes[j])
            if(j >= 3):
                value = math.radians(value)
                value = round(value, 5)
            else:
                value = round(value, 3)
            if j == 1 or j == 2 or j == 4 or j == 5:
                value = -value
            baseString += '.addKeyframe(TransformChannel.' + attributesJava[j] + ', ' + str(currentTime) + 'F, ' + str(value) + 'F)'
            
            keyframeDict[attributesJava[j]] = value
        keyframesDict[i] = keyframeDict
    #baseString += ');'
    #print(baseString)
    partDict["keyframes"] = keyframesDict
    partsList.append(partDict)
#print('}};')
masterDict["parts"] = partsList
masterDictJSON = json.dumps(masterDict)
print(masterDictJSON)