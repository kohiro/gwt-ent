@java -Xmx512M -cp "%~dp0\src;%~dp0\bin;%GWT_HOME%/gwt-user.jar;%GWT_HOME%/gwt-dev-windows.jar;%~dp0/lib/gwtent.jar;%~dp0/lib/gwtent-acegi.jar;%~dp0/lib/aspectjlib.jar;%~dp0/lib/aspectjrt.jar;%~dp0/lib/aspectjtools.jar;%~dp0/lib/aspectjweaver.jar" com.google.gwt.dev.GWTShell -out "%~dp0/www" %* com.gwtent.sample.Application/Application.html

echo %~dp0