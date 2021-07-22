# Work Logger

This is a small application to log the daily work in text files.

## Requirements

- OpenJDK 16+
- In windows, WIX Tools 3+ in the `%PATH%`

## Download, Build and Run

``` 
$ git clone https://github.com/idaho06/worklogger.git
$ cd worklogger
$ gradlew build
$ gradlew run
```

## Create Windows Installer

```
$ gradlew windowsInstaller
```

Result goes to the file `wl\build\WorkLogger-1.0.0.msi`

## Things I Learned

- Introduction to Gradle
    - Project initialization
    - Initial configuration of the project
    - Dependencies management
    - Creation of new tasks
    - How it integrates with IntelliJ IDEA

- General Java refresh
- JPackage, creation of an installer for Windows for a non-modular application
- Running Swing Actions in code
- Using Swing Look and Feel FlatLaF
- Using Java Preferences library
- Setting initial focus on a Swing element at start
- Loading files from the `resources` directory correctly
- Loading and using a custom Font

## TODO

- Add option for changing name of the text file
- Create Gradle tasks for Linux and macOS? installers