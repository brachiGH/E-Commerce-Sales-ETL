# run

Downlaod  (maven)[https://maven.apache.org/download.cgi] and add it to PATH variables
And set JAVA_HOME environment variable is not set. (for java 17)

```console
mvn -N io.takari:maven:wrapper
./mvnw clean install
./mvnw spring-boot:run --quiet
```


### java environment

Here’s how to set the JAVA_HOME environment variable on different operating systems:
**For Windows**

1.Find your Java installation directory:

        Typically, it's something like C:\Program Files\Java\jdk-11.0.8 (the exact path will depend on the version and installation directory).

2.Set the JAVA_HOME variable:

        Right-click on This PC or My Computer and select Properties.

        Click on Advanced system settings.

        Click on Environment Variables.

        Under System variables, click New and add a new variable with the name JAVA_HOME and the path to your Java installation directory.

        Also, update the Path variable:

        Find the Path variable in the System variables section and click Edit.

        Add a new entry with %JAVA_HOME%\bin.

3.Restart the command prompt to apply the changes.

**For macOS**

1.Find your Java installation directory:

You can find it by running the command:

        /usr/libexec/java_home

2.Set the JAVA_HOME variable:

Open a terminal.

Edit your shell profile file (.bash_profile, .zshrc, or .profile), for example:

        nano ~/.zshrc

Add the following line:


        export JAVA_HOME=$(/usr/libexec/java_home)

Save the file and apply the changes by running:


    source ~/.zshrc

**For Linux**

1.Find your Java installation directory:
    
Typically, it's something like /usr/lib/jvm/java-11-openjdk-amd64.

2.Set the JAVA_HOME variable:

Open a terminal.

Edit your shell profile file (.bashrc, .bash_profile, or .profile), for example:


```
nano ~/.bashrc
```

Add the following line:

```
export JAVA_HOME=/path/to/jdk-17
export PATH=$JAVA_HOME/bin:$PATH
```

Save the file and apply the changes by running:

```
source ~/.bashrc
```

**Verify the Configuration**

After setting the JAVA_HOME variable, verify that it’s correctly set by running:



echo $JAVA_HOME

For Windows, run in Command Prompt or PowerShell:



echo %JAVA_HOME%