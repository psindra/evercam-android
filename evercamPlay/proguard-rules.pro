# Don't show warnings for the following libraries
-dontwarn io.evercam.**
-dontwarn okio.**
-dontwarn org.joda.time.**
-dontwarn org.simpleframework.xml.**
-dontwarn com.mixpanel.android.**
-dontwarn com.google.android.gms.**
-dontwarn org.w3c.dom.bootstrap.DOMImplementationRegistry

# Fix the MenuBuilder NoClassDefFoundError https://code.google.com/p/android/issues/detail?id=78377
-keep class !android.support.v7.view.menu.**,!android.support.design.internal.NavigationMenu,!android.support.design.internal.NavigationMenuPresenter,!android.support.design.internal.NavigationSubMenu,** {*;}

# Keep line numbers for Crashlytics bug reports
-keepattributes SourceFile,LineNumberTable,Deprecated

-ignorewarnings
-keep class * {
    public private *;
}
-keep class io.evercam.** { *; }

-keep class local.org.apache.http.** { *; }
-keep class local.org.apache.** { *; }
-keep interface local.org.apache.** { *; }
-keep enum local.org.apache.** { *; }
-libraryjars libs/local-commons-logging-1.1.3.jar
-libraryjars libs/local-httpasyncclient-4.0.jar
-libraryjars libs/local-httpasyncclient-4.0.jar
-libraryjars libs/local-httpclient-4.3.2.jar
-libraryjars libs/local-httpcore-4.3.1.jar
-libraryjars libs/local-httpcore-nio-4.3.1.jar
-libraryjars libs/local-httpmime-4.3.2.jar
