To run tests you will need: <br />
-“IntelliJ IDEA Community Edition”. Download: (https://www.jetbrains.com/idea/download/?section=windows) Scroll down, under “Intellij IDE Ultimate” you will find a “Community Edition” which is free to download.  <br />
-Chrome browser <br />

How to run tests:  <br />
1.Before running tests make sure you are using the correct Chrome version. Check your browser version and compare to project version drivers. Current driver included in the project is the chrome 128 version.<br />
-If you have different version, download new drivers from https://googlechromelabs.github.io/chrome-for-testing/#stable.<br />
-Unpack and replace old driver(chromedriver.exe) with new one in this folder: Shop-QA\drivers\chromedriver-win64<br />
-Driver has to be named same as old driver(chromedriver.exe).<br />
2.Open project in “IntelliJ IDEA Community Edition”.<br />
3.Find file... Shop-QA\src\test\java\test-suite.xml<br />
4.Right click on it and press "Run"<br />

!Important do not move mouse in test browser while tests are running! <br />
!If JDK correto-11 is missing click Download Amazon Corretto 11.0.24!

