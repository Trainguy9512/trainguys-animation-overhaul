import maya.cmds as cmds
import math

selection = cmds.ls(selection=True)

startKeyframe = 0
endKeyframe = 100
keysPerFrame = 1

attributes = ['translateX', 'translateY', 'translateZ', 'rotateX', 'rotateY', 'rotateZ']
attributesJava = ['x', 'y', 'z', 'xRot', 'yRot', 'zRot']

animationName = 'test'


# put("leftLeg", ChannelTimeline.floatChannelTimeline().addKeyframe(TransformChannel.x, 0, 2F));

hashMapName = animationName + 'AnimationTimelines'
print('public static final HashMap<String, ChannelTimeline<Float>> ' + hashMapName + ' = new HashMap<String, ChannelTimeline<Float>>(){{')

for locator in selection:
    partName = locator.split('_')[0]
    
    baseString = '    put("' + partName + '", ChannelTimeline.floatChannelTimeline()'
    
    for i in range(endKeyframe * keysPerFrame):
        currentTime = i / keysPerFrame
        cmds.currentTime(currentTime)
        for j in range(len(attributes)):
            value = cmds.getAttr(locator + '.' + attributes[j])
            if(j >= 3):
                value = math.radians(value)
            if j == 1 or j == 2 or j == 4 or j == 5:
                value = -value
            baseString += '.addKeyframe(TransformChannel.' + attributesJava[j] + ', ' + str(currentTime) + 'F, ' + str(value) + 'F)'
        
    
    baseString += ');'
    print(baseString)

print('}};')