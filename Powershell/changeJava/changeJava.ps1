if (-not ([Security.Principal.WindowsPrincipal][Security.Principal.WindowsIdentity]::GetCurrent()).IsInRole([Security.Principal.WindowsBuiltInRole]::Administrator)) {
    Start-Process powershell.exe "-NoProfile -ExecutionPolicy Bypass -File `"$($MyInvocation.MyCommand.Path)`"" -Verb RunAs
    Exit
}

$java8 = "C:\Program Files\Java\jdk1.8.0_202\bin"
$java16 = "C:\Program Files\Java\jdk-16.0.2\bin"
$java17 = "C:\Program Files\Java\jdk-17\bin"

Write-Host "Please choose a Java version to set the environment variable for:"
Write-Host "1: Java 8"
Write-Host "2: Java 16"
Write-Host "3: Java 17"
$choice = Read-Host "Enter the number of your choice"

switch ($choice) {
    1 {
        [Environment]::SetEnvironmentVariable("JAVA_HOME", $java8, "Machine")
        [Environment]::SetEnvironmentVariable("Path", "$($java8);$($env:Path)", "Machine")
        Write-Host "Java 8 environment variable has been set"
    }
    2 {
        [Environment]::SetEnvironmentVariable("JAVA_HOME", $java16, "Machine")
        [Environment]::SetEnvironmentVariable("Path", "$($java16);$($env:Path)", "Machine")
        Write-Host "Java 16 environment variable has been set"
    }
    3 {
        [Environment]::SetEnvironmentVariable("JAVA_HOME", $java17, "Machine")
        [Environment]::SetEnvironmentVariable("Path", "$($java17);$($env:Path)", "Machine")
        Write-Host "Java 17 environment variable has been set"
    }
    default {
        Write-Host "Invalid choice. Please try again."
    }
}
