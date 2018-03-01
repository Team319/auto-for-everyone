# LabVIEW auto examples
Author: Max Westwater - 5254

Implementation examples are shown. For the AutoDrive function things are broken down into the code that goes in both 
Begin.vi and 'Autonomous Independent.vi'. There are also two examples in the implementation example file which are the 
defaults from NI for both normal and command and control. All methods should work. 5254 used the code style of AutoDrive.vi

AutoDrive.vi 
- Can simply be dragged into Autonomous Independent.vi
- Set parameters for function
  - String of the robot drive motor refnum name (taken from Begin.vi)
  - Time (in ms) to wait before driving
  - Speed to drive at 
  - Time (in ms) to drive forward
