# Slick2D-Mario-Clone
A Mario clone created in java with Slick2D

# LIBRARY DOCUMENTATION:

http://slick.ninjacave.com/javadoc/overview-summary.html

# INSTALLATION INSTRUCTIONS (FOR INTELLIJ)

1) Adding the slick jars
----------------------------
a) Download the Full Distribution of "Slick" and extract it
b) Start IntelliJ 9.0, start a project and go to your 'Project Structure' (Ctrl+Alt+Shift+S)
c) Under the 'Project Settings' select 'Libraries'
d) Click on the plus sign on top, give the library a name (ex. 'Slick')
e) On the right click 'Attach Classes...'
f) Go to where you extracted the slick-files and add the lwjgl.jar and slick.jar that you find in the root directory of the repo

2) Adjusting VM parameters
---------------------------------
a) Open the 'Run/Debug Configurations' (Run > Edit Configurations)
b) Under 'VM Parameters' add: "-Djava.library.path=lib/natives-win32" (or -linux, or -mac) without the quotes
