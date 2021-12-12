import maya.cmds as cmds
import math
import json

selection = cmds.ls(selection=True)

startKeyframe = 0
endKeyframe = 40
keysPerFrame = 1
entityKey = "player"
animationKey = "dummy"

fileName = cmds.file(query=True, sceneName=True, shortName=True)
splittedName = fileName.split('_')
finalName = '_'.join(splittedName[1:len(splittedName)])[0:-3]
animationKey = finalName

attributes = ['translateX', 'translateY', 'translateZ', 'rotateX', 'rotateY', 'rotateZ']
attributesJava = ['x', 'y', 'z', 'xRot', 'yRot', 'zRot']

#animationName = 'walk_normal'


# put("leftLeg", ChannelTimeline.floatChannelTimeline().addKeyframe(TransformChannel.x, 0, 2F));

#hashMapName = animationName + 'AnimationTimelines'
#print('public static final HashMap<String, ChannelTimeline<Float>> ' + hashMapName + ' = new HashMap<String, ChannelTimeline<Float>>(){{')

masterDict = {"frame_length": endKeyframe - startKeyframe}
partsList = []

for locator in selection:
    partName = locator.split(':')[1].split('_')[0]
    partDict = {"name": partName}
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
            
            keyframeDict[attributesJava[j]] = value
        keyframesDict[i] = keyframeDict
    partDict["keyframes"] = keyframesDict
    partsList.append(partDict)
masterDict["parts"] = partsList
masterDictJSON = json.dumps(masterDict)
print(masterDictJSON)

filePath = 'C:/Users/Trainguy/Desktop/Minecraft Modding/trainguys-clientside-tweaks/src/main/resources/assets/animationoverhaul/animations/' + entityKey + '/'

if not os.path.exists(filePath):
    os.makedirs(filePath)
    
filePath += animationKey + '.json'

with open(filePath, 'w') as outfile:
    json.dump(masterDict, outfile)

print("Saved animation " + animationKey + " for entity " + entityKey + " with " + str(len(selection)) + " parts")

#f = open(filePath, 'wb')
#f.write(masterDictJSON)