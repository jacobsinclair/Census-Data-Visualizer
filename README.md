# JailCensusData-Visualizer
An interactive visualization depicting census data from jails in Allegheny County, Pennsylvania.
I used Object-Oriented program design to create a visualization that is easily accessible by users 
and can be queried by inputted demographics. The program can be adjusted for various sizes of data sets. In my implementation I used sets ranging from 10 inmates all the way to 2600+ inmates.


# Requirements to run
* Because JavaFX isn't compatible with some JDK and SDK versions, you must have a compatible version: 17, 18, 18.0.1 or newer should work
* This program must be compiled and run differenly because it uses JavaFX.
* The file path you use to run should match the directory you save the files to.

*Instead of javac filename.java and java filename, use the following--


MAC:
* javac --module-path /Users/(your username here)/Downloads/javafx-(sdk or jdk)-(version of sdk or jdk that you have)/lib --add-modules javafx.controls JailCensusDisplayJPS.java
* java --module-path /Users/(your username here)/Downloads/javafx-(sdk or jdk)-(version of sdk or jdk that you have/lib --add-modules javafx.controls JailCensusDisplayJPS

  
WINDOWS:
* javac --module-path %PATH_TO_FX% --add-modules javafx.controls JailCensusDisplayJPS.java
java --module-path %PATH_TO_FX% --add-modules javafx.controls JailCensusDisplayJPS



# Images of various datasets working in the application




A large Dataset containing over 2600 inmates:

<img width="501" alt="Screenshot 2023-11-27 at 8 31 29 PM" src="https://github.com/jacobsinclair/JailCensusData-Visualizer/assets/134180713/174f76c8-25e1-462b-9561-720af2f4c4e2">





The large Dataset queried by male and female gender respectively:

<img width="500" alt="Screenshot 2023-11-27 at 8 34 38 PM" src="https://github.com/jacobsinclair/JailCensusData-Visualizer/assets/134180713/cc78a90b-bd75-4f0d-9ea4-da0c24ba97cb">
<img width="500" alt="Screenshot 2023-11-27 at 8 34 32 PM" src="https://github.com/jacobsinclair/JailCensusData-Visualizer/assets/134180713/7c1eff33-54c6-4a51-9766-12fa59b05ccf">





The large Dataset queried by an age range of 21-35 years old and male gender: 

<img width="500" alt="Screenshot 2023-11-27 at 8 34 58 PM" src="https://github.com/jacobsinclair/JailCensusData-Visualizer/assets/134180713/a1eeb83e-fa56-4782-9f08-2065d0c7e3b1">





The large Dataset queried by race:

<img width="500" alt="Screenshot 2023-11-27 at 8 33 43 PM" src="https://github.com/jacobsinclair/JailCensusData-Visualizer/assets/134180713/b62d6cc3-e245-49df-b599-e92f253ae120">





A smaller dataset of 6 of inmates: 

<img width="500" alt="Screenshot 2023-11-27 at 8 32 44 PM" src="https://github.com/jacobsinclair/JailCensusData-Visualizer/assets/134180713/88fea873-41f6-43e5-abdc-966cd89e7250">





Another smaller dataset of 10 inmates: 

<img width="488" alt="Screenshot 2023-11-27 at 8 51 03 PM" src="https://github.com/jacobsinclair/JailCensusData-Visualizer/assets/134180713/2a19f0f6-c8e5-4060-a04c-6540e1dda4b8">
